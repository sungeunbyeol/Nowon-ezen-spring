package shop.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import shop.admin.dao.CategoryDAO;
import shop.admin.dto.CategoryDTO;

public class CateInputOkAdminController extends AbstractCommandController {
	
	private CategoryDAO categoryDAO;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object arg2, BindException arg3)
			throws Exception {
		CategoryDTO dto = (CategoryDTO)arg2;
		int res = categoryDAO.insertCate(dto);
		if (res>0) {
			req.setAttribute("msg", "ī�װ� ��� ����!! ī�װ� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "ī�װ� ��� ����!! ī�װ� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_input.do");
		}
		return new ModelAndView("forward:message.jsp");
	}
}
