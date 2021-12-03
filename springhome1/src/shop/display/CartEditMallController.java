package shop.display;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CartEditMallController implements Controller {
	
	private CartBean cartBean;
		
	public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int pnum = ServletRequestUtils.getIntParameter(req, "pnum");
		int pqty = ServletRequestUtils.getIntParameter(req, "pqty");
		cartBean.editCart(pnum, pqty);
		
		return new ModelAndView("redirect:cartList.do");
	}

}
