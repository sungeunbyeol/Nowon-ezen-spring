package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class DeleteMemberController implements Controller {
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int no = ServletRequestUtils.getIntParameter(req, "no");
		int res = memberDAO.deleteMember(no);
		if (res>0) {
			req.setAttribute("msg", "ȸ����������!! ȸ������������� �̵��մϴ�.");
			req.setAttribute("url", "memberAll.do");
		}else {
			req.setAttribute("msg", "ȸ����������!! ȸ������������� �̵��մϴ�.");
			req.setAttribute("url", "memberAll.do");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
