package com.sh.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.Board;


/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		
		
		// 2. 업무로직
		List<Board> boards = boardService.findAll();
		request.setAttribute("boards", boards);
		
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
	}

}
