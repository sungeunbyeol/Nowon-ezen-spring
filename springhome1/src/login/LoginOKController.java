package login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginOKController implements Controller {
	private LoginCheck loginCheck;
	private LoginOkBean loginOkBean;
	
	public void setLoginCheck(LoginCheck loginCheck) {
		this.loginCheck = loginCheck;
	}
	public void setLoginOkBean(LoginOkBean loginOkBean) {
		this.loginOkBean = loginOkBean;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		loginCheck.setId(req.getParameter("id"));
		loginCheck.setPasswd(req.getParameter("passwd"));
		
		String saveId = req.getParameter("saveId");
		Cookie ck = new Cookie("saveId", loginCheck.getId());
		
		int res = loginCheck.checkLogin();
		String msg = null, url = null;
		switch(res){
		case LoginCheck.OK :
			if(saveId == null) {
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);
			
			loginOkBean.setId(loginCheck.getId());
			boolean isLogin = loginOkBean.isMemberSetting();
			HttpSession session = req.getSession();
			session.setAttribute("loginOkBean", loginOkBean);
			msg = "로그인 되었습니다.";
			url = "main.do";
			break;
		case LoginCheck.NOT_ID :
			msg = "없는 아이디 입니다. 다시 확인하시고 입력해 주세요";
			url = "login.do";
			break;
		case LoginCheck.NOT_PWD :
			msg = "비밀번호가 틀렸습니다. 다시 확인하시고 입력해 주세요";
			url = "login.do";
			break;
		case LoginCheck.ERROR :
			msg = "DB서버 오류 발생!! 관리자에게 문의해 주세요!!";
			url = "login.do";
			break;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("forward:message.jsp");
	}

}
