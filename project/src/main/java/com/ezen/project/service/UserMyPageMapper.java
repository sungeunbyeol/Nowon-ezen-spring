package com.ezen.project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.BookingDTO;

@Service
public class UserMyPageMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BookingDTO> reservationView(BookingDTO dto) {
		return sqlSession.selectList("reservationView", dto);
	}
}
