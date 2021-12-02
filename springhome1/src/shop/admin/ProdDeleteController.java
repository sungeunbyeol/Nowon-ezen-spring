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
				req.setAttribute("msg", "상품삭제 성공!!(이미지까지 삭제됨) 상품목록페이지로 이동합니다.");
				req.setAttribute("url", "shop/admin/prod_list");
			}else {
				req.setAttribute("msg", "상품삭제 성공!!(이미지삭제 실패!!) 상품목록페이지로 이동합니다.");
				req.setAttribute("url", "shop.admin/prod_list");
			}
		}else {
			req.setAttribute("msg", "상품삭제 실패!! 상품목록페이지로 이동합니다.");
			req.setAttribute("url", "shop.admin/prod_list");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
