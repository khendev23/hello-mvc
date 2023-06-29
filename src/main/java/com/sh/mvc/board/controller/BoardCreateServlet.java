package com.sh.mvc.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.common.HelloMvcFileRenamePolicy;

/**
 * Servlet implementation class BoardCreateServlet
 */
@WebServlet("/board/boardCreate")
public class BoardCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardCreate.jsp").forward(request, response);
	}

	/**
	 * 파일 업로드
	 * 1. 파일 업로드 HttpServletRequest -> MultipartRequest (기존 request 객체는 사용불가)
	 * 2. 업로드파일정보를 DB attachment에 저장
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 업로드파일 저장경로
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/board");
		System.out.println("saveDirectory = " + saveDirectory);
		
		// 파일하나당 최대크기 10MB
		int maxPostSize = 1024 * 1024 * 10;
		
		// 인코딩
		String encoding = "utf-8";
		
		// 파일명 재지정 정책객체
		// 한글.txt -> 20230629_160430123_999.txt
		//FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 동일한 이름의 파일이 있을 때 기존 파일 지워지지 않게 해주는 객체//
		FileRenamePolicy policy = new HelloMvcFileRenamePolicy();
				
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		
		
		// 1. 사용자 입력값 처리
		String title = multiReq.getParameter("title");
		String writer = multiReq.getParameter("writer");
		String content = multiReq.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		// Attachment객체 생성 (Board 추가)
		Enumeration<String> filenames = multiReq.getFileNames(); // upFilel1, upFile2 값이 담겨있다.
		while(filenames.hasMoreElements()) {
			String name = filenames.nextElement(); // input:file[name]
			File upFile = multiReq.getFile(name);
			if(upFile != null) {
				Attachment attach = new Attachment();
				attach.setOriginalFilename(multiReq.getOriginalFileName(name));
				attach.setRenamedFilename(multiReq.getFilesystemName(name)); // renamedFilename
				board.addAttachment(attach);
			}
		}
		
		
		// 2. 업무로직
		// board와 attachment를 서블릿에서 따로따로 만들면 안된다.
		// 같은 conn을 공유해야하기 때문이다.
		int result = boardService.insertBoard(board);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "게시판 등록이 완료되었습니다.");
		
		// 3. 인덱스페이지 리다이렉트
		response.sendRedirect(request.getContextPath() + "/board/boardList");
	}

}
