<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="model.NewsData" import="model.SubcategoryData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 보기</title>
<link href="../style.css?ver=1" rel="stylesheet">
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="webHeader.jsp"></jsp:include>

	<div class="basic_contentzone">
		<div>
			<br />
			<h3 id="intro">
				<b>뉴스 보기 설정</b>
				<button class="setting" onclick="window.open('/NewsCabinet/news/customCategory/management','키워드 수정하기','width=500,height=500,location=no,status=no,scrollbars=yes, toolbar=0, menubar=no');">
				</button>
			</h3>
			<form id="newsType" method="post" action="../news/main">
				<%
					int subCategoryId = -1;
					String selectedCustomCategory = "";
					SubcategoryData[] subcateData = (SubcategoryData[]) request.getAttribute("subcateData");
					String subCategory = (String) request.getAttribute("subCategory");
				%>
				<div class="categoryBox">
					<div class="box1">
						<b>기본 카테고리</b><br />
						<%
							for (int i = 0; i < subcateData.length; i++) {
								out.println(subcateData[i].getSubcategoryName());
								if (subcateData[i].getSubcategoryName().equals(subCategory)) {
									out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='"
									+ subcateData[i].getSubcategoryName() + "' checked></label>");
									subCategoryId = subcateData[i].getSubcategoryId();
								} else {
									out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='"
									+ subcateData[i].getSubcategoryName() + "'></label>");
								}
								if (i == 0) {
									out.println("<br/>");
								}
							}
						%>
					</div>
					<br />

					<div class='box1'>
						<%
							List customCategories = (ArrayList) request.getAttribute("customCategories");
							if (customCategories != null) {
								out.println("<b>내가 만든 카테고리</b><br/>");
								for (int i = 0; i < customCategories.size(); i++) {
									//System.out.println(customCategories[i]);
									out.println(customCategories.get(i));
									if (customCategories.get(i).equals(subCategory)) {
										out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='"
										+ customCategories.get(i) + "' checked><label for='subcategory'>");
										selectedCustomCategory = customCategories.get(i).toString();
										subCategoryId = subcateData[0].getSubcategoryId();
									} else {
										out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='"
										+ customCategories.get(i) + "'></label>");
									}
								}
							}
						%>
					</div>
					<br />
					<div class='box1'>
						<b>뉴스 보기 타입</b><br />
						<%
							String newsType = (String) request.getAttribute("newsType");
							if (newsType.toString().equals("sim")) {
								out.println(
								"관련도순 <label for='subcategory'><input type='radio' id='subcategory' name='newsType' value='sim' checked>\n최근순 <input type='radio' name='newsType' value='date'></label>");
								out.println("<br/>");
							} else if (newsType.toString().equals("date")) {
								out.println(
								"관련도순 <label for='subcategory'><input type='radio' id='subcategory' name='newsType' value='sim'>\n최근순 <input type='radio' name='newsType' value='date' checked></label>");
							}
						%>
					</div>
					<br /> <input class="FindButton" type="submit" value="선택 완료">
			</form>
		</div>
		<br/><br/><br/><br/><br/>
		<div class="newsListBox">
			<section class ="sectionArti">
				<%
					NewsData[] nd = (NewsData[]) request.getAttribute("newsdata");
					List scrappedNewsId = (ArrayList) request.getAttribute("scrappedNewsId");
					
					for (int i = 0; i < nd.length; i++) {
						boolean check = false;
						String tmpForScrap = "location.href='../scrap/scrapNews?location=" + i + "&subId=" + subCategoryId + "&custom="
						+ selectedCustomCategory + "&check=";
					%>
						<div class="otherRecordItem" style="cursor: pointer;" onclick="window.open('<%=nd[i].getUrl()%>')">
						<%
						for (int j = scrappedNewsId.size() - 1; j >= 0; j--) {
							if (scrappedNewsId.get(j).equals(i)) {
								check = true;
								//out.println((i + 1) + " <button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + "> 스크랩취소 </button>");
								out.println(" <button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + check + "'" + "> 스크랩취소 </button>");
							}
						}
						if (!check) {
							//out.println((i + 1) + " <button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + "> 스크랩하기 </button>");	
							out.println("<button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + check + "'" + "> 스크랩하기 </button>");
						}
					%>
							<p>
								<b><i><%=nd[i].getHeadline()%></i></b>
							</p>
							<p>
								<br> 
								<%=nd[i].getDescription()%>
							</p><br />
							<p>
								<%=nd[i].getPubDate()%>
							</p>
						</div>
						<br/>
				<%
					}
				%>
			</section>
		</div>
	</div>
   </script>

</body>
</html>