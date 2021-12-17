package com.ezen.mavenTest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.mavenTest.model.MemberDTO;
import com.ezen.mavenTest.service.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	MemberMapper memberMapper;
	
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
		//isMember = memberDAO.isCheckMember(name, ssn1, ssn2);
		boolean isMember = memberMapper.checkMember(params);
		if(isMember) {
			req.setAttribute("msg", "회원이십니다. 로그인해주세요");
			req.setAttribute("url", "member.do");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("name", params.get("name"));
			session.setAttribute("ssn1", params.get("ssn1"));
			session.setAttribute("ssn2", params.get("ssn2"));
			req.setAttribute("msg", "회원가입페이지로 이동합니다.");
			req.setAttribute("url", "insertMember.do");
		}
		return "message";
	}
	
	@RequestMapping(value="/insertMember.do", method=RequestMethod.GET)
	public ModelAndView InsertMember() {
		return new ModelAndView("member/member");
	}
	
	@RequestMapping(value="/insertMember.do", method=RequestMethod.POST)
	public String InsertOkMember(HttpServletRequest req, MemberDTO dto) {
		int res = memberMapper.insertMember(dto);
		if (res>0) {
			req.setAttribute("msg", "회원가입성공!! 회원관리페이지로 이동합니다.");
			req.setAttribute("url", "member.do");
		}else {
			req.setAttribute("msg", "회원가입실패!! 다시 입력해 주세요!!");
			req.setAttribute("url", "memberSsn.do");
		}
		return "message";
	}
	
	@RequestMapping("/memberAll.do")
	public String MemberAll(HttpServletRequest req) {
//		ModelAndView mav= new ModelAndView();
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "all";
		}

		List<MemberDTO> list = null;
		if (mode.equals("all")) {
			list = memberMapper.listMember();
		}else {
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			list = memberMapper.findMember(search, searchString);
		}
//		mav.addObject("listMember",list);
//		mav.addObject("mode",mode);
//		mav.setViewName("member/memberAll");
//		return mav;
		req.setAttribute("listMember", list);
		req.setAttribute("mode", mode);
		return "member/memberAll";
	}
	
	@RequestMapping(value="/deleteMember.do")
	public  String DeleteMember(HttpServletRequest req, @RequestParam int no) {
		int res = memberMapper.deleteMember(no);
		if (res>0) {
			req.setAttribute("msg", "회원삭제성공!! 회원목록페이지로 이동합니다.");
			req.setAttribute("url", "memberAll.do");
		}else {
			req.setAttribute("msg", "회원삭제실패!! 회원목록페이지로 이동합니다.");
			req.setAttribute("url", "memberAll.do");
		}
		return "message";
	}
	
	@RequestMapping(value= "/editMember.do", method=RequestMethod.GET)
	public ModelAndView EditMember(@RequestParam int no) {
		MemberDTO dto = memberMapper.getMember(no);
		return new ModelAndView("member/member_edit", "getMember", dto);
	}
	
	@RequestMapping(value="/editMember.do", method=RequestMethod.POST)
	public ModelAndView EditMemberOk(HttpServletRequest req, @ModelAttribute MemberDTO dto) {
		int res = memberMapper.updateMember(dto);
		if (res>0) {
			req.setAttribute("msg", "회원수정성공!! 회원목록페이지로 이동합니다.");
			req.setAttribute("url", "memberAll.do");
		}else {
			req.setAttribute("msg", "회원수정실패!! 회원목록페이지로 이동합니다.");
			req.setAttribute("url", "memberAll.do");
		}
		return new ModelAndView("message");
	}
	
}
