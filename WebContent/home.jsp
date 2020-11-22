<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<link href="style.css" rel="stylesheet">
</head>
<body>
	<div class="box-area">
		<header class="head">
			<div class="wrapper">
				<div class="logo">
					<a href="#"><b>N</b>ews<b>C</b>abinet</a>
				</div>
				<nav>
					<a href="#">홈</a> <a href="news.jsp">뉴스보기</a> <a href="#">스크랩보기</a> <a
						href="#">기록보기</a> <a href="Record/user/writingPage.jsp">기록작성</a>
				</nav>
			</div>
		</header>

		<div class="banner-area">
			<h2>
				<b class="title">Own your mind!</b>
			</h2>
			<script>
			$(function(){
				$(".title").fadeIn(2000);
			});
			</script>
		</div>
	</div>
	<div class="content-area">여기서 div하나 만들어서 작업하면 됨.</div>
</body>
</html>