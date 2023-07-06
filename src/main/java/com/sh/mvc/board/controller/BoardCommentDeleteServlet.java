package com.sh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mvc.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		int no = Integer.parseInt(request.getParameter("commentNo"));
		
		// 2. 업무 로직
		int result = boardService.deleteBoardComment(no);
		
		// 3. 응답
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer); // /를 쓰든 http://같은 프로토콜을 쓰든 알아서 잡기 때문에 상관없다.
		
	}

}
