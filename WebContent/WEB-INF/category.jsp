<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet">
<title>SCRAPNEWS</title>

</head>
<body>
	<% Vector<String> category = (Vector)request.getAttribute("subcategory");%>
	<div class="box-area">
		<header class="head">
			<div class="wrapper">
				<div class="logo">
					<a href="#"><b>N</b>ews<b>C</b>abinet</a>
				</div>
				<nav>
					<a href="#">홈</a> <a href="#">뉴스보기</a> <a href="#">스크랩보기</a> <a
						href="#">기록보기</a> <a href="#">기록작성</a>
				</nav>
			</div>
		</header>
		<div class="categoryBox">
			<ul class="categoryList">
				<%
					for (int i = 0; i < category.size(); i++){
						out.println("<li>");
						out.println(category.elementAt(i));
						out.println("</li> ");
					}
				%>	
					</ul>
		</div>
	</div>
</body>
</html>
