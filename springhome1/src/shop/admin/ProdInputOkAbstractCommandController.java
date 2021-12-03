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
import shop.admin.dto.ProductDTO;

public class ProdInputOkAbstractCommandController extends AbstractCommandController{
	
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		ProductDTO dto = (ProductDTO)arg2;
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		MultipartFile mf = mr.getFile("pimage");
		String filename = mf.getOriginalFilename();
		if(filename != null && !filename.trim().equals("")) {
			String upPath = req.getServletContext().getRealPath("files");
			File file = new File(upPath, filename);
			mf.transferTo(file);
		}
		
		String pcategory_fk = req.getParameter("pcategory_fk");
		String pcode = req.getParameter("pcode");
		dto.setPcategory_fk(pcategory_fk+pcode);
		dto.setPimage(filename);
		
		int res = productDAO.insertProduct(dto); 
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