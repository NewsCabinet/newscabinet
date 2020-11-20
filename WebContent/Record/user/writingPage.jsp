<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.Calendar" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../../style.css" rel="stylesheet">
<title>Home</title>
</head>
<jsp:include page="../../newsHeader.html" />
<body>
	<div class="content-area">
		<div class="wrapper">
		<p> </p>
			<form method = "post" action="Record/userWrite">
				<h2>기록 작성하기</h2>
				<h3> 제목 &nbsp; <input type="text" name="recordTitle"></h3> <br>
				<%	request.setAttribute("newsId", 1);	
				Calendar cal = Calendar.getInstance(); 
				String recordDate = null;
				%>

				<h3>날짜  &nbsp; <%= cal.get(Calendar.YEAR) %>년 <%= cal.get(Calendar.MONTH)+1 %>월 <%= cal.get(Calendar.DATE) %>일 </h3><br>

				<%
					int firstCategory = 1;
					int subCategory = 3;
				%>
				<h3>
					카테고리 상위
					<%=firstCategory%>&nbsp; &nbsp; &nbsp; 
					하위<%=subCategory%>&nbsp;
				</h3><br>
				<h3>
					공개 설정 : 
					<input type="radio" name="recordPrivate" value="1" checked> 공개 
					<input type="radio" name="recordPrivate" value="2"> 비공개
				</h3><br>
				<h3>
					<a href="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004784909">뉴스 보러가기:</a> 
				</h3><br>
				<iframe
					src="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004784909"
					style="width: 90%; height: 300px; margin: auto"> </iframe>
				<br>
				<br>
				<textarea name="txtcomment" cols="110" rows="30">뉴스 url을 카테고리 처럼 선택할 수 있게 할까??</textarea>
				<br>
				<br>
				<button type="button">저장하기</button>
			</form>
			<br><br>
         </div>
      </div> 
</body>
</html>