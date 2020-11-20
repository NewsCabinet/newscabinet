<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
</head>
<body>
	<div style="text-align: center">
		<h3>아이디 중복 확인</h3>
		<%	boolean check = (boolean) request.getAttribute("checkID");
			if(check){
				out.print("사용가능한 아이디입니다.");
			}
			
			out.print("이미 존재하는 아이디입니다.");
			
		%>
			
		</form>
	</div>

</body>
</html>