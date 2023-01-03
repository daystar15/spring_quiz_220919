<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<a href="/lesson05/weather_history_view">
						<img src="/img/lesson05/logo.png" alt="기상청 로고" width="50px">
					기상청
					</a>
				</h1>
				<nav>
					<ul class="main-menu">
						<li><a href="#">날씨</a></li>
						<li><a href="#">날씨입력</a></li>
						<li><a href="#">테마날씨</a></li>
						<li><a href="#">관측 기후</a></li>
					</ul>
				</nav>
			</header>
			<section class="contents col-9">
				<div class="inner">
					<h2 class="font-weight-bold">날씨 입력</h2>
					<form method="post" action="/lesson05/add_weather">
						<div class="d-flex">
							<div class="form-group d-flex align-items-center mr-3">
								<label for="date">날짜</label>
								<input type="text" id="date" name="date" class="form-control w-50">
							</div>
							<div class="form-group d-flex align-items-center mr-3">
								<label for="weather">날씨</label>
								<select id="weather" name="weather" class="form-control">
									<option>맑음</option>
									<option>흐림</option>
									<option>비</option>
									<option>눈</option>
								</select>
							</div>
							<div class="form-group d-flex align-items-center">
								<label for="microDust" class="microDust">미세먼지</label>
								<select id="microDust" name="microDust" class="form-control">
									<option>좋음</option>
									<option>보통</option>
									<option>나쁨</option>
									<option>최악</option>
								</select>
							</div>
						</div>
						<div class="d-flex justify-content-between">
							<div class="form-group d-flex align-items-center mr-3">
								<label for="temperatures" class="w-25">기온</label>
								<div class="input-group">
									<input type="text" id="temperatures" name="temperatures" class="form-control">
									<div class="input-group-append">
										<span class="input-group-text">℃</span>
									</div>
								</div>
							</div>
							<div class="form-group d-flex align-items-center mr-3">
								<label for="precipitation">강수량</label>
								<div class="input-group">
									<input type="text" id="precipitation" name="precipitation" class="form-control">
									<div class="input-group-append">
										<span class="input-group-text">mm</span>
									</div>
								</div>
							</div>
							<div class="form-group d-flex align-items-center">
								<label for="windSpeed" class="w-25">풍속</label>
								<div class="input-group">
									<input type="text" id="windSpeed" name="windSpeed" class="form-control">
									<div class="input-group-append">
										<span class="input-group-text">km/h</span>
									</div>
								</div>
							</div>
						</div>
						<input type="submit" value="저장" class="btn btn-success">
					</form>
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