package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class CheckMemberController implements Controller {
	private MemberDAO memberDAO; //DB연동할거면 dao 선언해주기
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = req.getParameter("name");
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		boolean isMember = memberDAO.checkMember(name, ssn1, ssn2);
		if(isMember) {
			req.setAttribute("msg", "회원이십니다. 로그인해주세요");
			req.setAttribute("url", "member.do");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("name", name);
			session.setAttribute("ssn1", ssn1);
			session.setAttribute("ssn2", ssn2);
			req.setAttribute("msg", "회원가입페이지로 이동합니다.");
			req.setAttribute("url", "insertMember.do");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
