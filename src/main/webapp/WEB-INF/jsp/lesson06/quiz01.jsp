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
				<div class="d-flex">
					<input type="text" id="url" name="url" class="form-control">
					<input type="button" value="중복 확인" class="btn btn-info" id="urlCheckBtn">
				</div>
				<small id="urlStatusArea"></small>
			</div>
			<input type="button" value="추가" class="btn btn-success w-100" id="go">
		</div>
	</div>
	<script>
		$(document).ready(function(){
			
			// 중복확인 버튼 클릭
			$("#urlCheckBtn").on("click", function(){

				let url = $("#url").val().trim();
				// 초기화
				$("#urlStatusArea").empty();
				// url 중복 확인
				$.ajax({
					// request
					type:"POST"
					, url: "/lesson06/quiz02/is_duplication"
					, data: {"url":url}
				
					// reponse
					// 0일 때 중복아님 = false / true면 중복
					, success:function(data) {
						alert(data.is_duplication);
						if (data.is_duplication) {
							$("#urlStatusArea").append('<span class="text-danger">중복된 url입니다.</span>');
						} 
						if (!data.is_duplication){
							$("#urlStatusArea").append('<span class="text-danger">저장가능한 url입니다.</span>');
						}
					}
					, error:function(e) {
						alert("실패" + e)
					}
				});
			})
			
			$("#go").on("click", function() {
				// validation
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
				
				// http로 시작하지도 않고, https로 시작하지도 않으면 alert
				if (url.startsWith('http') == false && url.startsWith('https') == false) {
					alert("주소 형식이 잘못되었습니다.");
					return;
				}
				
				// 데이터 확인
				console.log(name);
				console.log(url);
				
				// AJAX 통신
				$.ajax({
					// Request
					type: "POST" // 메소드를 무엇으로 할지
					, url: "/lesson06/quiz01/add_favorite" // form의 action 주소
					, data: {"name": name, "url": url}
					
					// Response
					, success:function(data) {  // string json => object
						// alert(data); // Object Object
						if (data.result == "성공") {
							location.href = "/lesson06/quiz01/after_add_favorite_view"
						}
					}
					, error:function(e) {
						alert("에러" + e);
					}
				});
			});
		});
	</script>
</body>
</html>