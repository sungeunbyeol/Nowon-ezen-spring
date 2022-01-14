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
	
	//insert�Ҷ� image �� addr map�� �־ companymapper.xml�� �Ѱ��ֱ�. dto�� �ұ� ����ϴٰ� map���� �߽��ϴ� .... 
	public int insertCompany(MultipartHttpServletRequest mr, String c_image, String addr) {
		Map<String, String> map = new Hashtable<String, String>();
		String level = "2"; 
		map.put("a_level", level); 
		map.put("c_address", addr);
		map.put("c_image", c_image);
		map.put("c_bnum", mr.getParameter("c_bnum"));
		map.put("c_ceo", mr.getParameter("c_ceo"));
		map.put("c_email", mr.getParameter("c_email"));
		map.put("c_password", mr.getParameter("c_password"));
		map.put("c_tel", mr.getParameter("c_tel"));
		map.put("c_name", mr.getParameter("c_name"));
		 
		return sqlSession.insert("insertCompany", map); 
	}
	
	//company ����
	public int updateCompany(CompanyDTO dto) {
		
		return sqlSession.update("updateCompany", dto); 
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
	public CompanyDTO getCompany(String c_email) {
		return sqlSession.selectOne("getCompany", c_email);
	}
	
	//c_num���� company ���� DB���� ��������
	public CompanyDTO getCompanyNum(int c_num) { 
		return sqlSession.selectOne("getCompanyNum", c_num);
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
		String sql = "select * from project_companyaccount where " + search + " like '%" + searchString + "%' and a_level='2'";
		Map<String, String> map = new Hashtable<String, String>(); 
		map.put("sql", sql);
		return sqlSession.selectList("findCompany", map);
	}
	
	//����� ȸ��Ż���Ҷ� ��� 
	public int deleteCompany(Map<String, String> params) {
		return sqlSession.update("deleteCompany", params);
	}
	
	//admin�� ��� ����
	public int adminDeleteCompany(int c_num) {
		return sqlSession.update("adminDeleteCompany", c_num);
	}
	
	//ã�� email�� �����ֱ� 
	public String findEmail(Map<String, String> params) {
		String c_email = sqlSession.selectOne("findEmail", params);
		return  c_email;
	}

	public boolean hasCompanyAccount(String c_name, String c_bnum) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("c_name", c_name);
		map.put("c_bnum", c_bnum);
		
		CompanyDTO cdto = sqlSession.selectOne("hasCompanyAccount", map);
		
		if(cdto != null) {
			return true;
		}else {
			return false;
		}
	}
} 