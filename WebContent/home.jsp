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
	<p class="newsType"> NewsCabinet이란? </p>
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