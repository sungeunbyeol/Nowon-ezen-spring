package shop.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.ProductDAO;
import shop.admin.dto.ProductDTO;

public class ProdListAdminController implements Controller {
	private ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<ProductDTO> list = productDAO.listProduct();
		arg0.setAttribute("upPath", arg0.getServletContext().getRealPath("files"));
		return new ModelAndView("shop/admin/prod_list", "listProd", list);
	}

}
