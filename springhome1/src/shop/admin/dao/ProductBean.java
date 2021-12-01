package shop.admin.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartRequest;

import shop.admin.dto.ProductDTO;

public class ProductBean implements ProductDAO {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insertProduct(MultipartRequest mr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductDTO> listProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduct(int pnum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductDTO getProduct(int pnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProduct(MultipartRequest mr) {
		// TODO Auto-generated method stub
		return 0;
	}

}
