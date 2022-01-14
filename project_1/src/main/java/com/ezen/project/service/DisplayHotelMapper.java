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
public class DisplayHotelMapper {

	@Autowired
	private SqlSession sqlSession;
	

	
//									추후에 인원수도 지정
	public List<HotelDTO> filter(Map<String, String> params) {
		//params으로 받을건 search 와 filter 두개
		//search값이 반드시 들어와야함
		List<HotelDTO> list = null;
		return list;
	}
	
//	지역별 호텔리스트
	public List<HotelDTO> hotelLocation(String condition){
		Map<String,String> sql = new Hashtable<String, String>();
		String str = null;
		String[] arr = new String[] {"seoul","pusan","a","b","c","d","e"};
		
		for(int i=0; i < arr.length; i++) {
			//지역으로 검색 추후 업데이트
			if(condition.equals(arr[i])) {
				switch(condition) {
				case "seoul" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "pusan" : 
					str = "select * from project_hotel where h_address like '%부산%'";
					break;
				case "jeju" : 
					str = "select * from project_hotel where h_address like '%제주%'";
					break;
				case "sokcho" : 
					str = "select * from project_hotel where h_address like '%속초%'";
					break;
				case "c" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "d" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "e" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				}
			}
		}
		
		//통합검색
		if(str == null) {
			str = "select * from project_hotel where h_name like '%"+condition+"%' or h_address like '%"+condition+"%'";
		}
		sql.put("sql", str);
		
		return sqlSession.selectList("hotelLocation", sql);
	}
	
	public List<HotelDTO> getHotelListByMaxPrice(String condition){
		Map<String,String> sql = new Hashtable<String, String>();
		String str = null;
		String[] arr = new String[] {"seoul","pusan","a","b","c","d","e"};
		
		for(int i=0; i < arr.length; i++) {
			//지역으로 검색 추후 업데이트
			if(condition.equals(arr[i])) {
				switch(condition) {
				case "seoul" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "pusan" : 
					str = "select * from project_hotel where h_address like '%부산%'";
					break;
				case "jeju" : 
					str = "select * from project_hotel where h_address like '%제주%'";
					break;
				case "sokcho" : 
					str = "select * from project_hotel where h_address like '%속초%'";
					break;
				case "c" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "d" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "e" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				}
			}
		}
		
		//통합검색
		if(str == null) {
			str = "SELECT h.* "
					+ "FROM project_hotel h INNER JOIN ("
					+ "SELECT h_num, MAX(room_price) as topprice "
					+ "FROM project_room "
					+ "GROUP BY h_num"
					+ ")r "
					+ "ON h.h_num=r.h_num "
					+ "WHERE h_name like '%"+condition+"%' or h_address like '%"+condition+"%' "
					+ "ORDER BY r.topprice desc";
		}
		sql.put("sql", str);
		
		return sqlSession.selectList("getHotelListByMaxPrice", sql);
	}
	
	public List<HotelDTO> getHotelListByMinPrice(String condition){
		Map<String,String> sql = new Hashtable<String, String>();
		String str = null;
		String[] arr = new String[] {"seoul","pusan","a","b","c","d","e"};
		
		for(int i=0; i < arr.length; i++) {
			//지역으로 검색 추후 업데이트
			if(condition.equals(arr[i])) {
				switch(condition) {
				case "seoul" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "pusan" : 
					str = "select * from project_hotel where h_address like '%부산%'";
					break;
				case "jeju" : 
					str = "select * from project_hotel where h_address like '%제주%'";
					break;
				case "sokcho" : 
					str = "select * from project_hotel where h_address like '%속초%'";
					break;
				case "c" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "d" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				case "e" : 
					str = "select * from project_hotel where h_address like '%서울%'";
					break;
				}
			}
		}
		
		//통합검색
		if(str == null) {
			str = "SELECT h.* "
					+ "FROM project_hotel h INNER JOIN ("
					+ "SELECT h_num, MIN(room_price) as lowprice "
					+ "FROM project_room "
					+ "GROUP BY h_num"
					+ ")r "
					+ "ON h.h_num=r.h_num "
					+ "WHERE h_name like '%"+condition+"%' or h_address like '%"+condition+"%' "
					+ "ORDER BY r.lowprice asc";
		}
		sql.put("sql", str);
		
		return sqlSession.selectList("getHotelListByMaxPrice", sql);
	}
	
//	전체호텔 전체리뷰수 반환
	public Map<Integer, Integer> countReview(List<HotelDTO> hotelList){
		List<Integer> h_num = new ArrayList<Integer>();
		for(int i=0; i < hotelList.size(); i++) {
			HotelDTO hdto = hotelList.get(i);
			h_num.add(hdto.getH_num());
		}
		
		List<ReviewDTO> allReview = sqlSession.selectList("allReview");
		Map<Integer, Integer> map = new Hashtable<Integer, Integer>();
		
		int count = 0;
		for(int j=0; j<h_num.size(); j++) {
			//등록된 모든 호텔
			int hotelNum = h_num.get(j);
			//등록된 모든 후기
			for(int i=0; i<allReview.size(); i++) {
				ReviewDTO rdto = allReview.get(i);
				if(hotelNum == rdto.getH_num()) {
					count++;
				}
			}
			map.put(hotelNum, count);
			count = 0;
		}
		return map;
		
	}
	
//	전체호텔 리뷰평균 반환
	public Map<Integer, Double> averageReview(List<HotelDTO> hotelList){
		List<Integer> h_num = new ArrayList<Integer>();
		for(int i=0; i < hotelList.size(); i++) {
			HotelDTO hdto = hotelList.get(i);
			h_num.add(hdto.getH_num());
		}
		
		List<ReviewDTO> allReview = sqlSession.selectList("allReview");
		Map<Integer, Double> map = new Hashtable<Integer, Double>();
		
		int count = 0;
		int totalStar = 0;
		for(int j=0; j<h_num.size(); j++) {
			//등록된 모든 호텔
			int hotelNum = h_num.get(j);
			//등록된 모든 후기
			for(int i=0; i<allReview.size(); i++) {
				ReviewDTO rdto = allReview.get(i);
				if(hotelNum == rdto.getH_num()) {
					count++;
					totalStar += rdto.getReview_star();
				}
			}
			double average = (double)totalStar / count;
			average = Math.round(average*10)/10.0;//소수 1번째 자리까지 표기
			map.put(hotelNum, average);
			count = 0;
			totalStar = 0;
		}
		return map;
		
	}
	
//	위시리스트 체크 확인
	public int isWishCheck(int h_num, int u_num) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("h_num", h_num);
		map.put("u_num", u_num);
		return sqlSession.selectOne("isWishCheck", map);
	}
	
