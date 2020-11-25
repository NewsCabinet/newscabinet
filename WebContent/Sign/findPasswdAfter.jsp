<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" import="model.ManageUser" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script>
	function windowClosed(){window.close();}
</script>
</head>
<body>


	<h3>비밀번호 찾기</h3>
	<%
		String userEmailId = (String)request.getParameter("userEmailId");
		String userPhone = (String)request.getParameter("userPhone");
		
		System.out.println("비밀번호 찾기 ID = " + userEmailId);
		System.out.println("비밀번호 찾기 phone = " + userPhone);
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String userPasswd = ManageUser.searchUserPasswdByEmailIdAndPhone(conn, userEmailId, userPhone);		
		
		if (userPasswd == null) {
		%>
			<p>해당 아이디와 이메일이 맞지 않습니다. 다시한번 확인해주시기 바랍니다.</p>
			<form action="findPasswd.html">
				<input type="submit" value="다시 찾기">
			</form>
			
	<% } else {%>
	 		<p>	회원가입시 사용한 비밀번호는 <%=userPasswd%> 입니다. </p>
	 		<input type="submit" value="확인" onclick=windowClosed()>	
		<%}%>
	

</body>
</html>