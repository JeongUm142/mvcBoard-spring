<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringBoard</title>
</head>
<body>
	<h1>게시물수정</h1>
	<c:set var="b" value="${board}"></c:set>
	<form action="/board/modifyBoard" method="post">
		<input type="hidden" name="boardNo" value="${b.boardNo}">
		<table>
				<tr>
					<th>지역명</th>
					<td>
					<select name="localName">
						<c:forEach var="l" items="${localList}">
							<!-- 선택한 지역명이 선택되도록 하기위한 변수지정 -->
							<c:set var="isSelected" value=""></c:set> 
							<!-- 앞에서 선택한 지역명과 셀렉트 된 지역과 같으면 선택 -->
							<c:if test="${b.localName == l.localName}">
								<c:set var="isSelected" value="selected"></c:set>
							</c:if>
							<option ${isSelected}>${l.localName}</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>	
					<th>제목</th>
					<td>
						<input type="text" name="boardTitle" value="${b.boardTitle}" required="required">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="boardContent" rows="4" cols="80" required="required">${b.boardContent}</textarea>
					</td>
				</tr>
				<tr>
					<th>작성자 확인</th>
					<td>
						<input type="text" name="memberId" placeholder="작성자를 입력해주세요">
					</td>
				</tr>
		</table>
		<button type="submit">수정</button>
		<a href="/board/boardOne?boardNo=${b.boardNo}">취소</a>
	</form>
</body>
</html>