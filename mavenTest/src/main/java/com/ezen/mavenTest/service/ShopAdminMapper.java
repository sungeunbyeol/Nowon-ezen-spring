package com.ezen.mavenTest.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.mavenTest.model.CategoryDTO;
import com.ezen.mavenTest.model.ProductDTO;

@Service
public class ShopAdminMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertCate(CategoryDTO dto) {
		return sqlSession.insert("insertCate", dto);
	}
	
	public List<CategoryDTO> listCate(){
		return sqlSession.selectList("listCate");
	}
	
	public int deleteCate(int cnum) {
		return sqlSession.delete("deleteCate", cnum);
	}
	
	public int inserProduct(MultipartHttpServletRequest mr, String pimage) {
			Map<String, String> map = new Hashtable<String, String>();
			map.put("pname",mr.getParameter("pname"));
			
			String pcategory_fk = mr.getParameter("pcategory_fk");
			String pcode = mr.getParameter("pcode");
			map.put("pcategory_fk", pcategory_fk+=pcode);
			
			map.put("pcompany", mr.getParameter("pcompany"));
			map.put("pimage", pimage);
			map.put("pqty", mr.getParameter("pqty"));
			map.put("price", mr.getParameter("price"));
			map.put("pspec", mr.getParameter("pspec"));
			map.put("pcontents", mr.getParameter("pcontents"));
			map.put("point", mr.getParameter("point"));
			
			return sqlSession.insert("insertProduct",map);
		
	}
	
	public List<ProductDTO> listProduct(){
		return sqlSession.selectList("listProduct");
	}
	
	public int deleteProduct(int pnum) {
		return sqlSession.delete("deleteProduct", pnum);
	}
	
	public ProductDTO getProduct(int pnum) {
		return sqlSession.selectOne("getProduct", pnum);
	}
	
	public int updateProduct(MultipartHttpServletRequest mr, String pimage2) {
			Map<String, String> map = new Hashtable<String, String>();
			map.put("pname", mr.getParameter("pname"));
			map.put("pcompany", mr.getParameter("pcompany"));
			map.put("pimage", pimage2);
			map.put("pqty", mr.getParameter("pqty"));
			map.put("price", mr.getParameter("price"));
			map.put("pspec", mr.getParameter("pspec"));
			map.put("pcontents", mr.getParameter("pcontents"));
			map.put("point", mr.getParameter("point"));
			map.put("pnum", mr.getParameter("pnum"));
			
			return sqlSession.update("updateProduct",map);
	}
	
	
}
