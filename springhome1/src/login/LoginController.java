package login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;

@Controller
public class LoginController {
	@Autowired
	private LoginCheck loginCheck;
	@Autowired
	private LoginOkBean loginOkBean;
	@Autowired
	private MemberDAO memberDAO;
	
	public void setLoginCheck(LoginCheck loginCheck) {
		this.loginCheck = loginCheck;
	}
	public void setLoginOkBean(LoginOkBean loginOkBean) {
		this.loginOkBean = loginOkBean;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@RequestMapping("/login.do")
	public String loginMain() {
		return "login/login";
	}
	@RequestMapping("/login_ok.do")
	public ModelAndView loginOK(HttpServletRequest req,HttpServletResponse resp, String saveId) {
		loginCheck.setId(req.getParameter("id"));
		loginCheck.setPasswd(req.getParameter("passwd"));
				
		Cookie ck = new Cookie("saveId", loginCheck.getId());
		
	 	int res = loginCheck.checkLogin();
	 	String msg = null, url = null;
		switch(res){
		case LoginCheck.OK :
			if (saveId == null){
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
		return new ModelAndView("message");
	}
	@RequestMapping("/search.do")
	public String loginSearch(HttpServletRequest req, String mode) {
		String title = mode.equals("id") ? "아이디" : "비밀번호";
		req.setAttribute("title", title);
		return "login/search";
	}
	@RequestMapping("/searchCheck.do")
	public ModelAndView loginsearchCheck(HttpServletRequest req,String name, String ssn1, String ssn2, String id) {
	
		String msg = memberDAO.searchMember(name, ssn1, ssn2, id);
		String url = null;
		req.setAttribute("msg", msg);
		if (msg != null) {
			return new ModelAndView("forward:closeWindow.jsp");
		}
		if(id==null){
				msg = "아이디를 찾을 수 없습니다. 다시 확인해 주세요!!";
				url = "search.do?mode=id";
		}else {
				msg = "해당하는 정보가 일치하지 않습니다. 다시 확인해 주세요!!";
				url = "search.do?mode=pw";
			}
		req.setAttribute("url",url);
		req.setAttribute("msg", msg);
		return new ModelAndView("message");
	}
	@RequestMapping("/logout.do")
	public String logOut(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "로그아웃되었습니다");
		req.setAttribute("url", "main.do");
		return "message";
	}
}