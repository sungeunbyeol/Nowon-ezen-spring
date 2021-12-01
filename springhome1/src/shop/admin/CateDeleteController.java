package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryBean;
import shop.admin.dao.CategoryDAO;

public class CateDeleteController implements Controller {
	
	private CategoryDAO categoryDAO;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String cnum = req.getParameter("cnum");
		CategoryBean dao = new CategoryBean();
		int res = dao.deleteCate(Integer.parseInt(cnum));
		if (res>0) {
			req.setAttribute("msg", "카테고리삭제성공!! 카테고리목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.shop");
		}else {
			req.setAttribute("msg", "카테고리삭제실패!! 카테고리목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.shop");
		}
		return new ModelAndView("forward:message.jsp");
	}
}
