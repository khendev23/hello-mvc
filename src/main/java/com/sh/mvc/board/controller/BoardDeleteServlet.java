package com.sh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.Board;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("deleteBoardNo"));
		
		// 2. 업무로직
		int result = boardService.deleteBoardById(no);
		HttpSession session = request.getSession();
		
		//3. 리다이렉트 처리
		session.setAttribute("msg", "삭제되었습니다.");
		response.sendRedirect(request.getContextPath() + "/board/boardList");
		
		
	}

}
