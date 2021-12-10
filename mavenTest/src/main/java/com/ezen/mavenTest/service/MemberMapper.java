package com.ezen.mavenTest.service;

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
	
	public List<MemberDTO> listMember(Map<String, String> map){
		List<MemberDTO> list = null;
			if (map.get("mode") == null) {
				list = sqlSession.selectList("listMember");
			}else {
				if (map.get("search") == null) {
					map.put("search", "id");
					map.put("searchString", "");
				}
			}
			return sqlSession.selectList("findMember", map);
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

	
}













