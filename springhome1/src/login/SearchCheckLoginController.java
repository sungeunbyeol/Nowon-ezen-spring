package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class SearchCheckLoginController implements Controller {
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String name = req.getParameter("name");
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		String id = req.getParameter("id");
		
		String msg = memberDAO.searchMember(name, ssn1, ssn2, id);
		req.setAttribute("msg", msg);
		if (msg != null) {
			return new ModelAndView("forward:closeWindow.jsp");
		}
		if (id == null) {
			req.setAttribute("msg", "���̵� ã�� �� �����ϴ�. �ٽ� Ȯ���� �ּ���!!");
			req.setAttribute("url", "searchLogin.do?mode=id");
		}else {
			req.setAttribute("msg", "�ش��ϴ� ������ ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���� �ּ���!!");
			req.setAttribute("url", "searchLogin.do?mode=pw");
		}
		return new ModelAndView("forward:message.jsp");
	}

}






