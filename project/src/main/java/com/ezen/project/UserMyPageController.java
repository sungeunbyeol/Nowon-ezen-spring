package com.ezen.project;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.ezen.project.service.UserJoinMapper;
import com.ezen.project.service.UserMyPageMapper;

@Controller
public class UserMyPageController {
	
	@Autowired
	UserJoinMapper userMapper;
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
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
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		UserDTO udto = userMyPageMapper.userList(u_num);
		
		req.setAttribute("udto", udto);
		
		return "user/user_info";
	}
	
	//예약현황 페이지로
	@RequestMapping("/user_bookList")
	public ModelAndView userBookList(HttpServletRequest req, @RequestParam (required = false)  String pageNum) throws Exception {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getCount();
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<BookingDTO> resultList = userMyPageMapper.reservationView(u_num, startRow, endRow) ;

		req.setAttribute("rowCount", rowCount);
	
		if (rowCount>0){
			int pageBlock = 3;
			int pageCount = rowCount / pageSize;
			if (rowCount%pageSize != 0){
				pageCount++;
			}
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}
		return new ModelAndView("user/user_bookList", "bookList", resultList);
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
		 
		 File file = new File(upPath+"\\review", picture);
         mf.transferTo(file);
         
        HttpSession session = req.getSession();
 		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
 		int u_num = loginokbean.getU_num();
 		List<ReviewDTO> resultList = userMyPageMapper.review(u_num);
 		
 		req.setAttribute("reviewList", resultList);
 		req.setAttribute("upPath", upPath);
		return "user/user_reviewList";
	}
	
	//내가쓴 리뷰 페이지로 이동
	@RequestMapping("/user_reviewList")
	public String user_reviewList(HttpServletRequest req, @RequestParam (required = false)  String pageNum) throws Exception {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getCount();
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<ReviewDTO> resultList = userMyPageMapper.review2(u_num, startRow, endRow);

		req.setAttribute("reviewList", resultList);
		req.setAttribute("upPath", upPath);
		req.setAttribute("rowCount", rowCount);
	
		if (rowCount>0){
			int pageBlock = 3;
			int pageCount = rowCount / pageSize;
			if (rowCount%pageSize != 0){
				pageCount++;
			}
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}
		return "user/user_reviewList";		
	}
	
	//리뷰 삭제 버튼 눌렀을 때
	@RequestMapping("/deleteReview")
	   public String reviewdelete(HttpServletRequest req, @RequestParam int review_num) {
	      String msg = null;
	      String picture = userMyPageMapper.getPicture(review_num);
	      String upPath2 = upPath+"\\review";
	      int res = userMyPageMapper.deleteReview(review_num);
	      
	      if(res > 0) {
	         //String upPath = req.getServletContext().getRealPath("files");
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
	public ModelAndView userpointList(HttpServletRequest req, @RequestParam(required = false) String pageNum) throws Exception{
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getCount();
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		HttpSession session = req.getSession();
		LoginOkBean loginOkBean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginOkBean.getU_num();
		
		List<UserPointDTO> resultList = userMyPageMapper.pointView(u_num, startRow, endRow);

		req.setAttribute("rowCount", rowCount);
	
		if (rowCount>0){
			int pageBlock = 3;
			int pageCount = rowCount / pageSize;
			if (rowCount%pageSize != 0){
				pageCount++;
			}
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}
		return new ModelAndView("user/user_pointList", "pointList", resultList);
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
	public ModelAndView userWishlist(HttpServletRequest req, @RequestParam (required = false) String pageNum) throws Exception {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getCount();
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<WishListDTO> resultList = userMyPageMapper.wishListView(u_num, startRow, endRow);

		req.setAttribute("rowCount", rowCount);
	
		if (rowCount>0){
			int pageBlock = 3;
			int pageCount = rowCount / pageSize;
			if (rowCount%pageSize != 0){
				pageCount++;
			}
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}
		return new ModelAndView("user/user_wishlist", "wishList", resultList);
	}
	
	//로그인 하지 않고 마이페이지나 위시리스트로 넘어갔을 때 실행시키기
	@RequestMapping("/user_needLogin")
	   public ModelAndView LoginNeed(HttpServletRequest req) {
	      req.setAttribute("msg", "로그인이 필요한 서비스 입니다");
	      req.setAttribute("url", "user_login");
	      return new ModelAndView("message");
	   }
}
