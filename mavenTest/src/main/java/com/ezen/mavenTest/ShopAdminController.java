package com.ezen.mavenTest;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.mavenTest.model.CategoryDTO;
import com.ezen.mavenTest.model.ProductDTO;
import com.ezen.mavenTest.service.ShopAdminMapper;


@Controller
public class ShopAdminController {
	
	@Autowired
	ShopAdminMapper shopAdminMapper;
	
	@RequestMapping("/shopAdmin.do")
	public ModelAndView ShopAdmin() {
		return new ModelAndView("shop/admin/main");
	}
	
	@RequestMapping("/cate_input.do")
	public ModelAndView CateInput() {
		return new ModelAndView("shop/admin/cate_input");
	}
	
	@RequestMapping("/cate_input_ok.do")
	public String CateInputOk(HttpServletRequest req, CategoryDTO dto) {
		int res = shopAdminMapper.insertCate(dto);
		if (res>0) {
			req.setAttribute("msg", "ī�װ��� ��� ����!! ī�װ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "ī�װ��� ��� ����!! ī�װ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_input.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping("/cate_list.do")
	public ModelAndView CateList() {
		List<CategoryDTO> list = shopAdminMapper.listCate();
		return new ModelAndView("shop/admin/cate_list", "cateList", list);
	}
	
	@RequestMapping("/cate_delete.do")
	public ModelAndView CateDelete(HttpServletRequest req, @RequestParam int cnum) {
		int res = shopAdminMapper.deleteCate(cnum);
		if (res>0) {
			req.setAttribute("msg", "ī�װ��� ���� ����!! ī�װ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "ī�װ��� ���� ����!! ī�װ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}
		return new ModelAndView("forward:message.jsp");
	}
	
	@RequestMapping("/prod_input.do")
	public ModelAndView ProdInput(HttpServletRequest req) {
		List<CategoryDTO> list = shopAdminMapper.listCate();
		req.setAttribute("cateList", list);
		return new ModelAndView("shop/admin/prod_input");
	}
	
	@RequestMapping(value="/prod_input_ok.do")
	public String ProdInputOk(HttpServletRequest req, ProductDTO dto) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		MultipartFile mf = mr.getFile("pimage");
		String filename = mf.getOriginalFilename();
		int res = shopAdminMapper.insertProduct(mr,pimage); 
		if (res>0){
			req.setAttribute("msg","��ǰ��ϼ���!! ��ǰ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_list.do");		
		}else {
			req.setAttribute("msg","��ǰ��Ͻ���!! ��ǰ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_input.do");						
		}
		return "message";
	}
	
	@RequestMapping("/prod_list.do")
	public ModelAndView ProdList(HttpServletRequest req) {
		List<ProductDTO> list = shopAdminMapper.listProduct();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		return new ModelAndView("shop/admin/prod_list", "listProd", list);
	}
	
	@RequestMapping("/prod_delete.do")
	public String ProdDelete(HttpServletRequest req, @RequestParam int pnum) {
		String pimage = req.getParameter("pimage");
		int res = shopAdminMapper.deleteProduct(pnum);
		String msg = null, url = "prod_list.do";
		if (res>0) {
			String upPath = req.getServletContext().getRealPath("files");
			File file = new File(upPath, pimage);
			if (file.exists()) {
				file.delete();
				msg = "��ǰ���� ����(�̹��� ������ ����)!! ��ǰ����������� �̵��մϴ�.";
			}else {
				msg = "��ǰ���� ����(�̹��� ������ ����)!! ��ǰ����������� �̵��մϴ�.";
			}
		}else {
			msg = "��ǰ���� ����!! ��ǰ����������� �̵��մϴ�.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:message.jsp";
	}
	
	@RequestMapping("/prod_view.do")
	public ModelAndView ProdView(HttpServletRequest req, @RequestParam int pnum, ProductDTO dto) {
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		return new ModelAndView("shop/admin/prod_view", "getProduct", dto);
	}

	@RequestMapping("/prod_update.do")
	public ModelAndView ProdUpdate(HttpServletRequest req, @RequestParam int pnum, ProductDTO dto) {
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		return new ModelAndView("shop/admin/prod_update", "getProduct", dto);
	}
	
	@RequestMapping("/prod_update_ok.do")
	public ModelAndView ProdUpdateOk(HttpServletRequest req, ProductDTO dto) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("pimage");
		String filename = mf.getOriginalFilename();
		if (filename != null && !filename.trim().equals("")) {
			String upPath = req.getServletContext().getRealPath("files");
			File file = new File(upPath, filename);
			mf.transferTo(file);
		}else {
			filename = req.getParameter("pimage2");
		}
				
		dto.setPimage(filename);
		
		int res = shopAdminMapper.updateProduct(dto);
		if (res>0) {
			req.setAttribute("msg", "��ǰ ���� ����!! ��ǰ ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "��ǰ ���� ����!! ��ǰ ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_input.do");
		}
		return new ModelAndView("forward:message.jsp");
	}
	
}