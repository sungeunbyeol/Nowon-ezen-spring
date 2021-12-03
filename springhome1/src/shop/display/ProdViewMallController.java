package shop.display;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryDAO;
import shop.admin.dto.CategoryDTO;
import shop.admin.dto.ProductDTO;

public class ProdViewMallController implements Controller {

	private CategoryDAO categoryDAO;
	private ProductList productList;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	public void setProductList(ProductList productList) {
		this.productList = productList;
	}
	
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		List<CategoryDTO> list = categoryDAO.listCate();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		int pnum = ServletRequestUtils.getIntParameter(req, "pnum");
		String select = req.getParameter("select");
		ProductDTO dto = productList.getProduct(pnum, select);
		
		return new ModelAndView("shop/display/mall_prodView", "getProduct", dto);
	}

}











