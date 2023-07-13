<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우리들의 전국일주</title>
<link href="/img/favicon.png" rel="icon">
</head>
<body>
	<h1>게시판</h1>
	<div>
		<c:forEach var="m" items="${localNameList}">
			<a href="/board/boardList?localName=${m.localName}">${m.localName}(${m.cnt})</a>
		</c:forEach>
	</div>
	<table border="1">
		<tr>
			<th>지역명</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="b" items="${boardList}">
			<tr>
				<td>${b.localName}</td>
				<td><a href="/board/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
				<td>${b.memberId}</td>
				<td>${b.createdate}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="/board/addBoard">추가</a>
	</div>
	<div>	
	<br>
		<c:if test="${currentPage > 1}">
			<a href="/board/boardList?currentPage=${currentPage - 1}&localList=${localList}">이전</a>
		</c:if>
		&nbsp;<span>${currentPage}</span>&nbsp;
		<c:if test="${currentPage < lastPage}">
			<a href="/board/boardList?currentPage=${currentPage + 1}&localList=${localList}">다음</a>
		</c:if>
	</div>
</body>
</html>