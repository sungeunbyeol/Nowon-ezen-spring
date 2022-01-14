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
	
	//insert할때 image 랑 addr map에 넣어서 companymapper.xml로 넘겨주기. dto로 할까 고민하다가 map으로 했습니다 .... 
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
	
	//company 수정
	public int updateCompany(CompanyDTO dto) {
		
		return sqlSession.update("updateCompany", dto); 
	}
	
	//아이디, 비밀번호 찾기 
	public String searchCompany(String c_name, String c_tel, String c_email) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("c_name", c_name);
		map.put("c_tel", c_tel); 
		String msg = null; 
		 
	//c_email을 받으면 비밀번호 찾기 이므로 password로 보낸다
		if(map.get("c_email") != null) {
			msg = sqlSession.selectOne("searchCompany_password", map);
		}else {
			msg = sqlSession.selectOne("searchCompany_email", map);
		} 
		return msg;
	}
	
	//email로 company 정보 DB에서 가져오기
	public CompanyDTO getCompany(String c_email) {
		return sqlSession.selectOne("getCompany", c_email);
	}
	
	//c_num으로 company 정보 DB에서 가져오기
	public CompanyDTO getCompanyNum(int c_num) { 
		return sqlSession.selectOne("getCompanyNum", c_num);
	}
	
	//컴퍼니 비밀번호 변경
	public int updateCompanyPassword(CompanyDTO dto) {
		return sqlSession.update("updateCompanyPassword", dto); 
	}
	
	//admin이 company list볼때 사용
	public List<CompanyDTO> listCompany(){
		return sqlSession.selectList("listCompany");
	}
	
	//admin이 list검색할 때 사용 a_level가 2 (기업) 인 것들만 검색
	public List<CompanyDTO> findCompany(String search, String searchString){
		String sql = "select * from project_companyaccount where " + search + " like '%" + searchString + "%' and a_level='2'";
		Map<String, String> map = new Hashtable<String, String>(); 
		map.put("sql", sql);
		return sqlSession.selectList("findCompany", map);
	}
	
	//기업이 회원탈퇴할때 사용 
	public int deleteCompany(Map<String, String> params) {
		return sqlSession.update("deleteCompany", params);
	}
	
	//admin이 기업 삭제
	public int adminDeleteCompany(int c_num) {
		return sqlSession.update("adminDeleteCompany", c_num);
	}
	
	//찾은 email값 보내주기 
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