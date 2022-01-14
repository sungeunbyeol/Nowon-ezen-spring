package com.ezen.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.project.model.CompanyQnADTO;
import com.ezen.project.model.UserQnADTO;
import com.ezen.project.service.QnABoardMapper;

@Controller
public class QnABoardController {
	
	@Autowired
	private QnABoardMapper qnaBoardMapper;
	
	@RequestMapping("/list_companyQnA")
	public String listCompanyQnA(HttpServletRequest req, @RequestParam(required=false) String pageNum) {
		HttpSession session = req.getSession();

		CompanyLoginOkBean companyLoginOkBean = (CompanyLoginOkBean)session.getAttribute("companyLoginOkBean");
		
		if(pageNum == null) pageNum = "1";
		// 따로 누른 페이지가 없이 초기화면일때 pageNum을 1로 설정
		
		int pageSize = 7;
		// 한페이지에 표시되는 글목록의 수

		int currentPage = Integer.parseInt(pageNum);
		// pageNum을 int형태로 변환해서 currentPage로 저장
		
		int startRow = (currentPage - 1) * pageSize + 1;
		// 1P:1, 2P:6, 3P:11, 4P:16....
		
		int endRow = startRow + (pageSize - 1);
		// 1P:5, 2P:10, 3P:15, 4P:20....
		
		int rowCount = qnaBoardMapper.getAdminCompanyQnACount();
		/*
		int rowCount = 0;
		
		if(companyLoginOkBean.getA_level().equals("3")) {
			rowCount = qnaBoardMapper.getAdminCompanyQnACount();
		}else {
			rowCount = qnaBoardMapper.getCompanyQnACount(companyLoginOkBean.getU_num());
		}
		*/
		if(endRow>rowCount) endRow = rowCount;
		// 5개씩 끊어지면 마지막에 5의배수가 아니면 개수가 안맞으므로 끝번호에 맞게 endRow를 조정
		
		List<CompanyQnADTO> list = null;
		
		if(companyLoginOkBean.getA_level().equals("3")) {
			list = qnaBoardMapper.listAdminCompanyQnA(startRow, endRow);	
		}else {
			list = qnaBoardMapper.listCompanyQnA(companyLoginOkBean.getC_num());
		}
		
		for(CompanyQnADTO cqadto : list) {
			if(qnaBoardMapper.getCnameByCnum(cqadto.getC_num()) == null) {
				cqadto.setC_name("탈퇴기업");
			}else {
				cqadto.setC_name(qnaBoardMapper.getCnameByCnum(cqadto.getC_num()));
			}
		}
		
		int linkSize = 0;
		int pageCount = 0;
		int startPage = 0;
		int endPage = 0;
		
		if(rowCount>0) {
			linkSize = 5;
			// 화면 하단에 나타낼 페이지링크 수

			pageCount = rowCount / pageSize + (rowCount%pageSize == 0 ? 0 : 1);
			
			startPage = ((currentPage-1) / linkSize) * linkSize + 1;
			// 화면에 나타낼 페이지링크의 처음
			
			endPage = startPage+linkSize-1;
			// 화면에 나타낼 페이지링크의 마지막 (현재페이지+페이지링크수-1)
			
			if(endPage>pageCount) endPage=pageCount;
		}
		
		req.setAttribute("listCompanyQnA", list);
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("linkSize", linkSize);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		return "board/company_qna_list";
	}
	
	@RequestMapping("/get_companyQnA")
	public String getCompanyQnA(HttpServletRequest req, @RequestParam int cqa_num) {
		CompanyQnADTO cqadto = qnaBoardMapper.getCompanyQnA(cqa_num);
		cqadto.setC_name(qnaBoardMapper.getCnameByCnum(cqadto.getC_num()));
		req.setAttribute("cqadto", cqadto);

		return "board/company_qna_content";
	}
	
	@RequestMapping("/write_companyQnA")
	public String writeCompanyQnA() {
		return "board/company_qna_writeform";
	}
	
