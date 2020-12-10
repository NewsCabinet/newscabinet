<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<style>
.category{
	
}
</style>
</head>
<body>
	<div>
		<form method="post" action="/NewsCabinet/news/customCategory/management">
			<p> 내가 만든 카테고리 관리 </p>
		<%
			List customCategories = (ArrayList) request.getAttribute("customCategories");
			if (customCategories != null) {
				for (int i = 0; i < customCategories.size(); i++) {
			%>
					<label><input type="checkbox" name="removeCategory" value="<%=customCategories.get(i)%>"> <%=customCategories.get(i)%></label>
			<%
				}
			}
		%>
			<p><input type="submit" value="삭제"> <input type="reset" value="초기화"></p>
		</form>
	</div>
</body>
</html>