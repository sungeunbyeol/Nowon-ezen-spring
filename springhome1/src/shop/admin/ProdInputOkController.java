package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.ProductBean;
import shop.admin.dao.ProductDAO;

public class ProdInputOkController extends AbstractCommandController{
	
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
		mr = new MultipartRequest(req, upPath, len, "EUC-KR");
		req.setAttribute("msg", "���� ���� �� ���� �߻�!!");
		req.setAttribute("url", "prod_input.shop");
		return new ModelAndView("forward:message.jsp");
		
		ProductBean pdao = new ProductBean();
		int res = pdao.insertProduct(mr);
		if (res>0){
			req.setAttribute("msg","��ǰ��ϼ���!! ��ǰ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_list.do");		
		}else {
			req.setAttribute("msg","��ǰ��Ͻ���!! ��ǰ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_input.do");						
		}
		return new ModelAndView("forward:message.jsp");
	}

}
