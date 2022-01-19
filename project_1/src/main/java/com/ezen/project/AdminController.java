package com.ezen.project;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.CompanyDTO;
import com.ezen.project.model.UserDTO;
import com.ezen.project.service.CompanyMapper;
import com.ezen.project.service.UserMapper;

@Controller
public class AdminController {

	@Autowired
	UserMapper userMapper;
	
	@Autowired 
	CompanyMapper companyMapper;
	
	
	//list에서 유저 목록보여주기
	@RequestMapping("/admin_user_list")//
	public ModelAndView adminUserList (HttpServletRequest req, 
			@RequestParam(required = false) String mode) {

		ModelAndView mav = new ModelAndView();
		//일반적으로 user_list페이지에 들어올 경우 mode ==null 에서 all로 바꿔줘서 전체 다보여주기
		if (mode==null) {
			mode = "all";	
		}
		//보여줄 list 선언
		List<UserDTO> list = null;
		//else 에서는 find일 경우 --> search(찾을 카테고리), searchString(찾는 단어) 
		if (mode.equals("all")) {
			//바로 DB에서 꺼내오기 
			list = userMapper.listUser();
		}else {
			//parameter로 받아오기 
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			//list를 mapper로 보내서 DB에서 꺼내오기
			list = userMapper.findUser(search, searchString);
		}
		//mav로 list mod url 모두 설정해서 admin_user_list페이지로 보내줘서 띄워주기
		mav.addObject("listUser", list);
		mav.addObject("mode", mode);
		mav.setViewName("admin/admin_user_list");

		return mav;
	}
	//관리자가 유저 삭제
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest req, 
			@RequestParam int u_num) {
	
		//num값 받아서 넘겨줘서 유저 목록 삭제
		int res = userMapper.AdmindeleteUser(u_num);

		if (res>0) {
			req.setAttribute("msg", "회원삭제성공!! 회원관리페이지로 이동합니다.");
			req.setAttribute("url", "admin_user_list");
		}else {
			req.setAttribute("msg", "회원삭제실패!! 회원관리페이지로 이동합니다.");
			req.setAttribute("url", "admin_user_list");
		}
		
		return "message";
	}
	
	//blacklist 등록 num값 받아서 실행
	@RequestMapping("/insertBlackUser")
	public String insertBlakcUser(HttpServletRequest req,
		int u_num, String u_black) {
		
		//dto를 num값으로 DB꺼내주기 
		UserDTO dto = userMapper.getUserNum(u_num);
		
		//a_level을 0으로 설정하기 
		dto.setA_level(Integer.toString(0));
		dto.setU_black(u_black);
		//설정한 a_level을 DB에 등록하기
		int res = userMapper.registBlackList(dto);

		if (res>0) { 
			req.setAttribute("msg", "블랙리스트 등록 성공!!");
			req.setAttribute("url", "admin_user_list");
		}else {
			req.setAttribute("msg", "블랙리스트 등록 성공!!");
			req.setAttribute("url", "admin_user_list");
		} 
		return "message";
	}


	@RequestMapping("/saveBlackContent")
	public String saveBlackContent(HttpServletRequest req,
			int u_num, String u_black) {
		
		//u_num으로 dto 가져오기 
		UserDTO dto = userMapper.getUserNum(u_num); 
		//blacklist에 등록된 이유 dto에 저장하기 
		dto.setU_black(u_black); 

		int res = userMapper.saveBlackContent(dto);

		if (res>0) { 
			req.setAttribute("msg", "블랙리스트 사유 저장!!");
			req.setAttribute("url", "admin_user_blacklist");
		}else {
			req.setAttribute("msg", "블랙리스트 사유 저장 실패!!");
			req.setAttribute("url", "admin_user_blacklist");
		} 
		return "message";
	}

	//블랙리스트 유저 일반유저로 전환
	@RequestMapping("/deleteBlackUser")
	public String deleteBlackUser(HttpServletRequest req,
			@RequestParam int u_num) { 
		//dto에서 a_level 0에서 1로 변경
		UserDTO dto = userMapper.getUserNum(u_num);
		dto.setA_level(Integer.toString(1));
		
		int res = userMapper.deleteBlackList(dto);
 
		if (res>0) { 
			req.setAttribute("msg", "블랙리스트 해제 성공!!");
			req.setAttribute("url", "admin_user_blacklist");
		}else {
			req.setAttribute("msg", "블랙리스트 해제 실패!!");
			req.setAttribute("url", "admin_user_blacklist");
		} 
		return "message";
	}

	//blacklist 목록 보기 & 찾기
	@RequestMapping("/admin_user_blacklist")
	public ModelAndView adminBlackListOk (HttpServletRequest req, 
			@RequestParam(required = false) String mode){
		
		//userlist와 마찬가지로 mode값 받아서 진행 
		ModelAndView mav = new ModelAndView();
		if (mode==null) {
			mode = "all";
		}
		List<UserDTO> list = null;
		if (mode.equals("all")) {
			list = userMapper.blacklistUser();
		}else {
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			list = userMapper.findBlackUser(search, searchString);
		} 
		//list뿌려주기 위해서 mav로 보내주기 
		mav.addObject("listUser", list);
		mav.addObject("mode", mode);
		mav.setViewName("admin/admin_user_blacklist");

		return mav; 
	}
	
	//company도 마찬가지
	@RequestMapping("/admin_company_list")
	public ModelAndView adminCompanyListOk (HttpServletRequest req, 
			@RequestParam(required = false) String mode) {

		ModelAndView mav = new ModelAndView();
		if (mode==null) {
			mode = "all";	
		} 
		List<CompanyDTO> list = null;
		if (mode.equals("all")) {
			list = companyMapper.listCompany();
		}else {
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			list = companyMapper.findCompany(search, searchString);
		}  
		mav.addObject("listCompany", list);
		mav.addObject("mode", mode);
		mav.setViewName("admin/admin_company_list");

		return mav;
	}
	
	//admin이 기업 삭제
	@RequestMapping("/deleteCompany")
	public ModelAndView deleteCompany(HttpServletRequest req, 
			@RequestParam int c_num) {
		ModelAndView mav = new ModelAndView();

		int res = companyMapper.adminDeleteCompany(c_num);

		if (res>0) {
			mav.addObject("msg", "기업삭제성공!! 기업관리페이지로 이동합니다.");
			mav.addObject("url", "admin_company_list");
		}else {
			mav.addObject("msg", "기업삭제실패!! 기업관리페이지로 이동합니다.");
			mav.addObject("url", "admin_company_list");
		}
		mav.setViewName("message");
		return mav;
	}
	
	
	
	@RequestMapping("/admin_user_qna")
	public String adminUserQna() {
		return "board/admin_user_qna";
	}
	
	@RequestMapping("/admin_company_qna")
	public String adminCompanyQna() {
		return "board/admin_company_qna";
	}
}