	@RequestMapping("/write_companyQnA_ok")
	public String writeCompanyQnAOk(HttpServletRequest req, @ModelAttribute CompanyQnADTO cqadto, BindingResult result) {
		if(result.hasErrors()) {
			cqadto.setCqa_num(0);
			cqadto.setCqa_re_step(0);
			cqadto.setCqa_re_level(0);
		}
		
		if(cqadto.getC_num_parent()==0) cqadto.setC_num_parent(cqadto.getC_num());

		int res = qnaBoardMapper.insertCompanyQnA(cqadto);
		
		if(res>0) {
			req.setAttribute("msg", "게시판에 글이 등록 되었습니다.");
			req.setAttribute("url", "list_companyQnA");
		}else {
			req.setAttribute("msg", "등록 오류 발생. 관리자에게 문의해 주세요.");
			req.setAttribute("url", "write_companyQnA");
		}
		
		return "message";
	}
	
	@RequestMapping("/delete_companyQnA_ok")
	public String deleteCompanyQnAOk(HttpServletRequest req, int cqa_num) {
		int res = qnaBoardMapper.deleteCompanyQnA(cqa_num);
		
		if(res>0) {
			req.setAttribute("msg", "글을 삭제하였습니다. 글 목록 페이지로 이동합니다.");
			req.setAttribute("url", "list_companyQnA");
		}else {
			req.setAttribute("msg", "삭제 오류 발생. 관리자에게 문의해 주세요.");
			req.setAttribute("url", "get_companyQnA?cqa_num="+cqa_num);
		}			
		
		return "message";
	}
	
	@RequestMapping("/update_companyQnA")
	public String updateCompanyQnA(HttpServletRequest req, int cqa_num) {
		CompanyQnADTO cqadto = qnaBoardMapper.getCompanyQnA(cqa_num);
		cqadto.setC_name(qnaBoardMapper.getCnameByCnum(cqadto.getC_num()));
		req.setAttribute("cqadto", cqadto);
		
		return "board/company_qna_updateform";
	}
	
	@RequestMapping("/update_companyQnA_ok")
	public String updateProBoard(HttpServletRequest req, CompanyQnADTO cqadto) {
		int res = qnaBoardMapper.updateCompanyQnA(cqadto);
		
		if(res>0) {
			req.setAttribute("msg", "글이 수정되었습니다. 글 목록 페이지로 이동합니다.");
			req.setAttribute("url", "list_companyQnA");
		}else {
			req.setAttribute("msg", "수정 오류 발생. 관리자에게 문의해 주세요.");
			req.setAttribute("url", "get_companyQnA?cqa_num="+cqadto.getCqa_num());
		}
		
		return "message";
	}
	
	@RequestMapping("/list_userQnA")
	public String listUserQnA(HttpServletRequest req, @RequestParam(required=false) String pageNum) {
		HttpSession session = req.getSession();
		
		LoginOkBean loginOkBean = (LoginOkBean)session.getAttribute("loginOkBean");
		
		if(pageNum == null) pageNum = "1";
		// 따로 누른 페이지가 없이 초기화면일때 pageNum을 1로 설정
		
		int pageSize = 7;
		// 한페이지에 표시되는 글목록의 수

		int currentPage = Integer.parseInt(pageNum);
		// pageNum을 int형태로 변환해서 currentPage로 저장
		
		int startRow = (currentPage - 1) * pageSize + 1;
		// 1P:1, 2P:6, 3P:11, 4P:16....
		
		int endRow = startRow + (pageSize - 1);
		// 1P:5, 2P:10, 3P:15, 4P:20....
		
		int rowCount = qnaBoardMapper.getAdminUserQnACount();
		/*
		int rowCount = 0;
		
		if(loginOkBean.getA_level().equals("3")) {
			rowCount = qnaBoardMapper.getAdminUserQnACount();
		}else {
			rowCount = qnaBoardMapper.getUserQnACount(loginOkBean.getU_num());
		}
		*/
		if(endRow>rowCount) endRow = rowCount;
		// 5개씩 끊어지면 마지막에 5의배수가 아니면 개수가 안맞으므로 끝번호에 맞게 endRow를 조정
		
		List<UserQnADTO> list = null;
		
		if(loginOkBean.getA_level().equals("3")) {
			list = qnaBoardMapper.listAdminUserQnA(startRow, endRow);
		}else {
			list = qnaBoardMapper.listUserQnA(loginOkBean.getU_num());
		}
		
		for(UserQnADTO uqadto : list) {
			if(qnaBoardMapper.getUnicknameByUnum(uqadto.getU_num()) == null) {
				uqadto.setU_nickname("탈퇴회원");
			}else {
				uqadto.setU_nickname(qnaBoardMapper.getUnicknameByUnum(uqadto.getU_num()));
			}
		}
		
		int linkSize = 0;
		int pageCount = 0;
		int startPage = 0;
		int endPage = 0;
		
		if(rowCount>0) {
			linkSize = 5;
			// 화면 하단에 나타낼 페이지링크 수

			pageCount = rowCount / pageSize + (rowCount%pageSize == 0 ? 0 : 1);
			
			startPage = ((currentPage-1) / linkSize) * linkSize + 1;
			// 화면에 나타낼 페이지링크의 처음
			
			endPage = startPage+linkSize-1;
			// 화면에 나타낼 페이지링크의 마지막 (현재페이지+페이지링크수-1)
			
			if(endPage>pageCount) endPage=pageCount;
		}
		
		req.setAttribute("listUserQnA", list);
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("linkSize", linkSize);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		return "board/user_qna_list";
	}
	
