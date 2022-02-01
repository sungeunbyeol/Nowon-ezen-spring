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
	public int insertUser(UserDTO udto) {
		return sqlSession.insert("insertUser", udto);
	}
	
	//email �޾����� ��й�ȣ ã�� �ȹ޾����� emailã�� �̹Ƿ� if������ ������� xml�� �����ش�. 
	public String searchUserInfo(String u_name, String u_tel, String u_email) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("u_name", u_name);
		map.put("u_tel", u_tel);
		
		if(map.get("u_email") == null) {
			return sqlSession.selectOne("searchUser_email", map);
		}else {
			return sqlSession.selectOne("searchUser_password", map);
		} 
	}
	
	//email������ user�� DTO DB���� �������� 
	public UserDTO getUserByEmail(String u_email) {
		return sqlSession.selectOne("getUserByEmail", u_email);
	}
	
	//u_num������ user�� DTO DB���� ��������
	public UserDTO getUserByUnum(int u_num) {
		return sqlSession.selectOne("getUserByUnum", u_num);
	}
	
	//user ��й�ȣ ����
	public int updateUserPassword(UserDTO dto) {
		return sqlSession.update("updateUserPassword", dto); 
	}
	
	//���� ������Ʈ ��� .   AdminController
	public int addBlackList(UserDTO dto) {
		return sqlSession.update("addBlackList", dto);
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
	public List<UserDTO> listBlackUser(){
		return sqlSession.selectList("listBlackUser");
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
	public List<UserDTO> findUserOnBlack(String search, String searchString){
		//a_level�� 0�λ���� �˻�
		String sql = "select * from project_useraccount where " + search + " like '%" + searchString + "%' and a_level = '0'";
		Map<String, String> map = new Hashtable<String, String>(); 
		map.put("sql", sql);
		return sqlSession.selectList("findUserOnBlack", map);
	}
	
	public String getUserPassword(int u_num) {
		return sqlSession.selectOne("getUserPassword", u_num);
	}
	
	//ȸ��Ż�� 
	public int deleteUser(int u_num, String u_password) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("u_num", String.valueOf(u_num));
		map.put("u_password", u_password);
		return sqlSession.update("deleteUser", map);
	}	
	
	//�����ڰ� ���� ���� ����
	public int deleteUserByAdmin(int u_num) {
		return sqlSession.update("deleteUserByAdmin", u_num);
	}
	
}
