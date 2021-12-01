package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class EditMemberOkAbstractCommandController extends AbstractCommandController {

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		MemberDTO dto = (MemberDTO)arg2;
		int res = memberDAO.updateMember(dto);
		if (res>0) {
			req.setAttribute("msg", "회원수정성공!! 회원목록페이지로 이동합니다.");
			req.setAttribute("url", "memberAll.do");
		}else {
			req.setAttribute("msg", "회원수정실패!! 회원목록페이지로 이동합니다.");
			req.setAttribute("url", "memberAll.do");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
