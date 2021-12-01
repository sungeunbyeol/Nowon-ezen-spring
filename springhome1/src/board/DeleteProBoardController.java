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
			msg = "�ۻ�������!! �۸���������� �̵��մϴ�.";
			url = "list_board.do";
		}else if (res<0){
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���!!";
			url = "deleteForm_board.do?num=" + num;
		}else {
			msg = "�ۻ�������!! �۳��뺸���������� �̵��մϴ�.";
			url = "content_board.do?num=" + num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("forward:message.jsp");
	}

}





