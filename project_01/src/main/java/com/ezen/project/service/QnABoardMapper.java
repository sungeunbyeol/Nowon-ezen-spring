package com.ezen.project.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.CompanyQnADTO;
import com.ezen.project.model.UserQnADTO;

@Service
public class QnABoardMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	// QnA�Խ��� ����Ʈ�� ���� ������ �����ֱ� ���� �޼ҵ� (������ ��������)
	public List<CompanyQnADTO> listAdminCompanyQnA(int start, int end) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		return sqlSession.selectList("listAdminCompanyQnA", map);
	}
	
	// QnA�Խ��� ����Ʈ�� ������ �����ֱ� ���� �޼ҵ� (��� ȸ����)
	public List<CompanyQnADTO> listCompanyQnA(int c_num) {
		return sqlSession.selectList("listCompanyQnA", c_num);
	}
	
	// �Խñ��� �� ����� Ȯ���ϴ� �޼ҵ� (�Խ��� ������ ������ ���)
	public int getAdminCompanyQnACount() {
		return sqlSession.selectOne("getAdminCompanyQnACount");
	}

	// Ư�� ����� �Խñ��� �� ����� Ȯ���ϴ� �޼ҵ� (�Խ��� ������ ������ ���)
	public int getCompanyQnACount(int c_num) {
		return sqlSession.selectOne("getCompanyQnACount");
	}
	
	// �Խñ� ����� �� ����ϴ� �޼ҵ�
	public int insertCompanyQnA(CompanyQnADTO cqadto) {
		String sql = "";
		
		if(cqadto.getCqa_num()==0) {
			// �Խñ��� ����� �ƴ϶� ó�� ���� ���� ���
			sql = "update project_companyQnA set cqa_re_step=cqa_re_step+1";
		}else {
			// �Խñ��� ����� ���
			sql = "update project_companyQnA set cqa_re_step=cqa_re_step+1 where cqa_re_step>"+cqadto.getCqa_re_step();	
			cqadto.setCqa_re_step(cqadto.getCqa_re_step() + 1);
			cqadto.setCqa_re_level(cqadto.getCqa_re_level() + 1);
		}
		
		Map<String, String> map = new Hashtable<String, String>();
		map.put("sql", sql);
		int res = sqlSession.update("plusCompanyQnARe", map);
		
		return sqlSession.insert("insertCompanyQnA", cqadto);
	}
	
	// �Խñ� ��Ͽ��� �Խñ��� ������ �� ������ �����ֱ� ���� �޼ҵ�
	public CompanyQnADTO getCompanyQnA(int cqa_num) {
		return sqlSession.selectOne("getCompanyQnA", cqa_num);
	}
	
	// �Խñ��� ������ �� ����ϴ� �޼ҵ�
	public int deleteCompanyQnA(int cqa_num) {
		return sqlSession.delete("deleteCompanyQnA", cqa_num);
	}
	
	// �Խñ��� ������ �� ����ϴ� �޼ҵ�
	public int updateCompanyQnA(CompanyQnADTO cqadto) {
		return sqlSession.update("updateCompanyQnA", cqadto);
	}
	
	// QnA�Խ��� ����Ʈ�� ���� ������ �����ֱ� ���� �޼ҵ� (������ ��������)
	public List<UserQnADTO> listAdminUserQnA(int start, int end) {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		return sqlSession.selectList("listAdminUserQnA", map);
	}
	
	// QnA�Խ��� ����Ʈ�� ������ �����ֱ� ���� �޼ҵ� (ȸ�� ��������)
	public List<UserQnADTO> listUserQnA(int u_num) {
		return sqlSession.selectList("listUserQnA", u_num);
	}
	
	// �Խñ��� �� ����� Ȯ���ϴ� �޼ҵ� (�Խ��� ������ ������ ���)
	public int getAdminUserQnACount() {
		return sqlSession.selectOne("getAdminUserQnACount");
	}
	
	// Ư�� ȸ���� �Խñ��� �� ����� Ȯ���ϴ� �޼ҵ� (�Խ��� ������ ������ ���)
	public int getUserQnACount(int u_num) {
		return sqlSession.selectOne("getUserQnACount", u_num);
	}
	
	// �Խñ� ����� �� ����ϴ� �޼ҵ�
	public int insertUserQnA(UserQnADTO uqadto) {
		String sql = "";
		
		if(uqadto.getUqa_num()==0) {
			// �Խñ��� ����� �ƴ϶� ó�� ���� ���� ���
			sql = "update project_userQnA set uqa_re_step=uqa_re_step+1";
		}else {
			// �Խñ��� ����� ���
			sql = "update project_userQnA set uqa_re_step=uqa_re_step+1 where uqa_re_step>"+uqadto.getUqa_re_step();	
			uqadto.setUqa_re_step(uqadto.getUqa_re_step() + 1);
			uqadto.setUqa_re_level(uqadto.getUqa_re_level() + 1);
		}
		
		Map<String, String> map = new Hashtable<String, String>();
		map.put("sql", sql);
		int res = sqlSession.update("plusUserQnARe", map);
		
		return sqlSession.insert("insertUserQnA", uqadto);
	}
	
	// �Խñ� ��Ͽ��� �Խñ��� ������ �� ������ �����ֱ� ���� �޼ҵ�
	public UserQnADTO getUserQnA(int uqa_num) {
		return sqlSession.selectOne("getUserQnA", uqa_num);
	}
	
	// �Խñ��� ������ �� ����ϴ� �޼ҵ�
	public int deleteUserQnA(int uqa_num) {
		return sqlSession.delete("deleteUserQnA", uqa_num);
	}
	
	// �Խñ��� ������ �� ����ϴ� �޼ҵ�
	public int updateUserQnA(UserQnADTO uqadto) {
		return sqlSession.update("updateUserQnA", uqadto);
	}
	
	public String getCnameByCnum(int c_num) {
		return sqlSession.selectOne("getCnameByCnum", c_num);
	}
	
	//UserQnA �Խñ� ����Ʈ���� �ۼ��� �г����� �ҷ��ö� ����ϴ� �޼ҵ�
	public String getUnicknameByUnum(int u_num) {
		return sqlSession.selectOne("getUnicknameByUnum", u_num);
	}
	
}
