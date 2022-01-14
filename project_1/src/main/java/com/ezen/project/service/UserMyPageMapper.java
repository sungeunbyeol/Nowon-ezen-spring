package com.ezen.project.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.UserDTO;
import com.ezen.project.model.UserPointDTO;
import com.ezen.project.model.WishListDTO;

@Service
public class UserMyPageMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BookingDTO> reservationView(int uNum) {
		return sqlSession.selectList("reservationView", uNum);
	}
	
	public List<UserPointDTO> pointView(int uNum) {
		return sqlSession.selectList("pointView", uNum);
	}
	
	public  List<WishListDTO> wishListView(int uNum) {
		return sqlSession.selectList("wishListView", uNum);
	}
	
	public int getBookingCount(){
		return sqlSession.selectOne("getBookingCount");
	}
	
	public List<BookingDTO> listBooking(int start, int end, int u_num){
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		map.put("u_num", u_num);
		return sqlSession.selectList("bookingList", map);
	}
	
	public String getRoomType(int room_num) {
		return sqlSession.selectOne("roomType", room_num);
	}
	
	public int getReviewCount(){
		return sqlSession.selectOne("getReviewCount");
	}
	
	public int getPointCount(){
		return sqlSession.selectOne("getPointCount");
	}
	
	public List<ReviewDTO> getReviewList(int start, int end, int u_num){
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		map.put("u_num", u_num);
		return sqlSession.selectList("getReviewList", map);
	}
	
	public List<ReviewDTO> getPointList(int start, int end, int u_num){
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		map.put("u_num", u_num);
		return sqlSession.selectList("getPointList", map);
	}

	public int insertReview(MultipartHttpServletRequest mr, String review_image) throws Exception{
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("h_num", mr.getParameter("h_num"));
		map.put("u_num", mr.getParameter("u_num"));
		map.put("review_contents", mr.getParameter("review_contents"));
		map.put("review_image", review_image);
		map.put("review_star", mr.getParameter("star"));
		map.put("review_nickname", mr.getParameter("review_nickname"));
		map.put("review_roomtype", mr.getParameter("room_type"));
	    
		return sqlSession.insert("insertReview", map);
	}
	
	public List<ReviewDTO> review(int u_num){
		return sqlSession.selectList("review", u_num);
	}
	
	public UserDTO userList(int u_num){
		return sqlSession.selectOne("userList", u_num);
	}
	
	public int deleteReview(int review_num) {
		return sqlSession.delete("deleteReview", review_num);
	}
	
	public String getPicture(int review_num) {
		return sqlSession.selectOne("getPicture",review_num);
	}
	
	public int changeNickName(Map<String, String> params) {
		return sqlSession.update("changeNickName",params);
	}
}