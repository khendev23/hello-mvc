package com.sh.mvc.board.model.service;

import static com.sh.mvc.common.JdbcTemplate.close;
import static com.sh.mvc.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;

public class BoardService {
	private final BoardDao boardDao = new BoardDao();

	public List<Board> findAll() {
		Connection conn = getConnection();
		List<Board> boards = boardDao.findAll(conn);
		close(conn);
		
		return boards;
	}



}
