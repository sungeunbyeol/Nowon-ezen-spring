package fileUpload;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FileUploadController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0;
		
		MultipartFile mf = mr.getFile("filename");
		String filename = mf.getOriginalFilename();

		//PrintWriter pw = arg1.getWriter();
		//pw.println("<h2>파일명 : " + filename + "</h2>");
		
		if(filename == null || filename.trim().equals("")) {
			return null;
		}
		String upPath = arg0.getServletContext().getRealPath("files");
		
		File file = new File(upPath, filename);
		mf.transferTo(file);
		
		PrintWriter pw = arg1.getWriter();
		String name= arg0.getParameter("name");
		pw.println("<h2>" + name + "님이 올리신 파일전송완료 </h2>");
		return null;
		
	}

}
