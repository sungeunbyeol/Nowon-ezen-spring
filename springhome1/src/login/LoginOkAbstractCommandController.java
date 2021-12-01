package login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import login.dao.LoginOkBean;

public class LoginOkAbstractCommandController extends AbstractCommandController {
	private LoginOkBean loginOkBean;

	public void setLoginOkBean(LoginOkBean loginOkBean) {
		this.loginOkBean = loginOkBean;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object arg2, BindException arg3)
			throws Exception {
		String saveId = req.getParameter("saveId");
		LoginCheck loginCheck = new LoginCheck();
		LoginOkBean loginOk = new LoginOkBean();
		loginCheck.setId(req.getParameter("id"));
		loginCheck.setPasswd(req.getParameter("passwd"));
		
		Cookie ck = new Cookie("saveId", loginCheck.getId());
		
	 	int res = loginCheck.checkLogin();
	 	switch(res){
		case LoginCheck.OK :
			if (saveId == null){
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);
			loginOk.setId(loginCheck.getId());
			boolean isLogin = loginOk.isMemberSetting();
			HttpSession session = req.getSession();
			session.setAttribute("loginOk", loginOk);
			req.setAttribute("msg", "로그인 되었습니다.");
			req.setAttribute("url", "main.do");
			break;
		case LoginCheck.NOT_ID :
			req.setAttribute("msg", "없는 아이디 입니다. 다시 확인하시고 입력해 주세요");
			req.setAttribute("url", "login.do");
			break;
		case LoginCheck.NOT_PWD :
			req.setAttribute("msg", "비밀번호가 틀렸습니다. 다시 확인하시고 입력해 주세요");
			req.setAttribute("url", "login.do");
			break;
		case LoginCheck.ERROR :
			req.setAttribute("msg", "DB서버 오류 발생!! 관리자에게 문의해 주세요!!");
			req.setAttribute("url", "login.do");
			break;
		}
		return new ModelAndView("forward:message.jsp");
	}

}