////	전체호텔 위시리스트 체크여부
//	public void wishList(List<HotelDTO> hotelList, int u_num){
//		
////		호텔고유값만 리스트에 저장
//		List<Integer> h_num = new ArrayList<Integer>();
//		for(int i=0; i < hotelList.size(); i++) {
//			HotelDTO hdto = hotelList.get(i);
//			h_num.add(hdto.getH_num());
//		}
//		
//		
//		List<WishListDTO> wishList = sqlSession.selectList("wishList", u_num);
//		for(int i=0; i<hotelList.size(); i++) {
//			HotelDTO hdto = hotelList.get(i);
//			for(int j=0; j<wishList.size(); j++) {
//				WishListDTO wdto = wishList.get(j);
//				if(hdto.getH_num() == wdto.getH_num()) {
//					hdto.setWishList(1);
//				}
//			}
//		}
//	}

	
//	호텔상세 호텔정보
	public HotelDTO hotelDetail(int h_num) {
		return sqlSession.selectOne("hotelDetail", h_num);
	}
	
//	호텔별 후기 갯수 반환
	public int reviewCount(int h_num) {
		return sqlSession.selectOne("reviewCount", h_num);
	}
	
//	호텔별 별점 반환
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
	
//	해당 호텔의 객실타입 리스트
											//twin, double, deluxe
	public RoomDTO typeRoom(int h_num, String type) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("h_num",String.valueOf(h_num));
		map.put("type", type);
		
		return sqlSession.selectOne("roomList", map);
	}
	
	public List<RoomDTO> typeRoomList(int h_num, String type) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("h_num",String.valueOf(h_num));
		map.put("type", type);
		
		return sqlSession.selectList("roomList", map);
	}
	
	//룸번호 추가
//	해당 호텔의 객실별 상세정보
											//twin, double, deluxe
	public RoomDTO typeRoomDetail(int room_num, int h_num) {
		Map<String, Integer> params = new Hashtable<String, Integer>();
		params.put("room_num", room_num);
		params.put("h_num", h_num);
		return sqlSession.selectOne("roomDetail", params);
	}

	
//	해당 호텔의 후기를 리스트로 반환
	public List<ReviewDTO> reviewList(int h_num) {
		List<ReviewDTO> reviewList = sqlSession.selectList("reviewList", h_num);
		return reviewList;
	}
	
//	위시리스트 해제
	public void wishRelease(Map<String, String> params) {
		sqlSession.delete("wishRelease", params);
	}

	
//	해당호텔의 회원번호를 위시리스트에 저장
	public void wishCheck(Map<String, String> params) {
		sqlSession.insert("wishCheck", params);
	}

	
//	유저정보 반환
	public UserDTO userInfo(int u_num) {
		return sqlSession.selectOne("userInfo",u_num);
	}
	
//	예약저장&유저 포인트 수정
	public int insertBook(Map<String,String> params) {
//		sqlSession.update("pointUpdate",params);
		return sqlSession.insert("insertBook",params);
	}
	
//	객실수량확인
	public int roomCountCheck(Map<String, String> params) {
		return sqlSession.selectOne("roomCountCheck",params);
	}
	
//	유저의 예약정보 반환1
	public BookingDTO bookInfo1() {
		return sqlSession.selectOne("bookInfo1");	
	}
	
