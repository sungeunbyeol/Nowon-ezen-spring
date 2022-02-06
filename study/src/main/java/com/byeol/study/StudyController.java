package com.byeol.study;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.byeol.study.model.StudyDTO;
import com.byeol.study.service.StudyMapper;

@Controller
public class StudyController {
	
	@Autowired
	StudyMapper studyMapper;

	@RequestMapping("/")
	public String main() {
		return "book";
	}
	
	@RequestMapping("/find")
	public String findbook(HttpServletRequest req, @RequestParam String search, String searchString) {
		StudyDTO dto = studyMapper.findbook(search, searchString);
		req.setAttribute("findlist", dto);
		return "find";
	}
	
	
	/*@RequestMapping("/insert")
	public String insertbook(HttpServletRequest req, @RequestParam Map<String, String> params) {
		params.put("String", bookname);
		int res = studyMapper.insertbooklist();
		return "";
	}
	*/
	
	@RequestMapping("/insert")
	public String insertbook(HttpServletRequest req, StudyDTO dto) {
		int res = studyMapper.insertbooklist(dto);
		
		if(res>0) {
			req.setAttribute("msg", "도서등록 성공쓰~");
			req.setAttribute("url", "list");
		}else {
			req.setAttribute("msg", "도서등록 실패쓰ㅜㅜ");
			req.setAttribute("url", "book");
		}
		return "message";
	}
	
	@RequestMapping("/list")
	public String listpage(HttpServletRequest req) {
		List<StudyDTO> list = studyMapper.listbookpage();
		req.setAttribute("list_list", list);
		return "list";
	}
	
	@RequestMapping("/delete")
	public String deletepage(HttpServletRequest req, @RequestParam String bookname) {
		int res = studyMapper.deletename(bookname);
		if(res>0) {
			req.setAttribute("msg", "도서삭제 성공쓰~");
			req.setAttribute("url", "list");
		}else {
			req.setAttribute("msg", "도서삭제 실패ㅠ_ㅠ");
			req.setAttribute("url", "book");
		}
		return "message";
	}
	
//	@RequestMapping("/find")
//	public String findbook(HttpServletRequest req, @RequestParam String search, String searchString) {
//		StudyDTO dto = studyMapper.findbook(search, searchString);
//		//찾는도서가 없으면 없다고 알림뜨기
//		//찾는도서가 있으면 find가서 보여주기
//		req.setAttribute("findlist", dto);
//		return "find";
//	}
	
}
