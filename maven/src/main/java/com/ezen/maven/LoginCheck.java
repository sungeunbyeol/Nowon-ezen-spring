package com.ezen.maven;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.ezen.maven.model.MemberDTO;

@Controller
public class LoginCheck {

	public static final int OK = 0;
	public static final int NOT_ID = 1;
	public static final int NOT_PWD = 2;
	public static final int ERROR = -1;
	
	private String id;
	private String passwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int checkLogin() {
		String sql = "select * from jspmember where id = ?";
		RowMapper<MemberDTO> mapper = new RowMapper<MemberDTO>(){
			@Override
			public MemberDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setSsn1(rs.getString("ssn1"));
				dto.setSsn2(rs.getString("ssn2"));
				dto.setEmail(rs.getString("email"));
				dto.setHp1(rs.getString("hp1"));
				dto.setHp2(rs.getString("hp2"));
				dto.setHp3(rs.getString("hp3"));
				dto.setJoindate(rs.getString("joindate"));
				return dto;
			}
		};	
		try {
			MemberDTO dto = jdbcTemplate.queryForObject(sql, mapper, id);
			if (dto.getPasswd().equals(passwd)) {
				return OK;
			}else {
				return NOT_PWD;
			}
		}catch(EmptyResultDataAccessException e) {
			return NOT_ID;
		}
	}
}
















