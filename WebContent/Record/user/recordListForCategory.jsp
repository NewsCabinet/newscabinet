<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, model.FristCategoryData, model.SubcategoryData, model.RecordData" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리별 보기</title>
<link href="../../style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../../webHeader.jsp"></jsp:include>
	<%
		ArrayList<FristCategoryData> firstCategoryList = (ArrayList)application.getAttribute("firstCategoryList");
		ArrayList<SubcategoryData> subCategoryList = (ArrayList)application.getAttribute("subCategoryList");
		ArrayList<RecordData> simpleRecordList = (ArrayList)request.getAttribute("simpleRecordList");
		int SelectedfirstCategoryId = (Integer)request.getAttribute("SelectedCategoryId");
		int SelectedSubCategoryId = (Integer)request.getAttribute("SelectedSubCategoryId");
	
	%>
	<div class="basic_contentzone">
		<div class="newsType">
			<div class="newsCategoryHeader">
				<ul>
					<li class='CH_FirtstLineli'><a
						href="/NewsCabinet/UserRecord/main">Folder</a></li>
					<li class='CH_FirtstLineliOn'><a
						href="/NewsCabinet/UserRecord/main/category">Category</a></li>
				</ul>
				<br>
				<br>
			</div>
			<div class="newsCategoryHeader">
				<ul>
					<%
					String presentFisrtCategoryName = "";
					for(int i = 0; i < firstCategoryList.size(); i++){
						int itemId = firstCategoryList.get(i).getCategoryId();
						String itemName = firstCategoryList.get(i).getCategoryName();
						String recordUrl = "/NewsCabinet/UserRecord/main/category?first=" + itemId;
						
						if(itemId == SelectedfirstCategoryId){
							out.println("<li class='CH_FirtstLineliOn'><a href='" + recordUrl + "'>" + itemName + "</a></li>");
							presentFisrtCategoryName = itemName;
						}else{
							out.println("<li class='CH_FirtstLineli'><a href='" + recordUrl + "'>" + itemName + "</a></li>");
						}
					}
					%>
					</ul>
					<br><br>
				</div>
				<div class="newsCategoryHeader">
					<ul>
						<%
						for(int i = 0; i < subCategoryList.size(); i++){
							int firstCategoryId = subCategoryList.get(i).getFirstCategoryId();
							int subItemId = subCategoryList.get(i).getSubcategoryId();
							String subItemName = subCategoryList.get(i).getSubcategoryName();
							String recordUrl = "/NewsCabinet/UserRecord/main/category?first=" + firstCategoryId + "&sub=" + subItemId ;
							
							if(firstCategoryId == SelectedfirstCategoryId){
								if(presentFisrtCategoryName.equals(subItemName)){
									continue;
								}else if(subCategoryList.get(i).getSubcategoryId() == SelectedSubCategoryId){
									out.println("<li class='CH_SecondLineliOn'><a href='" + recordUrl + "'>" + subItemName + "</a></li>");
								}else{
									out.println("<li class='CH_SecondLineli'><a href='" + recordUrl + "'>" + subItemName + "</a></li>");
								}
							}
						}%>
					</ul>
					<br><br>
				</div>
				
				<div class="simpleRecordContent">
					<% 
					for(int i = 0; i < simpleRecordList.size(); i++){
						int recordId = simpleRecordList.get(i).getRecordId();
						String subcategoryName = simpleRecordList.get(i).getSubcategoryName();
						String recordTitle = simpleRecordList.get(i).getRecordTitle();
						String userName = simpleRecordList.get(i).getUserName();
						String recordDate = simpleRecordList.get(i).getRecordDate();
						int recordCount = simpleRecordList.get(i).getRecordCount();
						String specificRecordUrl = "/NewsCabinet/UserRecord/record?id=" + recordId;
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