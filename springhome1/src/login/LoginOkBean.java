package login;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import member.dto.MemberDTO;

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
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean isMemberSetting() {
		String sql = "select * from jspmember where id = ?";
		RowMapper<MemberDTO> mapper = new RowMapper<MemberDTO>(){
			@Override
			public MemberDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDTO dto = new MemberDTO();
				no = rs.getInt("no");
				name = rs.getString("name");
				//id = rs.getString("id");
				passwd = rs.getString("passwd");
				ssn1 = rs.getString("ssn1");
				ssn2 = rs.getString("ssn2");
				email = rs.getString("email");
				hp1 = rs.getString("hp1");
				hp2 = rs.getString("hp2");
				hp3 = rs.getString("hp3");
				joindate = rs.getString("joindate");
				return dto;
			}
		};	
		try {
			MemberDTO dto = jdbcTemplate.queryForObject(sql, mapper, id);
			return true;
		}catch(EmptyResultDataAccessException e) {
			return false;
		}	
	}
}














