package com.eunbyeol.individaul;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eunbyeol.individaul.service.UserMapper;

@Controller
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value="/")
	public ModelAndView Index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/index")
	public String Main() {
		return "index";
	}

	//ȸ������ ��������
	@RequestMapping("/joinform")
	public String Joinform() {
		return "user/joinform";
	}
	
	//�̸��� �ߺ�üũ 
	@RequestMapping("/checkUseremail")
	public String CheckUserEmail(HttpServletRequest req, Map<String, String> map) {
		
		if(map.equals("")) {
			req.setAttribute("msg", "�̸����� �Է����ּ���");
			req.setAttribute("url", "joinform");
			return "message";
		}
		boolean isUser = 
		
		return "message";
	}
	
	//�̸��� �ߺ�üũ �ϰ� �Ѿ�� ȸ������ ������
	@RequestMapping("/joincheck")
	public String JoinCheck() {
		return "user/joincheck";
	}
	
	//ȸ�������� �����ϸ�
	@RequestMapping("/userjoinok")
	public String Joincheck() {
		return "user/loginform";
	}

	//�α��� ������
	@RequestMapping("/loginform")
	public String Loginform() {
		return "user/loginform";
	}
	
	//QnA������
	@RequestMapping("/qnaboard")
	public String Qnaboard() {
		return "user/qnaboard";
	}
	
}
