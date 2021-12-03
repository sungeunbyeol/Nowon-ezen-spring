package shop.admin;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.ProductDAO;

public class ProdDeleteAdminController implements Controller {
	private ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int pnum = ServletRequestUtils.getIntParameter(req, "pnum");
		String pimage = req.getParameter("pimage");
		int res = productDAO.deleteProduct(pnum);
		String msg = null, url = "prod_list.do";
		if (res>0) {
			String upPath = req.getServletContext().getRealPath("files");
			File file = new File(upPath, pimage);
			if (file.exists()) {
				file.delete();
				msg = "상품삭제 성공(이미지 삭제도 성공)!! 상품목록페이지로 이동합니다.";
			}else {
				msg = "상품삭제 성공(이미지 삭제는 실패)!! 상품목록페이지로 이동합니다.";
			}
		}else {
			msg = "상품삭제 실패!! 상품목록페이지로 이동합니다.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("forward:message.jsp");
	}

}














