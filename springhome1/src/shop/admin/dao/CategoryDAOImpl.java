package shop.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.admin.dto.CategoryDTO;

public class CategoryDAOImpl implements CategoryDAO {
	
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

	@Override
	public List<CategoryDTO> listCate() {
		String sql = "select * from category";
		RowMapper mapper = new RowMapper<CategoryDTO>() {
			@Override
			public CategoryDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				CategoryDTO dto = new CategoryDTO();
				dto.setCnum(rs.getInt("cnum"));
				dto.setCode(rs.getString("code"));
				dto.setCname(rs.getString("cname"));
				return dto;
			}
		};
		List<CategoryDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public int deleteCate(int cnum) {
		String sql = "delete from category where cnum = ?";
		int res = jdbcTemplate.update(sql, cnum);
		return res;
	}

}





