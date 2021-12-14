package com.ezen.mavenTest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.ezen.mavenTest.model.MemberDTO;
import com.ezen.mavenTest.service.LoginMapper;

@Controller
public class LoginOkBean {
	private int no;
	private String name;
	private String id;
	private String passwd;
	private String ssn1;
	private String ssn2;
	private String email;
	private String hp1;
	private String hp2;
	private String hp3;
	private String joindate;
	
	@Autowired
	private LoginMapper loginMapper;
	
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getSsn1() {
		return ssn1;
	}
	public String getSsn2() {
		return ssn2;
	}
	public String getEmail() {
		return email;
	}
	public String getHp1() {
		return hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public String getJoindate() {
		return joindate;
	}
	
	public void isMemberSetting() {
			MemberDTO dto = loginMapper.isMemberSetting(id);
			no = dto.getNo();
			name = dto.getName();
			id = dto.getId();
			passwd = dto.getPasswd();
			ssn1 = dto.getSsn1();
			ssn2 = dto.getSsn2();
			email = dto.getEmail();
			hp1 = dto.getHp1();
			hp2 = dto.getHp2();
			hp3 = dto.getHp3();
			joindate = dto.getJoindate();
	}
}














