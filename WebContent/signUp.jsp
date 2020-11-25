<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ page import="model.ManageUser, java.sql.Connection, java.sql.ResultSet" %>

<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet">
<title>Home</title>
<script method="post" action="Sign/checkId.html">
		function idCheck(){
		//아이디 중복 확인 창띄우기
		window.open("Sign/checkId.html", "아이디 확인", "width=400 height=350")
		}
</script>
</head>
<body>
		<div class="content-area">
			<div class="wrapper">
				<form method="post" action="SignUp">
					<p width="50%" text-align="left">
					
					
					아이디 : <input type="text" name="userid" required> 
					<input type="button" name="checkID" value="아이디 중복 체크" onclick="idCheck()" >
					
					<br/>
        			비밀번호 : <input type="password" name="passwd" required><br/>
        			이름 : <input type="text" name="name" required><br/>
       			 	이메일 : <input type="email" name="email"><br/> 
       			 	나이 : <input type="number" name="age"><br/>
       			 	관심 분야를 선택해주세요 <br/>
					<select name="category" size="1">
						<option value="1">정치</option>
						<option value="2">사회</option>
						<option value="3">경제</option>
						<option value="4">국제</option>
						<option value="5">문화</option>
						<option value="6">스포츠</option>
						<option value="7">IT</option>
						<option value="8">과학</option>
					</select>
					
				  <input type="submit" value="가입하기"/>
				  
					</p>
				</form>
			</div>
		</div>
</body>
</html>