package com.ezen.project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.ActivityDTO;
import com.ezen.project.model.BookingActDTO;
import com.ezen.project.model.ProgramDTO;

@Service
public class ActivityMapper {
	@Autowired
	private SqlSession sqlSession;
	
	// ����� ���������� ��Ƽ��Ƽ�� ����� �� ����ϴ� �޼ҵ�
	public int inputActivity(ActivityDTO adto) {
		return sqlSession.insert("inputActivity", adto);
	}
	
	// ����� ���������� ��Ƽ��Ƽ�� ������ �� ����ϴ� �޼ҵ�
	public int deleteActivity(int a_num) {
		return sqlSession.delete("deleteActivity", a_num);
	}
	
	// ����� ���������� ��Ƽ��Ƽ�� ������ �� ����ϴ� �޼ҵ�
	public int editActivity(ActivityDTO adto) {
		return sqlSession.update("editActivity", adto);
	}
	
	// ����� ����� ��Ƽ��Ƽ ����� �������� �޼ҵ�
	public List<ActivityDTO> listActivity(int c_num) {
		return sqlSession.selectList("listActivity", c_num);
	}
	
	// ��Ƽ��Ƽ Ư�� ������ �������� �޼ҵ�
	public ActivityDTO getActivity(int a_num) {
		return sqlSession.selectOne("getActivity", a_num);
	}
	
	public int inputProgram(ProgramDTO pdto) {
		return sqlSession.insert("inputProgram", pdto);
	}
	
	public List<ProgramDTO> listProgram(int a_num){
		return sqlSession.selectList("listProgram", a_num);
	}
	
	public ProgramDTO getProgram(int p_num) {
		return sqlSession.selectOne("getProgram", p_num);
	}
	
	public int editProgram(ProgramDTO pdto) {
		return sqlSession.update("editProgram", pdto);
	}
	
	public int deleteProgram(int p_num) {
		return sqlSession.delete("deleteProgram", p_num);
	}
	
	public List<BookingActDTO> listBookingActByCompany(int a_num) {
		return sqlSession.selectList("listBookingActByCompany", a_num);
	}

	public int denyBookingAct(int ba_num) {
		return sqlSession.update("denyBookingAct", ba_num);
	}
	
	public int confirmBookingAct(int ba_num) {
		return sqlSession.update("confirmBookingAct", ba_num);
	}
	
	public int checkoutBookingAct(int ba_num) {
		return sqlSession.update("checkoutBookingAct", ba_num);
	}
}
