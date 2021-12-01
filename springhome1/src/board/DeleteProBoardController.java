package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class DeleteProBoardController implements Controller {

	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int num = ServletRequestUtils.getIntParameter(req,  "num");
		String passwd = req.getParameter("passwd");
		int res = boardDAO.deleteBoard(num, passwd);
		String msg = null, url = null;
		if (res>0){
			msg = "글삭제성공!! 글목록페이지로 이동합니다.";
			url = "list_board.do";
		}else if (res<0){
			msg = "비밀번호가 틀렸습니다. 다시 입력해 주세요!!";
			url = "deleteForm_board.do?num=" + num;
		}else {
			msg = "글삭제실패!! 글내용보기페이지로 이동합니다.";
			url = "content_board.do?num=" + num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("forward:message.jsp");
	}

}





