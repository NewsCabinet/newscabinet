<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="model.NewsData" import="model.SubcategoryData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 보기</title>
<link href="../style.css?ver=1" rel="stylesheet">
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
	               
	               out.println("<div class='categoryBox'>");
	               out.println("<div class='box1'>");
	               for(int i = 0; i < subcateData.length; i++){
	                  out.println(subcateData[i].getSubcategoryName());
	                  if(subcateData[i].getSubcategoryName().equals(subCategory)){
	                    // out.println("<input type='radio' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "' checked>");
	                    out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "' checked></label>");
	                    subCategoryId = subcateData[i].getSubcategoryId();
	                  }else{
	                     out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "'></label>");
	                  }
	                  if(i == 0){
	                	  out.println("<br/>");
	                  }
	               }
	               out.println("</div>"); //box1 태크 끝
	               out.println("<br/>");
	               
	               out.println("<div class='box1'>");
	               List customCategories = (ArrayList) request.getAttribute("customCategories");
	               if(customCategories != null){
	            	   out.println("<b>내가 만든 카테고리</b>");
	            	   for(int i = 0; i< customCategories.size(); i++){
	            			//System.out.println(customCategories[i]);
	            			out.println(customCategories.get(i));
	            			if(customCategories.get(i).equals(subCategory)){
	            				out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='" + customCategories.get(i) + "' checked><label for='subcategory'>");
	            				selectedCustomCategory = customCategories.get(i).toString();
	            				subCategoryId = subcateData[0].getSubcategoryId();
	            			}else{
	            				out.println("<label for='subcategory'><input type='radio' id='subcategory' name='subCategory' value='" + customCategories.get(i) + "'></label>");
	            			}
	            	   }
	               }
	               out.println("</div>"); //box1 태크 끝

				%>
				<script type="text/javascript">
					function add_textbox() {
						document.getElementById("addCustomCategories").innerHTML += "<input type='text' name='customCategories' >";
					}
				</script>
				 <div id="addCustomCategories">
					 <input class="FindButton" style='margin-top:5px' type="button" value="나만의 키워드 추가" onclick="add_textbox()"> 
					<br>
				</div> 
				<%
				out.println("<div class = 'box1'>");
               String newsType = (String) request.getAttribute("newsType");
               if (newsType.toString().equals("sim")) {
                  out.println(
                        "관련도순 <label for='subcategory'><input type='radio' id='subcategory' name='newsType' value='sim' checked>\n최근순 <input type='radio' name='newsType' value='date'></label>");
                  out.println("<br/>"); 
               } else if (newsType.toString().equals("date")) {
                  out.println(
                        "관련도순 <label for='subcategory'><input type='radio' id='subcategory' name='newsType' value='sim'>\n최근순 <input type='radio' name='newsType' value='date' checked></label>");
               }
               out.println("</div>"); //box1태그 끝
         %>
				<input class="FindButton" style='margin-top:15px' type="submit" value="선택 완료">
			</form>
		</div>
		
		<section class ="sectionArti" style="padding-top: 0px;">
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
							//out.println((i + 1) + " <button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + "> 스크랩취소 </button>");
							out.println(" <button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + "> 스크랩취소 </button>");
						}
					}
					if(!check){
						//out.println((i + 1) + " <button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + "> 스크랩하기 </button>");	
						out.println("<button class='scrab_btn' name='scrap' onclick=" + tmpForScrap + "> 스크랩하기 </button>");	
					}
					
					request.setAttribute("SelectednewsCategory", subCategory);
					out.println("<p style='font-size: 13px'><b><i>");
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