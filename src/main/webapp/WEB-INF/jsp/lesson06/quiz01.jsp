<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
  
  <!-- bootstrap CDN link -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>즐겨 찾기 추가하기</h1>
		<div class="form">
			<div class="mb-3">
				<lable for="name">제목</lable>
				<input type="text" id="name" name="name" class="form-control">
			</div>
			<div class="mb-3">
				<lable for="url">주소</lable>
				<input type="text" id="url" name="url" class="form-control">
			</div>
			<input type="button" value="추가" class="btn btn-success w-100" id="go">
		</div>
	</div>
	<script>
		$(document).ready(function(){
			$("#go").on("click", function() {
				let name = $("#name").val().trim();
				if (name < 1) {
					alert("제목을 입력하세요");
					return;
				}
				
				let url = $("#url").val().trim();
				if (url < 1) {
					alert("주소를 입력하세요");
					return;
				}
				
				console.log(name);
				console.log(url);
				
				// AJAX
				$.ajax({
					// Request
					type: "POST"
					, url: "/lesson06/quiz01/add_favorite" // form의 action 주소
					, data: {"name": name, "url": url}
					
					// Response
					, success:function(data) {
						location.href="/lesson06/quiz01_1"
					}
					, error:function(e) {
						alert("에러")
					}
				})
			})
			
			
		});
	</script>
</body>
</html>