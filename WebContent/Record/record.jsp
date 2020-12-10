<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Record</title>
<link href="../style.css" rel="stylesheet">
<style>
.basic_contentzone {
   padding-top: 20px;
   position: relative;
   width: 100%;
   height: 500px;
   top: 100px;
}
</style>
</head>
<jsp:include page="../webHeader.jsp"></jsp:include>
<body>
	<div class="basic_contentzone">
		<%		
			ResultSet rs = (ResultSet) request.getAttribute("recordData");
			if (rs != null) {
				while (true) {
					if (rs.next()) {
						out.println("<h1> 제목 : " + rs.getString("record_title")+ "</h1");
						out.println("<p> 작성 날짜 : "+ rs.getString("record_title")+ "</p>");
						if(rs.getString("record_private").equals("1")){
							out.println("<p> 공개 설정 : 비공개 </p>");
						}else{
							out.println("<p> 공개 설정 : 공개 </p>");
						}
						out.println("<p> 작성 내용 : <br/> "+ rs.getString("record_comment")+ "</p>");
						
					} else {
						break;
					}
				}
			}
		%>
	</div>
</body>
</html>