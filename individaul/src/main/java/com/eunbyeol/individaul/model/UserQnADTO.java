package com.eunbyeol.individaul.model;

public class UserQnADTO {

	private int qna_no;
	private int id;
	private String qna_subject;
	private String qna_contents;
	private String qna_target_type;
	private String qna_purpose_type;
	
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_contents() {
		return qna_contents;
	}
	public void setQna_contents(String qna_contents) {
		this.qna_contents = qna_contents;
	}
	public String getQna_target_type() {
		return qna_target_type;
	}
	public void setQna_target_type(String qna_target_type) {
		this.qna_target_type = qna_target_type;
	}
	public String getQna_purpose_type() {
		return qna_purpose_type;
	}
	public void setQna_purpose_type(String qna_purpose_type) {
		this.qna_purpose_type = qna_purpose_type;
	}

	
}
