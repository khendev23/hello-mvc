package com.sh.mvc.board.model.service;

import static com.sh.mvc.common.JdbcTemplate.close;
import static com.sh.mvc.common.JdbcTemplate.commit;
import static com.sh.mvc.common.JdbcTemplate.getConnection;
import static com.sh.mvc.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.board.model.vo.BoardComment;

public class BoardService {
	private final BoardDao boardDao = new BoardDao();

	public List<Board> findAll(int start, int end) {
		Connection conn = getConnection();
		List<Board> boards = boardDao.findAll(conn, start, end);
		close(conn);
		
		return boards;
	}

	public int getTotalContent() {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.getTotalContent(conn);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int insertBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			// board테이블 추가
			result = boardDao.insertBoard(conn, board);
			
			// 발급된 board.no를 조회
			int boardNo = boardDao.getLastBoardNo(conn);
			board.setNo(boardNo); // servlet에서 redirect시 사용
			System.out.println("boardNo = " + boardNo);
			
			// attachment 테이블 추가
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					// insert into attachment values (seq_attachment_no.nextval, )
					attach.setBoardNo(boardNo); // fk컬럼값 세팅
					result = boardDao.insertAttachment(conn, attach);
					System.out.println("result = " + result);
				}
			}
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public Board findById(int no) {
		Connection conn = getConnection();
		Board board = boardDao.findbyId(conn, no);
		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, no);
		board.setAttachments(attachments);
		close(conn);
		
		return board;
	}

	public int updateReadCount(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.updateReadCount(conn, no);		
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Attachment findAttachmentById(int no) {
		Connection conn = getConnection();
		
		Attachment attach = boardDao.findAttachmentById(conn, no);
		close(conn);
		
		return attach;
	}

	public int deleteBoardById(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoardById(conn, no);		
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateBoard(Board board) {
	      int result = 0;
	      Connection conn = getConnection();
	      try {
	         
	         // board 테이블 추가
	         result = boardDao.updateBoard(conn, board);
	         
	         // attachment 테이블 추가
	         List<Attachment> attachments = board.getAttachments();
	         if (attachments != null && !attachments.isEmpty()) {
	            for(Attachment attach : attachments) {
	               result = boardDao.insertAttachment(conn, attach);               
	            }
	         }
	         commit(conn);
	      } catch (Exception e) {
	         rollback(conn);
	         throw e;
	      } finally {
	         close(conn);
	      }
	      
	      return result;
	   }

	public int deleteAttachment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteAttachment(conn, no);		
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.insertBoardComment(conn, boardComment);		
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<BoardComment> findBoardCommentByBoardNo(int no) {
		Connection conn = getConnection();
		List<BoardComment> boardComment = boardDao.findBoardCommentByBoardNo(conn, no);
		close(conn);
		
		return boardComment;
	}

	public int deleteBoardComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoardComment(conn, no);		
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}



}
