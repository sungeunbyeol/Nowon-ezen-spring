package com.ezen.project;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import com.ezen.project.model.UserPointDTO;
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
	
	// 내정보 페이지로
	@RequestMapping("/user_info")
	public String userInfo(HttpServletRequest req) {
//		HttpSession session = req.getSession();
//		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
//		int u_num = loginokbean.getU_num();
//		UserDTO udto = userMyPageMapper.userList(u_num);
//		
//		req.setAttribute("udto", udto);
		
		return "user/user_info";
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
		
		//List<BoardDBBean> list = boardDAO.listBoard(startRow, endRow);
		List<BookingDTO> list = userMyPageMapper.listBoard(startRow, endRow, u_num);
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
		
		//List<BookingDTO> bookList = userMyPageMapper.reservationView(u_num);
		//mav.addObject("bookList", bookList);
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
	public String user_reviewOk(HttpServletRequest req) throws IllegalStateException, Exception {
		 MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
	      MultipartFile mf = mr.getFile("picture");
	      String picture = mf.getOriginalFilename();
		 int res = userMyPageMapper.insertReview(mr, picture);
		 
		 File file = new File(upPath, picture);
         mf.transferTo(file);
         
        HttpSession session = req.getSession();
 		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
 		int u_num = loginokbean.getU_num();
 		List<ReviewDTO> listReview = userMyPageMapper.review(u_num);
 		
 		req.setAttribute("listReview", listReview);
 		req.setAttribute("upPath", upPath);
		return "user/user_reviewList";
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
		int pageSize = 4;
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
			int pageBlock = 3;
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
	      String upPath2 = upPath;
	      int res = userMyPageMapper.deleteReview(review_num);
	      
	      if(res > 0) {
	         File file = new File(upPath2, picture);
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
	//QnA 리스트로 이동
	@RequestMapping("/user_qna_list")
	public String userQnaList() {
		return "board/user_qna_list";
	}
	//QnA 작성하기로 이동
	@RequestMapping("/user_qna_writeform")
	public String userQnaWriteform() {
		return "board/user_qna_writeform";
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

	
}