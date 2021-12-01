package member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberAllController implements Controller {

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		
		List<MemberDTO> list = null;
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "all";
		}
		if (mode.equals("all")) {
			list = memberDAO.listMember();
		}else {
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			list = memberDAO.findMember(search, searchString);
		}
		req.setAttribute("mode", mode);
		return new ModelAndView("member/memberAll", "listMember", list);
	}

}
