<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>


	<nav class="navbar">
		<div class="navbar_logo">
			<img src="/NewsCabinet/images/NewsCabinetLogo.png">
			 <a href="/NewsCabinet/home.jsp">NewsCabinet</a>
		</div>

		<ul class="navbar_manu">
			<li><a href="/NewsCabinet/news/main">뉴스보기</a></li>
			<li><a href="/NewsCabinet/scrap/main">스크랩보기</a></li>
			<li><a href="/NewsCabinet/record/user/folder">나의 기록보기</a></li>
			<li><a href="/NewsCabinet/record/shared/main">공유된 기록보기</a></li>
			<li><a href="/NewsCabinet/record/user/write">기록작성</a></li>
		</ul>
		<div class="navbar_icons">
				<form method="POST" name="changeuser" action="/NewsCabinet/changeUser.jsp">
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
		<form method="POST" name="logout" action="/NewsCabinet/sign-out">
			  <% 
			if(session.getAttribute("userName")!= null || request.isRequestedSessionIdValid()){
				out.println("<button class=nameButton>" + "로그아웃" + "</button>");			}
				%>
			</form>

			
		</div>
		<a href="#" class="navbar_togglebtn"> <i class="fas fa-bars"></i>
		</a>
	</nav>
	<script>
	const toggleBtn = document.querySelector('.navbar_togglebtn');
	const menu = document.querySelector('.navbar_manu');
	const icons = document.querySelector('.navbar_icons');

	toggleBtn.addEventListener('click', () => {
 	menu.classList.toggle("active");
 	icons.classList.toggle("active");
}); 
</script>

