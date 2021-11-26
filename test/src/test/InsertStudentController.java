package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import test.dao.StudentDAO;
import test.dto.StudentDTO;

public class InsertStudentController extends AbstractCommandController{
	private StudentDAO studentDAO;
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		StudentDTO dto = (StudentDTO)arg2;
		int res = studentDAO.insertStudent(dto);
		ModelAndView mav = new ModelAndView();
		if (res>0) {
			mav.addObject("msg", "학생등록성공!! 학생목록페이지로 이동합니다.");
			mav.addObject("url", "listStudent.do");
		}else {
			mav.addObject("msg", "학생등록실패!! 학생등록페이지로 이동합니다.");
			mav.addObject("url", "student.do");
		}
		mav.setViewName("message");
		return mav;
	}
}







