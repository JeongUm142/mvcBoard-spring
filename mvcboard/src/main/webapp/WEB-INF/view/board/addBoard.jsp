<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>우리들의 전국일주</title>
	<link href="/img/favicon.png" rel="icon">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#addfile").click(function(){
				if($(".inputfile").last().val() == ''){
					alert('빈 파일업로드가 태그가 있습니다');
				} else{
					$(".files").append('<input type="file" name="multipartFile" class="inputfile">');
				}
			});
			
			
			$("#delfile").click(function(){
				if($(".inputfile").length == 1) {
					return;
				}
				if($(".inputfile").last().val() != ''){
					alert('빈 파일업로드가 태그가 없습니다');
				} else {
					$(".inputfile").last().remove();
				}
			});
		});
	</script>
</head>
<body>
	<h1>게시물입력</h1>
	<form action="/board/addBoard" method="post" encType="multipart/form-data">
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
			<tr>
				<th>파일 추가</th>
				<td>	
					<button type="button" id="addfile">추가</button>
					<button type="button" id="delfile">삭제</button>
					<div class="files">
						<input type="file" name="multipartFile" class="inputfile">
					</div>
				</td>
			</tr>
		</table>
		<button type="submit">추가</button>
		<a href="/board/boardList">취소</a>
	</form>
</body>
</html>