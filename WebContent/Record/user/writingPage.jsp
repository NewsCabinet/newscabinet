<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../style.css" rel="stylesheet">
<title>Home</title>
</head>
<jsp:include page="../../newsHeader.html"></jsp:include>

<body>
	<div class="basic_contentzone">
		<form method="post" action="../../UserRecord/restore">
			<h2 style="text-align: left; margin-left: 30px">기록 작성하기</h2>
			<p>
				<input class="listWriteInput" type="text" name="recordTitle"
					placeholder="제목을 입력해주세요">
			</p>
			<br>
			<%
				request.setAttribute("newsId", 1);
			String newsUrl = (String) request.getAttribute("newsUrl");
			String newsSubcategory = (String) request.getAttribute("subCategoryName");
			int newsSubcategoryId = (Integer) request.getAttribute("subCategoryId");
			String todayDate = (String) request.getAttribute("todayDate");
			ResultSet userFolder = (ResultSet) request.getAttribute("userFolder");
			%>

			<p>
				날짜
				<%=todayDate%>
			</p>
			<br>
			<p>
				카테고리
				<%=newsSubcategory%>
			</p>
			<br>
			<h3>폴더</h3>
			<%
				if (userFolder != null) {
				out.println("<select name='userFolder' size='2'>");
				while (userFolder.next()) {
					String folderName = userFolder.getString(1);
					out.print("<option value='" + folderName + "'>" + folderName + "</option>");
				}
				out.println("</select>");
			} else {
				out.print("Folder가 로딩되지 않음");
			}
			%>
			<br>
			<p>
				공개 설정 : <input type="radio" name="recordPrivate" value="false"
					checked> 공개 <input type="radio" name="recordPrivate"
					value="true"> 비공개
			</p>
			<br>
			<p>
				<a href="<%=newsUrl%>">뉴스 보러가기:</a>
			</p>
			<br>
			<br>

			<iframe class="newsBox" src="<%=newsUrl%>"> </iframe>
			<textarea class="textBox" name="recordComment">뉴스 url을 카테고리 처럼 선택할 수 있게 할까??</textarea>
			<br>
			<br>
			<button class="push_button_Stoore" type="button">저장하기</button>
		</form>
		<br>
	</div>
</body>
</html>