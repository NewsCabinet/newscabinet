<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../style.css" rel="stylesheet">
<title>Home</title>
</head>
<body>
<<<<<<< HEAD
	<div class="box-area">
		<header class="head">
			<div class="wrapper">
				<div class="logo">
					<a href="#"><b>N</b>ews<b>C</b>abinet</a>
				</div>
				<nav>
					<a href="../../index.jsp">홈</a> <a href="news.jsp">뉴스보기</a> <a
						href="#">스크랩보기</a> <a href="#">기록보기</a> <a
						href="Record/user/writingPage.jsp">기록작성</a>
				</nav>
			</div>
		</header>
		<div class="basic_contentzone">
			<div class="wrapper">
				<form method="post" action="Record/userWrite">
					<div class="listWrite">
						<h2 style="text-align: left; margin-left: 30px">
							<b>기록 작성하기</b>
						</h2>
						<input class="listWriteInput" type="text" name="record_title"
							placeholder="제목을 입력해주세요"> <br>
						<%
							request.setAttribute("newsId", 1);
						Calendar cal = Calendar.getInstance();
						String recordDate = null;
						recordDate = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH) + 1) + cal.get(Calendar.DATE);
						request.setAttribute("recordDate", recordDate);
						%>
						<p>
							날짜: &nbsp;
							<%=cal.get(Calendar.YEAR)%>년
							<%=cal.get(Calendar.MONTH) + 1%>월
							<%=cal.get(Calendar.DATE)%>일
						</p>
						<br>
						<%
							int firstCategory = 1;
						int subCategory = 3;
						%>
						<p>
							카테고리: 상위
							<%=firstCategory%>&nbsp; &nbsp; &nbsp; 하위<%=subCategory%>&nbsp;
						</p>
						<br>
						<p>
							공개 설정 : <input type="radio" name="SelectPrivate" value="1"
								checked> 공개 <input type="radio" name="SelectPrivate"
								value="2"> 비공개
						</p>
						<br>
						<p>
							<a
								href="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004784909">
								뉴스 보러가기:</a>
						<p>
							<br>
							<iframe class="newsBox"
								src="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004784909"
								style="width: 90%; height: 300px; margin: auto"> </iframe>
							<br> <br>
							<textarea class="textBox" name="txtcomment" cols="150" rows="30">뉴스 url을 카테고리 처럼 선택할 수 있게 할까??</textarea>
						
							<br> <br>
							<button class ="writhingPageBtn"type="button">저장하기</button>
					</div>
				</form>
				<br> <br>

			</div>
		</div>
	</div>
=======
	<div class="content-area">
		<div class="wrapper">
		<p> </p>
			<form method = "post" action="Record/userWrite">
				<h2>기록 작성하기</h2>
				<h3> 제목 &nbsp; <input type="text" name="recordTitle"></h3> <br>
				<%	request.setAttribute("newsId", 1);	
				Calendar cal = Calendar.getInstance(); 
				String recordDate = null;
				recordDate = cal.get(Calendar.YEAR)+"."+ (cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DATE);
				request.setAttribute("recordDate", recordDate);
				
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
					<input type="radio" name="recordPrivate" value="false" checked> 공개 
					<input type="radio" name="recordPrivate" value="true"> 비공개
				</h3><br>
				<h3>
					<a href="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004784909">뉴스 보러가기:</a> 
				</h3><br>
				<iframe
					src="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004784909"
					style="width: 90%; height: 300px; margin: auto"> </iframe>
				<br>
				<br>
				<textarea name="recordComment" cols="110" rows="30">뉴스 url을 카테고리 처럼 선택할 수 있게 할까??</textarea>
				<br>
				<br>
				<button type="button">저장하기</button>
			</form>
			<br><br>
         </div>
      </div> 
>>>>>>> main
</body>
</html>