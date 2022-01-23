package com.eunbyeol.individaul;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eunbyeol.individaul.model.UserListDTO;
import com.eunbyeol.individaul.service.UserMapper;

@Controller
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	//�̸��� �ߺ�üũ 
	@RequestMapping("/checkUseremail")
	public String CheckUserEmail(HttpServletRequest req, @RequestParam String email, String password,
			String password2, String name, String nickname, String tel, String birthday) {
		
		if(email.equals("")) {
			req.setAttribute("msg", "�̸����� �Է����ּ���");
			req.setAttribute("url", "joinform");
			return "message";
		}
		boolean checkuser = userMapper.isUserCheck(email);
		if(checkuser) {
			req.setAttribute("msg", "�ߺ��� �̸��� �Դϴ�.");
			req.setAttribute("url", "joinform");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("eamil", email);
			session.setAttribute("password", password);
			session.setAttribute("password2", password2);
			session.setAttribute("name", name);
			session.setAttribute("nicname", nickname);
			session.setAttribute("tel", tel);
			session.setAttribute("birthday", birthday);
			
			req.setAttribute("msg", "��밡���� �̸����Դϴ�.");
			req.setAttribute("url", "joincheck");
		}
		return "message";		
	}
	
	//�̸��� �ߺ�üũ ������ joincheck(�ߺ�Ȯ�� �� ������)
	@RequestMapping("/joincheck")
	public String JoinCheck(String email) {
		return "user/joincheck";
	}
	
	//ȸ�������� �����ϸ�
	@RequestMapping("/userjoinok")
	public String Joincheck(HttpServletRequest req, UserListDTO dto) {
		int res = userMapper.insertUser(dto);
		if(res>0) {
			req.setAttribute("msg", "ȸ�������� ���ϵ帳�ϴ�.");
			req.setAttribute("url", "index");
		}else {
			req.setAttribute("msg", "ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ����ּ���.");
			req.setAttribute("url", "joinform");
		}
		return "message";
	}
	
	
	//���̵� ã��
	
	
	//���ã��
	
	
	//ȸ������
	


	//ȸ��Ż��
	
	

	
	

}
