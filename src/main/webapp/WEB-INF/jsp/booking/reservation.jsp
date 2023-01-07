<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통나무 팬션</title>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
  
    <!-- bootstrap CDN link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>  

    <!-- datepicker 라이브러리 -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
	<!-- css -->
	<link rel="stylesheet" href="/css/booking/style.css">
	
	<!-- js -->
	<script src=""></script>
</head>
<body>
	<div class="container">
		<header>
	      <h1 class="logo text-center">
	        <a href="#">통나무 팬션</a>
	      </h1>
	    </header>
	    <nav>
	      <ul class="nav nav-fill">
	        <li class="nav-item"><a href="#" class="nav-link">펜션소개</a></li>
	        <li class="nav-item"><a href="#" class="nav-link">객실보기</a></li>
	        <li class="nav-item"><a href="#" class="nav-link">예약하기</a></li>
	        <li class="nav-item"><a href="#" class="nav-link">예약목록</a></li>
	      </ul>
	    </nav>
	    <div class="contents pt-5 pb-5">
	    	<div class="reserForm">
	    		<h2>예약 하기</h2>
	    		<div class="form-group">
	    			<label for="name">이름</label>
	    			<input type="text" id="name" name="name" class="form-control">
	    		</div>
	    		<div class="form-group">
	    			<label for="date">예약날짜</label>
	    			<input type="text" id="date" name="date" class="form-control">
	    		</div>
	    		<div class="form-group">
	    			<label for="day">숙박일수</label>
	    			<input type="text" id="day" name="day" class="form-control">
	    		</div>
	    		<div class="form-group">
	    			<label for="headcount">숙박인원</label>
	    			<input type="text" id="headcount" name="headcount" class="form-control">
	    		</div>
	    		<div class="form-group">
	    			<label for="phoneNumber">전화번호</label>
	    			<input type="text" id="phoneNumber" name="phoneNumber" class="form-control">
	    		</div>
	    		<button type="button" class="btn btn-warning w-100" id="reserBtn">예약하기</button>
	    	</div>
	    </div>
	    <footer>
	      <p>제주특별자치도 제주시 애월읍</p>
	      <p>사업자등록번호: 111-22-255222 / 농어촌민박사업자지정 / 대표:김통목</p>
	      <p>Copyright 2025 tongnamu All right reserved</p>
	    </footer>
	</div>
	
	<script>
		$(document).ready(function() {
			$("#date").datepicker({
				dateFormat: "yy-mm-dd"
			})
			
			$("#reserBtn").on("click", function() {
				let name = $("#name").val();
				if (name == '') {
					alert("이름을 입력해주세요.");
					return;
				}
				
				let date = $("#date").val();
				if (date == '') {
					alert("날짜를 선택해주세요.");
					return;
				}
				
				let day = $("#day").val();
				if (day == '') {
					alert("숙박일수를 입력해주세요");
					return;
				}
				
				let headcount = $("#headcount").val();
				if (headcount == '') {
					alert("숙박인원을 입력해주세요");
					return;
				}
				
				let phoneNumber = $("#phoneNumber").val();
				if (phoneNumber == '') {
					alert("휴대폰 번호를 입력해주세요");
					return;
				}
				
				$.ajax({
					type: "post"
					, url: "/booking/add_reservation"
					, data: {"name":name, 
							 "date":date, 
							 "day":day, 
							 "headcount":headcount, 
							 "phoneNumber":phoneNumber}
				
					, success:function(data) {
						alert(data.result);
						location.reload();
					}
					, error:function(e) {
						alert("실패 " + e);
					}
				})
			})
		})
	</script>
</body>
</html>