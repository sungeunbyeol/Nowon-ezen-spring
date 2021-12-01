package shop.admin.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartRequest;

import shop.admin.dto.ProductDTO;

public interface ProductDAO {
	public int insertProduct(MultipartRequest mr);
	public List<ProductDTO> listProduct();
	public int deleteProduct(int pnum);
	public ProductDTO getProduct(int pnum);
	public int updateProduct(MultipartRequest mr);
	
}
