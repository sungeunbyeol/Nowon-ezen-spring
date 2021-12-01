package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "로그아웃 되었습니다.");
		req.setAttribute("url", "main.do");
		return new ModelAndView("forward:message.jsp");
	}

}
