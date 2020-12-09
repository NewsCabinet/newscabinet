<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.FristCategoryData, model.SubcategoryData, model.RecordData" %>
<jsp:include page="../../webHeader.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 기록 보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<style type="text/css">
	.newsCategoryHeader{
		width: 60%;
		margin: 20px auto;
		padding: 10px;
	}
	
	ul{
	 list-style:none;
	}
	
	.newsCategoryHeader a{
		width: 60%;
		margin: 20px auto;
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
		display: block;
		float: left;
		padding: 5px;
		text-decoration: none;
	}
	
	
	.CategoryHeaderliOn {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		display: block;
		float: left;
		padding: 5px;
		border-bottom: 2px solid #2E404E;
	}


	#recordContent{
		text-align: left;
		width: 50%;
		margin: 10px auto;
		padding : 10px;
	}
	
	.otherRecordItem{
		width: 90%;
		text-align: left;
		border-bottom: 1px solid gray;
		margin: 10px auto;
		padding : 10px;
	}
</style>
</head>

<body>
	<%	
		ArrayList<FristCategoryData> firstCategoryList = (ArrayList)application.getAttribute("firstCategoryList");
		ArrayList<SubcategoryData> subCategoryList = (ArrayList)application.getAttribute("subCategoryList");
		ArrayList<RecordData> simpleRecordList = (ArrayList)request.getAttribute("simpleRecordList");
		int SelectedfirstCategoryId = (Integer)request.getAttribute("SelectedCategoryId");
	%>
	
	<div class="basic_contentzone">
			<section>
			<h3>전체 기록 보기</h3>
			</section>
				
			<div class="newsType">
				<div class="newsCategoryHeader">
					<ul>
					<li class='CategoryHeaderli'><a href="/NewsCabinet/OthersRecord/main">홈</a><li>
						<%
						String itemName = "";
						for(int i = 0; i < firstCategoryList.size(); i++){
							int itemId = firstCategoryList.get(i).getCategoryId();
							itemName = firstCategoryList.get(i).getCategoryName();
							String recordUrl = "/NewsCabinet/otherRecord?firstCategory=" + itemId;
							
							if(itemId == SelectedfirstCategoryId){
								out.println("<li class='CategoryHeaderliOn'><a href='" + recordUrl + "'>" + itemName + "</a></li>");
							}else{
								out.println("<li class='CategoryHeaderli'><a href='" + recordUrl + "'>" + itemName + "</a></li>");
							}
							
						}%>
					</ul>
					<br><br>
					<hr>
					<ul>
						<%
						for(int i = 0; i < subCategoryList.size(); i++){
							int firstCategoryId = subCategoryList.get(i).getFirstCategoryId();
							int subItemId = subCategoryList.get(i).getSubcategoryId();
							String subItemName = subCategoryList.get(i).getSubcategoryName();
							String recordUrl = "/NewsCabinet/otherRecord?first=" + firstCategoryId + "&sub=" + subItemId ;
							
							if(firstCategoryId == SelectedfirstCategoryId){
								out.println("<li class='CategoryHeaderli'><a href='" + recordUrl + "'>" + subItemName + "</a></li>");
							}
						}%>
					</ul>
					<br><br>
					<hr>
				</div>
				<div id=recordContent>
						<% 
						for(int i = 0; i < simpleRecordList.size(); i++){
							String subcategoryName = simpleRecordList.get(i).getSubcategoryName();
							String recordTitle = simpleRecordList.get(i).getRecordTitle();
							String userName = simpleRecordList.get(i).getUserName();
							String recordDate = simpleRecordList.get(i).getRecordDate();
							int recordCount = simpleRecordList.get(i).getRecordCount();
							%>
							<div class="otherRecordItem">
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