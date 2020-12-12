<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*, model.FristCategoryData, model.SubcategoryData, model.CustomCategoryData"
	import="model.UserScrapNewsData"%>
<jsp:include page="../webHeader.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크랩한 뉴스보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
		ArrayList<FristCategoryData> firstCategoryList = (ArrayList) application.getAttribute("firstCategoryList");
	ArrayList<SubcategoryData> subCategoryList = (ArrayList) application.getAttribute("subCategoryList");
	ArrayList<CustomCategoryData> customCategoryList = (ArrayList) request.getAttribute("userCustomCategoryList");
	ArrayList<UserScrapNewsData> userScrapList = (ArrayList) request.getAttribute("userScrapList");
	int SelectedfirstCategoryId = (Integer) request.getAttribute("SelectedCategoryId");
	int SelectedSubCategoryId = (Integer) request.getAttribute("SelectedSubCategoryId");
	%>


	<div class="basic_contentzone">
		<section>
			<br>
			<h3>스크랩한 뉴스 보기</h3>
			<br>
		</section>

		<div>
			<div class="newsCategoryHeader">
				<ul>
					<li class='CH_FirtstLineli'><a href="/NewsCabinet/scrap/main">홈</a>
					<li>
						<%
							String presentFisrtCategoryName = "";
						for (int i = 0; i < firstCategoryList.size(); i++) {
							int itemId = firstCategoryList.get(i).getCategoryId();
							String itemName = firstCategoryList.get(i).getCategoryName();
							String scrapUrl = "/NewsCabinet/scrap/category?first=" + itemId;

							if (itemId == SelectedfirstCategoryId) {
								out.println("<li class='CH_FirtstLineliOn'><a href='" + scrapUrl + "'>" + itemName + "</a></li>");
								presentFisrtCategoryName = itemName;
							} else {
								out.println("<li class='CH_FirtstLineli'><a href='" + scrapUrl + "'>" + itemName + "</a></li>");
							}
						}
						%>
					
				</ul>
				<br>
				<br>
			</div>
			<div class="newsCategoryHeader">
				<ul>
					<%
						for (int i = 0; i < subCategoryList.size(); i++) {
						int firstCategoryId = subCategoryList.get(i).getFirstCategoryId();
						int subItemId = subCategoryList.get(i).getSubcategoryId();
						String subItemName = subCategoryList.get(i).getSubcategoryName();
						String scrapUrl = "/NewsCabinet/scrap/category?first=" + firstCategoryId + "&sub=" + subItemId;

						if (firstCategoryId == SelectedfirstCategoryId) {
							if (presentFisrtCategoryName.equals(subItemName)) {
						continue;
							} else if (subCategoryList.get(i).getSubcategoryId() == SelectedSubCategoryId) {
						out.println("<li class='CH_SecondLineliOn'><a href='" + scrapUrl + "'>" + subItemName + "</a></li>");
							} else {
						out.println("<li class='CH_SecondLineli'><a href='" + scrapUrl + "'>" + subItemName + "</a></li>");
							}
						}
					}
					%>
				</ul>
				<br>
				<br>
			</div>
			<div class="newsCategoryHeader" id="customCategory">
				<ul>
					<%
						boolean checkCustomCategory = false;
					for (int i = 0; i < customCategoryList.size(); i++) {
						int firstCategoryId = customCategoryList.get(i).getFirstCategoryId();
						int customItemId = customCategoryList.get(i).getCustomCategoryId();
						String customItemName = customCategoryList.get(i).getCustomCategoryName();

						//custom Category -> 200이상
						customItemId += 200;
						String scrapUrl = "/NewsCabinet/scrap/category?first=" + firstCategoryId + "&sub=" + customItemId;

						if (firstCategoryId == SelectedfirstCategoryId && !customItemName.equals("전체")) {
							checkCustomCategory = true;
							if (customItemId == SelectedSubCategoryId) {
						out.println("<li class='CH_SecondLineliOn'><a href='" + scrapUrl + "'>" + customItemName + "</a></li>");
							} else {
						out.println("<li class='CH_SecondLineli'><a href='" + scrapUrl + "'>" + customItemName + "</a></li>");
							}
						}
					}
					%>
				</ul>
				<br>
				<br>
			</div>


			<div class="simpleRecordContent">
				<%
					String scrapinfo = "스크랩 기사";
				if (userScrapList.size() == 0) {
					scrapinfo = "스크랩한 기사가 없습니다";
				}
				%>
				<h3>
					&nbsp;&nbsp;<%=scrapinfo%></h3>
				<%
					for (int i = 0; i < userScrapList.size(); i++) {

					String scrapSubcategoryName = userScrapList.get(i).getSubCategoryName();
					String scrapHeadline = userScrapList.get(i).getHeadline();
					String scrapDescription = userScrapList.get(i).getDescription();
					String scrapPublicDate = userScrapList.get(i).getPublishedDate();
					String scrapNewsUrl = userScrapList.get(i).getNewsURL();
					int scrapCount = userScrapList.get(i).getScrapCount();

					String tmpForScrap = "location.href='/NewsCabinet/scrap/cancel?newsId=" + userScrapList.get(i).getNewsId() + "'";
				%>
				<button class='scrab_btn' name='scrap' onclick=<%=tmpForScrap%>>
					스크랩취소</button>
				<div class="simpleRecordItem" onclick="location.href='<%=scrapNewsUrl%>'">
					<p>
						<b>[<%=scrapSubcategoryName%>]
						</b> &nbsp;
						<%=scrapHeadline%>
					</p>
					<br>
					<%=scrapDescription%>
					<br> <br>
					<p>
						출고 날짜
						<%=scrapPublicDate%>
						&nbsp; | &nbsp; 스크랩수 &nbsp;<%=scrapCount%>
					</p>
					<br>
				</div>
				<%
					}
				%>

			</div>
		</div>
	</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	
</body>
<script>
	window.onload = function(){
		var check = <%=checkCustomCategory%>
		if(check == false)
			document.getElementById("customCategory").style.display="none";
		else
			document.getElementById("customCategory").style.display="";
	} 
</script>

</html>