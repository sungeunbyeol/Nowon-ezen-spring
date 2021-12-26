package com.ezen.project;

import java.util.Hashtable;

import org.springframework.stereotype.Controller;

import com.ezen.project.model.WishListDTO;


@Controller
public class WishListController {
	
	Hashtable<Integer, WishListDTO> ht;
	
	public WishListController() {
		ht = new Hashtable<Integer, WishListDTO>();
	}
	
	public boolean addCart(int pnum, WishListDTO dto) {
		if(ht.containsKey(pnum)) {
			WishListDTO dto2 = ht.get(pnum);
			dto2.setPqty(dto.getPqty() + dto2.getPqty());
		}
		else {
			ht.put(pnum, dto);
		}
		return true;
	}
	
	public Hashtable<Integer, WishListDTO> listWish(){
		return ht;
	}
	
	public void editWish(int pnum, int pqty) {
		if (pqty == 0) {
			deleteWish(pnum);
		}else {
			WishListDTO dto = ht.get(pnum);
			dto.setPqty(pqty);
		}
	}
	
	public int deleteWish(int pnum) {
		if (ht.remove(pnum) == null) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public void clearWish() {
		ht = new Hashtable<Integer, WishListDTO>();
	}
}













