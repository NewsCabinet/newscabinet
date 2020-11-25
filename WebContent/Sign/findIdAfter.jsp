<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.sql.Connection" import="model.ManageUser" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	function windowClosed(){window.close();}
</script>


 	<h3>아이디 찾기</h3>
	<% 
		String userPhone = (String)request.getParameter("userPhone");
		
		System.out.println("아이디 찾기 번호 = " + userPhone);
		
		if(userPhone == null)
			userPhone = "1";
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String resultUserEmailId = ManageUser.searchUserEmailIdIdByPhone(conn, userPhone);
		
		if(resultUserEmailId == null){  
			out.print("<p> 이메일이 존재하지 않습니다. 다시한번 확인해주시기 바랍니다. </p>");%>
			<form action="findId.html" >
				<input type="submit" value="다시 찾기" >
			</form>
		<% }else { %>
			<p>회원가입시 사용한 아이디는 <b><%=resultUserEmailId%></b> 입니다. </p>
			<input type="submit" value="확인" onclick="windowClosed()">
		<% }%>

</body>
</html>