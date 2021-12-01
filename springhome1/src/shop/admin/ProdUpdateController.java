package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.ProductBean;
import shop.admin.dao.ProductDAO;
import shop.admin.dto.ProductDTO;

public class ProdUpdateController implements Controller {
	
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String pnum = req.getParameter("pnum");
		ProductBean pdao = new ProductBean();
		ProductDTO dto = pdao.getProduct(Integer.parseInt(pnum));
		String upPath = req.getServletContext().getRealPath("prodImages");
		req.setAttribute("pdto", dto);
		req.setAttribute("upPath", upPath);
		return new ModelAndView("shop/admin/prod_update");
	}

}
