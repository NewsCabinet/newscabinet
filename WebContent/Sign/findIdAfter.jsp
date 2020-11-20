<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 	<h3>아이디 찾기</h3>
	<% String userId = (String)request.getAttribute("userId");
		
		if(userId == null){  %>
			<p> 이메일이 존재하지 않습니다. 다시한번 확인해주시기 바랍니다. </p>
			<input type="submit" value="다시 찾기" method="get" action="Sign/findId.jsp">
		<% }else { %>
			<p>회원가입시 사용한 아이디는 <%=userId%> 입니다. 
			</p>
			<input type="submit" value="로그인 화면으로 돌아가기" action="/index.html">
		<% }%>

	
</body>
</html>