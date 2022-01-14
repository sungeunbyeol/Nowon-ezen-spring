package com.ezen.project;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.service.HotelMapper;

@Controller
public class HotelController {
	
	@Autowired
	private HotelMapper hotelMapper;
	
	@Resource(name = "uploadPath")
	private String upPath;
	
	@RequestMapping("/hotel_main")
	public String hotelMain(HttpServletRequest req) {
		HttpSession session = req.getSession();
		CompanyLoginOkBean companyLoginOkBean = (CompanyLoginOkBean)session.getAttribute("companyLoginOkBean");
		session.setAttribute("c_num", companyLoginOkBean.getC_num());
		
		return "hotel/hotel_main";
	}
	
	@RequestMapping("/hotel_list")
	public String hotelList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int c_num = (Integer)session.getAttribute("c_num");
		
		List<HotelDTO> hotelList = hotelMapper.listHotel(c_num);
		req.setAttribute("hotelList", hotelList);
		
		return "hotel/hotel_list";
	}
	
	@RequestMapping("/hotel_input")
	public String hotelInput() {
		return "hotel/hotel_input";
	}
	
	@RequestMapping("/hotel_input_ok")
	public String hotelInputOk(HttpServletRequest req, HotelDTO hdto, BindingResult result) 
			throws IllegalStateException, IOException {
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		List<MultipartFile> mfList = mr.getFiles("h_images");
		
		// DTO�� �����̸��� �Ѳ����� �����ϱ� ���� �迭
		String[] images = new String[mfList.size()];
		
		// ���ε������� ���� ���� �ִ� ��쿡 ���� ���� �̸� ����
		for(int i=0; i<mfList.size(); ++i) {
			images[i] = mfList.get(i).getOriginalFilename();
			if(images[i] == null || images[i].trim().equals("")) {
				images[i] = "default.jpg";
			}else {
				images[i] = UUID.randomUUID().toString()+"_"+images[i];
			}
		}
		
		// ������ ���� �̸��� �Ѳ����� DTO�� ����
		hdto.setH_images(images);
		
		// ���� üũ�� �ȵǾ������� "n" �ֱ�
		if(hdto.getH_park() == null) hdto.setH_park("n");
		if(hdto.getH_meal() == null) hdto.setH_meal("n");
		if(hdto.getH_wifi() == null) hdto.setH_wifi("n");
		if(hdto.getH_disabled() == null) hdto.setH_disabled("n");
		if(hdto.getH_ott() == null) hdto.setH_ott("n");
		if(hdto.getH_pool() == null) hdto.setH_pool("n");
		if(hdto.getH_kids() == null) hdto.setH_kids("n");
		if(hdto.getH_bus() == null) hdto.setH_bus("n");
		if(hdto.getH_smoke() == null) hdto.setH_smoke("n");
		if(hdto.getH_front24() == null) hdto.setH_front24("n");
		
		// �ٰ����� @�� ġȯ
		hdto.setH_info(hdto.getH_info().replace("\r\n", "@"));
		hdto.setH_notice(hdto.getH_notice().replace("\r\n", "@"));
		
		// DB�� ���
		int res = hotelMapper.inputHotel(hdto);
		
		// DB ����� �����ϸ�
		if(res>0) {
			// ���� ���ϵ��� ���ε�
			for(int i=0; i<mfList.size(); ++i) {
				// ���ε��� ���ϸ��� default.jpg�� ���ε� ����
				if(!images[i].equals("default.jpg")) {
					File file = new File(upPath+"\\hotel", images[i]);
					mfList.get(i).transferTo(file);
				}
			}
			
            req.setAttribute("msg", "ȣ�ڵ�� ����!! ���ǵ�� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_group_input");
         }else {
            req.setAttribute("msg", "ȣ�ڵ�� ����!! �ٽ� �Է��� �ּ���.");
            req.setAttribute("url", "hotel_list");
         }
		
		return "message";
	}
	
	@RequestMapping("/hotel_edit")
	public String hotelEdit(HttpServletRequest req, int h_num) {
		HotelDTO hdto = hotelMapper.getHotel(h_num);
		
		// @�� �ٰ������� ġȯ
		hdto.setH_info(hdto.getH_info().replace("@", "\r\n"));
		hdto.setH_notice(hdto.getH_notice().replace("@", "\r\n"));
		
		req.setAttribute("hdto", hdto);
		
		return "hotel/hotel_edit";
	}
	
	@RequestMapping("/hotel_edit_ok")
	public String hotelEditOk(HttpServletRequest req, HotelDTO hdto, BindingResult result, 
			String[] pre_images) throws IllegalStateException, IOException {
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		List<MultipartFile> mfList = mr.getFiles("h_images");
		
		// DTO�� �����̸��� �Ѳ����� �����ϱ� ���� �迭 ����
		String[] images = new String[mfList.size()];
		
		// ���� �̸� ����
		for(int i=0; i<mfList.size(); ++i) {
			images[i] = mfList.get(i).getOriginalFilename();
			
			if(images[i] == null || images[i].trim().equals("")) {
				// ���� ���ε尡 ������ ���� ������ �̸��� �״�� ����
				images[i] = pre_images[i];
			}else {
				// ���� ���ε尡 ������ �����̸� ���� ����
				images[i] = UUID.randomUUID().toString()+"_"+images[i];
			}
		}
		
		// h_image1,2,3,4,5�� �̸��� �Ѳ����� DTO�� �����ϴ� �޼ҵ�
		hdto.setH_images(images);
		
		// ���� üũ�� �ȵǾ������� "n" �ֱ�
		if(hdto.getH_park() == null) hdto.setH_park("n");
		if(hdto.getH_meal() == null) hdto.setH_meal("n");
		if(hdto.getH_wifi() == null) hdto.setH_wifi("n");
		if(hdto.getH_disabled() == null) hdto.setH_disabled("n");
		if(hdto.getH_ott() == null) hdto.setH_ott("n");
		if(hdto.getH_pool() == null) hdto.setH_pool("n");
		if(hdto.getH_kids() == null) hdto.setH_kids("n");
		if(hdto.getH_bus() == null) hdto.setH_bus("n");
		if(hdto.getH_smoke() == null) hdto.setH_smoke("n");
		if(hdto.getH_front24() == null) hdto.setH_front24("n");
		
		// �ٰ����� @�� ġȯ
		hdto.setH_info(hdto.getH_info().replace("\r\n", "@"));
		hdto.setH_notice(hdto.getH_notice().replace("\r\n", "@"));
		
		// DB ���
		int res = hotelMapper.editHotel(hdto);
		
		if(res>0) {
			// ���� ������ ���ε�
			for(int i=0; i<mfList.size(); ++i) {
				// �� ���ϸ��̶� ���� ���ϸ��� �ٸ� ���
				if(!images[i].equals(pre_images[i])) {
					// �� ���� ���ε�
					File newFile = new File(upPath+"\\hotel", images[i]);
					mfList.get(i).transferTo(newFile);
					
					// ���� ���� ���� (default.jpg ����)
					File preFile = new File(upPath+"\\hotel", pre_images[i]);
					if(preFile.exists() && !preFile.getName().equals("default.jpg")) {
						preFile.delete();
					}
				}
			}
			
			req.setAttribute("msg", "ȣ�ڼ��� ����!! ȣ�ڸ�� �������� �̵��մϴ�.");
			req.setAttribute("url", "hotel_list");
        }else {
        	req.setAttribute("msg", "ȣ�ڼ��� ����!! ȣ�ڸ�� �������� �̵��մϴ�.");
        	req.setAttribute("url", "hotel_list");
        }
		
		return "message";
	}
	
	@RequestMapping("/hotel_delete_ok")
	public String hotelDeleteOk(HttpServletRequest req, int h_num, String[] h_images) {
		try {
			// ȣ���� ���� ���� ������ ����.
			int roomCount = hotelMapper.countRoomOnHotel(h_num);
			
			// ȣ���� ������ ������ �ִ� ���
			if(roomCount != 0) {
				// ���� DB�� �����ϱ� ���� �̹������� ������ �����ͼ� �����صд�.
				List<RoomDTO> rlist = hotelMapper.getAllRoomImagesOnHotel(h_num);
				
				// ������ ȣ�ڿ� �Ҽӵ� ������ DB���� ���� �����Ѵ�.
				hotelMapper.deleteRoomOnHotel(h_num);
				
				// DB���� ������ ���ǵ��� �̹��������� ���� �����Ѵ�. (default.jpg�� ���ܵ�)
				for(RoomDTO rdto : rlist) {
					File file1 = new File(upPath+"\\room", rdto.getRoom_image1());
					File file2 = new File(upPath+"\\room", rdto.getRoom_image2());
					File file3 = new File(upPath+"\\room", rdto.getRoom_image3());
					
					if(file1.exists() && !file1.getName().equals("default.jpg")) {
						file1.delete();
					}
					if(file2.exists() && !file2.getName().equals("default.jpg")) {
						file2.delete();
					}
					if(file3.exists() && !file3.getName().equals("default.jpg")) {
						file3.delete();
					}
				}
			}
			
			// ȣ���� �����Ѵ�.
			int res = hotelMapper.deleteHotel(h_num);
			
			if(res>0) {
				// ȣ�� �̹����� �����Ѵ�. (default.jpg�� ���� ���ܵ�)
				for(int i=0; i<h_images.length; ++i) {
					File file = new File(upPath+"\\hotel", h_images[i]);
					if (file.exists() && !file.getName().equals("default.jpg")) {
						file.delete();
					}
				}
				
	            req.setAttribute("msg", "ȣ�ڻ��� ����!! ȣ�ڸ�� �������� �̵��մϴ�.");
	            req.setAttribute("url", "hotel_list");
	         }else {
	            req.setAttribute("msg", "ȣ�ڻ��� ����!! ȣ�ڸ�� �������� �̵��մϴ�.");
	            req.setAttribute("url", "hotel_list");
	         }
		}catch(DataIntegrityViolationException e) {
			req.setAttribute("msg", "���� ������ �����ϹǷ� ������ �Ұ����մϴ�.");
			req.setAttribute("url", "hotel_list");
			e.printStackTrace();
		}
		
		return "message";
	}
	
	@RequestMapping("/room_group_list")
	public String roomGroupList(HttpServletRequest req, int h_num) {
		// �ش��ϴ� ȣ���� ���� �׷��� list�� ��ƿ´�.
		List<RoomDTO> roomGroupList = hotelMapper.listRoomGroup(h_num);
		
		// �� ���� �׷쿡 �Ҽӵ� ���� ���� ���ؼ� DTO�� ��´�.
		for(RoomDTO rdto : roomGroupList) {
			rdto.setMax_count(hotelMapper.countRoomOnGroup(rdto.getRoom_code()));
		}
		
		req.setAttribute("roomGroupList", roomGroupList);
		
		return "hotel/room_group_list";
	}
	
	@RequestMapping("/room_list")
	public String roomList(HttpServletRequest req, String room_code) {
		// �ش��ϴ� ���� �׷��� ������ list�� ��ƿ´�.
		List<RoomDTO> roomList = hotelMapper.listRoom(room_code);
		
		req.setAttribute("roomList", roomList);
		
		return "hotel/room_list";
	}
	
	@RequestMapping("/room_group_input")
	public String roomGroupInput(HttpServletRequest req, @RequestParam(required=false) String num) {
		int h_num = 0;
		
		if(num==null) {
			// ȣ���� ó�� ����Ҷ� �ٷ� ������ ����ϴ� ���
			h_num = hotelMapper.getMaxHnum();
		}else {
			// ȣ�� ��Ͽ��� ������ ����ϴ� ���
			h_num = Integer.parseInt(num);
		}
		
		req.setAttribute("h_num", h_num);
		
		return "hotel/room_input";
	}
	
	@RequestMapping("/room_group_input_ok")
	public String roomGroupInputOk(HttpServletRequest req, RoomDTO rdto, BindingResult result, 
			int max_count) throws IllegalStateException, IOException {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		
		// ���ε��� �̹������� ��ü�� ����Ʈ�� ��´�.
		List<MultipartFile> mfList = mr.getFiles("room_images");
		
		// ���ϸ��� ���� �迭�� ���� ������ŭ �����Ѵ�.
		String[] images = new String[mfList.size()];

		// DTO�� ���� ���� �̸��� �����Ѵ�.
		for(int i=0; i<mfList.size(); ++i) {
			// �켱 ���ε��� ���� �̸��� �迭�� ���
			images[i] = mfList.get(i).getOriginalFilename();
			
			if(images[i] == null || images[i].trim().equals("")) {
				// �迭�� ���� �̸��� ���� ��� (���ε带 ���ϴ� ���) default.jpg�� ����
				images[i] = "default.jpg";
			}else {
				// �迭�� ���� �̸��� �ִ� ��� (���ε带 �ϴ� ���) �ߺ� ���� ó��
				images[i] = UUID.randomUUID().toString()+"_"+images[i];
			}
		}
		
		// DTO�� room_image1,2,3�� �̸��� �Ѳ����� ����
		rdto.setRoom_images(images);

		// ���� �׷�� ���� (h_num + �ĺ���ȣ)
		String room_code = null;
		
		if(hotelMapper.getMaxRoomCode(rdto.getH_num())==null) {
			// DB�� �ش� ȣ�ڿ� ���� room_code�� ���� ��� "ȣ�ڰ�����ȣ_1"�� ����
			room_code = rdto.getH_num()+"_"+"1";
		}else {
			// DB�� �ش� ȣ�ڿ� ���� room_code�� ���� ��� �����ͼ� _�� �������� String�� �ڸ�
			String[] arr = hotelMapper.getMaxRoomCode(rdto.getH_num()).split("_");
			
			// 
			room_code = rdto.getH_num()+"_"+(Integer.parseInt(arr[1])+1);
		}
		
		// ���� �׷� ������ DTO�� ��´�
		rdto.setRoom_code(room_code);
		
		// ù ���� ���
		int res = hotelMapper.inputRoom(rdto);
		
		// ����� ����� �� ���
		if(res>0) {
			// ���� ������ ���ε�
			for(int i=0; i<mfList.size(); ++i) {
				if(!images[i].equals("default.jpg")) {
					File file = new File(upPath+"\\room", images[i]);
					mfList.get(i).transferTo(file);
				}
			}
			
			// ������ ���Ǽ���ŭ ���� ���
			for(int i=0; i<max_count-1; ++i) {
				res = hotelMapper.inputRoom(rdto);
			}
			
			req.setAttribute("msg", "���� �׷� ��� ����!! ���� �׷� ��� �������� �̵��մϴ�.");
			req.setAttribute("url", "room_group_list?h_num="+rdto.getH_num());
		}else {
			req.setAttribute("msg", "���� �׷� ��� ����!! �ٽ� �Է��� �ּ���.");
			req.setAttribute("url", "room_group_input?h_num="+rdto.getH_num());
		}
		
		return "message";
	}
	
	@RequestMapping("/room_input_ok")
	public String roomInputOk(HttpServletRequest req, String room_code) {
		// ���� ����� ���� ������ ������ ���� DTO�� room_code�� ���� ����
		RoomDTO rdto = hotelMapper.getRoomByRoomCode(room_code);
		
		// ���� ���
		int res = hotelMapper.inputRoom(rdto);
		
		if(res>0) {
			req.setAttribute("msg", "�����߰� ����!! ���� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_list?room_code="+room_code);
		}else {
            req.setAttribute("msg", "�����߰� ����!! �ٽ� �Է��� �ּ���.");
            req.setAttribute("url", "room_list?room_code="+room_code);
        }
		
		return "message";
	}
	
	@RequestMapping("/room_group_edit")
	public String roomGroupEdit(HttpServletRequest req, int room_num) {
		// ���� ������ ���� ������ ������ ���� DTO�� room_num�� ���� ����
		RoomDTO rdto = hotelMapper.getRoomByRoomNum(room_num);
		
		req.setAttribute("rdto", rdto);
		
		return "hotel/room_edit";
	}
	
	@RequestMapping("/room_group_edit_ok")
	public String roomGroupEditOk(HttpServletRequest req, RoomDTO rdto, BindingResult result, 
			String[] pre_images) throws IllegalStateException, IOException {
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		List<MultipartFile> mfList = mr.getFiles("room_images");

		// DTO�� �����̸��� �Ѳ����� �����ϱ� ���� �迭
		String[] images = new String[mfList.size()];

		// ���� �̸��� ����
		for(int i=0; i<mfList.size(); ++i) {
			images[i] = mfList.get(i).getOriginalFilename();
			if(images[i] == null || images[i].trim().equals("")) {
				// ���� ���ε尡 ������ ���� ������ �̸��� �״�� ����
				images[i] = pre_images[i];
			}else {
				// ���� ���ε尡 ������ ���� �̸� ���� ����
				images[i] = UUID.randomUUID().toString()+"_"+images[i];
			}
		}
		
		// room_image1,2,3�� �̸��� �Ѳ����� DTO�� �����ϴ� �޼ҵ�
		rdto.setRoom_images(images);
		
		// DB ����
		int res = hotelMapper.editRoomGroup(rdto);
		
		if(res>0) {
			// ���� ������ ���ε�
			for(int i=0; i<mfList.size(); ++i) {
				// �� ���ϸ��̶� ���� ���ϸ��� �ٸ� ���
				if(!images[i].equals(pre_images[i])) {
					// �� ���� ���ε�
					File newFile = new File(upPath+"\\room", images[i]);
					mfList.get(i).transferTo(newFile);
					
					// ���� ���� ���� (default.jpg ����)
					File preFile = new File(upPath+"\\room", pre_images[i]);
					if(preFile.exists() && !preFile.getName().equals("default.jpg")) {
						preFile.delete();
					}
				}
			}
			
            req.setAttribute("msg", "���� �׷� ���� ����!! ���� �׷� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_group_list?h_num="+rdto.getH_num());
        }else {
            req.setAttribute("msg", "���� �׷� ���� ����!! ���� �׷� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_group_list?h_num="+rdto.getH_num());
        }
		
		return "message";
	}
	
	@RequestMapping("/room_group_delete_ok")
	public String roomGroupDeleteOk(HttpServletRequest req, String room_code, int h_num, String[] room_images) {
		try {
			// DB ����
			int res = hotelMapper.deleteRoomGroup(room_code);
			
			// ������ �����ϸ�
			if(res>0) {
				// ���� ���� ����
				for(int i=0; i<room_images.length; ++i) {
					File file = new File(upPath+"\\room", room_images[i]);
					if (file.exists() && !file.getName().equals("default.jpg")) {
						file.delete();
					}
				}
				
	            req.setAttribute("msg", "���� �׷� ���� ����!! ���� �׷� ��� �������� �̵��մϴ�.");
	            req.setAttribute("url", "room_group_list?h_num="+h_num);
	        }else {
	            req.setAttribute("msg", "���� �׷� ���� ����!! �ٽ� �Է��� �ּ���.");
	            req.setAttribute("url", "room_group_list?h_num="+h_num);
	        }
			
		}catch(DataIntegrityViolationException e) {
			// foreign key�� ���� ���� ó��
			req.setAttribute("msg", "���� ������ �����ϹǷ� ������ �Ұ����մϴ�.");
			req.setAttribute("url", "room_group_list?h_num="+h_num);
			e.printStackTrace();
		}
		
		return "message";
	}
	
	@RequestMapping("/room_delete_ok")
	public String roomDeleteOk(HttpServletRequest req, int room_num, String room_code) {
		// DB ����
		int res = hotelMapper.deleteRoom(room_num);
		
		if(res>0) {
			req.setAttribute("msg", "���ǻ��� ����!! ���� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_list?room_code="+room_code);
		}else {
            req.setAttribute("msg", "���ǻ��� ����!! �ٽ� �Է��� �ּ���.");
            req.setAttribute("url", "room_list?room_code="+room_code);
        }
		
		return "message";
	}
	
	@RequestMapping("/room_disable")
	public String roomDisable(HttpServletRequest req, int room_num, String room_code) {
		// room_enable�� 'n'����
		int res = hotelMapper.disableRoom(room_num);
		
		if(res>0) {
            req.setAttribute("msg", "���� ��Ȱ��ȭ ����!! ���� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_list?room_code="+room_code);
        }else {
            req.setAttribute("msg", "���� ��Ȱ��ȭ ����!! �ٽ� �õ��� �ּ���.");
            req.setAttribute("url", "room_list?room_code="+room_code);
        }
		
		return "message";
	}
	
	@RequestMapping("/room_enable")
	public String roomEnable(HttpServletRequest req, int room_num, String room_code) {
		// room_enable�� 'y'��
		int res = hotelMapper.enableRoom(room_num);
		
		if(res>0) {
            req.setAttribute("msg", "���� Ȱ��ȭ ����!! ���� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "room_list?room_code="+room_code);
        }else {
            req.setAttribute("msg", "���� Ȱ��ȭ ����!! �ٽ� �õ��� �ּ���.");
            req.setAttribute("url", "room_list?room_code="+room_code);
        }
		
		return "message";
	}
	
	@RequestMapping("/hotel_booklist")
	public String hotelBooklist(HttpServletRequest req, int h_num) {
		List<BookingDTO> bookList = hotelMapper.listBooking(h_num);
		
		for(BookingDTO bdto : bookList) {
			RoomDTO rdto = hotelMapper.getRoomDTOByRoomnum(bdto.getRoom_num());
			
			bdto.setH_name(hotelMapper.getHnameByHnum(h_num));
			bdto.setRoom_image1(rdto.getRoom_image1());
			bdto.setRoom_name(rdto.getRoom_name());
			bdto.setRoom_capacity(rdto.getRoom_capacity());
		}
		
		req.setAttribute("bookList", bookList);
		
		return "hotel/hotel_booklist";
	}
	
	@RequestMapping("/confirm_booking")
	public String confirmBooking(HttpServletRequest req, int book_num, int room_num, int h_num) {
		int res = hotelMapper.confirmBooking(book_num);
		
		if(res>0) {
            req.setAttribute("msg", "���� ���� ����!! ���� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "hotel_booklist?h_num="+h_num);
        }else {
            req.setAttribute("msg", "���� ���� ����!! �ٽ� �õ��� �ּ���.");
            req.setAttribute("url", "hotel_booklist?h_num="+h_num);
        }
		
		return "message";
	}
	
	@RequestMapping("/deny_booking")
	public String denyBooking(HttpServletRequest req, int book_num, int room_num, int h_num) {
		int res = hotelMapper.denyBooking(book_num);
		
		if(res>0) {
            req.setAttribute("msg", "���� �ź� ����!! ���� ��� �������� �̵��մϴ�.");
            req.setAttribute("url", "hotel_booklist?h_num="+h_num);
        }else {
            req.setAttribute("msg", "���� �ź� ����!! �ٽ� �õ��� �ּ���.");
            req.setAttribute("url", "hotel_booklist?h_num="+h_num);
        }
		
		return "message";
	}
	
}
