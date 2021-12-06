package member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping("/member.do")
	public ModelAndView insertMember() {
		return new ModelAndView("member/index");
	}
	
	@RequestMapping("/memberSsn.do")
	public String MemberSsn() {
		return "member/memberSsn";
	}
	
	@RequestMapping("/checkMember.do")
	public String CheckMember(HttpServletRequest req, @RequestParam Map<String, String>params) {
		boolean isMember = memberDAO.checkMember(params.get("name"), params.get("ssn1"), params.get("ssn2"));
		if(isMember) {
			req.setAttribute("msg", "ȸ���̽ʴϴ�. �α������ּ���");
			req.setAttribute("url", "member.do");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("name", params.get("name"));
			session.setAttribute("ssn1", params.get("ssn1"));
			session.setAttribute("ssn2", params.get("ssn2"));
			req.setAttribute("msg", "ȸ�������������� �̵��մϴ�.");
			req.setAttribute("url", "insertMember.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/insertMember.do")
	public ModelAndView InsertMember() {
		return new ModelAndView("member/member");
	}
	
	@RequestMapping("/insertMemberOk.do")
	public String InsertOkMember(HttpServletRequest req, MemberDTO dto) {
		int res = memberDAO.insertMember(dto);
		if (res>0) {
			req.setAttribute("msg", "ȸ�����Լ���!! ȸ�������������� �̵��մϴ�.");
			req.setAttribute("url", "member.do");
		}else {
			req.setAttribute("msg", "ȸ�����Խ���!! �ٽ� �Է��� �ּ���!!");
			req.setAttribute("url", "memberSsn.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/memberAll.do")
	public ModelAndView MemberAll(HttpServletRequest req, @RequestParam String mode ) {
		List<MemberDTO> list = null;
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
	
	@RequestMapping(value="/deleteMember.do")
	public  String DeleteMember(HttpServletRequest req, @RequestParam int num) {
		int res = memberDAO.deleteMember(Integer.parseInt("num"));
		if (res>0) {
			req.setAttribute("msg", "ȸ����������!! ȸ������������� �̵��մϴ�.");
			req.setAttribute("url", "memberAll.do");
		}else {
			req.setAttribute("msg", "ȸ����������!! ȸ������������� �̵��մϴ�.");
			req.setAttribute("url", "memberAll.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping("/editMember.do")
	public ModelAndView EditMember(@RequestParam int no) {
		MemberDTO dto = memberDAO.getMember(no);
		return new ModelAndView("member/member_edit", "getMember", dto);
	}
	
	@RequestMapping("/editMemberOk.do")
	public ModelAndView EditMemberOk(HttpServletRequest req, @ModelAttribute MemberDTO dto) {
		int res = memberDAO.updateMember(dto);
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
