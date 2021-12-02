package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryDAO;

public class CateDeleteAdminController implements Controller {

	private CategoryDAO categoryDAO;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int cnum = ServletRequestUtils.getIntParameter(req, "cnum");
		int res = categoryDAO.deleteCate(cnum);
		if (res>0) {
			req.setAttribute("msg", "카테고리 삭제 성공!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "카테고리 삭제 실패!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
