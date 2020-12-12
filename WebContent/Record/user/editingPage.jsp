<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../webHeader.jsp"></jsp:include>
<meta charset="UTF-8">
<link href="/NewsCabinet/style.css" rel="stylesheet">
<title>기록작성하기</title>
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<body>
	
	<div class="basicTest">
		<div>
			<%	
				String today = (String)request.getAttribute("todayDate");
				ArrayList<FristCategoryData> firstCategoryList = (ArrayList)application.getAttribute("firstCategoryList");
				ArrayList<SubcategoryData> subCategoryList = (ArrayList)application.getAttribute("subCategoryList");
				ArrayList<CustomCategoryData> userCustomCategoryList = (ArrayList)request.getAttribute("userCustomCategoryList");
				ArrayList<UserFolderData> userForderList = (ArrayList)request.getAttribute("userForderList");				
				ArrayList<UserScrapNewsData> scrapNewsList = (ArrayList)request.getAttribute("scrapNewsList");
			%>
	
			<form id="writeForm" method="post" action="/NewsCabinet/UserRecord/record/edit/save">
				<input type="hidden" name="recordId" value="<%=(int)request.getAttribute("recordId")%>">
				<br>
				<h2>기록 작성하기</h2>
				<br>
					<p>
						<b>제목 : &nbsp;&nbsp;</b><input class="listWriteInput" type="text" name="recordTitle" placeholder="제목을 입력해주세요" required>
					</p>
				<br>
					<p>
						<b>작성 날짜 : <%=today%> </b>
					</p>
				<br>
				<p>
					<b>카테고리 &nbsp;</b>
					<select name="firstCategory" id="firstCategory" required>
						<%
							for (int i = 0; i < firstCategoryList.size(); i++) {
							out.println("<option value='" + firstCategoryList.get(i).getCategoryId() + "'>"
							+ firstCategoryList.get(i).getCategoryName() + "</option>");
						}
						%>
					</select>
					<select name="subCategory" id="subCategory" required></select>
					<b>&nbsp;&nbsp; | &nbsp;&nbsp; 폴더 지정  &nbsp; </b>
					<select name='userFolder' required>
						<% for(int i = 0; i < userForderList.size(); i++){
								out.println("<option value='" + userForderList.get(i).getFolderId() + "'>" + userForderList.get(i).getFolderName() + "</option>");	
						}%>
					</select>
				<p>
				<br>
				<p>
					<b>공개 설정 : 
					<input type="radio" name="recordPrivate" value="false" checked> 공개 
					<input type="radio" name="recordPrivate" value="true"> 비공개
					</b>
				</p>
				<br>
				<div class="SRScrapNews">
					<%
					for(int i = 0; i < scrapNewsList.size(); i++){
						String scrapSubCategoryName = scrapNewsList.get(i).getSubCategoryName();
						String scrapHeadline = scrapNewsList.get(i).getHeadline();
						String scrapNewsUrl = scrapNewsList.get(i).getNewsURL();%>
						
						<br>
						<div class="SRScrapNewsItem">
						<a href='<%=scrapNewsUrl%>'> [<%=scrapSubCategoryName%>] <%=scrapHeadline%></a>
						</div>
					
					<% }%>					
			</div>
				<br>
				<br>
				<br>
	
				<textarea class="textBox" name="recordComment" required></textarea>
				<br>
				<br>
				<button class="push_button_Stoore" type="submit">수정 완료하기</button>
			</form>
			<br/>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#firstCategory").change(function() {
            	var subcategoryNameArray = new Array();
    			var subcategoryNameinJS = "";
    			var customCategoryNameArray = new Array();
    			var customCategoryNameinJS = "";
    			
            	<%for(int i = 0; i< firstCategoryList.size(); i++){
            		int selectedFirstCategory = firstCategoryList.get(i).getCategoryId();%>
            		
        			//상위 카테고리 선택시, 하위 카테고리 채워 넣기
            		if(this.value == "<%=selectedFirstCategory%>"){
            			
            			//subcategory in js
            			subcategoryNameArray = new Array();
            	
            			<%
            			for(int j = 0; j < subCategoryList.size(); j++){
            				if(subCategoryList.get(j).getFirstCategoryId() == selectedFirstCategory){%>
            					subcategoryNameinJS = "<%=subCategoryList.get(j).getSubcategoryName()%>";
            					subcategoryNameArray.push(subcategoryNameinJS)
            				<%}else continue;
            			}%>
     
            			//custom category in js
            			customCategoryNameArray = new Array();
            			<%
            			for(int k = 0; k < userCustomCategoryList.size(); k++){
            			if(userCustomCategoryList.get(k).getFirstCategoryId() == selectedFirstCategory){%>
            					customCategoryNameinJS = "<%=userCustomCategoryList.get(k).getCustomCategoryName()%>"
            					customCategoryNameArray.push(customCategoryNameinJS)
            				<%}else continue;
            			}%>	
            			
            		}
              <%}%>	
            	
            	$('#subCategory').empty();
                for (var i = 0; i < subcategoryNameArray.length; i++) {
                	var option = $("<option value='" + subcategoryNameArray[i] + "'>" + subcategoryNameArray[i] +  "</option>");
                    $('#subCategory').append(option);
                }
                
                for(var i = 0; i < customCategoryNameArray.length; i++){
                	var option = $("<option value='" + customCategoryNameArray[i] + "'>" + customCategoryNameArray[i] +  "</option>");
                    $('#subCategory').append(option);
                }
            });
        });
	</script>
</html>