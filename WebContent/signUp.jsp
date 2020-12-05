<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
					
					이메일 : <input type="email" name="userEmailId" id="userEmailId" required> 
					<input type="button" name="checkID" value="아이디 중복 체크" onclick="idCheck()" >
					
					<br/>
        			비밀번호 : <input type="text" name="userPassword" required><br/>
        			이름 : <input type="text" name="userName" required><br/>
       			 	핸드폰 번호 : <input type="tel" name="userPhone" placeholder="010-9999-9999" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"required><br>
       			 	나이 : <input type="number" name="userAge" required><br/>
       				성별 : 여성 <input type="radio" name="userGender" value="true" checked> 
       					 | 남성  <input type="radio" name="userGender"  value="false"> <br>
       			 	관심 분야를 선택해주세요 <br/>
					<select name="category" size="1">
						<option value="1">정치</option>
						<option value="2">경제</option>
						<option value="3">국제</option>
						<option value="4">사회</option>
						<option value="5">문화</option>
						<option value="6">IT</option>
						<option value="7">과학</option>
						<option value="8">스포츠</option>
					</select>
					<br>
				  <input type="submit" value="가입하기"/>
				  
					</p>
				</form>
			</div>
		</div>
</body>
</html>