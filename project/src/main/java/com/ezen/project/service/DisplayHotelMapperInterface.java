package com.ezen.project.service;

import java.util.List;
import java.util.Map;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.RoomDTO;

public interface DisplayHotelMapperInterface {
	
	//ȣ�� �˻� ���
	public List<HotelDTO> filter(Map<String, String> params);
	public int reviewCount(int h_num);
	public double reviewStar(int h_num);
	
	//ȣ�� ��
	public HotelDTO hotelDetail(int h_num);
	public RoomDTO typeRoom(int h_num, String type);
	public String checkWish(int h_num, int u_num);
	public List<ReviewDTO> reviewList(int h_num);
	
	//���� ��
	public void addWish(int h_num, int u_num, int review_num);
	
	//���� ������
	public void addBooking(Map<String,String> info);
	
	//���� ��
	public BookingDTO showBooking(Map<String,Integer> info);
	
	//���ø���Ʈ
	public void deleteWish(int w_num, int u_num);
	
}
