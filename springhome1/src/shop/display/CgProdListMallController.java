package shop.display;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryDAO;
import shop.admin.dto.CategoryDTO;
import shop.admin.dto.ProductDTO;

public class CgProdListMallController implements Controller {

	private CategoryDAO categoryDAO;
	private ProductList productList;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	public void setProductList(ProductList productList) {
		this.productList = productList;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		List<CategoryDTO> list = categoryDAO.listCate();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		String code = req.getParameter("code");
		List<ProductDTO> plist = productList.selectByCode(code);
		req.setAttribute("code", plist);
		return new ModelAndView("shop/display/mall_cgProdList");
	}

}








