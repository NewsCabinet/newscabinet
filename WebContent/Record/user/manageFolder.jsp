<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html, body { overflow: hidden; }
.folder {
	background-repeat: no-repeat;
	border-width: 3px;
	border-top: 10px solid;
	border-color: #2E404E;
	background-color:white;
	width: 50px;
	height: 50px;
	text-align: center;
	font-size: 20px;
	margin: 20px;
	float: left;
}
.folderName{
	padding-top: 20px;
	font-size : 10px;
}
</style>
</head>
<body>
	<p>폴더 관리</p>
	<form method="POST" action="/NewsCabinet/UserRecord/main/folder/management">
		<p>
			현재 폴더 :
			<%
				ArrayList<UserFolderData> userForderList = (ArrayList) request.getAttribute("folders");
				for(int i = 0; i< userForderList.size(); i++){
					out.println("<div class='folder'> <p class='folderName'>"+ userForderList.get(i).getFolderName()+ "</p></div> ");
				}
			%>
		</p>
		<p/>
		<div style="clear : both;">
		<input type="button" value="폴더 삭제하기" onclick="">
		<p/>
		<input type="button" value="폴더 추가하기" onclick="addFolder()">
		<div id="addedFolder">
		</div>
		<input type="submit" value="추가 완료" onClick="reloadSelf()">
		</div>
	</form>
	<script type="text/javascript">
		function closeWindow(){
			window.opener.parent.location.reload();
			self.close();
		}
	</script>
	<input type="button" value="닫기" onClick="closeWindow()">
</body>
<script type="text/javascript">
	function addFolder() {
		document.getElementById("addedFolder").innerHTML += "추가할 폴더 : "
		document.getElementById("addedFolder").innerHTML += "<input type='text' name='folder' >";
	}
	function reloadSelf() {
		location.reload();
	}
</script>
</html>