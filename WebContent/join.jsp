<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ page import="model.ManageUser, java.sql.Connection, java.sql.ResultSet" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>HOME</title>
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="box-area">
		<header class="head">
			<div class="wrapper">
				<div class="logo">
                    <a href="#"><b>N</b>ews<b>C</b>abinet</a>
				</div>
				<nav>
					<a href="#">홈</a> 
					<a href="#">뉴스보기</a>
					 <a href="#">스크랩보기</a> 
					 <a href="#">기록보기</a>
				     <a href="#">기록작성</a>
				</nav>
			</div>
		</header>
		<div class="content-area">
			<div class="wrapper">
				<form method="post" action="doJoin">
					<p width="50%" text-align="left">
					<%!	
						String userID = null;
						boolean checkID = false;
						boolean checkDuplicatedID(String userID){
							ServletContext sc = getServletContext();
							Connection conn = (Connection) sc.getAttribute("DBconnection");
							ResultSet rs = ManageUser.searchUserByID(conn, userID);
							
							if (rs != null) {
								try {
									if (rs.next())  // 이미 존재하는 아이디
										return false;
									else 
										return true;
								} catch (Exception e) {
									e.printStackTrace();
								} // try end
							} 
							return false;
						} 
					%>
					
					
					아이디 : <input type="text" name="userid" required> 
					<% userID = request.getParameter("userid"); %>
					<input type="button" name="checkDuplicationID" value="아이디 중복 체크" onclick="checkDuplicatedID(userID)"> 
					<% if(checkDuplicatedID(userID)){
							checkID = true;
							out.print("<b>사용가능한 아이디입니다</b>");
						}else{
							out.print("이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요");
						}
					%>
					<% out.print("지금 동적으로 되는건가??"); %>
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
					
					<% if(checkID){
						out.print("<input type=" + "'submit'"+ "value=" + "'가입하기'/>");
					} else{
						out.print("아이디 중복을 확인해주세요");
					}
					%>
					<input type="submit" value="가입하기"/> 
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>