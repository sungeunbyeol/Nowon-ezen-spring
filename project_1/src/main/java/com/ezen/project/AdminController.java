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
	
	
	//list���� ���� ��Ϻ����ֱ�
	@RequestMapping("/admin_user_list")//
	public ModelAndView adminUserList (HttpServletRequest req, 
			@RequestParam(required = false) String mode) {

		ModelAndView mav = new ModelAndView();
		//�Ϲ������� user_list�������� ���� ��� mode ==null ���� all�� �ٲ��༭ ��ü �ٺ����ֱ�
		if (mode==null) {
			mode = "all";	
		}
		//������ list ����
		List<UserDTO> list = null;
		//else ������ find�� ��� --> search(ã�� ī�װ�), searchString(ã�� �ܾ�) 
		if (mode.equals("all")) {
			//�ٷ� DB���� �������� 
			list = userMapper.listUser();
		}else {
			//parameter�� �޾ƿ��� 
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			//list�� mapper�� ������ DB���� ��������
			list = userMapper.findUser(search, searchString);
		}
		//mav�� list mod url ��� �����ؼ� admin_user_list�������� �����༭ ����ֱ�
		mav.addObject("listUser", list);
		mav.addObject("mode", mode);
		mav.setViewName("admin/admin_user_list");

		return mav;
	}
	//�����ڰ� ���� ����
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest req, 
			@RequestParam int u_num) {
	
		//num�� �޾Ƽ� �Ѱ��༭ ���� ��� ����
		int res = userMapper.AdmindeleteUser(u_num);

		if (res>0) {
			req.setAttribute("msg", "ȸ����������!! ȸ�������������� �̵��մϴ�.");
			req.setAttribute("url", "admin_user_list");
		}else {
			req.setAttribute("msg", "ȸ����������!! ȸ�������������� �̵��մϴ�.");
			req.setAttribute("url", "admin_user_list");
		}
		
		return "message";
	}
	
	//blacklist ��� num�� �޾Ƽ� ����
	@RequestMapping("/insertBlackUser")
	public String insertBlakcUser(HttpServletRequest req,
		int u_num, String u_black) {
		
		//dto�� num������ DB�����ֱ� 
		UserDTO dto = userMapper.getUserNum(u_num);
		
		//a_level�� 0���� �����ϱ� 
		dto.setA_level(Integer.toString(0));
		dto.setU_black(u_black);
		//������ a_level�� DB�� ����ϱ�
		int res = userMapper.registBlackList(dto);

		if (res>0) { 
			req.setAttribute("msg", "������Ʈ ��� ����!!");
			req.setAttribute("url", "admin_user_list");
		}else {
			req.setAttribute("msg", "������Ʈ ��� ����!!");
			req.setAttribute("url", "admin_user_list");
		} 
		return "message";
	}


	@RequestMapping("/saveBlackContent")
	public String saveBlackContent(HttpServletRequest req,
			int u_num, String u_black) {
		
		//u_num���� dto �������� 
		UserDTO dto = userMapper.getUserNum(u_num); 
		//blacklist�� ��ϵ� ���� dto�� �����ϱ� 
		dto.setU_black(u_black); 

		int res = userMapper.saveBlackContent(dto);

		if (res>0) { 
			req.setAttribute("msg", "������Ʈ ���� ����!!");
			req.setAttribute("url", "admin_user_blacklist");
		}else {
			req.setAttribute("msg", "������Ʈ ���� ���� ����!!");
			req.setAttribute("url", "admin_user_blacklist");
		} 
		return "message";
	}

	//������Ʈ ���� �Ϲ������� ��ȯ
	@RequestMapping("/deleteBlackUser")
	public String deleteBlackUser(HttpServletRequest req,
			@RequestParam int u_num) { 
		//dto���� a_level 0���� 1�� ����
		UserDTO dto = userMapper.getUserNum(u_num);
		dto.setA_level(Integer.toString(1));
		
		int res = userMapper.deleteBlackList(dto);
 
		if (res>0) { 
			req.setAttribute("msg", "������Ʈ ���� ����!!");
			req.setAttribute("url", "admin_user_blacklist");
		}else {
			req.setAttribute("msg", "������Ʈ ���� ����!!");
			req.setAttribute("url", "admin_user_blacklist");
		} 
		return "message";
	}

	//blacklist ��� ���� & ã��
	@RequestMapping("/admin_user_blacklist")
	public ModelAndView adminBlackListOk (HttpServletRequest req, 
			@RequestParam(required = false) String mode){
		
		//userlist�� ���������� mode�� �޾Ƽ� ���� 
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
		//list�ѷ��ֱ� ���ؼ� mav�� �����ֱ� 
		mav.addObject("listUser", list);
		mav.addObject("mode", mode);
		mav.setViewName("admin/admin_user_blacklist");

		return mav; 
	}
	
	//company�� ��������
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
	
	//admin�� ��� ����
	@RequestMapping("/deleteCompany")
	public ModelAndView deleteCompany(HttpServletRequest req, 
			@RequestParam int c_num) {
		ModelAndView mav = new ModelAndView();

		int res = companyMapper.adminDeleteCompany(c_num);

		if (res>0) {
			mav.addObject("msg", "�����������!! ��������������� �̵��մϴ�.");
			mav.addObject("url", "admin_company_list");
		}else {
			mav.addObject("msg", "�����������!! ��������������� �̵��մϴ�.");
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
