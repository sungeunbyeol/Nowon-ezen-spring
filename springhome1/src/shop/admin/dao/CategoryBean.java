package shop.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import member.dto.MemberDTO;
import shop.admin.dto.CategoryDTO;

public class CategoryBean implements CategoryDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public int insertCate(CategoryDTO dto) {
		String sql = "insert into category values(cate_seq.nextval, ?, ?)";
		Object[] values = new Object[] {dto.getCode(), dto.getCname()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	class MyRowMapper implements RowMapper<CategoryDTO>{
		public CategoryDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			CategoryDTO dto = new CategoryDTO();
			dto.setCname(rs.getString("cname"));
			dto.setCnum(rs.getInt("cnum"));
			dto.setCode(rs.getString("code"));
			return dto;
		}
	}
	
	private MyRowMapper mapper = new MyRowMapper();
	@Override
	public List<CategoryDTO> listCate() {
		String sql = "select * from category order by cnum asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public int deleteCate(int cnum) {
		String sql = "delete from category where cnum = ?";
		return jdbcTemplate.update(sql, cnum);
	}

}
