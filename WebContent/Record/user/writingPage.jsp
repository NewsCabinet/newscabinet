<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.UserScrapNewsData, model.CustomCategoryData, model.UserFolderData,
			model.FristCategoryData, model.SubcategoryData"
	import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../../webHeader.jsp"></jsp:include>
	<meta charset="UTF-8">
	<link href="/NewsCabinet/style.css" rel="stylesheet">
	<title>기록작성하기</title>
	
</head>
<body>
	
	<div class="basic_contentzone">
	
		<%		String today = (String)request.getAttribute("todayDate");
				ArrayList<FristCategoryData> firstCategoryList = (ArrayList)request.getAttribute("firstCategoryList");
				ArrayList<SubcategoryData> subCategoryList = (ArrayList)request.getAttribute("subCategoryList");
				ArrayList<CustomCategoryData> userCustomCategoryList = (ArrayList)request.getAttribute("userCustomCategoryList");
				ArrayList<UserFolderData> userForderList = (ArrayList)request.getAttribute("userForderList");				
				ArrayList<UserScrapNewsData> userScrapList = (ArrayList)request.getAttribute("userScrapList");
			%>

		<form method="post" action="/NewsCabinet/UserRecord/restore">
			<br>
			<h2 style="text-align: center; margin-left: 30px">기록 작성하기</h2>
			<br>
				<p>
					<input class="listWriteInput" type="text" name="recordTitle" placeholder="제목을 입력해주세요" required>
				</p>
			<br>
				<p>
					날짜 : <%=today%>
				</p>	
			<br>
			  	<p>
					카테고리  &nbsp;
					<select name="firstCategory" id="firstCategory" required>
			            <%  for(int i = 0; i< firstCategoryList.size(); i++){
								out.println("<option value='"+ firstCategoryList.get(i).getCategoryId() + "'>" + firstCategoryList.get(i).getCategoryName() + "</option>");
							}%>
			        </select>
			        <select name="subCategory" id="subCategory" required>
			        </select>
				</p>
			<br>
			<p> 폴더 지정 &nbsp;
			<select name='userFolder' required>
				<% for(int i = 0; i < userForderList.size(); i++){
						out.println("<option value='" + userForderList.get(i).getFolderId() + "'>" + userForderList.get(i).getFolderName() + "</option>");	
				}%>
			</select>
			<p>
			<br>
			<p>
				공개 설정 : 
				<input type="radio" name="recordPrivate" value="false" checked> 공개 
				<input type="radio" name="recordPrivate" value="true"> 비공개
			</p>
			<br>
			<p>
				<input type="button" value="스크랩한 뉴스 고르기" onclick="getUserChosenScap()">
			</p>
			
			<div id="scrapNewsDiv" >
			<br>
				<%
				if(userScrapList.size() > 0){
					for(int i = 0; i < userScrapList.size() ; i++){
						int newsId = userScrapList.get(i).getNewsId();
						String newsHead = userScrapList.get(i).getHeadline();
						String subCategoryName = userScrapList.get(i).getSubCategoryName();
						String newsUrl = userScrapList.get(i).getNewsURL();
						String pTag = "<p id='" + "SelectedPtag"+ newsId + "' style='display:none'>";
						String str = "<input type='radio' name='radioSelectedNews' value='" + newsId +"'>" 
									+ "&nbsp&nbsp [" + subCategoryName +"] "+ newsHead + "</input><br>";
						String checkboxStr = "<input type='checkbox' name='checkBoxSelectedNews' value='"+ newsId +"' style='display:none'>" ;
						out.println(pTag + str + checkboxStr+ "</p>");
					}
				}else{
					out.println("스크랩한 뉴스 불러오는데 오류");
				}
				%>
			</div>
			<br>
			<div>
				<iframe id='selectedNewsFrame' name='selectedNewsFrame' width='60%' height='400' style="display:none"></iframe>
			</div>
			<br>
			<br>

			<textarea class="textBox" name="recordComment" required></textarea>
			<br>
			<br>
			<button class="push_button_Stoore" type="submit">저장하기</button>
		</form>
		<br>
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
            			if(userCustomCategoryList.get(k).getCategoryId() == selectedFirstCategory){%>
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
	<script>
		function getUserChosenScap(){
			window.open("/NewsCabinet/UserRecord/scrapNews", "뉴스 선택하기", "width=600 height=500")
		}
	</script>
	<script>
		$("input[name='radioSelectedNews']").change(function(){
			<% 
			for(int i = 0; i < userScrapList.size(); i++){%>
				var index = <%=i%>
				if(document.getElementsByName("radioSelectedNews")[index].checked == true){
					<% 
					String selectedNewsURL = userScrapList.get(i).getNewsURL();
					if(selectedNewsURL == null || selectedNewsURL.equals("")){%>
					//URL이 없는 경우도 있어서 없을 경우 네이버 뉴스 뜨도록 설정
					document.getElementById("selectedNewsFrame").src = "https://news.naver.com/";
					<%}else%>
					document.getElementById("selectedNewsFrame").src = "<%=selectedNewsURL%>";
				}
		  <%}%>
		});
	</script>
	


</html>