package com.ezen.project.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.UserDTO;

@Service
public class UserMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	//�������
	public int insertUser(UserDTO dto) {
		return sqlSession.insert("insertUser", dto);
	}
	
	//���� ������Ʈ 
	public int updateUser(UserDTO dto) {
		return sqlSession.update("updateUser", dto);
	}
	
	//email �޾����� ��й�ȣ ã�� �ȹ޾����� emailã�� �̹Ƿ� if������ ������� xml�� �����ش�. 
	public String searchUser(String u_name, String u_tel, String u_email) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("u_name", u_name);
		map.put("u_tel", u_tel);
		 
		String msg = null; 
				
		if(map.get("u_email") != null) {
			msg = sqlSession.selectOne("searchUser_password", map);
		}else {
			msg = sqlSession.selectOne("searchUser_email", map);
		} 
		return msg;
	}
	
	//email������ user�� DTO DB���� �������� 
	public UserDTO getUser(String u_email) {
		return sqlSession.selectOne("getUser", u_email);
	}
	
	//u_num������ user�� DTO DB���� ��������
	public UserDTO getUserNum(int u_num) {
		return sqlSession.selectOne("getUserNum", u_num);
	}
	
	//user ��й�ȣ ����
	public int updateUserPassword(UserDTO dto) {
		return sqlSession.update("updateUserPassword", dto); 
	}
	
	//���� ������Ʈ ��� .   AdminController
	public int registBlackList(UserDTO dto) {
		return sqlSession.update("registBlackList", dto);
	}
	
	public int deleteBlackList(UserDTO dto) {
		return sqlSession.update("deleteBlackList", dto);
	}
	
	//������Ʈ ���� ����. AdminController
	public int saveBlackContent(UserDTO dto) {
		return sqlSession.update("saveBlackContent", dto);
	}
	
	//���� ����Ʈ �̱�  AdminController
	public List<UserDTO> listUser(){
		return sqlSession.selectList("listUser");
	}
	
	//������Ʈ �̱�  AdminController
	public List<UserDTO> blacklistUser(){
		return sqlSession.selectList("blacklistUser");
	} 
	 
	//list���� ���� �˻�  AdminController
	public List<UserDTO> findUser(String search, String searchString){
		//ã�� ����߿��� a_level �� 0 or 1�λ���鸸 �˻�
		String sql = "select * from project_useraccount where " + search + " like '%" + searchString + "%' and a_level between '0' and '1' "; 
		Map<String, String> map = new Hashtable<String, String>();  
		map.put("sql", sql); 
		return sqlSession.selectList("findUser", map);
	} 
	
	//������Ʈ���� ���� �˻�  AdminController
	public List<UserDTO> findBlackUser(String search, String searchString){
		//a_level�� 0�λ���� �˻�
		String sql = "select * from project_useraccount where " + search + " like '%" + searchString + "%' and a_level = '0'";
		Map<String, String> map = new Hashtable<String, String>(); 
		map.put("sql", sql);
		return sqlSession.selectList("findBlackUser", map);
	}
	
	//ȸ��Ż�� 
	public int UserdeleteUser(String u_num, String u_password) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("u_num", u_num);
		map.put("u_password", u_password);
		return sqlSession.update("UserdeleteUser",map);
	}	
	
	//�����ڰ� ���� ���� ����  AdminController
	public int AdmindeleteUser(int u_num) {
		return sqlSession.update("AdmindeleteUser", u_num);
	}
	
	//���Ե� �����ΰ� Ȯ���ϰ� dto ���� email�� ��ġ�ϴ°� �ִٸ� dto�� �����´�. 
	public boolean isCheckUser(String u_email) {
		UserDTO dto = sqlSession.selectOne("checkUser", u_email);
		if(dto!= null) {
			return true;
		}else {
			return false;
		}
	}
	 
}
