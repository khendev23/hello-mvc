package com.sh.mvc.board.model.vo;

import java.sql.Date;

public class Attachment {

	private int no;
	private int boardNo;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;
	
	public Attachment() {
		super();
	}

	public Attachment(String originalFilename, String renamedFilename) {
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
	}
	
	public Attachment(int no, int boardNo, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getRenamedFilename() {
		return renamedFilename;
	}

	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}

	public Date getRegDdate() {
		return regDate;
	}

	public void setRegDdate(Date regDdate) {
		this.regDate = regDdate;
	}

	@Override
	public String toString() {
		return "Attachment [no=" + no + ", boardNo=" + boardNo + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", regDdate=" + regDate + "]";
	}
	
	
	
	
	
}
