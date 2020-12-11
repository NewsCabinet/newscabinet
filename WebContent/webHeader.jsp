<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page import="javax.servlet.http.HttpSession" %>
	
<div class="box-area">
	<header class="head">
		<div class="wrapper">
			<form method="POST" name="changeuser" action="/NewsCabinet/changeUser.jsp">
				<%
            	  if(session.getAttribute("userName")!= null){
                	 out.println("<button class=nameButton>"  + session.getAttribute("userName") + "님 환영합니다!</button>"); 
            	  }
            	  else{
            	   out.println("<p class=nameButton> SignIn session이 종료되었습니다.</p>");
            	  }
            	 %>
			</form>
			<div class="logo">
				<a href="/NewsCabinet/home.jsp"><b style="color: #bbb">N</b>ews<b
					style="color: #bbb">C</b>abinet</a>
			</div>
			<nav>
				<a class=headerA href="/NewsCabinet/news/main">뉴스보기</a> 
				<a class=headerA href="/NewsCabinet/scrap/main">스크랩보기</a>

				<a class=headerA href="/NewsCabinet/UserRecord/main">나의 기록보기</a> 
				<a class=headerA href="/NewsCabinet/OthersRecord/main">다른사람의 기록보기</a>

				<a class=headerA href="/NewsCabinet/UserRecord/write">기록작성</a>
			</nav>
		</div>
	</header>
</div> 