<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" import="model.ManageUser" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 중복 확인</title>
	<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script>
		function windowClosed(){
			
		opener.document.getElementById("userEmailId").innerHTML = '<%=userEmail%>';
		consol.log("<%=userEmail%>");
			
		window.close();
		}
	</script>
</head>
<body>
	<%!String userEmail = ""; %>
	<%	String userInputEmailId = (String)request.getParameter("checkEmailId");
		System.out.println("입력 아이디 = " + userInputEmailId);
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		int result = ManageUser.serachUserEmailIdForCheck(conn, userInputEmailId);
	
		if(result != -1){%>
			<p> 이미존재하는 아이디입니다.<br> 다른 아이디를 사용해주세요 </p>
			<form action="checkId.html" >
				<input type="submit" value="다시찾기">
			</form>
		<%}else {%>
			<p> <b> <%=userInputEmailId%></b> 는  사용가능한 아이디입니다.</p>
			<% userEmail = userInputEmailId;
				System.out.println("userEmail = " + userEmail);
			%>
			
			<input type="submit" value="확인" onclick="windowClosed()">
		<% }%>
		
</body>
</html>