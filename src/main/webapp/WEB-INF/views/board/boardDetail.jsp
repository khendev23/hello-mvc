<%@page import="java.util.List"%>
<%@page import="com.sh.mvc.board.model.vo.Attachment"%>
<%@page import="com.sh.mvc.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Board board = (Board) request.getAttribute("board");
	List <Attachment> attachments = board.getAttachments();
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%= board.getNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= board.getTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= board.getWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= board.getReadCount() %></td>
		</tr>
			<% if(attachments != null && !attachments.isEmpty()) { 
				for(Attachment attachment : attachments) { 
			%>
					<tr>
						<th>첨부파일</th>
						<td>
							<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
							<a href="<%= request.getContextPath()%>/board/fileDownload?no=<%= attachment.getNo() %>"><%= attachment.getOriginalFilename() %></a>
						</td>
					</tr>
				<% } %>
			<% } %>
		<tr>
			<th>내 용</th>
			<td>
				<textarea readonly style="resize:none;" rows="10"><%= board.getContent() %></textarea>
			</td>
		</tr>
		<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
		<% if(loginMember != null 
			&& (loginMember.getMemberRole() == MemberRole.A 
				|| loginMember.getMemberId().equals(board.getWriter()))) { %>
		<tr>
			<th colspan="2">
				<%-- 첨부파일이 없는 게시물 수정 --%>
				<input type="button" value="수정하기" onclick="updateBoard()">
				<input type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
		<% } %>
	</table>
</section>
<form name="boardDelFrm" action="<%= request.getContextPath() %>/board/boardDelete" method="post"></form>
<script>
	const deleteBoard = (e) => {
		if(confirm("정말 삭제하시겠습니까?"))
			document.boardDelFrm.submit();
	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>