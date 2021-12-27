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
	

	
//									추후에 인원수도 지정
	@Override
	public List<HotelDTO> filter(Map<String, String> params) {
		//params으로 받을건 search 와 filter 두개
		//search값이 반드시 들어와야함
		List<HotelDTO> list = null;
		return list;
	}

//	호텔별 후기 갯수 반환
	@Override
	public int reviewCount(int h_num) {
		return sqlSession.selectOne("reviewCount", h_num);
	}
	
//	호텔별 별점 반환
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

//	호텔상세 호텔정보
	@Override
	public HotelDTO hotelDetail(int h_num) {
		return sqlSession.selectOne("hotelDetail", h_num);
	}
	
//	해당 호텔의 객실별 상세정보
	@Override						//twin, double, deluxe
	public RoomDTO typeRoom(int h_num, String type) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("h_num",String.valueOf(h_num));
		map.put("type", type);
		
		RoomDTO dto = sqlSession.selectOne("roomDetail", map);
		return dto;
	}

//	해당호텔의 리뷰와 회원번호를 위시리스트에 저장
	@Override
	public void addWish(int h_num, int u_num, int review_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("h_num", h_num);
		map.put("u_num", u_num);
		map.put("review_num", review_num);
		sqlSession.insert("addWish", map);
	}
	
//	해달호텔이 체크되어 있으면 Y반환
	@Override
	public String checkWish(int h_num, int u_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("h_num", h_num);
		map.put("u_num", u_num);
		String checkWish = sqlSession.selectOne("checkWish", map);
		return checkWish;
	}

//	해당 호텔의 후기를 리스트로 반환
	@Override
	public List<ReviewDTO> reviewList(int h_num) {
		List<ReviewDTO> reviewList = sqlSession.selectList("reviewList", h_num);
		return reviewList;
	}

//	예약페이지에서 받은 내용으로 BookingDTO 저장
	@Override
	public void addBooking(Map<String, String> info) {
		sqlSession.insert("addBooking", info);
	}

//	room_num, h_num, u_num으로 해당 예약내역이 있는 BookingDTO 반환
	@Override
	public BookingDTO showBooking(Map<String, Integer> info) {
		BookingDTO bdto = sqlSession.selectOne("showBooking", info);
		return bdto;
	}

//	위시리스트 내역에서 삭제
	@Override
	public void deleteWish(int w_num, int u_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("w_num", w_num);
		map.put("u_num", u_num);
		sqlSession.delete("deleteWish", map);
	}

//	유저정보 반환
	public UserDTO userInfo(int u_num) {
		return sqlSession.selectOne("userInfo",u_num);
	}
	
//	유저의 예약정보 반환
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
