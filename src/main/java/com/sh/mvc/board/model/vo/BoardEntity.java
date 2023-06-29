package com.sh.mvc.board.model.vo;

import java.sql.Date;

public class BoardEntity{

	private int no;
	private String title;
	private String writer;
	private String content;
	private int readCount;
	private Date regDate;
	
//	private int attachCnt; // 실제 없지만 필요한 것(비추) -> 자식 클래스를 만들어 넣는 것을 추천
	
	public BoardEntity() {
		super();
	}

	public BoardEntity(int no, String title, String writer, String content, int readCount, Date regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.readCount = readCount;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", readCount="
				+ readCount + ", regDate=" + regDate + "]";
	}
	
	
	
	
}
