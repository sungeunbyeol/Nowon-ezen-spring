package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SearchLoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String mode = req.getParameter("mode");
		String title = mode.equals("id") ? "아이디" : "비밀번호";
		req.setAttribute("title", title);
		return new ModelAndView("login/search");
	}

}
