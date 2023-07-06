package com.sh.mvc.board.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Board extends BoardEntity {

	private int attachCnt;
	private List<Attachment> attachments = new ArrayList<>();
	private int commentCnt;
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(int no, String title, String writer, String content, int readCount, Date regDate, int attachCnt, int commentCnt) {
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
	
	
	
	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	@Override
	public String toString() {
		return "Board [attachCnt=" + attachCnt + ", attachments=" + attachments + ", commentCnt=" + commentCnt + "]";
	}


	
	
	
	
}
