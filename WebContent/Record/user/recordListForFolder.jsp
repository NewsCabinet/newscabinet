<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폴더별 보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<style>
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
	.otherRecordItem{
		width: 60%;
		text-align: left;
		border-bottom: 1px solid gray;
		margin: 10px auto;
		padding : 10px;
	}
	#listType{
		padding-top: 100px;
		width: 80%;
		height: 50px;
		top: 300px;
		text-align:left;
		margin-top:80px;
		font-size: 20px;
		font-weight: bold;
	}
	#list{
		width: 70%;
		background-color: #EEEEEE;
	}
</style>
</head>
<body>
	<jsp:include page="../../webHeader.jsp"></jsp:include>
	<jsp:include page="recordType.jsp"></jsp:include>
	<div>
		<div>
			<p class="listType" style="font-size: 20px; font-weight:bold; margin-top: 40px; margin-right : 700px;">
				Folder > <%=request.getAttribute("folderName")%>
			</p>
		</div>
		<div style="background-color: #EEEEEE; width: 60%; margin:0px auto;">
			<div>
				<%
					ArrayList<RecordData> recordList = (ArrayList) request.getAttribute("recordData");
					for(int i = 0; i< recordList.size(); i++){
						String tmpForScrap = "location.href='/NewsCabinet/UserRecord/record?id=" + recordList.get(i).getRecordId() + "'";
				%>
						<div class="otherRecordItem" style="cursor: pointer;" onclick="<%=tmpForScrap%>;">
							<p>
								&nbsp;
								<%=recordList.get(i).getRecordTitle()%>
							</p>
							<br>
							<p>
								&nbsp;
								<%=recordList.get(i).getRecordDate()%>
								&nbsp; | &nbsp; 조회수 &nbsp;<%=recordList.get(i).getRecordCount()%>
							</p>
							<br>
						</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>