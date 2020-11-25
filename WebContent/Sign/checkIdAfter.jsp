<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" import="model.ManageUser" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<script>
	function windowClosed(){window.close();}
</script>
</head>
<body>
	<%	String userInputId = (String)request.getParameter("checkId");
		System.out.println("입력 아이디 = " + userInputId);
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		int result = ManageUser.serachUserIDForCheck(conn, userInputId);
	
		if(result != -1){%>
			<p> 이미존재하는 아이디입니다.<br> 다른 아이디를 사용해주세요 </p>
			<form action="checkId.html" >
				<input type="submit" value="다시찾기">
			</form>
		<%}else {%>
			<p> 사용가능한 아이디입니다.</p>
			<input type="submit" value="확인" onclick=windowClosed()>
		<% }%>
</body>
</html>