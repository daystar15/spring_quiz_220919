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
	    <div class="contents text-center pt-5 pb-5">
	    	<h2 class="mb-3 font-weight-bold">예약 목록 보기</h2>
	    	<table class="table viewList">
	    		<thead>
	    			<tr>
	    				<th>이름</th>
		    			<th>예약날짜</th>
		    			<th>숙박일수</th>
		    			<th>숙박인원</th>
		    			<th>전화번호</th>
		    			<th>예약상태</th>
		    			<th></th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="list" items="${bookingList}">
	    				<tr>
		    				<td>${list.name}</td>
		    				<td>
		    					<fmt:formatDate value="${list.date}" pattern="yyyy년 MM월 dd일" />
		    				</td>
		    				<td>${list.day}</td>
		    				<td>${list.headcount}</td>
		    				<td>${list.phoneNumber}</td>
		    				<td>
		    					<c:choose>
		    						<c:when test="${list.state eq '대기중'}">
		    							<span class="text-info">${list.state}</span>
		    						</c:when>
		    						<c:when test="${list.state eq '확정'}">
		    							<span class="text-success">${list.state}</span>
		    						</c:when>
		    						<c:when test="${list.state eq '취소'}">
		    							<span class="text-danger">${list.state}</span>
		    						</c:when>
		    					</c:choose>
		    				</td>
		    				<td>
		    					<button type="button" class="btn btn-danger delBtn" data-booking-id="${list.id}">삭제</button>
		    				</td>
		    			</tr>
	    			</c:forEach>
	    		</tbody>
	    	</table>
	    </div>
	    <footer>
	      <p>제주특별자치도 제주시 애월읍</p>
	      <p>사업자등록번호: 111-22-255222 / 농어촌민박사업자지정 / 대표:김통목</p>
	      <p>Copyright 2025 tongnamu All right reserved</p>
	    </footer>
	</div>
	
	<script>
		$(document).ready(function() {
			$(".delBtn").on("click", function() {
				// data이름은 꼭 하이픈으로 짓기(대문자 넣으면 안됨)
				let id = $(this).data('booking-id');
				// console.log(id);
				
				$.ajax({
					// request
					type: "delete"
					, url: "/booking/delete_booking_list"
					, data: {"id":id} // "리퀘스트파라미터명":받아온변수(let으로 만든)
					
					// response
					, success:function(data) {
						// {"code":1, "result":"성공"}
						// {"code":1500, "result":"실패", "error_message":삭제하는데 실패했습니다.}
						// window.location.reload(true);
						if (data.code == 1) {
							// 새로고침
							window.location.reload(true);
						} else if (data.code == 500) {
							alert(daga.error_message);
						}
					}
					, erroe:function(e) {
						alert("삭제하는데 통신이 실패했습니다. " + e);
					}
				});
			})
		})
	</script>
</body>
</html>