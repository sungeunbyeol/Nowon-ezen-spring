package com.ezen.project;
import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.model.UserDTO;
import com.ezen.project.model.WishListDTO;
import com.ezen.project.service.DisplayHotelMapper;
import com.ezen.project.service.UserMyPageMapper;

@Controller
public class UserMyPageController {
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	@Autowired
	DisplayController displayController;
	
	@Autowired
	UserMyPageMapper userMyPageMapper;
	
	@Autowired
	LoginOkBean loginokbean;
	
	@Autowired
	LoginCheck logincheck;
	
	@Resource(name = "uploadPath")
	private String upPath;
	
	//������������ �̵�
	@RequestMapping("/user_myPage")
	public String userMyPage() {
		return "user/user_myPage";
	}
	
	//������Ȳ ��������
	@RequestMapping("/user_bookList")
	public ModelAndView userBookList(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		HttpSession session = req.getSession();
		LoginOkBean loginOkBean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginOkBean.getU_num();
		
		ModelAndView mav = new ModelAndView();
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 3;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getBookingCount();
		
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<BookingDTO> list = userMyPageMapper.listBooking(startRow, endRow, u_num);
		
		mav.addObject("bookList", list);
		mav.addObject("number", number);
		mav.addObject("rowCount", rowCount);
		
		if (rowCount>0){
//			[1] [2] [3]
			int pageBlock = 2;
//			31(�Խñۼ�) / 5  =  �� : 6, ������ = 1
			int pageCount = rowCount / pageSize;
//			�������� 0�� �ƴϸ�, ������ �Խñ� �����ֱ� ���� ��++ ����
			if (rowCount%pageSize != 0){
				pageCount++;
			}
//									(1-1)	/	3		*	3		+ 1   = 1
//									(2-1)	/	3		*	3		+ 1   = 1
//									(4-1)	/	3		*	3		+ 1	  = 4
			int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
//								1	+	3	-	1	= 3..
//								4	+	3	-	1	= 6..
//								7	+	3	-	1	= 9
			int endPage = startPage + pageBlock - 1;
//					3	>	7	����
//					9	>	7	��		endPage = 7(������������ �ѹ��� 7�� ��)
			if (endPage > pageCount) endPage = pageCount;
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.setViewName("user/user_bookList");
		return mav;
	}
	
	//���侲�� ȭ������
	@RequestMapping("/user_reviewform")
	public String userReviewform(HttpServletRequest req, @RequestParam int h_num, int room_num) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		String review_nickname = loginokbean.getU_nickname();
		String room_type = userMyPageMapper.getRoomType(room_num);
		
		req.setAttribute("room_type", room_type);
		req.setAttribute("u_num", u_num);
		req.setAttribute("h_num", h_num);
		req.setAttribute("review_nickname", review_nickname);
		
		return "user/user_reviewform";
	}
	
	//���� ����� ��
	@RequestMapping("/user_reviewOk")
	public String user_reviewOk(HttpServletRequest req) throws Exception {
		 
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("review_image");
		
		String review_image = mf.getOriginalFilename();
		
		if(review_image != null && !review_image.trim().equals("")) {
			review_image = UUID.randomUUID().toString()+"_"+review_image;
		}
		
		int res = userMyPageMapper.insertReview(mr, review_image);
		
		if(res > 0) {
			File file = new File(upPath+"\\review", review_image);
			mf.transferTo(file);
			
			req.setAttribute("msg", "���� ��� ����!! ���� ����Ʈ �������� �̵��մϴ�.");
            req.setAttribute("url", "user_reviewList");
		}
		
		return "message";
	}
	
