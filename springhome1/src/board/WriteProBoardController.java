package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class WriteProBoardController extends AbstractCommandController {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		BoardDBBean dto = (BoardDBBean)arg2;
		dto.setIp(req.getRemoteAddr());
		int res = boardDAO.insertBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "게시글 등록 성공!! 게시글 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_board.do");
		}else {
			req.setAttribute("msg", "게시글 등록 실패!! 게시글 등록페이지로 이동합니다.");
			req.setAttribute("url", "writeForm_board.do");
		}
		return new ModelAndView("forward:message.jsp");
	}
}






















