<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>우리들의 전국일주</title>
	<link href="/img/favicon.png" rel="icon">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	<script>
		$(document).ready(function(){
			
			const x = [];
			const y = [];
			const barColors = ["#FFE0E2", "#E8B2C3", "#B0C7D9", "#FFD3CB", "#9CD6D0", "#D6FFBF", "#E8DAB2", "#CFA7E8", "#C1E5FF"];
			
			// 동기호출로 x와 y값을 셋팅 
			$.ajax({
				async : false, // true(비동기), false(동기)
				url : '/rest/localNameList',
				type : 'get',
				success : function(model) {
					// 백엔드 모델을 프론트 모델로 변경
					// model -> {'model' : [{localName:'부산'}, {cnt:10},...]}
					model.forEach(function(item, index) { // 문법으로 foreach 만들 때는 for(in)
						$('#target').append('<tr>');
						$('#target').append('<td>' + item.localName + '</td>');
						$('#target').append('<td>' + item.cnt + '</td>');
						$('#target').append('</tr>');
						
						// chart모델 생성
						x.push(item.localName);
						y.push(item.cnt);
					});
				}
			});
			
			new Chart("target2", {
				type: "pie",
				data: {
					labels: x,
					datasets: [{
						backgroundColor: barColors,
						data: y
					}]
				},
				options: {
					legend: {
						labels: { // x의 색, 크기
							fontColor: "#333",
							fontSize: 10
						}
					},
					title: { // 타이틀
						display: true,
						text: "우리들의 전국일주"
					}
				}
			});	
		});
	</script>
</head>
<body>
	<h1>Ajax API사용으로 local</h1>
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>게시글수</th>
			</tr>
		</thead>
		<tbody id="target">
		</tbody>
	</table>
	<canvas id="target2" style="width:150px; max-width:700px"></canvas>
</body>
</html>