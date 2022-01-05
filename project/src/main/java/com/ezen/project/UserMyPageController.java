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

	@RequestMapping("/user_myPage")
	public String userMyPage() {
		return "user/user_myPage";
	}
	
	@RequestMapping("/user_info")
	public String userInfo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		UserDTO udto = userMyPageMapper.userList(u_num);
		
		req.setAttribute("udto", udto);
		
		return "user/user_info";
	}
	
	@RequestMapping("/user_bookList")
	public ModelAndView userBookList(HttpServletRequest req, @RequestParam int u_num) {
		List<BookingDTO> resultList = userMyPageMapper.reservationView(u_num) ;
		return new ModelAndView("user/user_bookList", "bookList", resultList);
	}
	
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
	
	@RequestMapping("/user_reviewList")
	public String user_reviewList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		List<ReviewDTO> resultList = userMyPageMapper.review(u_num);
		
		req.setAttribute("reviewList", resultList);
		req.setAttribute("upPath", upPath);
		
		return "user/user_reviewList";
	}
	
	@RequestMapping("/deleteReview")
	   public String reviewdelete(HttpServletRequest req, @RequestParam int review_num) {
		/*
		 * String picture = null; try { picture =
		 * java.net.URLDecoder.decode(req.getParameter("picture"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
	      
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
	
	
	@RequestMapping("/user_pointList")
	public ModelAndView userpointList(HttpServletRequest req, @RequestParam int u_num) {
		List<UserPointDTO> resultList = userMyPageMapper.pointView(u_num);
		return new ModelAndView("user/user_pointList", "pointList", resultList);
	}
	
	@RequestMapping("/user_qna_list")
	public String userQnaList() {
		return "board/user_qna_list";
	}
	
	@RequestMapping("/user_qna_writeform")
	public String userQnaWriteform() {
		return "board/user_qna_writeform";
	}
	
	@RequestMapping("/user_wishlist")
	public ModelAndView userWishlist(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBean loginokbean = (LoginOkBean)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		System.out.println(u_num);
		List<WishListDTO> wdto = userMyPageMapper.wishListView(u_num);
		return new ModelAndView("user/user_wishlist", "wishList", wdto);
	}
	
	@RequestMapping("/user_needLogin")
	   public ModelAndView LoginNeed(HttpServletRequest req) {
	      req.setAttribute("msg", "로그인이 필요한 서비스 입니다");
	      req.setAttribute("url", "user_login");
	      return new ModelAndView("message");
	   }
	
	@RequestMapping("/user_bookWriteform")
	public String userBookWriteform(HttpServletRequest req, @RequestParam int h_num, String type, int u_num) {
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoom(h_num, type);
		UserDTO udto = displayHotelMapper.userInfo(u_num);
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		req.setAttribute("udto", udto);
		return "user/user_bookWriteform";
	}
	
	@RequestMapping("/user_bookDetail")
	public String userBookDetail(HttpServletRequest req, @RequestParam int h_num, int u_num) {
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		BookingDTO bdto = displayHotelMapper.bookInfo(u_num, h_num, 0);
		req.setAttribute("hdto", hdto);
		req.setAttribute("bdto", bdto);
		return "user/user_bookDetail";
	}
	
	@RequestMapping("/user_bookCancel")
	public String userBookCancel(HttpServletRequest req, @RequestParam int room_price, int book_num) {
		BookingDTO bdto = displayHotelMapper.bookInfo(0,0,book_num);
//		session값으로 유저dto 가져와서 req로 내려보내줘야함
		req.setAttribute("bdto", bdto);
		req.setAttribute("room_price", room_price);
		return "user/user_bookCancel";
	}
}
