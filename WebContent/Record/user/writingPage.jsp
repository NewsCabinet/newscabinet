<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" 
	import="javax.servlet.ServletContext" 
	import="java.sql.Connection" 
	import="model.ManageRecord, model.UserScrapNews"
	import="java.sql.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../../webHeader.jsp"></jsp:include>
	<meta charset="UTF-8">
	<link href="/NewsCabinet/style.css" rel="stylesheet">
	<title>기록작성하기</title>
	<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript">

		<% List categoryInJava = new ArrayList(); %>
		<%
			ResultSet rs = (ResultSet) application.getAttribute("Categories");
			if (rs != null) {
				rs.beforeFirst();
				String tmp = "";
				while (true) {
					if (rs.next()) {
				if (!tmp.equals(rs.getString("category_name"))) {
					categoryInJava.add(rs.getString("category_name"));
				}
				tmp = rs.getString("category_name");
					} else {
				break;
					}
				}
			}
		%>
		category = new Array();
		<%
		for(int i = 0; i< categoryInJava.size(); i++){%>
			category.push("<%=categoryInJava.get(i).toString()%>");
		<%}%>
		
        $(function() {
            $("#firstCategory").change(function() {
            	<%for(int i = 0; i< categoryInJava.size(); i++){
            		String selectedValue = Integer.toString(i);
            	%>
            	
            		if(this.value == '<%=selectedValue%>') {
            			subcategoryArray = new Array();
            			<% String nameTest = "testing";%>
            			<%
						rs = (ResultSet) application.getAttribute("Categories");
						if(rs!=null){
							rs.beforeFirst();
							String tmp = categoryInJava.get(i).toString();
							while(true){
								if(rs.next()){
									if(tmp.equals(rs.getString("category_name"))){
										nameTest = rs.getString("subcategory_name");%>
										var name = "<%=nameTest%>"
										subcategoryArray.push(name)
								<%	}
								}else break;	
							}
						}%>		
            		}
           		<%}%>

                $('#subCategory').empty();
                for (var i = 0; i < subcategoryArray.length; i++) {
                	var option = $("<option value='" + subcategoryArray[i] + "'>" + subcategoryArray[i] +  "</option>");
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
</head>
<body>
	
	<div class="basic_contentzone">
	
		<%	ArrayList<Integer> userFolderIdList = (ArrayList)request.getAttribute("forderIdList");
			ArrayList<String> userFolderNameList = (ArrayList)request.getAttribute("forderNameList");
			String today = (String)request.getAttribute("todayDate");
			ArrayList<UserScrapNews> userScrapList = (ArrayList)request.getAttribute("userScrapList");
		%>

		<form method="post" action="/NewsCabinet/UserRecord/restore">
			<h2 style="text-align: left; margin-left: 30px">기록 작성하기</h2>
			<p>
				<input class="listWriteInput" type="text" name="recordTitle" placeholder="제목을 입력해주세요">
			</p>
			<br>
		
			<p>
				날짜 : <%=today%>
			<br>
			</p>
				<p>
				카테고리  <select id="firstCategory" required>
		            <%  for(int i = 0; i< categoryInJava.size(); i++){
							out.println("<option value='"+ i + "'>" + categoryInJava.get(i).toString() + "</option>");
						}%>
		        </select>
		        <select name= "subCategory" id="subCategory" required>
		        </select>
			</p>
			<br>
			<h3>폴더</h3>
			<select name='userFolder'>
				<% for(int i = 0; i < userFolderIdList.size(); i++){
						out.println("<option value='" + userFolderIdList.get(i) + "'>" + userFolderNameList.get(i) + "</option>");	
				}%>
			</select>
			
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

			<textarea class="textBox" name="recordComment"></textarea>
			<br>
			<br>
			<button class="push_button_Stoore" type="submit">저장하기</button>
		</form>
		<br>
	</div>
</body>
<script>
	$("input[name='radioSelectedNews']").change(function(){
		<% 
		
		String newsContent = "";	
		int listLen = userScrapList.size();
		
			for(int i = 0; i < listLen; i++){%>
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