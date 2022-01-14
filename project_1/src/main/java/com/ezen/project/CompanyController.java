package com.ezen.project;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	CompanyMapper companyMapper;
	
	//파일경로
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
			req.setAttribute("msg", "회원이신 분입니다. 로그인을 해 주세요!!");
			req.setAttribute("url", "company_login");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("c_name", c_name);
			session.setAttribute("c_bnum", c_bnum);
			req.setAttribute("msg", "회원가입페이지로 이동합니다.");
			req.setAttribute("url", "company_register");
		}
		return "message";
	}
	
	@RequestMapping("/company_register")
	public String companyRegister() {
		return "company/company_register";
	}
	
	//company_register에서 보냄
	@RequestMapping("/company_register_ok")
	   public String companyRegisterOk (HttpServletRequest req, CompanyDTO cdto, BindingResult result) 
	         throws IOException { 
		  //Multipart로 image가져옴 
		  MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
	      MultipartFile mf = mr.getFile("c_image");
	      String c_image = mf.getOriginalFilename(); 
	      if(c_image == null || c_image.trim().equals("")) {
	         c_image = "default.jpg";
	      }else{ 
	         c_image = UUID.randomUUID().toString()+"_"+c_image;
	      } 
	       
	      //이미지 없으면 default사진 사용
	      if(!c_image.equals("default.jpg")) {
	         File file = new File(upPath+"\\company", c_image);
	         mf.transferTo(file);
	      }
	      
	      //주소 저장시 @ 가지고 저장 
	      //수정페이지에서는 @를 떼고 보여준다. 
	      String addr1 = mr.getParameter("addr1");
	      String addr2 = mr.getParameter("addr2");
	      String addr3 = mr.getParameter("addr3");
	      String addr4 = mr.getParameter("addr4");
	      String addr = addr1 +"@"+ addr2 +"@"+ addr3 + "@" + addr4;
	       
	      int res=companyMapper.insertCompany(mr, c_image, addr); 
	          
	      if (res>0) {  
	         req.setAttribute("msg", "회원가입성공!! 메인페이지로 이동합니다.");
	         req.setAttribute("url", "company_main");
	      }else{
	         req.setAttribute("msg", "회원가입실패!! 다시 입력해 주세요!!");
	         req.setAttribute("url",  "company_register");
	      } 
	         return "message";
	      }
		
	//company_mypage에서 기업탈퇴 
	@RequestMapping("/company_delete")
	public String deletePage() {
		return "/company/company_delete";
	}
	
	//회원탈퇴 company_delete.jsp페이지에서 이동
	@RequestMapping("/company_delete_ok")
	public String deleteOkPage(@RequestParam Map<String, String> params, HttpServletRequest req) {
		int res = companyMapper.deleteCompany(params);
		System.out.println(res);
		if(res>0){
			req.setAttribute("msg", "회원 탈퇴되었습니다.");
			req.setAttribute("url", "company_main");
			HttpSession session = req.getSession();
			session.invalidate();
			
		}else {
			req.setAttribute("msg", "회원 탈퇴 실패하였습니다.");
			req.setAttribute("url", "company_delete");
		}
	
		return "message";
	} 
	
	//company_mypage.jsp c_num값을 받아온다. 
	@RequestMapping("/company_edit")
	public String companyEdit(HttpServletRequest req) {
		HttpSession session = req.getSession(); 
		CompanyLoginOkBean companyLoginOkBean = (CompanyLoginOkBean)session.getAttribute("companyLoginOkBean");
		int c_num = companyLoginOkBean.getC_num();
		CompanyDTO dto = companyMapper.getCompanyNum(c_num);
		req.setAttribute("cdto", dto);
		req.setAttribute("upPath", upPath);
		return "company/company_edit";
		} 
	
		//company_edit.jsp에서 받아옴  
	   @RequestMapping("/company_edit_ok")
	   public String editCompanyOkForm(HttpServletRequest req,String c_image2
	         ) throws IOException{ 
	      MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
	      MultipartFile mf = mr.getFile("c_image");
	      String c_image = mf.getOriginalFilename(); 
	       
	      HttpSession session = req.getSession();
	      CompanyLoginOkBean cpLoginOkBean = (CompanyLoginOkBean)session.getAttribute("companyLoginOkBean");
	      int c_num = cpLoginOkBean.getC_num();
	      CompanyDTO cdto = companyMapper.getCompanyNum(c_num);
	      
	      if(c_image == null || c_image.trim().equals("")) {
	         c_image = c_image2; 
	      }else {
	         // 새 파일 업로드
	         c_image = UUID.randomUUID().toString()+"_"+c_image;
	         File newFile = new File(upPath+"\\company", c_image);
	         mf.transferTo(newFile);
	         // 기존 파일 삭제 (default.jpg 예외)
	         File preFile = new File(upPath+"\\company", c_image2);
	         if(preFile.exists() && !preFile.getName().equals("default.jpg")) {
	            preFile.delete();
	         }
	      }
	      
	      String addr1 = req.getParameter("addr1");
	      String addr2 = req.getParameter("addr2");
	      String addr3 = req.getParameter("addr3");
	      String addr4 = mr.getParameter("addr4");
	      String addr = addr1 +"@"+ addr2 +"@"+ addr3 + "@" + addr4;
	      
	      cdto.setC_address(addr);
	      cdto.setC_name(mr.getParameter("c_name"));
	      cdto.setC_tel(mr.getParameter("c_tel"));
	      cdto.setC_image(c_image);
	      
	      int res = companyMapper.updateCompany(cdto);  
	      
	      if (res>0) {
	         req.setAttribute("msg", "회원수정성공!!");
	         req.setAttribute("url", "company_edit");
	      }else{
	         req.setAttribute("msg", "회원수정실패!! 다시 입력해 주세요.");
	         req.setAttribute("url", "company_edit");
	      } 
	      return "message";
	   }
	
	 //mode따라서 email or password찾기 
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
			msg = "아이디를 찾을수 없습니다. 다시 확인해 주세요.";
			url = "company_search?mode=c_email";
		}else {
			msg = "해당하는 정보가 일치하지 않습니다. 다시 확인해주세요";
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
	
	//company정보 c_email로 구해온다음에 페스워드 변경 
	@RequestMapping("/company_update_password_ok")
	public String companyupdatepassword(HttpServletRequest req, 
			@RequestParam String c_email ) {
		CompanyDTO dto = companyMapper.getCompany(c_email);
		dto.setC_password(req.getParameter("c_password")); 
		int res = companyMapper.updateCompanyPassword(dto);
		if (res>0) { 
			req.setAttribute("msg", "비밀번호 변경 성공!! 다시 로그인해주세요");
			req.setAttribute("url", "company_login");
		}else {
			req.setAttribute("msg", "비밀번호 변경 실패!!");
			req.setAttribute("url", "company_update_password");
		} 
		return "message";
	}
	
	@RequestMapping("/company_search")
	public String companySearch() {
		return "company/company_search";
	}
	
	@RequestMapping("/company_search_email_ok")
	public String companySearchEmailOk(@RequestParam Map<String, String> params, HttpServletRequest req) {
		String value = companyMapper.findEmail(params);
		req.setAttribute("c_email", value);
		return "company/company_search_email_ok";
	}
	
	@RequestMapping("/company_update_password")
	public String companyUpdatePassword() {
		return "company/company_update_password";
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
		CompanyLoginOkBean cpLoginOkBean = (CompanyLoginOkBean)session.getAttribute("companyLoginOkBean");
		int c_num = cpLoginOkBean.getC_num();
		req.setAttribute("c_num", c_num);
		return "company/company_myPage";
	}
	
	@RequestMapping("/company_bookList")
	public String companyBookList() {
		return "company/company_bookList";
	}
}
