package com.ezen.project.service;

import java.util.List;
import java.util.Map;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.RoomDTO;

public interface DisplayHotelMapperInterface {
	
	//호텔 검색 결과
	public List<HotelDTO> filter(Map<String, String> params);
	public int reviewCount(int h_num);
	public double reviewStar(int h_num);
	
	//호텔 상세
	public HotelDTO hotelDetail(int h_num);
	public RoomDTO typeRoom(int h_num, String type);
	public String checkWish(int h_num, int u_num);
	public List<ReviewDTO> reviewList(int h_num);
	
	//객실 상세
	public void addWish(int h_num, int u_num, int review_num);
	
	//예약 페이지
	public void addBooking(Map<String,String> info);
	
	//예약 상세
	public BookingDTO showBooking(Map<String,Integer> info);
	
	//위시리스트
	public void deleteWish(int w_num, int u_num);
	
}
