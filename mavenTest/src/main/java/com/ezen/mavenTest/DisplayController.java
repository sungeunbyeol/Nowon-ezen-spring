package com.ezen.mavenTest;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.mavenTest.model.CategoryDTO;
import com.ezen.mavenTest.model.ProductDTO;
import com.ezen.mavenTest.service.ShopAdminMapper;

@Controller
public class DisplayController {

	@Autowired
	ShopAdminMapper shopAdminMapper;
	
	@Autowired
	private ProductList productList;
	
	@Autowired
	private CartBean cartBean;
	
	@RequestMapping("/shop.do")
	public ModelAndView ShopMall(HttpServletRequest req) {
		List<CategoryDTO> list = shopAdminMapper.listCate();
		
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		List<ProductDTO> plist = productList.selectBySpec("hit");
		List<ProductDTO> plist2 = productList.selectBySpec("new");
		List<ProductDTO> plist3 = productList.selectBySpec("best");
		req.setAttribute("hit", plist);
		req.setAttribute("pnew", plist2);
		req.setAttribute("best", plist3);
		return new ModelAndView("shop/display/mall");
	}
	
	@RequestMapping("/mall_cgProdList.do")
	public ModelAndView MallCgProdList(HttpServletRequest req) {
		List<CategoryDTO> list = shopAdminMapper.listCate();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		String code = req.getParameter("code");
		List<ProductDTO> plist = productList.selectByCode(code);
		req.setAttribute("code", plist);
		return new ModelAndView("shop/display/mall_cgProdList");
	}
	
	@RequestMapping("/mall_prodView.do")
	public ModelAndView MallProdView(HttpServletRequest req, @RequestParam Map<String, String>params) {
		List<CategoryDTO> list = shopAdminMapper.listCate();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		ProductDTO dto = productList.getProduct(Integer.parseInt(params.get("pnum")), params.get("select"));
		
		return new ModelAndView("shop/display/mall_prodView", "getProduct", dto);
	}
	
	@RequestMapping("/cartAdd.do")
	public String CartAdd(HttpServletRequest req, @RequestParam Map<String, String> params) {
		ProductDTO dto = productList.getProduct(Integer.parseInt(params.get("pnum"), params.get("select"));
		dto.setPqty(ServletRequestUtils.getIntParameter(req, "qty"));
		boolean addCart = cartBean.addCart(Integer.parseInt(params.get("pnum"), dto);
		return "redirect:cartList.do";
	}
	
	@RequestMapping("/cartList.do")
	public ModelAndView CartList(HttpServletRequest req) {
		Hashtable<Integer, ProductDTO> ht = cartBean.listCart();
		List<CategoryDTO> list = shopAdminMapper.listCate();
		req.setAttribute("upPath", req.getServletContext().getRealPath("files"));
		req.setAttribute("cateList", list);
		
		return new ModelAndView("shop/display/mall_cartList", "cartList", ht);
	}
	
	@RequestMapping("/cartEdit.do")
	public ModelAndView CartEdit(@RequestParam Map<String, String> params) {
		cartBean.editCart(params.get("pnum"), params.get("pqty"));
		
		return new ModelAndView("redirect:cartList.do");
	}
	
	@RequestMapping("/cartDel.do")
	public ModelAndView CartDel(@RequestParam int pnum) {
		int res = cartBean.deleteCart(pnum);

		return new ModelAndView("redirect:cartList.do");
	}	
	
}
