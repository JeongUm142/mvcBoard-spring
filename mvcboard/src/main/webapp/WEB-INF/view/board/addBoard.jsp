<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringBoard</title>
</head>
<body>
	<h1>게시물입력</h1>
	<form action="/board/addBoard" method="post">
		<table>
			<tr>
				<th>지역명</th>
				<td>
					<select name="localName">
						<c:forEach var="l" items="${localList}">
							<option>${l.localName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>	
				<th>제목</th>
				<td>
					<input type="text" name="boardTitle" required="required">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="boardContent" rows="4" cols="80" required="required"></textarea>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="memberId" required="required">
				</td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>