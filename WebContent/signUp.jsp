<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<link href="style.css?ver=1" rel="stylesheet">
<title>Home</title>
<script method="post" action="Sign/checkId.html">
		function idCheck(){
		//아이디 중복 확인 창띄우기
		window.open("Sign/checkId.html", "아이디 확인", "width=400 height=350")
		}
</script>
</head>
<body>
   <div class="box-area">
      <header class="head">
         <div class="wrapper">
            <div class="logo">
              <a href="/NewsCabinet/home.jsp"><b style="color: #bbb">N</b>ews<b
					style="color: #bbb">C</b>abinet</a>
            </div>
         </div>
      </header>
   </div>
		<div class="basic_contentzone" style='background-color: #C7C7C7'>
			<div class="login">
			<h2>SignUp</h2>
				<form method="post" action="SignUp">
					<input class="modifyLogin" type="email" name="userEmailId" placeholder="이메일을 입력해주세요" required>  
					<input class ="FindButton"type="button" name="checkID" value="중복 체크" onclick="idCheck()" >
        			 <input class="modifyLogin" type="password" name="userPassword" placeholder="비밀번호를 입력해주세요" required><br/>
        			 <input class="modifyLogin" type="text" name="userName" placeholder="이름을 입력해주세요" required><br/>
       			 	<input class="modifyLogin" type="tel" name="userPhone" placeholder="번호를 입력해주세요 010-9999-9999" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"required><br>
       			 	<input class="modifyLogin"  type="number" name="userAge" placeholder="나이를 입력해주세요" required><br/>
       			 	<div class="box1">
       				<p style='font-size:20px'>여성<input type="radio" name="userGender" value="true" checked></p> 
       			    <p style='font-size:20px'>남성<input type="radio" name="userGender"  value="false"></p> 
       					 </div>
       					 <br>
       			     <p style='font-size:15px'>관심 분야를 선택해주세요</p>
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

					<br />
					<br />
				  <input class="FindButton" style="top:20px" type="submit" value="가입하기"/>

				  
				</form>
			</div>
		</div>
</body>
</html>