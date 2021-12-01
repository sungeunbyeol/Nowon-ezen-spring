package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import shop.admin.dao.ProductBean;
import shop.admin.dao.ProductDAO;

public class ProdUpdateOkController extends AbstractCommandController {
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		MultipartRequest mr = null;
		String upPath = req.getServletContext().getRealPath("prodImages");
		int len = 20*1024*1024;
		req.setAttribute("msg", "파일 전송 중 오류 발생!!");
		req.setAttribute("url", "prod_update.shop?pnum=" + mr.getParameter("pnum"));	
		return new ModelAndView("redirect:message.jsp");
		ProductBean pdao = new ProductBean();
		int res = pdao.updateProduct(mr);
		if (res>0){
			req.setAttribute("msg","상품수정성공!! 상품목록페이지로 이동합니다.");
			req.setAttribute("url","prod_list.shop");					
		}else {
			msg = "상품수정실패!! 상품수정페이지로 이동합니다.";
			url = "prod_update.shop?pnum=" + mr.getParameter("pnum");					
		}
		return new ModelAndView("forward:message.jsp");
	}

}
