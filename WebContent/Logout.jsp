<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<meta charset="UTF-8">
<title>LOGOUT</title>
</head>
<body>

    <h1><b class="title">See You again!</b></h1>

<%
System.out.println("로그아웃");
    // 1: 기존의 세션 데이터를 모두 삭제
       session.invalidate();
 	    out.println("<script language='javascript'>");
 	    out.println("alert('재로그인 해주세요');");  
 	    out.println("window.open('index.html','toolbar=0')");
 	    out.println("</script>");
%>
</body>
</html>