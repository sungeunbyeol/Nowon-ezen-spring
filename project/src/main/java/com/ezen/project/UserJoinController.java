package com.ezen.project;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.UserDTO;
import com.ezen.project.service.UserJoinMapper;

@Controller
public class UserJoinController {

	@Autowired
	private UserJoinMapper userjoinMapper;
	@Autowired
	private LoginCheck loginCheck;
	@Autowired
	private LoginOkBean loginOkBean;
	
	//ȸ������ ȭ������ �̵�
	@RequestMapping(value="/user_join" , method=RequestMethod.GET)
	public String userJoin() {
		return "user/user_join";
	}
	
	//ȸ������ �ϱ�
	@RequestMapping(value="/user_join", method=RequestMethod.POST)
	public String InsertOkUser(HttpServletRequest req, UserDTO dto) {
		int res = userjoinMapper.insertUser(dto);
		if (res>0) {
			req.setAttribute("msg", "ȸ�����Կ� �����Ͽ����ϴ�.");
			req.setAttribute("url", "main.do");
		}else {
			req.setAttribute("msg", "ȸ�������� �ٽ� �õ����ּ���.");
			req.setAttribute("url", "user_join.do");
		}
		return "message";
	}

	// �α��� ȭ������ �̵�
	@RequestMapping("/user_login")
	public String userLogin() {
		return "user/user_login";
	}

	//�α��� �õ����� ��
	@RequestMapping("/user_login_ok")
	public String userLoginOk(HttpServletRequest req, HttpServletResponse resp, @RequestParam(required = false) String saveEmail) {
		loginCheck.setU_email(req.getParameter("u_email"));
		loginCheck.setU_passwd(req.getParameter("u_password"));
		
		Cookie ck = new Cookie("saveEmail", loginCheck.getU_email());
		
		int res = userjoinMapper.checkLogin(loginCheck.getU_email(), loginCheck.getU_password());
		String msg = null, url = null;
		switch(res) {
		case LoginCheck.OK :
			if(saveEmail == null) {
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);
			
			loginOkBean.setU_email(loginCheck.getU_email());
			loginOkBean.isMemberSetting();
			
			HttpSession session = req.getSession();
			session.setAttribute("loginOkBean", loginOkBean);
			msg = "�α��� �Ǿ����ϴ�.";
			url = "main";
			break;
		case LoginCheck.NOT_EMAIL :
			msg = "���� ���̵� �Դϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���";
			url = "user_login";
			break;
		case LoginCheck.NOT_PWD :
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���";
			url = "user_login";
			break;
		case LoginCheck.ERROR :
			msg = "DB���� ���� �߻�!! �����ڿ��� ������ �ּ���!!";
			url = "user_login";
			break;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}

	//�α׾ƿ� �Ҷ� 
	@RequestMapping("/user_logout")
	public ModelAndView Logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "main.do");
		return new ModelAndView("message");
	}
	
	// ȸ��Ż�� ��������
	@RequestMapping("/user_delete")
	public String deletePage() {
		return "/user/user_delete";
	}
	
	//ȸ��Ż�� �õ����� ��
	@RequestMapping("/user_delete_user") 
	public String deleteOkPage(@RequestParam Map<String, String> params, HttpServletRequest req) {
		String u_num = req.getParameter("u_num");
		String u_password = req.getParameter("u_password");	
		int res = userjoinMapper.UserdeleteUser(u_num, u_password);
   
		if(res>0){
			req.setAttribute("msg", "ȸ�� Ż��Ǿ����ϴ�.");
			req.setAttribute("url", "main");
			HttpSession sessio = req.getSession();
			sessio.invalidate();
		}else { 
			req.setAttribute("msg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			req.setAttribute("url", "user_delete");
		}
		
		return "message"; 
	}
	
	//��й�ȣ ���� ��������
	@RequestMapping("/user_password_edit")
	public String updateUserPassword() {
		return "/user/user_password_edit";
	}

	// �����ȣ ���� �õ����� ��
	@RequestMapping("/user_update_password_ok")
	public String updatepassword(HttpServletRequest req, 
			@RequestParam String u_email ) {
		
		UserDTO dto = userjoinMapper.getUser(u_email);
		dto.setU_password(req.getParameter("u_password")); 
		int res = userjoinMapper.updateUserPassword(dto);
		HttpSession session = req.getSession();
		session.invalidate();
		if (res>0) { 
			req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �α������ּ���");
			req.setAttribute("url", "/project");
		}else {
			req.setAttribute("msg", "��й�ȣ ���� ����!!");
			req.setAttribute("url", "/project");
		} 
		return "message";
	}
	
}
