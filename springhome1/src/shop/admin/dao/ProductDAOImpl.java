package shop.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.admin.dto.ProductDTO;

public class ProductDAOImpl implements ProductDAO {
	
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
	
	@Override
	public int insertProduct(ProductDTO dto) {
		String sql = "insert into product values(prod_seq.nextval, ?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] values = new Object[] {dto.getPname(), dto.getPcategory_fk(), dto.getPcompany(), dto.getPimage(), 
				dto.getPqty(), dto.getPrice(), dto.getPspec(), dto.getPcontents(), dto.getPoint()};
		return jdbcTemplate.update(sql, values);
	}

	@Override
	public List<ProductDTO> listProduct() {
		String sql = "select * from product";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public ProductDTO getProduct(int pnum) {
		String sql = "select * from product where pnum = ?";
		return jdbcTemplate.queryForObject(sql, mapper, pnum);
	}

	@Override
	public int deleteProduct(int pnum) {
		String sql = "delete from  product where pnum = ?";
		return jdbcTemplate.update(sql, pnum);
	}

	@Override
	public int updateProduct(ProductDTO dto) {
		String sql = "update product set pname=?, pcompany=?, pimage=?, "
				+ "pqty=?, price=?, pspec=?, pcontents=?, point=? where pnum=?";
		Object[] values = new Object[] {dto.getPname(), dto.getPcompany(), dto.getPimage(), dto.getPqty(),
				dto.getPrice(), dto.getPspec(), dto.getPcontents(), dto.getPoint(), dto.getPnum()};
		return jdbcTemplate.update(sql, values);
	}

}














