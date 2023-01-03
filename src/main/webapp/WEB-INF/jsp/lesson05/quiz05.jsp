<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상청</title>
  <!-- bootstrap CDN link -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <link rel="stylesheet" type="text/css" href="/css/lesson05/style.css">
</head>
<body>
	<div id="wrap">
		<div class="d-flex">
			<header class="col-3 pl-0 pr-0">
				<h1 class="logo">
					<a href="#">
						<img src="/img/lesson05/logo.png" alt="기상청 로고" width="50px">
						기상청
					</a>
				</h1>
				<nav>
					<ul class="main-menu">
						<li><a href="#">날씨</a></li>
						<li><a href="/lesson05/add_weather_view">날씨입력</a></li>
						<li><a href="#">테마날씨</a></li>
						<li><a href="#">관측 기후</a></li>
					</ul>
				</nav>
			</header>
			<section class="contents col-9">
				<div class="inner">
					<h2 class="font-weight-bold">과거 날씨</h2>
					<table class="table text-center">
						<thead>
							<tr>
								<th>날짜</th>
								<th>날씨</th>
								<th>기온</th>
								<th>강수량</th>
								<th>미세먼지</th>
								<th>풍속</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="list">
								<tr>
									<td>
										<fmt:formatDate value="${list.date}" pattern="yyyy년 MM월 dd일" />
									</td>
									<td>
										<c:if test="${list.weather eq '맑음'}">
											<img src="/img/lesson05/sunny.jpg" alt="날씨">
										</c:if>
										<c:if test="${list.weather eq '비'}">
											<img src="/img/lesson05/rainy.jpg" alt="날씨">
										</c:if>
										<c:if test="${list.weather eq '흐림'}">
											<img src="/img/lesson05/cloudy.jpg" alt="날씨">
										</c:if>
										<c:if test="${list.weather eq '구름조금'}">
											<img src="/img/lesson05/partlyCloudy.jpg" alt="날씨">
										</c:if>
									</td>
									<td>${list.temperatures}℃</td>
									<td>${list.precipitation}mm</td>
									<td>${list.microDust}</td>
									<td>${list.windSpeed}km/h</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</div>
		<footer>
			<div class="inner d-flex align-items-center">
				<h1 class="footerLogo">
					<img src="/img/lesson05/foot_logo.png" alt="기상청" width="200px">
				</h1>
				<div class="footerInfo">
					<p>{07062} 서울시 동작구 여의대방로 16길 61</p>
					<p>Copyrght@2020 KMA. All Rights RESERVED.</p>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>