<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/NewsCabinet/style.css?ver=1" rel="stylesheet">
<style>
</style>
</head>
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
<jsp:include page="webHeader.jsp"></jsp:include>
<body>
	<section id="banner">
		<div class="content">
			<h1>
				<b>OWN YOUR MIND!</b>
			</h1>

		</div>
	</section>
	<form method="POST" name="chaxngeuser" action="user/manage">

		<div class="basic_contentzone">
			<div class="login">
				<h2>회원 정보 수정</h2>
				<input class="modifyLogin" type="email" name="userEmailId"
					placeholder="이메일을 입력해주세요" required> <br /> <input
					class="modifyLogin" type="passWord" name="userPassword"
					placeholder="비밀번호를 입력해주세요" required><br /> <input
					class="modifyLogin" type="text" name="userName"
					placeholder="이름을 입력해주세요" required><br /> <input
					class="modifyLogin" type="tel" name="userPhone"
					placeholder="번호를 입력해주세요 010-9999-9999"
					pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required><br> <input
					class="modifyLogin" type="number" name="userAge"
					placeholder="나이를입력해주세요" required> <br /> <br />
				<div class="box1">
					<p style='font-size: 20px'>
						여성 <input type="radio" name="userGender" value="true" checked>
					</p>
					<p style='font-size: 20px'>
						남성 <input type="radio" name="userGender" value="false">
					</p>
				</div>

				<p style='font-size: 15px'>관심 분야를 선택해주세요</p>
				<select name="category" size="1">
					<option value="1">정치</option>
					<option value="2">경제</option>
					<option value="3">국제</option>
					<option value="4">사회</option>
					<option value="5">문화</option>
					<option value="6">IT</option>
					<option value="7">과학</option>
					<option value="8">스포츠</option>
				</select> <br /> <br /> <input class="FindButton" type="submit"
					value="수정하기" />
			</div>
		</div>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>