package com.ezen.project.model;

public class UserPointDTO {
	private int p_num;
	private int book_num;
	private int u_num;
	private String p_status;
	private String p_contents;
	private String book_savepoint;
	private String u_point;
	private String book_joindate;
	
	public String getBook_savepoint() {
		return book_savepoint;
	}
	public void setBook_savepoint(String book_savepoint) {
		this.book_savepoint = book_savepoint;
	}
	public String getU_point() {
		return u_point;
	}
	public void setU_point(String u_point) {
		this.u_point = u_point;
	}
	public String getBook_joindate() {
		return book_joindate;
	}
	public void setBook_joindate(String book_joindate) {
		this.book_joindate = book_joindate;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getBook_num() {
		return book_num;
	}
	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}
	public int getU_num() {
		return u_num;
	}
	public void setU_num(int u_num) {
		this.u_num = u_num;
	}
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public String getP_contents() {
		return p_contents;
	}
	public void setP_contents(String p_contents) {
		this.p_contents = p_contents;
	}
	
}
