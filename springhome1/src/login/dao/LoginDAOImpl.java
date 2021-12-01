package login.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDAOImpl implements LoginOkBean {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public boolean isMemberSetting() {
		// TODO Auto-generated method stub
		return false;
	}

}
