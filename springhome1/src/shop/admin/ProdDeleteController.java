package shop.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.ProductBean;
import shop.admin.dao.ProductDAO;

public class ProdDeleteController implements Controller {
	
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String pnum = req.getParameter("pnum");
		String pimage = req.getParameter("pimage");
		int res = productDAO.deleteProduct(Integer.parseInt(pnum));
		if (res>0){
			String upPath = req.getServletContext().getRealPath("prodImages");
			File file = new File(upPath, pimage);
			if (file.exists()){
				file.delete();
				req.setAttribute("msg", "��ǰ���� ����!!(�̹������� ������) ��ǰ����������� �̵��մϴ�.");
				req.setAttribute("url", "shop/admin/prod_list");
			}else {
				req.setAttribute("msg", "��ǰ���� ����!!(�̹������� ����!!) ��ǰ����������� �̵��մϴ�.");
				req.setAttribute("url", "shop.admin/prod_list");
			}
		}else {
			req.setAttribute("msg", "��ǰ���� ����!! ��ǰ����������� �̵��մϴ�.");
			req.setAttribute("url", "shop.admin/prod_list");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