	@RequestMapping("/get_userQnA")
	public String getUserQnA(HttpServletRequest req, @RequestParam int uqa_num) {
		UserQnADTO uqadto = qnaBoardMapper.getUserQnA(uqa_num);
		uqadto.setU_nickname(qnaBoardMapper.getUnicknameByUnum(uqadto.getU_num()));
		req.setAttribute("uqadto", uqadto);

		return "board/user_qna_content";
	}
	
	@RequestMapping("/write_userQnA")
	public String writeUserQnA() {
		return "board/user_qna_writeform";
	}
	
	@RequestMapping("/write_userQnA_ok")
	public String writeUserQnAOk(HttpServletRequest req, @ModelAttribute UserQnADTO uqadto, BindingResult result) {
		if(result.hasErrors()) {
			uqadto.setUqa_num(0);
			uqadto.setUqa_re_step(0);
			uqadto.setUqa_re_level(0);
		}
		
		if(uqadto.getU_num_parent()==0) uqadto.setU_num_parent(uqadto.getU_num());
		
		int res = qnaBoardMapper.insertUserQnA(uqadto);
		
		if(res>0) {
			req.setAttribute("msg", "게시판에 글이 등록 되었습니다.");
			req.setAttribute("url", "list_userQnA");
		}else {
			req.setAttribute("msg", "등록 오류 발생. 관리자에게 문의해 주세요.");
			req.setAttribute("url", "write_userQnA");
		}
		
		return "message";
	}
	
	@RequestMapping("/delete_userQnA_ok")
	public String deleteUserQnAOk(HttpServletRequest req, int uqa_num) {
		int res = qnaBoardMapper.deleteUserQnA(uqa_num);
		
		if(res>0) {
			req.setAttribute("msg", "글을 삭제하였습니다. 글 목록 페이지로 이동합니다.");
			req.setAttribute("url", "list_userQnA");
		}else {
			req.setAttribute("msg", "삭제 오류 발생. 관리자에게 문의해 주세요.");
			req.setAttribute("url", "get_userQnA?uqa_num="+uqa_num);
		}			
		
		return "message";
	}
	
	@RequestMapping("/update_userQnA")
	public String updateUserQnA(HttpServletRequest req, int uqa_num) {
		UserQnADTO uqadto = qnaBoardMapper.getUserQnA(uqa_num);
		uqadto.setU_nickname(qnaBoardMapper.getUnicknameByUnum(uqadto.getU_num()));
		req.setAttribute("uqadto", uqadto);
		
		return "board/user_qna_updateform";
	}
	
	@RequestMapping("/update_userQnA_ok")
	public String updateProBoard(HttpServletRequest req, UserQnADTO uqadto) {
		int res = qnaBoardMapper.updateUserQnA(uqadto);
		
		if(res>0) {
			req.setAttribute("msg", "글이 수정되었습니다. 글 목록 페이지로 이동합니다.");
			req.setAttribute("url", "list_userQnA");
		}else {
			req.setAttribute("msg", "수정 오류 발생. 관리자에게 문의해 주세요.");
			req.setAttribute("url", "get_userQnA?uqa_num="+uqadto.getUqa_num());
		}
		
		return "message";
	}
}
