<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<!-- css -->
	<link rel="stylesheet" href="/css/booking/style.css">
	
	<!-- js -->
	
	<!-- <script src="/js/booking/main.js"></script> -->
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
    <div class="contents">
      <div class="mainBanner d-flex">
        <img src="/img/booking/test06_banner1.jpg" id="bannerImg" alt="통나무 팬션 배너1">
      </div>
      <div class="mainBox d-flex">
        <div class="nowReser col-4 d-flex align-items-center justify-content-center">
          <h2>
            실시간<br>
            예약하기
          </h2>
        </div>
        <div class="reserBox col-4 pt-3 pb-3">
          <div class="inner">
          	<h4>예약확인</h4>
          	<div class="form-group d-flex align-items-center mt-3">
	            <label for="name">이름:</label>
	            <input type="text" name="name" id="name" class="form-control">
	          </div>
	          <div class="form-group d-flex align-items-center">
	            <label for="phoneNumber">전화번호:</label>
	            <input type="text" name="phoneNumber" id="phoneNumber" class="form-control">
	          </div>
	          <button class="btn btn-success findBtn">조회</button>
          </div>
        </div>
        <div class="callBox col-4 d-flex align-items-center justify-content-center">
        	<h3>
        		<a href="tel)010-0000-1111">
        			010-<br>
        			0000-1111
        		</a>
        	</h3>
        </div>
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
  		$(".findBtn").on("click", function() {
  			let name = $("#name").val().trim();
  			if (name == '') {
  				alert("예약시 작성한 이름을 입력해주세요.");
  				return;
  			} 
  				
  			let phoneNumber = $("#phoneNumber").val().trim();
  			if (phoneNumber == '') {
  				alert("예약시 작성한 휴대폰번호를 입력해주세요.");
  				return;
  			}
  			
  			// ajax
  			$.ajax({
  				// request
  				type: "post"
  				, url: "/booking/reservation_search"
  				, data: {"name":name, "phoneNumber":phoneNumber}
  				
  				// response
  				, success:function(data) {
  	  				if (data.code == 1) { // 조회된 내역 있을 때
  	  					let message = "이름 : " + data.booking.name + 
  	  					"\n날짜 : " + data.date + 
  	  					"\n일수 : " + data.booking.day + 
						"\n인원 : " + data.booking.headcount  + 
						"\n상태 : " + data.booking.state;
	  	  				alert(message);
  	  				} else { // 조회된 내역 없을 때 또는 에러 상황
  	  					alert("예약 내용이 없습니다.");
  	  				}
  				}
  				, error:function(e) {
  					alert("조회에 실패했습니다.");
  				}
  			})
  		})
  	})
  </script>
</body>
</html>