//	유저의 예약정보 반환2
	public BookingDTO bookInfo2(int book_num) {
		BookingDTO bdto = sqlSession.selectOne("bookInfo2", book_num);
		return bdto;
	}

//  유저의 예약정보 반환
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
	
//	예약취소
	public int deleteBook(int book_num, int u_num) {
		int usePoint = sqlSession.selectOne("usePoint", book_num);
		int savePoint = sqlSession.selectOne("savePoint", book_num);
		int update = usePoint - savePoint;
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("update", update);
		map.put("u_num", u_num);
		sqlSession.update("updatePoint", map);
		
		//취소 포인트 파라미터값 만들어주기
		Map<String, String> params = new Hashtable<String, String>();
		BookingDTO bdto = sqlSession.selectOne("getBooking", book_num);
		String p_status = "취소";
		String h_name = sqlSession.selectOne("getHotelName",bdto.getH_num());
		String p_contents = h_name + " 예약 취소";
		int p_remaining = sqlSession.selectOne("getUserPoint", u_num);
		params.put("book_num", String.valueOf(book_num));
		params.put("u_num", String.valueOf(u_num));
		params.put("p_status", p_status);
		params.put("p_contents", p_contents);
		params.put("p_point", String.valueOf(update));
		params.put("p_remaining", String.valueOf(p_remaining));
		sqlSession.insert("cancelPoint", params);
		
		return sqlSession.update("deleteBook", book_num);
	}
	
//	객실가격반환
	public int getRoomPrice(int book_num) {
		int room_num = sqlSession.selectOne("getRoomNum",book_num);
		return sqlSession.selectOne("getRoomPrice", room_num);
	}
	
//	포인트db적립 기록
	public void savePoint(Map<String,String> params) {
		
		sqlSession.update("savePointUpdate", params);
		
		Map<String, String> map = new Hashtable<String, String>();
		String h_name = sqlSession.selectOne("getHotelName",params);
		int p_point = (int)Double.parseDouble(params.get("book_savepoint"));
		int book_num = sqlSession.selectOne("getBookNum", params);
		int u_point = sqlSession.selectOne("getUserPoint", params.get("u_num"));
		
		map.put("u_num", params.get("u_num"));
		map.put("p_status","적립");
		map.put("p_contents",h_name+" 예약 확정");
		map.put("p_point", params.get("book_savepoint"));
		map.put("book_num", String.valueOf(book_num));
		map.put("p_remaining",String.valueOf(u_point));
		sqlSession.insert("savePointDB", map);
		sqlSession.update("updatePointEnd",u_point);
	}
	
//	예약시 사용한 포인트db기록
	public void usedPoint(Map<String,String> params) {
		
		sqlSession.update("usedPointUpdate", params);
		
		Map<String, String> map = new Hashtable<String, String>();
		String h_name = sqlSession.selectOne("getHotelName",params);
		int p_point = (int)Double.parseDouble(params.get("book_savepoint"));
		int book_num = sqlSession.selectOne("getBookNum", params);
		int u_point = sqlSession.selectOne("getUserPoint", params.get("u_num"));
		map.put("u_num", params.get("u_num"));
		map.put("p_status","사용");
		map.put("p_contents",h_name+" 예약 할인");
		map.put("p_point", params.get("inputPoint"));
		map.put("book_num", String.valueOf(book_num));
		map.put("p_remaining",String.valueOf(u_point));
		sqlSession.insert("savePointDB", map);
	}
	
//	호텔전체목록
	public Map<String, Integer> hotelList(){
		List<HotelDTO> hotelList = sqlSession.selectList("hotelList");
		
//		지역 다 정해지면 추가
		String[] location = new String[] {"서울","부산","제주","인천","서귀포"};
		Map<String, Integer> map = new Hashtable<String, Integer>();
		
		for(int i=0; i<hotelList.size(); i++) {
			HotelDTO hdto = hotelList.get(i);
			String h_address = hdto.getH_address();
			
			for(int j=0; j<location.length; j++) {
				if(h_address.contains(location[j])) {
					if(map.containsKey(location[j])) {
						int count = map.get(location[j]);
						count++;
						map.put(location[j], count);
					}
					else {
						int count = 1;
						map.put(location[j], count);
					}
				}
			}
		}
		return map;
	}

//	위시리스트에서 찜하기 해제
	public void wishRelease2(int w_num) {
		System.out.println(w_num);
		sqlSession.delete("wishRelease2", w_num);
	}

	public List<Integer> getBookedRoomCount(Map<String,String> map) {
		String sql = "SELECT count(*) FROM project_booking WHERE "
				+ "(book_indate <= '"+map.get("book_outdate")+
				"' AND book_outdate >= '"+map.get("book_indate")+"') GROUP BY room_num";
		Map<String, String> map2 = new Hashtable<>();
		System.out.println(sql);
		map2.put("sql", sql);
		return sqlSession.selectList("getBookedRoomCount", map2);
	}
	
}
