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
	public List<ReviewDTO> reviewList(int h_num);
	
	//객실 상세
	public void wishCheck(Map<String, String> params);
	
	
	
	
}
