<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폴더별 보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
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