package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class UpdateProBoardAbstractCommandController extends AbstractCommandController {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		BoardDBBean dto = (BoardDBBean)arg2;
		int res = boardDAO.updateBoard(dto);
		String msg = null, url = null;
		if (res>0){
			msg = "글수정제성공!! 글목록페이지로 이동합니다.";
			url = "list_board.do";
		}else if (res<0){
			msg = "비밀번호가 틀렸습니다. 다시 입력해 주세요!!";
			url = "updateForm_board.do?num=" + dto.getNum();
		}else {
			msg = "글수정실패!! 글내용보기페이지로 이동합니다.";
			url = "content_board.do?num=" + dto.getNum();
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("forward:message.jsp");
	}

}
