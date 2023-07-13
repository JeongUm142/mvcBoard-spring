<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우리들의 전국일주</title>
<link href="/img/favicon.png" rel="icon">
</head>
<body>
	<h1>게시물삭제</h1>
	<c:set var="b" value="${board}"></c:set>
	<form action="/board/removeBoard" method="post">
		<input type="hidden" name="boardNo" value="${b.boardNo}">
		<table>
				<tr>
					<th>지역명</th>
					<td><input type="text" name="localName" value="${b.localName}" readonly="readonly"></td>
				</tr>
				<tr>	
					<th>제목</th>
					<td>
						<input type="text" name="boardTitle" value="${b.boardTitle}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="boardContent" rows="4" cols="80" readonly="readonly">${b.boardContent}</textarea>
					</td>
				</tr>
				<tr>
					<th>작성자 확인</th>
					<td>
						<input type="text" name="memberId" placeholder="삭제를 위해 작성자를 입력해주세요">
					</td>
				</tr>
		</table>
		<button type="submit">삭제</button>
		<a href="/board/boardOne?boardNo=${b.boardNo}">취소</a>
	</form>
</body>
</html>