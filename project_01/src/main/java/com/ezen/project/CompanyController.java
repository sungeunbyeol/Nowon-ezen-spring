package com.ezen.project;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.project.model.CompanyDTO;
import com.ezen.project.service.CompanyMapper;

@Controller
public class CompanyController {
	 
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	//���ϰ��
	@Resource(name = "uploadPath")
	private String upPath;
	
	@RequestMapping("/company_ssn")
	public String companySsn() {
		return "/company/company_ssn";
	}
	@RequestMapping("/company_check")
	public String companyCheck(String c_name, String c_bnum, HttpServletRequest req) {
		boolean isDupl = companyMapper.hasCompanyAccount(c_name, c_bnum);
		
		if (isDupl) {
			req.setAttribute("msg", "�̹� ���Ե� �̸����Դϴ�. �α����� �� �ּ���!!");
			req.setAttribute("url", "company_login");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("c_name", c_name);
			session.setAttribute("c_bnum", c_bnum);
			req.setAttribute("msg", "ȸ�������������� �̵��մϴ�.");
			req.setAttribute("url", "company_register");
		}
		return "message";
	}
	
	@RequestMapping("/company_register")
	public String companyRegister() {
		return "company/company_register";
	}
	
	//company_register���� ����
	@RequestMapping("/company_register_ok")
	public String companyRegisterOk (HttpServletRequest req, CompanyDTO cdto, BindingResult result) 
			throws IOException { 
		//Multipart�� image������ 
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("c_image");
		
		String c_image = mf.getOriginalFilename(); 
		
		if(c_image == null || c_image.trim().equals("")) {
			c_image = "default.jpg";
		}else{ 
			c_image = UUID.randomUUID().toString()+"_"+c_image;
		} 
	       
		//�̹��� ������ default���� ���
		if(!c_image.equals("default.jpg")) {
			File file = new File(upPath+"\\company", c_image);
			mf.transferTo(file);
		}
	      
		//�ּ� ����� @ ������ ���� 
		//���������������� @�� ���� �����ش�. 
		String addr1 = mr.getParameter("addr1");
		String addr2 = mr.getParameter("addr2");
		String addr3 = mr.getParameter("addr3");
		String addr4 = mr.getParameter("addr4");
		String addr = addr1 +"@"+ addr2 +"@"+ addr3 + "@" + addr4;
		
		String c_password = pwdEncoder.encode(cdto.getC_password());
		
		// DTO�� ��ȣȭ�� ��й�ȣ�� ����
		cdto.setC_password(c_password);
		cdto.setC_image(c_image);
		cdto.setC_address(addr);
		
		int res = companyMapper.insertCompany(cdto); 
	          
		if (res>0) {  
			req.setAttribute("msg", "ȸ�����Լ���!! ������������ �̵��մϴ�.");
			req.setAttribute("url", "company_main");
		}else{
			req.setAttribute("msg", "ȸ�����Խ���!! �ٽ� �Է��� �ּ���!!");
			req.setAttribute("url",  "company_register");
		} 
		return "message";
	}
		
	//company_mypage���� ���Ż�� 
	@RequestMapping("/company_delete")
	public String deletePage() {
		return "/company/company_delete";
	}
	
	//ȸ��Ż�� company_delete.jsp���������� �̵�
	@RequestMapping("/company_delete_ok")
	public String deleteOkPage(HttpServletRequest req, int c_num, String raw_password) {
		HttpSession session = req.getSession();
		
		String c_password = companyMapper.getCompanyByCnum(c_num).getC_password();
		String c_image = companyMapper.getCompanyByCnum(c_num).getC_image();
		
		if(pwdEncoder.matches(raw_password, c_password)) {
			int res = companyMapper.deleteCompany(c_num, c_password);
			
			if(res>0){
				session.invalidate();
				
				File file = new File(upPath+"\\company", c_image);
				
				if(file.exists() && !file.getName().equals("default.jpg")) {
					file.delete();
				}
				
				req.setAttribute("msg", "ȸ�� Ż��Ǿ����ϴ�.");
				req.setAttribute("url", "company_main");
			}else {
				req.setAttribute("msg", "ȸ�� Ż�� �����Ͽ����ϴ�.");
				req.setAttribute("url", "company_delete");
			}
		}else {
			req.setAttribute("msg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.");
			req.setAttribute("url", "company_delete");
		}

		return "message";
	} 
	
