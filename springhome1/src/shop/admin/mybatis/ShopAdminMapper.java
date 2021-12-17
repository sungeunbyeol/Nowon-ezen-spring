package shop.admin.mybatis;

import java.io.*;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import shop.admin.dto.CategoryDTO;
import shop.admin.dto.ProductDTO;

public class ShopAdminMapper {

	private static SqlSessionFactory sqlMapper;
	
	static {  
		try {
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("Configuration.xml 파일을 빌드하는데 실패하였습니다.");
		}
	}
	
	public static int insertCate(CategoryDTO dto) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			int res = sqlSession.insert("insertCate", dto);
			sqlSession.commit();
			return res;
		}
		finally {
			sqlSession.close();
		}
	}
	
	public static List<CategoryDTO> listCate(){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			List<CategoryDTO> list = sqlSession.selectList("listCate");
			return list;
		}
		finally {
			sqlSession.close();
		}
	}
	
	public static int deleteCate(int cnum) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			int res = sqlSession.delete("deleteCate", cnum);
			sqlSession.commit();
			return res;
		}
		finally {
			sqlSession.close();
		}
	}
	
	public static int inserProduct(MultipartHttpServletRequest mr, String pimage) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
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
			
			int res = sqlSession.insert("insertProduct",map);
			sqlSession.commit();
			return res;
		}
		finally {
			sqlSession.close();
		}	
	}
	
	public static List<ProductDTO> listProduct(){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			List<ProductDTO> list = sqlSession.selectList("listProduct");
			return list;
		}
		finally {
			sqlSession.close();
		}
	}
	
	public static int deleteProduct(int pnum) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			int res = sqlSession.delete("deleteProduct", pnum);
			sqlSession.commit();
			return res;
		}
		finally {
			sqlSession.close();
		}
	}
	
	public static ProductDTO getProduct(int pnum) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			ProductDTO dto = sqlSession.selectOne("getProduct", pnum);
			return dto;
		}
		finally {
			sqlSession.close();
		}
	}
	
	public static int updateProduct(MultipartHttpServletRequest mr, String pimage2) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
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
			
			int res = sqlSession.update("updateProduct",map);
			sqlSession.commit();
			return res;
		}
		finally {
			sqlSession.close();
		}
	}
	
	
}
