package com.ezen.project.model;

public class ReviewDTO {
	
	private int review_num;
	private int h_num;
	private int u_num;
	private String review_contents;
	private String review_joindate;
	private String review_image;
	private int review_star;
	private String review_nickname;
	private String review_roomtype;
	
	public String getReview_nickname() {
		return review_nickname;
	}
	public void setReview_nickname(String review_nickname) {
		this.review_nickname = review_nickname;
	}
	public String getReview_roomtype() {
		return review_roomtype;
	}
	public void setReview_roomtype(String review_roomtype) {
		this.review_roomtype = review_roomtype;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getH_num() {
		return h_num;
	}
	public void setH_num(int h_num) {
		this.h_num = h_num;
	}
	public int getU_num() {
		return u_num;
	}
	public void setU_num(int u_num) {
		this.u_num = u_num;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public String getReview_joindate() {
		return review_joindate;
	}
	public void setReview_joindate(String review_joindate) {
		this.review_joindate = review_joindate;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}

}
