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
		String email = (String)request.getParameter("emailForId");
		
		System.out.println("아이디 찾기 Mail = " + email);
		
		if(email == null)
			email = "a";
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String resultUserId = ManageUser.searchUserIDByEmail(conn, email);
		
		if(resultUserId == null){  
			out.print("<p> 이메일이 존재하지 않습니다. 다시한번 확인해주시기 바랍니다. </p>");%>
			<form action="findId.html" >
				<input type="submit" value="다시 찾기" >
			</form>
		<% }else { %>
			<p>회원가입시 사용한 아이디는 <%=resultUserId%> 입니다. </p>
			<input type="submit" value="확인" onclick=windowClosed()>
		<% }%>

</body>
</html>