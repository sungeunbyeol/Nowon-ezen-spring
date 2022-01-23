package com.eunbyeol.individaul;

import org.springframework.beans.factory.annotation.Autowired;

import com.eunbyeol.individaul.model.UserListDTO;
import com.eunbyeol.individaul.service.LoginMapper;

public class LoginOkBean {
	private int user_no;
	private String name;
	private String email;
	private String password;
	private String birthday;
	private String tel;
	private String address;
	private String nickname;
	private String joindate;
	private String user_type;
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	@Autowired
	LoginMapper loginMapper;
	
	public boolean isUserSetting() {
		UserListDTO dto = loginMapper.checkLogin(email);
		if(dto != null) {
			user_no = dto.getUser_no();
			name = dto.getName();
			email = dto.getEmail();
			password = dto.getPassword();
			birthday = dto.getBirthday();
			tel = dto.getTel();
			address = dto.getAddress();
			nickname = dto.getNickname();
			joindate = dto.getJoindate();
			user_type = dto.getUser_type();
			
			return true;
		}
		return false;
	}
}
