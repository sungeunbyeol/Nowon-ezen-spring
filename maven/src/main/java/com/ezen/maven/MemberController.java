package com.ezen.maven;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.maven.service.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	MemberMapper memberMapper;
	
	@RequestMapping("/memberindex")
	public String Member() {
		return "member/index";
	}
	
	@RequestMapping("/memberSsn")
	public String memberssn() {
		return "member/memberSsn";
	}
	
	@RequestMapping("/checkMember")
	public String checkmember(HttpServletRequest req, @RequestParam Map<String, String>params) {
		boolean ismember = memberMapper.checkMember(params);
		if(ismember) {
			req.setAttribute("msg", "ȸ���̽ʴϴ�. �α������ּ���");
			req.setAttribute("url", "memberindex");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("name", params.get("name"));
			session.setAttribute("ssn1", params.get("ssn1"));
			session.setAttribute("ssn2", params.get("ssn2"));
			req.setAttribute("msg", "ȸ���� �ƴմϴ�. ȸ������â���� �̵��մϴ�");
			req.setAttribute("url", "member");
		}
		
		return "message";
	}
	
	@RequestMapping("member")
	public String insertMember() {
		return "member/member";
	}
	
	
}
