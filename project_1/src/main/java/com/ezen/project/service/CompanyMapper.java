package com.ezen.project.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.project.model.CompanyDTO;


@Service
public class CompanyMapper {
	 
	@Autowired 
	private SqlSession sqlSession;
	
	public int insertCompany(CompanyDTO cdto) {
		return sqlSession.insert("insertCompany", cdto); 
	}
	
	//company ����
	public int updateCompany(CompanyDTO cdto) {
		return sqlSession.update("updateCompany", cdto); 
	}
	
	//���̵�, ��й�ȣ ã�� 
	public String searchCompany(String c_name, String c_tel, String c_email) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("c_name", c_name);
		map.put("c_tel", c_tel); 
		String msg = null; 
		
	//c_email�� ������ ��й�ȣ ã�� �̹Ƿ� password�� ������
		if(map.get("c_email") != null) {
			msg = sqlSession.selectOne("searchCompany_password", map);
		}else {
			msg = sqlSession.selectOne("searchCompany_email", map);
		} 
		return msg;
	}
	
	//email�� company ���� DB���� ��������
	public CompanyDTO getCompanyByEmail(String c_email) {
		return sqlSession.selectOne("getCompanyByEmail", c_email);
	}
	
	//c_num���� company ���� DB���� ��������
	public CompanyDTO getCompanyByCnum(int c_num) { 
		return sqlSession.selectOne("getCompanyByCnum", c_num);
	}
	
	//���۴� ��й�ȣ ����
	public int updateCompanyPassword(CompanyDTO dto) {
		return sqlSession.update("updateCompanyPassword", dto); 
	}
	
	//admin�� company list���� ���
	public List<CompanyDTO> listCompany(){
		return sqlSession.selectList("listCompany");
	}
	
	//admin�� list�˻��� �� ��� a_level�� 2 (���) �� �͵鸸 �˻�
	public List<CompanyDTO> findCompany(String search, String searchString){
		String sql = "select * from project_companyaccount where " + search + 
				" like '%" + searchString + "%' and a_level='2'";
		Map<String, String> map = new Hashtable<String, String>(); 
		map.put("sql", sql);
		return sqlSession.selectList("findCompany", map);
	}
	
	//����� ȸ��Ż���Ҷ� ��� 
	public int deleteCompany(int c_num, String c_password) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("c_num", String.valueOf(c_num));
		map.put("c_password", c_password);
		return sqlSession.update("deleteCompany", map);
	}
	
	//admin�� ��� ����
	public int deleteCompanyByAdmin(int c_num) {
		return sqlSession.update("deleteCompanyByAdmin", c_num);
	}
	
	//ã�� email�� �����ֱ� 
	public String findEmail(Map<String, String> params) {
		return  sqlSession.selectOne("findEmail", params);
	}

	public boolean hasCompanyAccount(String c_email, String c_bnum) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("c_email", c_email);
		map.put("c_bnum", c_bnum);
		
		CompanyDTO cdto = sqlSession.selectOne("hasCompanyAccount", map);
		
		if(cdto != null) {
			return true;
		}else {
			return false;
		}
	}
	
} 