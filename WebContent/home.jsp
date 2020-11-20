<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/style.css" rel="stylesheet">
<title>Home</title>
</head>
<jsp:include page="newsHeader.html" />
<body>
	<div class="content-area">
		<div class="wrapper">
			<p>
				<%
					int signInCheck = (Integer) request.getAttribute("SignInCheck");
					HttpSession userSession = request.getSession(false);

					if (signInCheck == 1) {
						out.print("로그인에 성공하였습니다.");
						if(session != null){
							out.println(userSession.getAttribute("userId"));
							out.println(userSession.getAttribute("userName"));
							out.println(userSession.getAttribute("userCategory"));
						}
						
					} else if (signInCheck == 0)
							out.print("잘못된 비밀번호 입니다. 다시 시도해주세요");
						else
							out.print("없는 아이디입니다. 다시 시도해주세요");
					
				%>

				<a href="index.html"><input type="button" value="다시로그인하기"></a>

			</p>
		</div>
	</div>
	

	
			
</body>
</html>