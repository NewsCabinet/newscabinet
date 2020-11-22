<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.sql.ResultSet" %>

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
			<form method = "post" action="../../UserRecord/restore">
				<h2>기록 작성하기</h2>
				<h3> 제목 &nbsp; <input type="text" name="recordTitle"></h3> <br>
				<%	request.setAttribute("newsId", 1);	
					String newsUrl = (String)request.getAttribute("newsUrl");
					String newsSubcategory = (String) request.getAttribute("subCategoryName");
					int newsSubcategoryId = (Integer) request.getAttribute("subCategoryId");
					String todayDate = (String)request.getAttribute("todayDate");
					ResultSet userFolder = (ResultSet)request.getAttribute("userFolder");
				
				%>

				<h3>날짜  <%=todayDate %> </h3><br>
				<h3> 카테고리 <%= newsSubcategory %>  </h3><br>
				<h3> 폴더  
					<%
						if(userFolder != null){
							out.println("<select name='userFolder' size='2'>");
							while(userFolder.next()){
								String folderName = userFolder.getString(1);
								out.print("<option value='" + folderName + "'>" + folderName+ "</option>");
							}
							out.println("</select>");
						}else{
							out.print("Folder가 로딩되지 않음");
						}
					%>
				</h3> <br>
				 
				<h3>
					공개 설정 : 
					<input type="radio" name="recordPrivate" value="false" checked> 공개 
					<input type="radio" name="recordPrivate" value="true"> 비공개
				</h3><br>
				<h3>
					<a href="<%=newsUrl%>">뉴스 보러가기:</a> 
				</h3><br>
				<iframe
					src="<%=newsUrl%>"
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
</body>
</html>