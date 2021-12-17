package com.ezen.mavenTest;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import shop.admin.dto.ProductDTO;

public class CartBean {
	Hashtable<Integer, ProductDTO> ht;
	
	public CartBean() {
		ht = new Hashtable<>();
	}
	
	public boolean addCart(int pnum, ProductDTO dto) {
		if (ht.containsKey(pnum)) {
			ProductDTO dto2 = ht.get(pnum);
			dto2.setPqty(dto.getPqty() + dto2.getPqty());
		}else {
			ht.put(pnum, dto);
		}
		return true;
	}
	
	public Hashtable<Integer, ProductDTO> listCart(){
		return ht;
	}
	
	public void editCart(int pnum, int pqty) {
		if (pqty == 0) {
			deleteCart(pnum);
		}else {
			ProductDTO dto = ht.get(pnum);
			dto.setPqty(pqty);
		}
	}
	
	public int deleteCart(int pnum) {
		if (ht.remove(pnum) == null) {
			return 0;
		}else {
			return 1;
		}
	}
}













