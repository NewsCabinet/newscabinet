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
<% %>
	<% Vector<String> category = (Vector)request.getAttribute("subcategory");%>
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
</body>
</html>
