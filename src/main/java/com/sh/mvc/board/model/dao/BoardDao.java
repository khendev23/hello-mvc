package com.sh.mvc.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.mvc.board.model.exception.BoardException;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.board.model.vo.BoardEntity;
import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.exception.MemberException;
import com.sh.mvc.member.model.vo.Gender;
import com.sh.mvc.member.model.vo.Member;
import com.sh.mvc.member.model.vo.MemberRole;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		// src/main/resources/sql/member/member-query.properties 작성
		// build/classes/sql/member/member-query.properties 톰캣용 읽기파일
		String filename = 
			BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
//			System.out.println("prop@dao = " + prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Board> findAll(Connection conn, int start, int end) {
		List<Board> boards = new ArrayList<>();
		String sql = prop.getProperty("findBoardAll");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Board board = handleBoardResultSet(rset);
					boards.add(board);
				}				
			}
		} catch(SQLException e) {
			throw new BoardException(e);
		}
		
		
		
		return boards;
	}

	private Board handleBoardResultSet(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		String title = rset.getString("title");
		String writer = rset.getString("writer");
		String content = rset.getString("content");
		int readCount = rset.getInt("read_count");
		Date regDate = rset.getDate("reg_date");
		int attachCnt = rset.getInt("attach_cnt");
		
		return new Board(no, title, writer, content, readCount, regDate, attachCnt);
	}

	public int getTotalContent(Connection conn) {
		int result = 0;
		String sql = prop.getProperty("getTotalContent");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery()
		) {
			while(rset.next()) {
				result = rset.getInt(1);				
			}
		} catch(SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}

	public int insertBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		// insert into board values (seq_board_no.nextval, ?, ?, ?, default, default) 
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return result;
	}

	public int getLastBoardNo(Connection conn) {
		int boardNo = 0;
		String sql = prop.getProperty("getLastBoardNo");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					boardNo = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new BoardException();
		}
		
		
		return boardNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException();
		}
		return result;
	}

}
