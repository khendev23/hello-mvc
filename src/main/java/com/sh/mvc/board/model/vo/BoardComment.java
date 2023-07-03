package com.sh.mvc.board.model.vo;

import java.sql.Date;

public class BoardComment {

	// 필드
	private int no;
	private int commentLevel;
	private String writer;
	private String content;
	private int boardNo;
	private int commentRef;
	private Date regDate;
	
	// 생성자
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardComment(int no, int commentLevel, String writer, String content, int boardNo, int commentRef,
			Date regDate) {
		super();
		this.no = no;
		this.commentLevel = commentLevel;
		this.writer = writer;
		this.content = content;
		this.boardNo = boardNo;
		this.commentRef = commentRef;
		this.regDate = regDate;
	}

	// 메소드
	// getter / setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentRef() {
		return commentRef;
	}

	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	// toString
	@Override
	public String toString() {
		return "BoardComment [no=" + no + ", commentLevel=" + commentLevel + ", writer=" + writer + ", content="
				+ content + ", boardNo=" + boardNo + ", commentRef=" + commentRef + ", regDate=" + regDate + "]";
	}
	
	
	
	
}
