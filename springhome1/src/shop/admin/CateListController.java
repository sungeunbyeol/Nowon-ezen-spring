package shop.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import shop.admin.dao.CategoryBean;
import shop.admin.dao.CategoryDAO;
import shop.admin.dto.CategoryDTO;

public class CateListController implements Controller {
	private CategoryDAO categoryDAO;
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		CategoryBean dao = new CategoryBean();
		List<CategoryDTO> list = dao.listCate();
		req.setAttribute("cateList", list);
		return new ModelAndView("shop/admin/cate_list");
	}

}
