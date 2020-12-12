<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<div class="box-area">
	<header class="head">
		<div class="wrapper">
		<!-- action="/NewsCabinet/Logout.jsp" -->
			<form method="POST" name="logout" action="/NewsCabinet/Logout.jsp">

			  <% 
			if(session.getAttribute("userName")!= null || request.isRequestedSessionIdValid()){
				out.println("<button class=nameButton>" + "로그아웃" + "</button>");
			}
				%>
			</form>

			<form method="POST" name="changeuser"
				action="/NewsCabinet/changeUser.jsp">
				<%
            	  if(session.getAttribute("userName")!= null || request.isRequestedSessionIdValid()){
                	 out.println("<button class=nameButton>"  + session.getAttribute("userName") + "</button>"); 
            	  }
            	  else{
            		  if(session.getAttribute("userName") == null || !request.isRequestedSessionIdValid())
            	   				out.println("<p class=nameButton> session이 종료되었습니다.</p>");
            	  }
            	 %>
			</form>

			<div class="logo">
				<a href="/NewsCabinet/home.jsp"><b style="color: #bbb">N</b>ews<b
					style="color: #bbb">C</b>abinet</a>
			</div>
			<nav>
				<a class=headerA href="/NewsCabinet/news/main" onclick="reload()">뉴스보기</a> <a
					class=headerA href="/NewsCabinet/scrap/main">스크랩보기</a> <a
					class=headerA href="/NewsCabinet/UserRecord/main">나의 기록보기</a> <a
					class=headerA href="/NewsCabinet/OthersRecord/main">다른사람의 기록보기</a>
				<a class=headerA href="/NewsCabinet/UserRecord/write">기록작성</a>
			</nav>
		</div>
	</header>
</div>
