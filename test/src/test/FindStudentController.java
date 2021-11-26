package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import test.dao.StudentDAO;
import test.dto.StudentDTO;

public class FindStudentController implements Controller {
	private StudentDAO studentDAO;
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String name = req.getParameter("name");
		List<StudentDTO> find = studentDAO.findStudent(name);
		ModelAndView mav = new ModelAndView("list", "listStudent", find);
		return mav;
	}

}
