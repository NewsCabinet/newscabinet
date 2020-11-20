<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>비밀번호 찾기</h3>
	<%
		String userPw = (String) request.getAttribute("userPw");
		if (userPw == null) {
		%>
			<p>해당 아이디와 이메일이 맞지 않습니다. 다시한번 확인해주시기 바랍니다.</p>
			<input type="submit" value="다시 찾기" action="../Sign/findPasswd.jsp">
	<% } else {%>
	 		<p>	회원가입시 사용한 비밀번호는 <%=userPw%> 입니다. </p>
			<input type="submit" value="로그인 화면으로 돌아가기" action="/index.html">
	<%
		}
	%>

</body>
</html>