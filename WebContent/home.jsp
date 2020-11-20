<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet">
<title>Home</title>
</head>

<body>
<div class="box-area">
	<header class="head">
		<div class="wrapper" width="1170px" margin="0">
			<div class="logo">
				<a href="#"><b>N</b>ews<b>C</b>abinet</a>
			</div>
			<nav>
				<a href="home.jsp">홈</a>
				 <a href="#">뉴스보기</a>
				  <a href="#">스크랩보기</a> 
				  <a href="#">기록보기</a> 
				  <a href="Record/user/writingPage.jsp">기록작성</a>
			</nav>
		</div>
	</header>
</div>


	<div class="content-area">
		<div class="wrapper">
			<p>
				<%	request.setCharacterEncoding("UTF-8");
					int signInCheck = (Integer) request.getAttribute("SignInCheck");
					HttpSession userSession = request.getSession(false);
				%>


			</p>
		</div>
	</div>
	
	
</body>
</html>