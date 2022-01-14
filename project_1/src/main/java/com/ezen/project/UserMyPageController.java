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
	
	//마이페이지로 이동
	@RequestMapping("/user_myPage")
	public String userMyPage() {
		return "user/user_myPage";
	}
	
	//예약현황 페이지로
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
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.setViewName("user/user_bookList");
		return mav;
	}
	
	//리뷰쓰는 화면으로
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
	
	//리뷰 실행될 때
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
			
			req.setAttribute("msg", "리뷰 등록 성공!! 리뷰 리스트 페이지로 이동합니다.");
            req.setAttribute("url", "user_reviewList");
		}
		
		return "message";
	}
	
	//내가쓴 리뷰 페이지로 이동
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
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.addObject("upPath", upPath);
		mav.setViewName("user/user_reviewList");
		return mav;
	}
	
	//리뷰 삭제 버튼 눌렀을 때
	@RequestMapping("/deleteReview")
	   public String reviewdelete(HttpServletRequest req, @RequestParam int review_num) {
	      
	      String msg = null;
	      String picture = userMyPageMapper.getPicture(review_num);
	      int res = userMyPageMapper.deleteReview(review_num);
	      
	      if(res > 0) {
	         File file = new File(upPath+"\\review", picture);
	         
	         if(file.exists()) {
	            file.delete();
	            msg = "리뷰삭제(이미지삭제)";
	         }
	         else {
	            msg = "리뷰삭제(이미지남음)";
	         }
	      }
	      else {
	         msg = "리뷰삭제 실패";
	      }
	      
	      req.setAttribute("msg", msg);
	      req.setAttribute("url", "user_reviewList");
	      return "message";
	   }
	
	//포인트페이지로 이동할 때
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
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.setViewName("user/user_pointList");
		
		return mav;
	}

	//위시리스트로 이동
	@RequestMapping("/user_wishlist")
	public ModelAndView userWishlist(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		List<WishListDTO> wdto = userMyPageMapper.wishListView(u_num);
		return new ModelAndView("user/user_wishlist", "wishList", wdto);
	}
	
	//유저 닉네임 변경
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
			msg="닉네임이 수정되었습니다";
			url="user_info";
		} else {
			msg="닉네임 수정에 실패했습니다";
			url="user_info";
		}

		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "message";
	}

//	hotelSeachOk 페이지에서 위시리스트 체크/해제
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
	
//	hotelContent 페이지에서 위시리스트 체크/해제
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
	
//	wishList페이지 에서 위시리스트 해제
	@RequestMapping(value="/wishRelease3")
	public String wishRelease3(HttpServletRequest req, @RequestParam int w_num) {
		displayHotelMapper.wishRelease2(w_num);
		return "user/user_wishlist";
	}
	
//	비회원이 회원전용 페이지 들어가려고 할때 실행
	@RequestMapping("/user_needLogin")
	public ModelAndView LoginNeed(HttpServletRequest req) {
		req.setAttribute("msg", "로그인이 필요한 서비스 입니다");
		req.setAttribute("url", "user_login");
		return new ModelAndView("message");
	}
	
//	bookWriteform페이지, roomContents페이지에서 h_num과 room_num으로 상세정보 보내줌
	@RequestMapping("/user_bookWriteform")
	public String userBookWriteform(HttpServletRequest req, @RequestParam int h_num, int room_num) {
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoomDetail(room_num, h_num);
		
//		회원, 비회원 구분
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		try {
			int u_num = loginInfo.getU_num();
			UserDTO udto = displayHotelMapper.userInfo(u_num);
			req.setAttribute("udto", udto);
		}catch(Exception e) {
			req.setAttribute("msg", "로그인이 필요한 서비스 입니다");
			req.setAttribute("url", "display_roomContent?h_num="+h_num+"&room_num="+room_num);
			return "message";
		}
		
//		indate, outdate session에서 꺼내서 따로 보냄
		String indate = (String)session.getAttribute("indate");
		String outdate = (String)session.getAttribute("outdate");
		
		req.setAttribute("indate", indate);
		req.setAttribute("outdate", outdate);	
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		return "user/user_bookWriteform";
	}
	
//	예약확정 확인, bookWriteform에서 적은 정보 db에 저장시킴(잘못되거나 오류나면 취소됨)
	@RequestMapping(value="/user_bookConfirm")
	public String bookConfirm(HttpServletRequest req, @RequestParam Map<String, String> params) {
		//예약한 회원 정보
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		
//		객실 남아있는지 다시한번 확인, 0개 이하면 예약취소
		int roomCount = displayHotelMapper.roomCountCheck(params);
		if(roomCount <= 0) {
			req.setAttribute("msg", "예약 실패");
			req.setAttribute("url", "main");
		}  else {
//			DB에 저장시도
			int res = displayHotelMapper.insertBook(params);
			if(res>0) {
//				회원이 입력한 포인트가 0보다 크면, 포인트 수정
				if(Integer.parseInt(params.get("inputPoint")) != 0) {
					displayHotelMapper.usedPoint(params);
				}
//				적립받을 포인트 업데이트해줌
				displayHotelMapper.savePoint(params);
				req.setAttribute("msg", "예약 성공");
				req.setAttribute("url", "user_bookList");
			} else {
				req.setAttribute("msg", "예약가능한 객실이 없습니다");
				req.setAttribute("url", "main");
			}
		}
		return "message";
	}
	
//	예약상세보기
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
	
//	예약 취소전 
	@RequestMapping("/user_bookCancel")
	public String userBookCancel(HttpServletRequest req, int book_num) {
		//RequestParam값에 h_num 나중에 추가
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

//	예약취소 확인
	@RequestMapping("/user_bookCancel_ok")
	public String userBookCancelOk(HttpServletRequest req, @RequestParam(required = false) int book_num, int room_price) {
		HttpSession session = req.getSession();
		LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginInfo.getU_num();
		int res = displayHotelMapper.deleteBook(book_num, u_num);
		if(res > 0) {
			req.setAttribute("msg", "예약이 취소되었습니다");
			req.setAttribute("url", "user_bookList");
		} else {
			req.setAttribute("msg", "취소에 실패했습니다. /n다시 시도해주세요.");
			req.setAttribute("url", "user_bookCancel?room_price="+room_price);
		}
		return "message";
	}
	
}