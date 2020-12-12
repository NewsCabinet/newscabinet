<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.FristCategoryData, model.SubcategoryData, model.RecordData" %>
<jsp:include page="../../webHeader.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 기록 보기</title>
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
<link href="/NewsCabinet/style.css?ver=1" rel="stylesheet">
</head>
<body>
	<%	
		ArrayList<FristCategoryData> firstCategoryList = (ArrayList)application.getAttribute("firstCategoryList");
		ArrayList<SubcategoryData> subCategoryList = (ArrayList)application.getAttribute("subCategoryList");
		ArrayList<RecordData> popularTop10RecordList = (ArrayList)request.getAttribute("popularTop10RecordList");
		request.setAttribute("isOherRecord", true);
	%>
	
	<div class="basic_contentzone">
			<section>
			<br>
			<h3>전체 기록 보기</h3>
			<br>
			</section>
				
			<div class="newsType">
				<div class="newsCategoryHeader">
					<ul>
					<li class='CH_FirtstLineliOn'><a href="/NewsCabinet/record/shared/main">홈</a><li>
						<%
						for(int i = 0; i < firstCategoryList.size(); i++){
							int itemId = firstCategoryList.get(i).getCategoryId();
							String itemName = firstCategoryList.get(i).getCategoryName();
							String recordUrl = "/NewsCabinet/record/shared/category?first=" + itemId;
							out.println("<li class='CH_FirtstLineli'><a href='" + recordUrl + "'>" + itemName + "</a></li>");
						}%>
					</ul>
					<br><br>
				</div>
				<div class="newsCategoryHeader">
					<ul>
						<li class="CH_SecondLineli">인기 기록 top10 </li>
					</ul>
					<br><br>
				</div>
				<div class="simpleRecordContent">
					<h3>&nbsp;&nbsp;인기 기록</h3>
						<% 
						for(int i = 0; i < popularTop10RecordList.size(); i++){
							String subcategoryName = popularTop10RecordList.get(i).getSubcategoryName();
							int recordId = popularTop10RecordList.get(i).getRecordId();
							String recordTitle = popularTop10RecordList.get(i).getRecordTitle();
							String userName = popularTop10RecordList.get(i).getUserName();
							String recordDate = popularTop10RecordList.get(i).getRecordDate();
							int recordCount = popularTop10RecordList.get(i).getRecordCount();
							String specificRecordUrl = "/NewsCabinet/record/specific?id=" + recordId;
							String recordTest = "/NewsCabinet/Record/record.jsp";
							%>
							<div class="simpleRecordItem" onclick="location.href='<%=specificRecordUrl%>'">
								<p> <b>[<%=subcategoryName%>]</b> &nbsp; <%=recordTitle %>
								</p><br>
								<p>
								<%=userName %> &nbsp; | &nbsp; <%=recordDate %> &nbsp; | &nbsp; 조회수 &nbsp;<%=recordCount %>
								</p><br>
							</div>
						<%} %>
							
				</div>
				
			</div>
			
	</div>

</body>
</html>