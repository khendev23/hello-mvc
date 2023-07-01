package com.sh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.common.util.HelloMvcUtils;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		// 2. 업무로직
		// 게시글 읽음 여부 검사
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false; // 기본값 : 안읽음
		String boardCookieVal = "";
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if("boardCookie".equals(name)) {
					boardCookieVal = value; // 기존값 대입
					if(value.contains("[" + no + "]")) {
						hasRead = true;
					}
				}
			}
		}
		
		if(!hasRead) {
			int result = boardService.updateReadCount(no);
			
			// 쿠키생성
			Cookie cookie = new Cookie("boardCookie", boardCookieVal + "[" + no + "]");
			cookie.setPath(request.getContextPath() + "/board/boardDetail");
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie); // Set-Cookie : boardCookie=[10][20]
		}
		Board board = boardService.findById(no); // board, List<Attachment>
		System.out.println("board = " + board);
		
		// 2-1 Secure Coding 처리
		String unsecureTitle = board.getTitle();
		String secureTitle = HelloMvcUtils.escapeHtml(unsecureTitle);
		board.setTitle(secureTitle);
		
		// 3. 응답처리 jsp
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(request, response);
	}

}
