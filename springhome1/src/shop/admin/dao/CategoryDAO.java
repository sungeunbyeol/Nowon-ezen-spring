package shop.admin.dao;

import java.util.List;

import shop.admin.dto.CategoryDTO;

public interface CategoryDAO {
	public int insertCate(CategoryDTO dto);
	public List<CategoryDTO> listCate();
	public int deleteCate(int cnum);
}
