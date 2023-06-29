package com.sh.mvc.board.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Board extends BoardEntity {

	private int attachCnt;
	private List<Attachment> attachments = new ArrayList<>();

	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(int no, String title, String writer, String content, int readCount, Date regDate, int attachCnt) {
		super(no, title, writer, content, readCount, regDate);
		this.attachCnt = attachCnt;
	}

	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(Attachment attach) {
		if(attach != null)
			this.attachments.add(attach);
	}
	
	@Override
	public String toString() {
		return "Board [attachCnt=" + attachCnt + ", attachments=" + attachments + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
	
}
