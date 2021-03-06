package com.ezen.project;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.ActivityDTO;
import com.ezen.project.model.BookingActDTO;
import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ProgramDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.model.UserDTO;
import com.ezen.project.service.ActivityMapper;
import com.ezen.project.service.DisplayActMapper;
import com.ezen.project.service.DisplayHotelMapper;
import com.ezen.project.service.HotelMapper;
import com.ezen.project.service.UserMapper;
import com.ezen.project.service.UserMyPageMapper;

@Controller
public class BookingController {
	
	@Autowired
	private DisplayHotelMapper displayHotelMapper;
	
	@Autowired
	private HotelMapper hotelMapper;
	
	@Autowired
	private DisplayActMapper displayActMapper;
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMyPageMapper userMyPageMapper;
	
	//예약현황 페이지로
	@RequestMapping("/user_bookList")
	public ModelAndView userBookList(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		HttpSession session = req.getSession();
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
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
		
		rowCount = userMyPageMapper.getBookingCount(u_num);
		
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<BookingDTO> list = userMyPageMapper.listBookingByUser(startRow, endRow, u_num);
		
		mav.addObject("bookList", list);
		mav.addObject("number", number);
		mav.addObject("rowCount", rowCount);
		
		if (rowCount>0){
//				[1] [2] [3]
			int pageBlock = 2;
//				31(게시글수) / 5  =  몫 : 6, 나머지 = 1
			int pageCount = rowCount / pageSize;
//				나머지가 0이 아니면, 나머지 게시글 보여주기 위해 몫++ 해줌
			if (rowCount%pageSize != 0){
				pageCount++;
			}
//										(1-1)	/	3		*	3		+ 1   = 1
//										(2-1)	/	3		*	3		+ 1   = 1
//										(4-1)	/	3		*	3		+ 1	  = 4
			int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
//									1	+	3	-	1	= 3..
//									4	+	3	-	1	= 6..
//									7	+	3	-	1	= 9
			int endPage = startPage + pageBlock - 1;
//						3	>	7	거짓
//						9	>	7	참		endPage = 7(마지막페이지 넘버는 7이 됨)
			if (endPage > pageCount) endPage = pageCount;
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.setViewName("user/user_bookList");
		return mav;
	}
	
//	bookWriteform페이지, roomContents페이지에서 h_num과 room_num으로 상세정보 보내줌
	@RequestMapping("/user_bookWriteform")
	public String userBookWriteform(HttpServletRequest req, @RequestParam int h_num, int room_num) {
		HotelDTO hdto = hotelMapper.getHotel(h_num);
		RoomDTO rdto = hotelMapper.getRoomByRoomNum(room_num);
//		회원, 비회원 구분
		HttpSession session = req.getSession();
		LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		try {
			int u_num = loginInfo.getU_num();
			UserDTO udto = userMapper.getUserByUnum(u_num);
			req.setAttribute("udto", udto);
		}catch(Exception e) {
			req.setAttribute("msg", "로그인이 필요한 서비스 입니다");
			req.setAttribute("url", "user_login");
			return "message";
		}
		
//		indate, outdate session에서 꺼내서 따로 보냄
		String indate = (String)session.getAttribute("indate");
		String outdate = (String)session.getAttribute("outdate");
		
		req.setAttribute("indate", indate);
		req.setAttribute("outdate", outdate);	
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", rdto);
		return "user/user_bookWriteform";
	}
	
//	예약확정 확인, bookWriteform에서 적은 정보 db에 저장시킴(잘못되거나 오류나면 취소됨)
	@RequestMapping(value="/user_bookConfirm")
	public String bookConfirm(HttpServletRequest req, @RequestParam Map<String, String> params) {
		//예약한 회원 정보
		HttpSession session = req.getSession();
		LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
//		DB에 저장시도
		int res = displayHotelMapper.insertBook(params);
		
		if(res>0) {
//			회원이 입력한 포인트가 0보다 크면, 포인트 수정
			if(Integer.parseInt(params.get("inputPoint")) != 0) {
				displayHotelMapper.usedPoint(params);
			}
			
//			적립받을 포인트 업데이트해줌
			displayHotelMapper.savePoint(params);
			req.setAttribute("msg", "예약 성공");
			req.setAttribute("url", "user_bookList");
		} else {
			req.setAttribute("msg", "예약가능한 객실이 없습니다");
			req.setAttribute("url", "main");
		}
		
		return "message";
	}
	
//	예약상세보기
	@RequestMapping("/user_bookDetail")
	public String userBookDetail(HttpServletRequest req, @RequestParam int h_num, int room_num, int book_num) {
		HttpSession session = req.getSession();
		LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
		BookingDTO bdto = displayHotelMapper.bookInfo(book_num);
		HotelDTO hdto = hotelMapper.getHotel(h_num);
		req.setAttribute("hdto", hdto);
		req.setAttribute("bdto", bdto);
		return "user/user_bookDetail";
	}
	
//	예약 취소전 
	@RequestMapping("/user_bookCancel")
	public String userBookCancel(HttpServletRequest req, int book_num) {
		//RequestParam값에 h_num 나중에 추가
		BookingDTO bdto = displayHotelMapper.bookInfo(book_num);
		HotelDTO hdto = hotelMapper.getHotel(bdto.getH_num());
		HttpSession session = req.getSession();
		LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		UserDTO udto = userMapper.getUserByUnum(u_num);
		
		int room_price = displayHotelMapper.getRoomPrice(book_num);
		
		req.setAttribute("udto", udto);
		req.setAttribute("bdto", bdto);
		req.setAttribute("h_image1", hdto.getH_image1());
		req.setAttribute("room_price", room_price);
		return "user/user_bookCancel";
	}

//	예약취소 확인
	@RequestMapping("/user_bookCancel_ok")
	public String userBookCancelOk(HttpServletRequest req, @RequestParam(required = false) int book_num, int room_price) {
		HttpSession session = req.getSession();
		LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		
		int res = displayHotelMapper.deleteBook(book_num, u_num);
		if(res > 0) {
			req.setAttribute("msg", "예약이 취소되었습니다. 환불처리에 2~3일 정도 소요 될 수 있습니다");
			req.setAttribute("url", "user_bookList");
		} else {
			req.setAttribute("msg", "취소에 실패했습니다. 다시 시도해주세요.");
			req.setAttribute("url", "user_bookCancel?room_price="+room_price);
		}
		return "message";
	}
	
	@RequestMapping("/user_bookActList")
	public String userBookActList(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		HttpSession session = req.getSession();
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		int u_num = loginOkBean.getU_num();
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 3;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		rowCount = userMyPageMapper.countBookingAct(u_num);
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<BookingActDTO> list = userMyPageMapper.listBookingActByUser(startRow, endRow, u_num);
		
		req.setAttribute("bookActList", list);
		req.setAttribute("number", number);
		req.setAttribute("rowCount", rowCount);
		
		if (rowCount>0){
//			[1] [2] [3]
			int pageBlock = 2;
//			31(게시글수) / 5  =  몫 : 6, 나머지 = 1
			int pageCount = rowCount / pageSize;
//			나머지가 0이 아니면, 나머지 게시글 보여주기 위해 몫++ 해줌
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
//					3	>	7	거짓
//					9	>	7	참		endPage = 7(마지막페이지 넘버는 7이 됨)
			if (endPage > pageCount) endPage = pageCount;
			
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}		
		return "user/user_bookActList";
	}
	
	@RequestMapping("/user_bookActWriteForm")
	public String userBookActWriteForm(HttpServletRequest req, int p_num, int canBooker) {
		ProgramDTO pdto = activityMapper.getProgram(p_num);
		req.setAttribute("pdto", pdto);
		req.setAttribute("canBooker", canBooker);
		return "/user/user_bookActWriteform";
	}
	
	@RequestMapping("/user_bookActConfirm")
	public String userBookActConfirm(HttpServletRequest req, BookingActDTO badto) {
		int res = displayActMapper.insertBookAct(badto);
		
		if(res > 0) {
			req.setAttribute("msg", "예약 성공");
		}else {
			req.setAttribute("msg", "예약 실패");
		}
		req.setAttribute("url", "display_activityContent?a_num="+badto.getA_num());
		return "message";
	}
	
//	예약상세보기
	@RequestMapping("/user_bookActDetail")
	public String userBookActDetail(HttpServletRequest req, @RequestParam int a_num, int ba_num) {
		HttpSession session = req.getSession();
		LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
		BookingActDTO badto = displayActMapper.getBookAct(ba_num);
		ActivityDTO adto = activityMapper.getActivity(a_num);
		req.setAttribute("adto", adto);
		req.setAttribute("badto", badto);
		return "user/user_bookActDetail";
	}
	
	@RequestMapping("/user_bookActCancel")
	public String userBookActCancle(HttpServletRequest req, int ba_num) {
		int res = displayActMapper.deleteActBook(ba_num);
		if(res > 0) {
			req.setAttribute("msg", "예약이 취소되었습니다. 환불처리에 2~3일 정도 소요 될 수 있습니다");
			req.setAttribute("url", "user_bookActList");
		} else {
			req.setAttribute("msg", "취소에 실패했습니다. 다시 시도해주세요.");
			req.setAttribute("url", "user_bookActList");
		}
		
		return "message";
	}
}
