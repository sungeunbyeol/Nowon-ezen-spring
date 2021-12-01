package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDBBean;

public class BoardDataBean implements BoardDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class MyRowMapper implements RowMapper<BoardDBBean>{
		@Override
		public BoardDBBean mapRow(ResultSet rs, int arg1) throws SQLException {
			BoardDBBean dto = new BoardDBBean();
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			dto.setRe_step(rs.getInt("re_step"));
			dto.setRe_level(rs.getInt("re_level"));
			return dto;
		}
	}
	
	private MyRowMapper mapper = new MyRowMapper();
	
	@Override
	public List<BoardDBBean> listBoard(int start, int end) {
		String sql = "select * from "
				+ "(select rownum rn, A.* from "
				+ "(select * from board order by re_step asc)A)"
				+ " where rn between ? and ?";
		Object[] values = new Object[] {start, end};
		List<BoardDBBean> list = jdbcTemplate.query(sql, values, mapper);
		return list;
	}

	@Override
	public BoardDBBean getBoard(int num, String mode) {
		String sql = null;
		if (mode.equals("content")) {
			sql = "update board set readcount = readcount + 1 where num = ?";
			jdbcTemplate.update(sql, num);
		}
		sql = "select * from board where num = ?";
		BoardDBBean dto = jdbcTemplate.queryForObject(sql, mapper, num);
		return dto;
	}

	@Override
	public int insertBoard(BoardDBBean dto) {
		String sql = "";
		if (dto.getNum() == 0) {
			sql = "update board set re_step = re_step + 1";
		}else {
			sql = "update board set re_step = re_step + 1 where re_step>" + dto.getRe_step();
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
		}
		jdbcTemplate.update(sql);
		
		sql = "insert into board values(board_seq.nextval, ?,?,?,?,sysdate,0,?,?,?,?)";
		Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), dto.getSubject(), dto.getPasswd(),
				dto.getContent(), dto.getIp(), dto.getRe_step(), dto.getRe_level()};
		int res = jdbcTemplate.update(sql, values);
		return res;

	}

	protected boolean isPassword(int num, String passwd) {
		BoardDBBean dto = getBoard(num, "password");
		if (dto.getPasswd().equals(passwd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int deleteBoard(int num, String passwd) {
		if (!isPassword(num, passwd)) {
			return -1;
		}
		String sql = "delete from board where num = ?";
		int res = jdbcTemplate.update(sql, num);
		return res;
	}

	@Override
	   public int updateBoard(BoardDBBean dto) {
	      if (!isPassword(dto.getNum(), dto.getPasswd())) {
	         return -1;
	      }
	      String sql = "update board set writer=?, email=?, subject=?, content=? where num = ?";
	      Object[] values = new Object[] 
	            {dto.getWriter(), dto.getEmail(), dto.getSubject(), dto.getContent(), dto.getNum()};
	      int res = jdbcTemplate.update(sql, values);
	      return res;
	   }

	@Override
	public int getCount() {
		String sql = "select count(*) from board";
		int res = jdbcTemplate.queryForInt(sql);
		return res;
	}

}






