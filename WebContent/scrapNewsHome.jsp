<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.FristCategoryData, model.SubcategoryData, model.UserScrapNewsData" %>
<jsp:include page="webHeader.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크랩보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<style type="text/css">
	/*CH : categoryHeader*/
	.newsCategoryHeader{
		width: 65%;
		margin: 5px auto;
		border-bottom: 1px solid #2E404E;
	}

	.newsCategoryHeader a{
		width: 60%;
		margin: 20px auto;
		padding:8px;
		text-decoration: none;
		color: black;
	}
	
	.newsCategoryHeader ul{
	list-style:none;
	}
	
	.CH_FirtstLineli {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		text-decoration: none;
	}
	
	.CH_FirtstLineli a{
		width: 60%;
		padding: 10px;
		text-decoration: none;
		color: black;
	}
	
	.CH_FirtstLineli a:hover{
		border-bottom: 2px solid #2E404E;
	}
	
	.CH_FirtstLineliOn {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		text-decoration: none;
		font-weight:bolder;
	}
	
	.CH_SecondLineli {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		text-decoration: none;
	}
	
	.CH_SecondLineli a:hover{
		background: #2E404E;
		color: white;
		font-weight: bold;
		border-radius: 10px;
	}
	
	.CH_SecondLineliOn {
		float: left;
		margin: 1px;
		display: block;
		padding: 5px;
		text-decoration: none;
		background: #2E404E;
		color: white;
		font-weight: bold;
		border-radius: 10px;
	}

	.simpleRecordContent{
		text-align: left;
		width: 60%;
		margin: 10px auto;
		padding : 10px;
	}
	
	.simpleRecordItem {
		text-align: left;
		border-bottom: 1px solid gray;
		margin: 10px auto;
		padding : 10px;
	}
	
	.simpleRecordItem a{
		text-decoration: none;
		padding: 20px;
		color:black;
	}
	
	.simpleRecordItem a:hover{
		box-shadow: 1px 1px 10px #ddd;
	}
	
</style>
</head>

<body>
	<%	
		ArrayList<FristCategoryData> firstCategoryList = (ArrayList)application.getAttribute("firstCategoryList");
		ArrayList<SubcategoryData> subCategoryList = (ArrayList)application.getAttribute("subCategoryList");
		ArrayList<UserScrapNewsData> scrapTop10List = (ArrayList)request.getAttribute("scrapTop10List");
	%>
	
	<div class="basic_contentzone">
			<section>
			<br>
			<h3>스크랩 보기</h3>
			<br>
			</section>
				
			<div class="newsType">
				<div class="newsCategoryHeader">
					<ul>
					<li class='CH_FirtstLineli'><a href="/NewsCabinet/OthersRecord/main">홈</a><li>
						<%
						for(int i = 0; i < firstCategoryList.size(); i++){
							int itemId = firstCategoryList.get(i).getCategoryId();
							String itemName = firstCategoryList.get(i).getCategoryName();
							String recordUrl = "/NewsCabinet/otherRecord?first=" + itemId;
							out.println("<li class='CH_FirtstLineli'><a href='" + recordUrl + "'>" + itemName + "</a></li>");
						}%>
					</ul>
					<br><br>
				</div>
				<div class="newsCategoryHeader">
					<ul>
						<li class="CH_SecondLineli">인기 스크랩 top10 </li>
					</ul>
					<br><br>
				</div>
				<div class="simpleRecordContent">
					<h3>&nbsp;&nbsp;인기 스크랩 기사</h3>
						<% 
						for(int i = 0; i < scrapTop10List.size(); i++){
							
							String scrapSubcategoryName = scrapTop10List.get(i).getSubCategoryName();
							String scrapHeadline = scrapTop10List.get(i).getHeadline();
							String scrapDescription = scrapTop10List.get(i).getDescription();
							String scrapPublicDate = scrapTop10List.get(i).getPublishedDate();
							int scrapCount = scrapTop10List.get(i).getScrapCount();
							String specificRecordUrl = "/NewsCabinet/UserRecord/record?id=" + 1;
							
							%>
							<div class="simpleRecordItem" onclick="location.href='<%=specificRecordUrl%>'">
								<p><b>[<%=scrapSubcategoryName%>]</b> &nbsp; <%=scrapHeadline %>
								</p>
								<br>
								<%=scrapDescription%>
								<br>
								<br>
								<p>
								 출고 날짜 <%=scrapPublicDate %> &nbsp; | &nbsp; 스크랩수 &nbsp;<%=scrapCount %>
								</p><br>
							</div>
						<%} %>
							
				</div>
				
			</div>
			
	</div>
			



</body>
</html>