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
<script>
function itemChange(){
	
	
	var keyboard = ["갈축","청축","적축"];
	var mouse = ["광마우스","유선마우스","비싼마우스","미키마우스"];
	var monitor = ["17인치","22인치","24인치","26인치"];
	 
	var selectItem = $("#select1").val();
	 
	var changeItem;
	  
	if(selectItem == "키보드"){
	  changeItem = keyboard;
	}
	else if(selectItem == "마우스"){
	  changeItem = mouse;
	}
	else if(selectItem == "모니터"){
	  changeItem =  monitor;
	}
	 
	$('#select2').empty();
	 
	for(var count = 0; count < changeItem.size(); count++){                
	   var option = $("<option>"+changeItem[count]+"</option>");
	   $('#select2').append(option);
	}
	 
}

</script>
<script language="JavaScript">
	function makeSubcategoryList(){
		String selectFirst = document.getElementById("firstCategory");
		
		var length = 5;
		
		for(var i = 0; i < length; i++){
			var op = new Option();
			op.value = "y" +  i;
			op.text = "y" +  i;
			document.getElementById("subCategory").add(op);
			//document.getElementById("subCategory")[document.getElementById("subCategory").length] = new Option("i","i");	

		}
	}
</script>

</head>
<body>
		<div class="content-area">
			<div class="wrapper">
				<form method="post" action="SignUp">
					<p width="50%" text-align="left">
					
					이메일 : <input type="email" name="userEmailId" required> 
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
						<option value="2">사회</option>
						<option value="3">경제</option>
						<option value="4">국제</option>
						<option value="5">문화</option>
						<option value="6">스포츠</option>
						<option value="7">IT</option>
						<option value="8">과학</option>
					</select>
					<p>
						카테고리 
						상위 <select name="firstCategory" id="firstCategory" onChange="makeSubcategoryList()" >
							    <option value="1">정치</option>
							    <option value="2">경제</option>
							    <option value="3">국제</option>
							    <option value="4">사회</option>
							    <option value="5">문화</option>
							    <option value="6">IT</option>
							    <option value="7">과학</option>
							    <option value="8">스포츠</option>
						</select> 
						<select name="subCategory" id="subCategory">
							<option value="1">default</option>
						</select>
		
					</p>
					

					<br>
				  <input type="submit" value="가입하기"/>
				  
					</p>
				</form>
			</div>
		</div>
</body>
</html>