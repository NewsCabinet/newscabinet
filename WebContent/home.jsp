<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<link href="./style.css" rel="stylesheet">
<meta charset="UTF-8">
<title>HOME</title>
</head>

<body>
    <div class="box-area">
		<header class="head">
			<div class="wrapper">
			<form name ="changeuser" action="changeUser.jsp">
			   <% HttpSession userSession = request.getSession(false);
            	 String userName = (String)userSession.getAttribute("userName");
             	 out.println("<button class=nameButton>" + userName + "님 환영합니다!</button>"); %>
             </form>
				<div class="logo">
                    <a href="/NewsCabinet/home.jsp"><b style="color: #bbb">N</b>ews<b style="color: #bbb">C</b>abinet</a>
				</div>
				<nav>
					<a class=headerA href="/NewsCabinet/news/main">뉴스보기</a>
				    <a class=headerA href="#">나의 기록보기</a>
				    <a class=headerA href="#">다른사람 기록보기</a>
				    <a class=headerA href="/NewsCabinet/Record/user/writingPage.jsp">기록작성</a>
                </nav>
			</div>
        </header>
    </div>

</body>
</html>