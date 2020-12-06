<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./style.css?ver=1" rel="stylesheet">
<style>
.basic_contentzone {
	padding-top: 20px;
	position: relative;
	width: 100%;
	height: 630px;
	top: 100px;
}

body {
	overflow: hidden;
}
</style>
</head>

<jsp:include page="webHeader.jsp"></jsp:include>

<body>
	<form method="POST" name="chaxngeuser" action="user/changeuser">
		<div class="basic_contentzone" style='background-color: #C7C7C7'>
			<div class="login">
				<h2>회원 정보 수정</h2>
				<input class="modifyLogin" type="email" name="userEmailId" placeholder="이메일을 입력해주세요" required>  <br />
				<input class="modifyLogin" type="passWord" name="userPassword" placeholder="비밀번호를 입력해주세요" required><br />
				<input class="modifyLogin" type="text" name="userName" placeholder="이름을 입력해주세요" required><br /> 
				<input class="modifyLogin" type="tel" name="userPhone" placeholder="번호를 입력해주세요 010-9999-9999"
					pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required><br>
			    <input class="modifyLogin" type="number" name="userAge" placeholder="나이를입력해주세요" required>
			    <br /> 
			    <br />
			    <div class="box1">
				<p style='font-size:20px'>여성 <input type="radio" name="userGender" value="true" checked></p>
				<p style='font-size:20px'>남성 <input type="radio" name="userGender" value="false"></p>
				</div>

				<p style='font-size:15px'>관심 분야를 선택해주세요</p>
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
				<br />
				<br />
				<input class="FindButton" type="submit" value="수정하기" />
			</div>
		</div>
	</form>
</body>
</html>