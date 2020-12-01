<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="newsHeader.html"></jsp:include>

<body>
	<form name ="changeuser" action="user/changeuser">
        			
        			<!--  비밀번호 : <input type="text" name="userPassword" required><br/>
        			이름 : <input type="text" name="userName" required><br/>
       			 	핸드폰 번호 : <input type="tel" name="userPhone" placeholder="010-9999-9999" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"required><br>
       			 	나이 : <input type="number" name="userAge" required><br/>
       				성별 : 여성 <input type="radio" name="userGender" value="true" checked> 
       					 | 남성  <input type="radio" name="userGender"  value="false"> <br>
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
					-->
					이메일:<input type="email" name="userEmailId" required> 
					이름 : <input type="text" name="userName" required><br/>
					핸드폰 번호 : <input type="tel" name="userPhone" placeholder="010-9999-9999" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"required><br>
				    나이 : <input type="number" name="userAge" required><br/>
				    성별 : 여성 <input type="radio" name="userGender" value="true" checked> 
       					 | 남성  <input type="radio" name="userGender"  value="false"> <br>
       			    
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
				  <input type="submit" value="수정하기"/>
	</form>
</body>
</html>