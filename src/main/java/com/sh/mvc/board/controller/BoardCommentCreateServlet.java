package com.sh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentCreateServlet
 */
@WebServlet("/board/boardCommentCreate")
public class BoardCommentCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardComment boardComment = new BoardComment(0, commentLevel, writer, content, boardNo, commentRef, null);
		System.out.println("boardComment = " + boardComment);
		
		// 2. 업무로직
		int result = boardService.insertBoardComment(boardComment);
		
		// 3. 응답처리 - redirect
		response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
		
	}

}
