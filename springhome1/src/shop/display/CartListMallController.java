package shop.display;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryDAO;
import shop.admin.dto.CategoryDTO;
import shop.admin.dto.ProductDTO;

public class CartListMallController implements Controller {

	private CartBean cartBean;
	private CategoryDAO categoryDAO;
	
	public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		Hashtable<Integer, ProductDTO> ht = cartBean.listCart();
		List<CategoryDTO> list = categoryDAO.listCate();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		return new ModelAndView("shop/display/mall_cartList", "cartList", ht);
	}

}
