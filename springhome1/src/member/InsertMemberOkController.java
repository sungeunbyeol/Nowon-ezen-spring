package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class InsertMemberOkController extends AbstractCommandController {
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		MemberDTO dto = (MemberDTO)arg2;
		int res = memberDAO.insertMember(dto);
		if (res>0) {
			req.setAttribute("msg", "회원가입성공!! 회원관리페이지로 이동합니다.");
			req.setAttribute("url", "member.do");
		}else {
			req.setAttribute("msg", "회원가입실패!! 다시 입력해 주세요!!");
			req.setAttribute("url", "memberSsn.do");
		}
		return new ModelAndView("forward:message.jsp");
	}

}
