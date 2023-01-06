<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기 목록</title>
  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
  
  <!-- bootstrap CDN link -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>즐겨 찾기 목록</h1>
		<table class="table">
			<thead>
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>주소</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="site" items="${list}" varStatus="status">
					<tr>
						<td>${status.count}</td> <!-- ${site.id}도 가능하다. -->
						<td>${site.name}</td>
						<td>${site.url}</td>
						<td>
							<!-- 1) name 속성 + value 속성 삭제 -->
							<%-- <button type="button" name="delBtn" class="btn btn-danger" value="${site.id}">삭제</button> --%>
							
							<!-- 2) data를 이용해서 태그에 임시 저장하기, data-뒤에 카멜케이스로 지으면 작동이 안됨(꼭 소문자 하이픈으로 짓기 -->
							<button type="button" class="del-btn btn btn-danger" data-favorite-id="${site.id}">삭제</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		$(document).ready(function(){
			//$("button[name=delBtn]").on("click", function(e) {
				/* value를 꺼내오는 방식 */
				// let id = $(this).val();
				// let id = $(this).attr('value');
				//let id = e.target.value;
				//alert(id);
				/* $.ajax({
					// request
					type:"delete"
					, url:"/lesson06/quiz02/after_delete_favorite"
					, data: {"id": id}
				
					// response
					, success:function(data) {
						alert(data);
						if (data == '성공') {
							location.reload();
						}
					}
					, error:function(e) {
						alert("에러")
					}
				}); */
			//})
			
			// 2) data를 이용해서 태그에 임시 저장하기
			// 태그: data-favorite-id  data- 뒤에 우리가 이름을 정한다.(대문자 절대 안됨)
			// 스크립트: $(this).data('favorite-id');
			$(".del-btn").on("click", function() {
				let id = $(this).data('favorite-id');
				// alert(id);
				
				$.ajax({
					// request
					type: "delete"
					, url: "/lesson06/quiz02/delete_favorite"
					, data: {"id":id}
				
					// response
					, success:function(data) {
						if (data.code == 1) {
							// 성공  location.reload가 잘 작동되지 않으면 앞에 window나 document를 붙이자.
							window.location.reload(true); // 새로고침
						} else if (data.code == 500) {
							// 에러
							alert(data.error_message);
						}
					}
					, error:function(e) {
						alert("에러 " + e); // ajax호출도 못하고 잘못됨
					}
				});
			});
		});
	</script>
</body>
</html>