	//������ ���� �������� �̵�
	@RequestMapping("/user_reviewList")
	public ModelAndView user_reviewList(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		
		ModelAndView mav = new ModelAndView();
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 2;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getReviewCount();
		
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<ReviewDTO> listReview = userMyPageMapper.getReviewList(startRow, endRow, u_num);
		
		mav.addObject("listReview", listReview);
		mav.addObject("number", number);
		mav.addObject("rowCount", rowCount);
		
		if (rowCount>0){
//			[1] [2] [3]
			int pageBlock = 2;
//			31(�Խñۼ�) / 5  =  �� : 6, ������ = 1
			int pageCount = rowCount / pageSize;
//			�������� 0�� �ƴϸ�, ������ �Խñ� �����ֱ� ���� ��++ ����
			if (rowCount%pageSize != 0){
				pageCount++;
			}
//									(1-1)	/	3		*	3		+ 1   = 1
//									(2-1)	/	3		*	3		+ 1   = 1
//									(4-1)	/	3		*	3		+ 1	  = 4
			int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
//								1	+	3	-	1	= 3..
//								4	+	3	-	1	= 6..
//								7	+	3	-	1	= 9
			int endPage = startPage + pageBlock - 1;
//					3	>	7	����
//					9	>	7	��		endPage = 7(������������ �ѹ��� 7�� ��)
			if (endPage > pageCount) endPage = pageCount;
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.addObject("upPath", upPath);
		mav.setViewName("user/user_reviewList");
		return mav;
	}
	
	//���� ���� ��ư ������ ��
	@RequestMapping("/deleteReview")
	   public String reviewdelete(HttpServletRequest req, @RequestParam int review_num) {
	      
	      String msg = null;
	      String picture = userMyPageMapper.getPicture(review_num);
	      int res = userMyPageMapper.deleteReview(review_num);
	      
	      if(res > 0) {
	         File file = new File(upPath+"\\review", picture);
	         
	         if(file.exists()) {
	            file.delete();
	            msg = "�������(�̹�������)";
	         }
	         else {
	            msg = "�������(�̹�������)";
	         }
	      }
	      else {
	         msg = "������� ����";
	      }
	      
	      req.setAttribute("msg", msg);
	      req.setAttribute("url", "user_reviewList");
	      return "message";
	   }
	
	//����Ʈ�������� �̵��� ��
	@RequestMapping("/user_pointList")
	public ModelAndView userpointList(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		
		ModelAndView mav = new ModelAndView();
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getPointCount();
		
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<ReviewDTO> pointList = userMyPageMapper.getPointList(startRow, endRow, u_num);
		//List<UserPointDTO> resultList = userMyPageMapper.pointView(u_num);
		
		
		mav.addObject("pointList", pointList);
		mav.addObject("number", number);
		mav.addObject("rowCount", rowCount);
		
		if (rowCount>0){
//			[1] [2] [3]
			int pageBlock = 2;
//			31(�Խñۼ�) / 5  =  �� : 6, ������ = 1
			int pageCount = rowCount / pageSize;
//			�������� 0�� �ƴϸ�, ������ �Խñ� �����ֱ� ���� ��++ ����
			if (rowCount%pageSize != 0){
				pageCount++;
			}
//									(1-1)	/	3		*	3		+ 1   = 1
//									(2-1)	/	3		*	3		+ 1   = 1
//									(4-1)	/	3		*	3		+ 1	  = 4
			int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
//								1	+	3	-	1	= 3..
//								4	+	3	-	1	= 6..
//								7	+	3	-	1	= 9
			int endPage = startPage + pageBlock - 1;
//					3	>	7	����
//					9	>	7	��		endPage = 7(������������ �ѹ��� 7�� ��)
			if (endPage > pageCount) endPage = pageCount;
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.setViewName("user/user_pointList");
		
		return mav;
	}

	//���ø���Ʈ�� �̵�
	@RequestMapping("/user_wishlist")
	public ModelAndView userWishlist(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		List<WishListDTO> wdto = userMyPageMapper.wishListView(u_num);
		return new ModelAndView("user/user_wishlist", "wishList", wdto);
	}
	
	//���� �г��� ����
	@RequestMapping("/user_infoChange")
	public String user_infoChange(HttpServletRequest req, @RequestParam String nickname) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		
		Map<String, String> params = new Hashtable<String, String>();
		params.put("u_num", String.valueOf(u_num));
		params.put("nickname", nickname);
		
		int res = userMyPageMapper.changeNickName(params);
		String msg = null , url = null;
		if(res > 0) {
			loginokbean.setU_nickname(nickname);
			session.setAttribute("loginOkBean", loginokbean);
			msg="�г����� �����Ǿ����ϴ�";
			url="user_info";
		} else {
			msg="�г��� ������ �����߽��ϴ�";
			url="user_info";
		}

		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "message";
	}

//	hotelSeachOk ���������� ���ø���Ʈ üũ/����
	@RequestMapping(value="/wishRelease")
	public String wishRelease(HttpServletRequest req, @RequestParam Map<String, String> params) {
		displayHotelMapper.wishRelease(params);
		displayController.searchResult(req, params);
		return "display/display_hotelSearchOk";
	}
	@RequestMapping(value="/wishCheck")
	public String wishCheck(HttpServletRequest req,@RequestParam Map<String, String> params) {
		displayHotelMapper.wishCheck(params);
		displayController.searchResult(req, params);
		return "display/display_hotelSearchOk";
	}
	
//	hotelContent ���������� ���ø���Ʈ üũ/����
	@RequestMapping(value="/wishRelease2")
	public String wishRelease2(HttpServletRequest req, @RequestParam Map<String, String> params) {
		displayHotelMapper.wishRelease(params);
		displayController.searchResult(req, params);
		return "display/display_hotelContent";
	}
	@RequestMapping(value="/wishCheck2")
	public String wishCheck2(HttpServletRequest req,@RequestParam Map<String, String> params) {
		displayHotelMapper.wishCheck(params);
		displayController.searchResult(req, params);
		return "display/display_hotelContent";
	}
	
//	wishList������ ���� ���ø���Ʈ ����
	@RequestMapping(value="/wishRelease3")
	public String wishRelease3(HttpServletRequest req, @RequestParam int w_num) {
		displayHotelMapper.wishRelease2(w_num);
		return "user/user_wishlist";
	}
	
//	��ȸ���� ȸ������ ������ ������ �Ҷ� ����
	@RequestMapping("/user_needLogin")
	public ModelAndView LoginNeed(HttpServletRequest req) {
		req.setAttribute("msg", "�α����� �ʿ��� ���� �Դϴ�");
		req.setAttribute("url", "user_login");
		return new ModelAndView("message");
	}
	
//	bookWriteform������, roomContents���������� h_num�� room_num���� ������ ������
	@RequestMapping("/user_bookWriteform")
	public String userBookWriteform(HttpServletRequest req, @RequestParam int h_num, int room_num) {
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoomDetail(room_num, h_num);
		
//		ȸ��, ��ȸ�� ����
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		try {
			int u_num = loginInfo.getU_num();
			UserDTO udto = displayHotelMapper.userInfo(u_num);
			req.setAttribute("udto", udto);
		}catch(Exception e) {
			req.setAttribute("msg", "�α����� �ʿ��� ���� �Դϴ�");
			req.setAttribute("url", "display_roomContent?h_num="+h_num+"&room_num="+room_num);
			return "message";
		}
		
//		indate, outdate session���� ������ ���� ����
		String indate = (String)session.getAttribute("indate");
		String outdate = (String)session.getAttribute("outdate");
		
		req.setAttribute("indate", indate);
		req.setAttribute("outdate", outdate);	
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		return "user/user_bookWriteform";
	}
	
//	����Ȯ�� Ȯ��, bookWriteform���� ���� ���� db�� �����Ŵ(�߸��ǰų� �������� ��ҵ�)
	@RequestMapping(value="/user_bookConfirm")
	public String bookConfirm(HttpServletRequest req, @RequestParam Map<String, String> params) {
		//������ ȸ�� ����
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		
//		���� �����ִ��� �ٽ��ѹ� Ȯ��, 0�� ���ϸ� �������
		int roomCount = displayHotelMapper.roomCountCheck(params);
		if(roomCount <= 0) {
			req.setAttribute("msg", "���� ����");
			req.setAttribute("url", "main");
		}  else {
//			DB�� ����õ�
			int res = displayHotelMapper.insertBook(params);
			if(res>0) {
//				ȸ���� �Է��� ����Ʈ�� 0���� ũ��, ����Ʈ ����
				if(Integer.parseInt(params.get("inputPoint")) != 0) {
					displayHotelMapper.usedPoint(params);
				}
//				�������� ����Ʈ ������Ʈ����
				displayHotelMapper.savePoint(params);
				req.setAttribute("msg", "���� ����");
				req.setAttribute("url", "user_bookList");
			} else {
				req.setAttribute("msg", "���డ���� ������ �����ϴ�");
				req.setAttribute("url", "main");
			}
		}
		return "message";
	}
	
//	����󼼺���
	@RequestMapping("/user_bookDetail")
	public String userBookDetail(HttpServletRequest req, @RequestParam int h_num, int room_num, int book_num) {
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		BookingDTO bdto = displayHotelMapper.bookInfo2(book_num);
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		req.setAttribute("hdto", hdto);
		req.setAttribute("bdto", bdto);
		return "user/user_bookDetail";
	}
	
//	���� ����� 
	@RequestMapping("/user_bookCancel")
	public String userBookCancel(HttpServletRequest req, int book_num) {
		//RequestParam���� h_num ���߿� �߰�
		BookingDTO bdto = displayHotelMapper.bookInfo2(book_num);
		HotelDTO hdto = displayHotelMapper.hotelDetail(bdto.getH_num());
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		UserDTO udto = displayHotelMapper.userInfo(u_num);
		/*
		 * Map<String, String> params = new Hashtable<String, String>();
		 * params.put("u_num", String.valueOf(u_num)); params.put("h_num",
		 * String.valueOf(h_num));
		 */
		int room_price = displayHotelMapper.getRoomPrice(book_num);
		
		req.setAttribute("udto", udto);
		req.setAttribute("bdto", bdto);
		req.setAttribute("h_image1", hdto.getH_image1());
		req.setAttribute("room_price", room_price);
		return "user/user_bookCancel";
	}

//	������� Ȯ��
	@RequestMapping("/user_bookCancel_ok")
	public String userBookCancelOk(HttpServletRequest req, @RequestParam(required = false) int book_num, int room_price) {
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		int res = displayHotelMapper.deleteBook(book_num, u_num);
		if(res > 0) {
			req.setAttribute("msg", "������ ��ҵǾ����ϴ�");
			req.setAttribute("url", "user_bookList");
		} else {
			req.setAttribute("msg", "��ҿ� �����߽��ϴ�. /n�ٽ� �õ����ּ���.");
			req.setAttribute("url", "user_bookCancel?room_price="+room_price);
		}
		return "message";
	}
	
}