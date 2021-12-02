package shop.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import shop.admin.dao.ProductDAO;

public class ProdInputOkController extends AbstractCommandController{
	
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		MultipartFile mf = mr.getFile("pimage");
		String pimage = mf.getOriginalFilename();
		
		String upPath = req.getServletContext().getRealPath("prodImages");
		File file = new File(upPath, pimage);
		mf.transferTo(file);
		int len = 20*1024*1024;
		
		if(pimage == null || pimage.trim().equals("")) {
			req.setAttribute("msg", "파일 전송 중 오류 발생!!");
			req.setAttribute("url", "prod_input.shop");
		}
		
		int res = productDAO.insertProduct(mr); 
		if (res>0){
			req.setAttribute("msg","상품등록성공!! 상품목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");		
		}else {
			req.setAttribute("msg","상품등록실패!! 상품등록페이지로 이동합니다.");
			req.setAttribute("url", "prod_input.do");						
		}
		return new ModelAndView("forward:message.jsp");
	}

}
