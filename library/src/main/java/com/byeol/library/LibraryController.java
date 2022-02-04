package com.byeol.library;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.byeol.library.model.LibraryDTO;
import com.byeol.library.service.LibraryMapper;

@Controller
public class LibraryController {

	@Autowired
	LibraryMapper libraryMapper;
	
	@RequestMapping("/")
	public String index() {
		return "book";
	}
	
	@RequestMapping("/list")
	public String listpage(HttpServletRequest req) {
		List<LibraryDTO> list = libraryMapper.listpage();
		req.setAttribute("listpage", list);
		return "list";
//		ModelAndView mav = new ModelAndView("list", "listpage", list);
//		return mav;
	}
	
	@RequestMapping("/insert")
	public String insertbook(HttpServletRequest req, LibraryDTO dto)  {//어떤값으로 받아오는지 
		
		int res = libraryMapper.insertbook(dto);
	
		if(res>0) {
			req.setAttribute("msg", "도서등록 성공!");
			req.setAttribute("url", "list");
		}else {
			req.setAttribute("msg", "도서등록 실패!");
			req.setAttribute("url", "book");
		}
		return "message";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam String bookname) {
		
		int res = libraryMapper.deletebook(bookname);
		
		if(res>0) {
			req.setAttribute("msg", "도서삭제 성공!");
			req.setAttribute("url", "list");
		}else {
			req.setAttribute("msg", "도서삭제 실패!");
			req.setAttribute("url", "book");
		}
		
		return "message";
	}
	
	@RequestMapping("/find")
	public String find(HttpServletRequest req, @RequestParam String search, String searchString) {
		
		LibraryDTO dto = libraryMapper.findbook(search, searchString);
		req.setAttribute("jj", dto);
		return "find";
	}
	
}
