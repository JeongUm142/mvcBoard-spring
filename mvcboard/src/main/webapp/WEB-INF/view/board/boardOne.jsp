<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringBoard</title>
</head>
<body>
	<h1>상세보기</h1>
		<c:set var="b" value="${board}"></c:set>
	<table>
				<tr>
					<th>지역명</th>
					<td>
						${b.localName}
					</td>
				</tr>
				<tr>	
					<th>제목</th>
					<td>
						${b.boardTitle}
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						${b.boardContent}
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						${b.memberId}
					</td>
				</tr>
	</table>
	<a href="/board/modifyBoard?boardNo=${b.boardNo}">수정</a>
	<a href="/board/removeBoard?boardNo=${b.boardNo}">삭제</a>
	<a href="/board/boardList">뒤로가기</a>
</body>
</html>