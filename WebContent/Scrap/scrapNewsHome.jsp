<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.FristCategoryData, model.SubcategoryData, model.UserScrapNewsData" %>
<jsp:include page="../webHeader.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크랩보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
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
					<li class='CH_FirtstLineli'><a href="/NewsCabinet/scrap/main">홈</a><li>
						<%
						for(int i = 0; i < firstCategoryList.size(); i++){
							int itemId = firstCategoryList.get(i).getCategoryId();
							String itemName = firstCategoryList.get(i).getCategoryName();
							String scrapUrl = "/NewsCabinet/scrap/news?first=" + itemId;
							String scrapTest = "/NewsCabinet/Scrap/scrapNewsCategory.jsp";
							out.println("<li class='CH_FirtstLineli'><a href='" + scrapUrl + "'>" + itemName + "</a></li>");
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
						if(scrapTop10List == null)
							System.out.println("scrapTop10List is null");
						
						for(int i = 0; i < scrapTop10List.size(); i++){
							String scrapSubcategoryName = scrapTop10List.get(i).getSubCategoryName();
							String scrapHeadline = scrapTop10List.get(i).getHeadline();
							String scrapDescription = scrapTop10List.get(i).getDescription();
							String scrapPublicDate = scrapTop10List.get(i).getPublishedDate();
							String scrapNewsUrl = scrapTop10List.get(i).getNewsURL();
							int scrapCount = scrapTop10List.get(i).getScrapCount();
							
							%>
							<div class="simpleRecordItem" onclick="location.href='<%=scrapNewsUrl%>'">
								
								<p><b>[<%=scrapSubcategoryName%>]</b> &nbsp; <%=scrapHeadline %>
								</p>
								<br>
								<%=scrapDescription%>
								<br>
								<br>
								<p>
								 출고 날짜 <%=scrapPublicDate %> &nbsp; | &nbsp; 스크랩수 &nbsp;<%=scrapCount %>
								</p>
								
								<br>
							</div>
						<%} %>
							
				</div>
				
			</div>
			
	</div>
			



</body>
</html>