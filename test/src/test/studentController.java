package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import test.dao.StudentDAO;
import test.dto.StudentDTO;
import test.mybatis.StudentMapper;

@Controller
public class studentController {

	/*
	private StudentDAO studentDAO;
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	*/
	
	@RequestMapping("/listStudent.do")
	public ModelAndView listStrudent() {
		//List<StudentDTO> list = studentDAO.listStudent();
		List<StudentDTO> list = StudentMapper.listStudent();
		ModelAndView mav = new ModelAndView("list", "listStudent", list);	
		return mav;
	}
	
	@RequestMapping("/student.do")
	public String indexStudent() {
		return "student";
	}
	
	@RequestMapping("/insertStudent.do")
	public String insertStudent(HttpServletRequest req, StudentDTO dto) {
		//int res = studentDAO.insertStudent(dto);
		int res = StudentMapper.insertStudent(dto);
		if (res>0) {
			req.setAttribute("msg", "학생등록성공!! 학생목록페이지로 이동합니다.");
			req.setAttribute("url", "listStudent.do");
		}else {
			req.setAttribute("msg", "학생등록실패!! 학생등록페이지로 이동합니다.");
			req.setAttribute("url", "student.do");
		}
		return "message";
	}
	
	
	@RequestMapping("/deleteStudent.do")
	public String deleteStudent(HttpServletRequest req, @RequestParam String id) {
		//int res = studentDAO.deleteStudent(id);
		int res = StudentMapper.deleteStudent(id);
		if (res>0) {
			req.setAttribute("msg", "학생삭제성공!! 학생목록페이지로 이동합니다.");
			req.setAttribute("url", "listStudent.do");
		}else {
			req.setAttribute("msg", "학생삭제실패!! 학생등록페이지로 이동합니다.");
			req.setAttribute("url", "student.do");
		}
		return "message";
	}
	
	@RequestMapping("/findStudent.do")
	public ModelAndView findStrudent(@RequestParam String name) {
		//List<StudentDTO> find = studentDAO.findStudent(name);
		List<StudentDTO> find = StudentMapper.findStudent(name);
		ModelAndView mav = new ModelAndView("list", "listStudent", find);	
		return mav;
	}
	
}
/*
 * RequestMapping을 통한 annotation에서 
 * 메소드의 반환형은 String, Map, Model, ModelAndView, View객체, void
*/