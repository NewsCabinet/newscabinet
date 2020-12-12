<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 기록 보기</title>
<link href="../style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../../webHeader.jsp"></jsp:include>
	<div class="basic_contentzone">
			<div class="newsCategoryHeader">
				<ul>
					<li class='CH_FirtstLineliOn'><a
						href="/NewsCabinet/UserRecord/main">Folder</a></li>
					<li class='CH_FirtstLineli'><a
						href="/NewsCabinet/UserRecord/main/category">Category</a></li>
				</ul>
				<br>
				<br>
			</div>
	<div>
		<div>
			<p id="introRecord">
				Folder <button class="setting" onclick="window.open('/NewsCabinet/UserRecord/main/folder/management','폴더 추가하기','width=500,height=500,location=no,status=no,scrollbars=yes, toolbar=0, menubar=no');"></button>
			</p>
		</div>
		<div class="folderListInMain">
			<%
				ArrayList<UserFolderData> userForderList = (ArrayList) request.getAttribute("folders");
				for(int i = 0; i< userForderList.size(); i++){
					String tmpForFolder = "location.href='../UserRecord/main/folder/list?folderId=" + userForderList.get(i).getFolderId() + "'";
					out.println("<button name='folder' class='folder' onclick=" + tmpForFolder + ">" + userForderList.get(i).getFolderName() + "</button>");
				}
			%>
		</div>
	</div>
	</div>
</body>
</html>