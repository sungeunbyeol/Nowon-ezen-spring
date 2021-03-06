package board;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;
import board.mybatis.BoardMapper;

@Controller
public class BoardController {

	
	@Autowired 
	private BoardDAO boardDAO;
	

	@RequestMapping("/list_board.do")
	public String listBoard(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		//rowCount = boardDAO.getCount();
		rowCount = BoardMapper.getCount();
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		//List<BoardDBBean> list = boardDAO.listBoard(startRow, endRow);
		List<BoardDBBean> list = BoardMapper.listBoard(startRow, endRow);
		req.setAttribute("listBoard", list);
		req.setAttribute("number", number);
		req.setAttribute("rowCount", rowCount);
	
		if (rowCount>0){
			int pageBlock = 3;
			int pageCount = rowCount / pageSize;
			if (rowCount%pageSize != 0){
				pageCount++;
			}
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}
		return "board/list";
	}
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.GET)
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.POST)
	public String writePro(HttpServletRequest req, @ModelAttribute BoardDBBean dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setNum(0);
			dto.setRe_step(0);
			dto.setRe_level(0);
		}
		dto.setIp(req.getRemoteAddr());
		//int res = boardDAO.insertBoard(dto);
		int res = BoardMapper.insertBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "?????? ???? ????!! ?????? ???????????? ??????????.");
			req.setAttribute("url", "list_board.do");
		}else {
			req.setAttribute("msg", "?????? ???? ????!! ?????? ???????????? ??????????.");
			req.setAttribute("url", "writeForm_board.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/delete_board.do", method=RequestMethod.GET)
	public String deleteFormBoard() {
		return "board/deleteForm";
	}
	
	@RequestMapping(value="/content_board.do")
	public ModelAndView contentBoard(@RequestParam int num) {
		//BoardDBBean dto = boardDAO.getBoard(num, "content");
		BoardDBBean dto = BoardMapper.getBoard(num, "content");
		return new ModelAndView("board/content", "getBoard", dto);
	}
	
	@RequestMapping(value="/update_board.do", method=RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam int num) {
		BoardDBBean dto = boardDAO.getBoard(num, "update");
		return new ModelAndView("board/update", "getBoard", dto);
	}
	
	@RequestMapping(value="/delete_board.do", method=RequestMethod.POST)
	public String deleteProBoard(HttpServletRequest req, @RequestParam Map<String, String> params) {
		//int res = boardDAO.deleteBoard(Integer.parseInt(params.get("num")), params.get("passwd"));
		int res = BoardMapper.deleteBoard(Integer.parseInt(params.get("num")), params.get("passwd"));
		String msg = null, url = null;
		if (res>0){
			msg = "??????????!! ?????????????? ??????????.";
			url = "list_board.do";
		}else if (res<0){
			msg = "?????????? ??????????. ???? ?????? ??????!!";
			url = "delete_board.do?num=" + params.get("num");
		}else {
			msg = "??????????!! ?????????????????? ??????????.";
			url = "content_board.do?num=" + params.get("num");
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/update_board.do", method=RequestMethod.POST)
	public String updatePro(HttpServletRequest req, BoardDBBean dto) {
		//int res = boardDAO.updateBoard(dto);
		int res = BoardMapper.updateBoard(dto);
		String msg = null, url = null;
		if (res>0){
			msg = "????????????!! ?????????????? ??????????.";
			url = "list_board.do";
		}else if (res<0){
			msg = "?????????? ??????????. ???? ?????? ??????!!";
			url = "update_board.do?num=" + dto.getNum();
		}else {
			msg = "??????????!! ?????????????????? ??????????.";
			url = "content_board.do?num=" + dto.getNum();
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:message.jsp";
	}
}





















