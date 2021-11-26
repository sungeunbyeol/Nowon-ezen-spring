package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import test.dao.StudentDAO;

public class DeleteStudentController implements Controller {
	private StudentDAO studentDAO;
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		int res = studentDAO.deleteStudent(id);
		ModelAndView mav = new ModelAndView();
		if (res>0) {
			mav.addObject("msg", "�л���������!! �л������������ �̵��մϴ�.");
			mav.addObject("url", "listStudent.do");
		}else {
			mav.addObject("msg", "�л���������!! �л������������� �̵��մϴ�.");
			mav.addObject("url", "student.do");
		}
		mav.setViewName("message");
		return mav;
	}

}
