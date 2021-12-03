package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.ProductDAO;
import shop.admin.dto.ProductDTO;

public class ProdUpdateAdminController implements Controller {
	private ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int pnum = ServletRequestUtils.getIntParameter(arg0, "pnum");
		ProductDTO dto = productDAO.getProduct(pnum);
		arg0.setAttribute("upPath", arg0.getServletContext().getRealPath("files"));
		return new ModelAndView("shop/admin/prod_update", "getProduct", dto);
	}

}
