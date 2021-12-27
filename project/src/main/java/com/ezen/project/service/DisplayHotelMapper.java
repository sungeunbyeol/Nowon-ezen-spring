package com.ezen.project.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.model.UserDTO;

@Service
public class DisplayHotelMapper implements DisplayHotelMapperInterface{

	@Autowired
	private SqlSession sqlSession;
	

	
//									���Ŀ� �ο����� ����
	@Override
	public List<HotelDTO> filter(Map<String, String> params) {
		//params���� ������ search �� filter �ΰ�
		//search���� �ݵ�� ���;���
		List<HotelDTO> list = null;
		return list;
	}

//	ȣ�ں� �ı� ���� ��ȯ
	@Override
	public int reviewCount(int h_num) {
		return sqlSession.selectOne("reviewCount", h_num);
	}
	
//	ȣ�ں� ���� ��ȯ
	@Override
	public double reviewStar(int h_num) {
		List<Integer> star = new ArrayList<Integer>();
		star = sqlSession.selectList("reviewStar", h_num);
		int totalStar = 0;
		for(int i = 0; i < star.size(); i++) {
			totalStar += star.get(i);
		}
		double averageStar = (double)totalStar/star.size();
		return averageStar;
	}

//	ȣ�ڻ� ȣ������
	@Override
	public HotelDTO hotelDetail(int h_num) {
		return sqlSession.selectOne("hotelDetail", h_num);
	}
	
//	�ش� ȣ���� ���Ǻ� ������
	@Override						//twin, double, deluxe
	public RoomDTO typeRoom(int h_num, String type) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("h_num",String.valueOf(h_num));
		map.put("type", type);
		
		RoomDTO dto = sqlSession.selectOne("roomDetail", map);
		return dto;
	}

//	�ش�ȣ���� ����� ȸ����ȣ�� ���ø���Ʈ�� ����
	@Override
	public void addWish(int h_num, int u_num, int review_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("h_num", h_num);
		map.put("u_num", u_num);
		map.put("review_num", review_num);
		sqlSession.insert("addWish", map);
	}
	
//	�ش�ȣ���� üũ�Ǿ� ������ Y��ȯ
	@Override
	public String checkWish(int h_num, int u_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("h_num", h_num);
		map.put("u_num", u_num);
		String checkWish = sqlSession.selectOne("checkWish", map);
		return checkWish;
	}

//	�ش� ȣ���� �ı⸦ ����Ʈ�� ��ȯ
	@Override
	public List<ReviewDTO> reviewList(int h_num) {
		List<ReviewDTO> reviewList = sqlSession.selectList("reviewList", h_num);
		return reviewList;
	}

//	�������������� ���� �������� BookingDTO ����
	@Override
	public void addBooking(Map<String, String> info) {
		sqlSession.insert("addBooking", info);
	}

//	room_num, h_num, u_num���� �ش� ���೻���� �ִ� BookingDTO ��ȯ
	@Override
	public BookingDTO showBooking(Map<String, Integer> info) {
		BookingDTO bdto = sqlSession.selectOne("showBooking", info);
		return bdto;
	}

//	���ø���Ʈ �������� ����
	@Override
	public void deleteWish(int w_num, int u_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("w_num", w_num);
		map.put("u_num", u_num);
		sqlSession.delete("deleteWish", map);
	}

//	�������� ��ȯ
	public UserDTO userInfo(int u_num) {
		return sqlSession.selectOne("userInfo",u_num);
	}
	
//	������ �������� ��ȯ
	public BookingDTO bookInfo(int u_num,int h_num, int book_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		if(book_num > 0) {
			map.put("book_num",book_num);
			return sqlSession.selectOne("bookInfo1", map);
		} else {
		map.put("u_num", u_num);
			map.put("h_num", h_num);
			return sqlSession.selectOne("bookInfo2", map);
		}
	}
	
}
