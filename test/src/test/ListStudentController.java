package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import test.dao.StudentDAO;
import test.dto.StudentDTO;

public class ListStudentController implements Controller {
	private StudentDAO studentDAO;
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<StudentDTO> list = studentDAO.listStudent();
		
		ModelAndView mav = new ModelAndView("list", "listStudent", list);//nextPage, request¿« key, request¿« value
		return mav;
	}

}
