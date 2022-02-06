package com.byeol.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.byeol.student.model.StudentDTO;
import com.byeol.student.service.StudentMapper;

@Controller
public class StudentController {

	@Autowired
	StudentMapper studentMapper;
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/student")
	public String index() {
		return "student";
	}
	
	@RequestMapping("insertStudent")
	public String insert(HttpServletRequest req, StudentDTO dto) {
		int res = studentMapper.InsertStudent(dto);
		
		if(res>0) {
			req.setAttribute("msg", "학생등록 성공!");
			req.setAttribute("url", "listStudent");
		}else {
			req.setAttribute("msg", "학생등록 실패!");
			req.setAttribute("url", "student");
		}
		
		return "message";
	}
	
	@RequestMapping("/listStudent")
	public String listStudent(HttpServletRequest req) {
		List<StudentDTO> list = studentMapper.ListStudent();
		req.setAttribute("listStudent", list);
		return "list";
	}
	
	@RequestMapping("/deleteStudent")
	public String deleteStudent(HttpServletRequest req, @RequestParam String id) {
		int res = studentMapper.deletestudent(id);
		
		if(res>0) {
			req.setAttribute("msg", "학생삭제성공!");
			req.setAttribute("url", "listStudent");
		}else {
			req.setAttribute("msg", "학생삭제실패!");
			req.setAttribute("url", "student");
		}
		return "message";
	}
	
	@RequestMapping("/findStudent")
	public String findStudent(HttpServletRequest req, @RequestParam String name) {
		StudentDTO dto = studentMapper.findstudent(name);
		req.setAttribute("studentfind", dto);
		return "find";
	}
}
