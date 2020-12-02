<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 기록 보기</title>
</head>
<body>
	<p>나의 기록보기</p>
	<%
		ResultSet rs = (ResultSet) request.getAttribute("folders");
		if(rs!= null){
			while(true){
				if(rs.next()){
					out.println(rs.getString("folder_name") + "<br/>");
				}else{
					break;
				}
			}
		}
	%>
</body>
</html>