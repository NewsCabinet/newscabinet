<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="model.NewsData" import="model.SubcategoryData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 보기</title>
<link href="../style.css" rel="stylesheet">
<style>
/*body {
   text-align: left;
}*/

.basic_contentzone {
	padding-top: 20px;
	position: relative;
	top: 100px;
	width: 100%;
	height: 500px;
}
</style>
</head>
<jsp:include page="webHeader.jsp"></jsp:include>

<body>
	<div class="basic_contentzone">
		<div class="newsType">
			<form method="post" action="../news/main">
				<% int subCategoryId = -1; %>
				<% String selectedCustomCategory = ""; %>
				<%
	               SubcategoryData[] subcateData = (SubcategoryData[]) request.getAttribute("subcateData");
	               String subCategory = (String) request.getAttribute("subCategory");
	               
	               out.println("<div class='categoryBox'>\n<ul class='categoryList'>");
	               for(int i = 0; i < subcateData.length; i++){
	                  out.println(subcateData[i].getSubcategoryName());
	                  if(subcateData[i].getSubcategoryName().equals(subCategory)){
	                     out.println("<input type='radio' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "' checked>");
	                     subCategoryId = subcateData[i].getSubcategoryId();
	                  }else{
	                     out.println("<input type='radio' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "'>");
	                  }
	                  if(i == 0){
	                	  out.println("<br/>");
	                  }
	               }
	               out.println("<br/>");
	               
	               List customCategories = (ArrayList) request.getAttribute("customCategories");
	               if(customCategories != null){
	            	   out.println("내가 만든 카테고리 : ");
	            	   for(int i = 0; i< customCategories.size(); i++){
	            			//System.out.println(customCategories[i]);
	            			out.println(customCategories.get(i));
	            			if(customCategories.get(i).equals(subCategory)){
	            				out.println("<input type='radio' name='subCategory' value='" + customCategories.get(i) + "' checked>");
	            				selectedCustomCategory = customCategories.get(i).toString();
	            				subCategoryId = subcateData[0].getSubcategoryId();
	            			}else{
	            				out.println("<input type='radio' name='subCategory' value='" + customCategories.get(i) + "'>");
	            			}
	            	   }
	               }
				%>
				<script type="text/javascript">
					function add_textbox() {
						document.getElementById("addCustomCategories").innerHTML += "<input type='text' name='customCategories' >";
					}
				</script>
				<div id="addCustomCategories">
					<input type="button" value="나만의 키워드 추가" onclick="add_textbox()">
					<br>
				</div>
				<%
               String newsType = (String) request.getAttribute("newsType");
               if (newsType.toString().equals("sim")) {
                  out.println(
                        "관련도순 <input type='radio' name='newsType' value='sim' checked>\n최근순 <input type='radio' name='newsType' value='date'>");
                  out.println("<br/>"); 
               } else if (newsType.toString().equals("date")) {
                  out.println(
                        "관련도순 <input type='radio' name='newsType' value='sim'>\n최근순 <input type='radio' name='newsType' value='date' checked>");
               }
         %>
				<input type="submit" value="선택 완료">
			</form>
		</div>
		<section style="padding-top: 0px;">
			<%
				NewsData[] nd = (NewsData[]) request.getAttribute("newsdata");
				List scrappedNewsId = (ArrayList) request.getAttribute("scrappedNewsId"); 
				
				for (int i = 0; i < nd.length; i++) {
					out.println("<div id='newsContents'>");
					String tmpForScrap = "location.href='../scrap/scrapNews?location=" + i + "&subId="+ subCategoryId
										+ "&custom=" + selectedCustomCategory + "'";
					
					boolean check = false;
					for(int j = scrappedNewsId.size() -1; j >= 0; j--){
						if(scrappedNewsId.get(j).equals(i)){
							check = true;
							out.println((i + 1) + " <button name='scrap' onclick=" + tmpForScrap + "> 스크랩취소 </button>");
						}
					}
					if(!check){
						out.println((i + 1) + " <button name='scrap' onclick=" + tmpForScrap + "> 스크랩하기 </button>");	
					}
					
					request.setAttribute("SelectednewsCategory", subCategory);
					out.println("<p style='font-size: 10px'><b><i>");
					out.println(nd[i].getHeadline());
					out.println("</i></b><br/><p class='newsDescription'>");
					out.println("<br>");
					out.println(nd[i].getDescription());
					out.println("</p><a class='newsDescription' href='");
					out.println(nd[i].getUrl());
					out.println("' target='_blank'>");
					out.println(nd[i].getUrl());
					out.println("</a><br/><p class='newsDescription'>");
					out.println(nd[i].getPubDate());
					out.println("</p><br></div>");
				}
			%>
		</section>
	</div>
</body>
</html>