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
			<p id="introRecord" >
				Folder > <%=request.getAttribute("folderName")%>
			</p>
		</div>
		<div >
			<div class="simpleRecordContent">
				<%
					ArrayList<RecordData> simpleRecordList = (ArrayList) request.getAttribute("recordData");
				for (int i = 0; i < simpleRecordList.size(); i++) {
					int recordId = simpleRecordList.get(i).getRecordId();
					String subcategoryName = simpleRecordList.get(i).getSubcategoryName();
					String recordTitle = simpleRecordList.get(i).getRecordTitle();
					String recordDate = simpleRecordList.get(i).getRecordDate();
					int recordCount = simpleRecordList.get(i).getRecordCount();
					String specificRecordUrl = "/NewsCabinet/UserRecord/record?id=" + recordId;
				%>
				<div class="simpleRecordItem" onclick="location.href='<%=specificRecordUrl%>'">
					<p>
						<b>[<%=subcategoryName%>]
						</b> &nbsp;
						<%=recordTitle%>
					</p>
					<br>
					<p>
						&nbsp;
						<%=recordDate%>
						&nbsp; | &nbsp; 조회수 &nbsp;<%=recordCount%>
					</p>
					<br>
				</div>
				<%} %>
			</div>
		</div>
	</div>
</body>
</html>