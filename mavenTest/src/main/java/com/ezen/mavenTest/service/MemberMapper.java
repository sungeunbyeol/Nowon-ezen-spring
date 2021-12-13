package com.ezen.mavenTest.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ezen.mavenTest.model.MemberDTO;

@Service
public class MemberMapper {

	private SqlSession sqlSession;
	
	public boolean checkMember(Map<String, String> map) {
			MemberDTO dto = sqlSession.selectOne("checkMember", map);
			if (dto != null) {
				return true;
			}else {
				return false;
			}
	}
	
	public int insertMember(MemberDTO dto) {
			return sqlSession.insert("insertMember", dto);
	}
	
	public List<MemberDTO> listMember(){
		return sqlSession.selectList("listMember");
	}
	
	public int deleteMember(int no) {
			return sqlSession.delete("deleteMember", no);
	}
	
	public MemberDTO getMember(int no) {
			return sqlSession.selectOne("getMember", no);
	}
	
	public int updateMember(MemberDTO dto) {
			return sqlSession.update("updateMember", dto);
	}

	public List<MemberDTO> findMember(String search, String searchString) {
		String sql = "select * from jspmember where " + search + " like '" + searchString +"'";
		Map<String, String> map = new Hashtable<String, String>();
		map.put("sql",sql);
		return sqlSession.selectList("findMember",map);
	}

	public String searchMember(String name, String ssn1, String ssn2, String id) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("name", name);
		map.put("ssn1", ssn1);
		map.put("ssn2", ssn2);
		if(id != null) {
			map.put("id", id);
		}
		String msg = null;
		if(map.get("id")!=null) {
			msg = sqlSession.selectOne("searchMember_pw",map);
		}
		else {
			msg = sqlSession.selectOne("searchMember_id",map);
		}
		return msg;
	}
	
}













