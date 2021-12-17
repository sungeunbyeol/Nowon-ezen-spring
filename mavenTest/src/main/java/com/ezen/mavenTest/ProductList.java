package com.ezen.mavenTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ezen.mavenTest.model.ProductDTO;

public class ProductList {
	
	Hashtable<String, List<ProductDTO>> ht = new Hashtable<>();
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class MyRowMapper implements RowMapper<ProductDTO>{
		@Override
		public ProductDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			ProductDTO dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setPname(rs.getString("pname"));
			dto.setPcategory_fk(rs.getString("pcategory_fk"));
			dto.setPcompany(rs.getString("pcompany"));
			dto.setPimage(rs.getString("pimage"));
			dto.setPqty(rs.getInt("pqty"));
			dto.setPrice(rs.getInt("price"));
			dto.setPspec(rs.getString("pspec"));
			dto.setPcontents(rs.getString("pcontents"));
			dto.setPoint(rs.getInt("point"));
			dto.setPinputdate(rs.getString("pinputdate"));
			return dto;
		}
	}
	
	private MyRowMapper mapper = new MyRowMapper();
	
	public List<ProductDTO> selectBySpec(String pspec) throws SQLException {
		String sql = "select * from product where pspec = ?";
		List<ProductDTO> list = jdbcTemplate.query(sql, mapper, pspec);
		ht.put(pspec, list);
		return list;
	}
		
	public List<ProductDTO> selectByCode(String code) throws SQLException {
		String sql = "select * from product where pcategory_fk like ?";
		List<ProductDTO> list = jdbcTemplate.query(sql, mapper, code+"%");
		ht.put(code, list);
		return list;
	}
	
	public ProductDTO getProduct(int pnum, String select) {
		List<ProductDTO> list = ht.get(select);
		for(ProductDTO dto : list) {
			if (dto.getPnum() == pnum) {
				return dto;
			}
		}
		return null;
	}
}















