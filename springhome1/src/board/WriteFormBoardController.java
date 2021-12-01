package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class WriteFormBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		int num = 0, re_step = 0, re_level = 0;
		String snum = req.getParameter("num");
		if (snum != null){
			num = Integer.parseInt(snum);
			re_step = Integer.parseInt(req.getParameter("re_step"));
			re_level = Integer.parseInt(req.getParameter("re_level"));
		}
		req.setAttribute("num", num);
		req.setAttribute("re_step", re_step);
		req.setAttribute("re_level", re_level);
		return new ModelAndView("board/writeForm");
	}

}
