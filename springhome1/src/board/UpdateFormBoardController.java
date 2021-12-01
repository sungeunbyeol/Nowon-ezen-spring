package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class UpdateFormBoardController implements Controller {
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int num = ServletRequestUtils.getIntParameter(req, "num");
		
		BoardDBBean dto = boardDAO.getBoard(num, "update");
		req.setAttribute("getBoard", dto);
		return new ModelAndView("board/update");
	}

}
