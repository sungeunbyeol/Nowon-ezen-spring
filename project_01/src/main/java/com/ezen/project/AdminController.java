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
	private UserMapper userMapper;
	
	@Autowired 
	private CompanyMapper companyMapper;
	
	@Autowired
	private LoginOkBeanUser loginOkBean;
	
	@Autowired
	private LoginOkBeanCompany companyLoginOkBean;
	
	//list���� ���� ��Ϻ����ֱ�
	@RequestMapping("/admin_user_list")
	public ModelAndView adminUserList (HttpServletRequest req, 
			@RequestParam(required = false) String mode) {

		ModelAndView mav = new ModelAndView();
		
		if(loginOkBean.getA_level().equals("3")) {
			if (mode==null) {
				mode = "all";
			}
			
			List<UserDTO> uList = null; 
			
			if (mode.equals("all")) {
				uList = userMapper.listUser();
			}else {
				String search = req.getParameter("search");
				String searchString = req.getParameter("searchString");
				uList = userMapper.findUser(search, searchString);
			}
			
			mav.addObject("uList", uList);
			mav.addObject("mode", mode);
			mav.setViewName("admin/admin_user_list");
		}else {
			mav.addObject ("msg","admin�� ���� �����մϴ�");
			mav.addObject("url", "/project");
			mav.setViewName("message");
			}
		return mav;
	}
	
	//�����ڰ� ���� ����
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest req, 
			@RequestParam int u_num) {
	
		//num�� �޾Ƽ� �Ѱ��༭ ���� ��� ����
		int res = userMapper.deleteUserByAdmin(u_num);

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
	public String insertBlackUser(HttpServletRequest req,
		int u_num, String u_black) {
		
		//dto�� num������ DB�����ֱ� 
		UserDTO udto = userMapper.getUserByUnum(u_num);
		
		//a_level�� 0���� �����ϱ� 
		udto.setA_level(Integer.toString(0));
		udto.setU_black(u_black);
		//������ a_level�� DB�� ����ϱ�
		int res = userMapper.addBlackList(udto);

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
		UserDTO dto = userMapper.getUserByUnum(u_num); 
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
		UserDTO dto = userMapper.getUserByUnum(u_num);
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
	public ModelAndView adminUserBlackList (HttpServletRequest req, 
			@RequestParam(required = false) String mode){
		
		//userlist�� ���������� mode�� �޾Ƽ� ���� 
		ModelAndView mav = new ModelAndView();
		if(loginOkBean.getA_level().equals("3")) {
			if (mode==null) {
				mode = "all";
			}
			
			List<UserDTO> buList = null;
			
			if (mode.equals("all")) {
				buList = userMapper.listBlackUser();
			}else {
				String search = req.getParameter("search");
				String searchString = req.getParameter("searchString");
				buList = userMapper.findUserOnBlack(search, searchString);
			}
			
			mav.addObject("buList", buList);
			mav.addObject("mode", mode);
			mav.setViewName("admin/admin_user_blacklist");
		}else {
			mav.addObject ("msg","admin�� ���� �����մϴ�");
			mav.addObject("url", "main");
			mav.setViewName("message");
		}
		return mav;
	}
	
	//company�� ��������
	@RequestMapping("/admin_company_list")
	public ModelAndView adminCompanyList (HttpServletRequest req, 
			@RequestParam(required = false) String mode) {

		ModelAndView mav = new ModelAndView();
		if(companyLoginOkBean.getA_level().equals("3")) {
			if (mode==null) {
				mode = "all";
			}
			List<CompanyDTO> cList = null;
			if (mode.equals("all")) {
				cList = companyMapper.listCompany();
			}else {
				String search = req.getParameter("search");
				String searchString = req.getParameter("searchString");
				cList = companyMapper.findCompany(search, searchString);
			}
			mav.addObject("cList", cList);
			mav.addObject("mode", mode);
			mav.setViewName("admin/admin_company_list");
		}else {
			mav.addObject ("msg","admin�� ���� �����մϴ�");
			mav.addObject("url", "/company_main");
			mav.setViewName("message");
		}
		return mav;
	}
	
	//admin�� ��� ����
	@RequestMapping("/deleteCompany")
	public ModelAndView deleteCompany(HttpServletRequest req, 
			@RequestParam int c_num) {
		ModelAndView mav = new ModelAndView();

		int res = companyMapper.deleteCompanyByAdmin(c_num);

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
