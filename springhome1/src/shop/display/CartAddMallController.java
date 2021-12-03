package shop.display;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dto.ProductDTO;

public class CartAddMallController implements Controller {

	private CartBean cartBean;
	private ProductList productList;
	
	public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}
	public void setProductList(ProductList productList) {
		this.productList = productList;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int pnum = ServletRequestUtils.getIntParameter(req, "pnum");
		String select = req.getParameter("select");
		ProductDTO dto = productList.getProduct(pnum, select);
		dto.setPqty(ServletRequestUtils.getIntParameter(req, "qty"));
		boolean addCart = cartBean.addCart(pnum, dto);
		return new ModelAndView("redirect:cartList.do");
	}
}