	//company_mypage.jsp c_num���� �޾ƿ´�. 
	@RequestMapping("/company_edit")
	public String companyEdit(HttpServletRequest req) {
		HttpSession session = req.getSession(); 
		LoginOkBeanCompany companyLoginOkBean = (LoginOkBeanCompany)session.getAttribute("companyLoginOkBean");
		
		int c_num = companyLoginOkBean.getC_num();
		CompanyDTO dto = companyMapper.getCompanyByCnum(c_num);
		
		req.setAttribute("cdto", dto);
		
		return "company/company_edit";
	} 
	
		//company_edit.jsp���� �޾ƿ�  
	@RequestMapping("/company_edit_ok")
	public String editCompanyOkForm(HttpServletRequest req, String pre_image) throws IOException { 
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("c_image");
		String c_image = mf.getOriginalFilename(); 
		
		HttpSession session = req.getSession();
		LoginOkBeanCompany cpLoginOkBean = (LoginOkBeanCompany)session.getAttribute("companyLoginOkBean");
		
		int c_num = cpLoginOkBean.getC_num();
		CompanyDTO cdto = companyMapper.getCompanyByCnum(c_num);
	      
		if(c_image == null || c_image.trim().equals("")) {
			c_image = pre_image; 
		}else {
			// �� ���� ���ε�
			c_image = UUID.randomUUID().toString()+"_"+c_image;
			File newFile = new File(upPath+"\\company", c_image);
			mf.transferTo(newFile);
			
			// ���� ���� ���� (default.jpg ����)
			File preFile = new File(upPath+"\\company", pre_image);
			if(preFile.exists() && !preFile.getName().equals("default.jpg")) {
				preFile.delete();
			}
		}
		
		System.out.println(mr.getParameter("addr4"));
		System.out.println(req.getParameter("addr4"));
		
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String addr3 = req.getParameter("addr3");
		String addr4 = req.getParameter("addr4");
		String addr = addr1 +"@"+ addr2 +"@"+ addr3 + "@" + addr4;
  
		cdto.setC_address(addr);
		cdto.setC_name(req.getParameter("c_name"));
		cdto.setC_tel(req.getParameter("c_tel"));
		cdto.setC_image(c_image);
	      
		int res = companyMapper.updateCompany(cdto);  
	      
		if (res>0) {
			req.setAttribute("msg", "ȸ����������!!");
			req.setAttribute("url", "company_edit");
		}else{
			req.setAttribute("msg", "ȸ����������!! �ٽ� �Է��� �ּ���.");
			req.setAttribute("url", "company_edit");
		} 
		return "message";
	}
	
