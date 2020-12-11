<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 기록 보기</title>
<link href="../style.css" rel="stylesheet">
<style>
.basic_contentzone {
	padding-top: 20px;
	position: relative;
	width: 100%;
	top: 100px;
}
	.basic_type_header {
		padding-top: 10px;
		position: relative;
		width: 100%;
		height: 200px;
		top: 100px;
	}
	.newsCategoryHeader{
		width: 60%;
		margin: 20px auto;
	}
	
	ul{
	 list-style:none;
	}
	
	.newsCategoryHeader a{
		width: 60%;
		padding: 10px;
		text-decoration: none;
		color: black;
	}
	
	.newsCategoryHeader a:hover{
		border-bottom: 2px solid #2E404E;
	}
	
	.CategoryHeaderli {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		float: left;
		text-decoration: none;
	}
	
	.CategoryHeaderliOn {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		float: left;
		border-bottom: 2px solid #2E404E;
	}

.folder {
	background-repeat: no-repeat;
	border-width: 3px;
	border-top: 10px solid;
	border-color: #2E404E;
	background-color:white;
	width: 100px;
	height: 144px;
	text-align: center;
	font-size: 20px;
	margin: 20px;
	float: left;
}
.settingForRecord{
	position: relative;
	background-image: url( "/NewsCabinet/images/setting.png" );
	background-repeat: no-repeat;
	width : 50px;
	height: 50px;
	border: none;
	background-color : white;
	margin-left: 700px;
}
.contents {
	background-color: #EEEEEE;
	width: 60%;
	/*margin:0px auto;*/
}
#introRecord{
	font-size: 20px; font-weight:bold; margin-right : 50px;
}
</style>
</head>
<body>
<jsp:include page="../../webHeader.jsp"></jsp:include>
<jsp:include page="recordType.jsp"></jsp:include>
	<div>
		<div>
			<p id="introRecord"> Folder 
				<button class="settingForRecord" onclick="window.open('/NewsCabinet/UserRecord/main/folder/management','폴더 추가하기','width=430,height=500,location=no,status=no,scrollbars=yes, toolbar=0, menubar=no');"></button>
			</p>
		</div>
		<div style="background-color: #EEEEEE; width: 60%; margin:0px auto;">
			
			<%
				ArrayList<UserFolderData> userForderList = (ArrayList) request.getAttribute("folders");
				for(int i = 0; i< userForderList.size(); i++){
					String tmpForFolder = "location.href='../UserRecord/main/folder/list?folderId=" + userForderList.get(i).getFolderId() + "'";
					out.println("<button name='folder' class='folder' onclick=" + tmpForFolder + ">" + userForderList.get(i).getFolderName() + "</button>");
				}
			%>
		</div>
	</div>
</body>
</html>