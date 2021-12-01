package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryBean;
import shop.admin.dao.CategoryDAO;
import shop.admin.dto.CategoryDTO;

public class CateInputOkController extends AbstractCommandController {
	
	private CategoryDAO categoryDAO;
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		CategoryDTO dto = new CategoryDTO();
		dto.setCode(req.getParameter("code"));
		dto.setCname(req.getParameter("cname"));
		CategoryBean dao = new CategoryBean();
		int res = dao.insertCate(dto);
		if (res>0) {
			req.setAttribute("msg", "카테고리등록성공!! 카테고리목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "카테고리등록실패!! 카테고리등록페이지로 이동합니다.");
			req.setAttribute("url", "cate_input.do");
		}
		return new ModelAndView("forward:message.jsp");
	}
}