	//mode���� email or passwordã�� 
	@RequestMapping("/company_search_ok")
	public String companySearchOk(HttpServletRequest req) {
		String mode = req.getParameter("mode");
		String c_email = req.getParameter("c_email");
		String c_name = req.getParameter("c_name");
		String c_bnum = req.getParameter("c_bnum");
		
		String msg = null, url = null;
		
		try {  
			msg = companyMapper.searchCompany(c_email, c_name, c_bnum);
			url = null;
			req.setAttribute("msg", msg);
		}catch(Exception e){
			 
		}
		
		if (msg != null) {
			if(mode.equals("c_email")) {
			return "company_search_email_ok";
			}else if(mode.equals("c_password")) {
			
			req.setAttribute("c_email", c_email);
			return "company_update_password";
			}
			
		}	
		if(c_email ==null) {
			msg = "���̵� ã���� �����ϴ�. �ٽ� Ȯ���� �ּ���.";
			url = "company_search?mode=c_email";
		}else {
			msg = "�ش��ϴ� ������ ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���";
			url = "company_search?mode=c_password";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("/company_password_edit")
	public String updateCompanyPassword() {
		return "/company/company_password_edit";
	}
	
	@RequestMapping("/company_search")
	public String companySearch() {
		return "company/company_search";
	}
	
	@RequestMapping("/company_search_email_ok")
	public String companySearchEmailOk(@RequestParam Map<String, String> params, 
			HttpServletRequest req) {
		String value = companyMapper.findEmail(params);
		if(value != null) {
			req.setAttribute("c_email", value);
			return "company/company_search_email_ok";
		}else {
			req.setAttribute("msg", "���̵� ã���� �����ϴ�. �ٽ� Ȯ���� �ּ���.");
			req.setAttribute("url", "company_search?mode=c_email");
			return "message";
		}
	}
	
//	��й�ȣ ���� �� Ȯ��
	@RequestMapping("/company_passwordCheck")
	public String companyPasswordCheck(HttpServletRequest req) {
		return "company/company_passwordCheck";
	}
	
	//�������������� ��й�ȣ ������ ������ �� 
	@RequestMapping("/company_password_edit_ok")
	public String companyPasswordEditOk(HttpServletRequest req, String c_email, 
			String raw_pre_password, String raw_new_password) {
		
		CompanyDTO dto = companyMapper.getCompanyByEmail(c_email);
		
		HttpSession session = req.getSession();
		LoginOkBeanCompany companyLoginOkBean = (LoginOkBeanCompany)session.getAttribute("companyLoginOkBean");
		
		// ���� ��й�ȣ
		String pre_password = companyLoginOkBean.getC_password();
		
		// ����ڰ� �Է��� ���� ��й�ȣ�� ���� ��й�ȣ�� ��
		if(pwdEncoder.matches(raw_pre_password, pre_password)) {
			// ���� ��й�ȣ�� �°� �Է����� ���
			
			// DTO�� �� ��й�ȣ ����
			String new_password = pwdEncoder.encode(raw_new_password);
			dto.setC_password(new_password);
			
			// ��й�ȣ ������Ʈ
			int res = companyMapper.updateCompanyPassword(dto);
			
			if (res>0) {
				// ������ ���� ����
				session.invalidate();
				
				req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �α������ּ���");
				req.setAttribute("url", "company_main");
			}else {
				req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �õ����ּ���.");
				req.setAttribute("url", "company_passwordCheck");
			}
		}else {
			// ���� ��й�ȣ�� �߸� �Է����� ���
			req.setAttribute("msg", "���� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�. �ٽ� Ȯ�����ּ���.");
			req.setAttribute("url", "company_passwordCheck");
		}
		
		return "message";
	}
	
	@RequestMapping("/company_update_password")
	public String companyUpdatePassword() {
		return "company/company_update_password";
	}
	
	//company���� c_email�� ���ؿ´����� �佺���� ���� 
	@RequestMapping("/company_update_password_ok")
	public String companyUpdatePasswordOk(HttpServletRequest req, @RequestParam String c_email) {
		CompanyDTO cdto = companyMapper.getCompanyByEmail(c_email);
		
		String new_password = pwdEncoder.encode(req.getParameter("raw_password"));
		cdto.setC_password(new_password);
		
		int res = companyMapper.updateCompanyPassword(cdto);
		HttpSession session = req.getSession();
		
		if (res>0) {
			session.invalidate();
			req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �α������ּ���.");
			req.setAttribute("url", "company_login");
		}else {
			req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �õ����ּ���.");
			req.setAttribute("url", "company_login");
		} 
		return "message";
	}
	
	@RequestMapping("/company_qna_list")
	public String companyQnaList() {
		return "board/company_qna_list";
	}
	
	@RequestMapping("/company_qna_writeform")
	public String companyQnaWriteform() {
		return "board/company_qna_writeform";
	}
	
	@RequestMapping("/company_myPage")
	public String companymyPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBeanCompany companyLoginOkBean = (LoginOkBeanCompany)session.getAttribute("companyLoginOkBean");
		int c_num = companyLoginOkBean.getC_num();
		req.setAttribute("c_num", c_num);
		return "company/company_myPage";
	}
	
}
