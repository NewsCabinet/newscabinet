<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.Connection" 
 		  import="model.ManageScrapNews, model.UserScrapNewsData"
 		  import="java.sql.ResultSet"
 		  import="java.util.ArrayList"
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>

<body>
	<form>
	<h3>뉴스를 선택해주세요</h3>
		<div>
			<%!ArrayList<UserScrapNewsData> userScrapList = null;%>
			<% userScrapList = (ArrayList)request.getAttribute("userScrapList");
			
				if(userScrapList.size() > 0){
					for(int i = 0; i < userScrapList.size() ; i++){
						int newsId = userScrapList.get(i).getNewsId();
						String newsHead = userScrapList.get(i).getHeadline();
						String subCategoryName = userScrapList.get(i).getSubCategoryName();
						String str = "<input type='checkbox' id='userSelectedNews' name='userSelectedNews' value='" + newsId +" '>" 
									+ " [" + subCategoryName +"] "+ newsHead + "<br><br>";
						out.println(str);
					}
				}else{
					out.println("스크랩한 뉴스 불러오는데 오류");
				}
			%>
		<br>
		<input class="FindButton" type="button" value="선택하기" onclick="sendSelectedData2()" >
		</div>
	</form>
</body>
<script>
	function sendSelectedData2(){
		//opener.document.getElementById("scrapNewsDiv").innerHTML = "";
	
		//var listlen = document.getElementsByName("userSelectedNews").length;
	
		<% 
			int listLen = userScrapList.size();
			int selectCount = 0;
			String newsURL = "";
			
				for(int i = 0; i < listLen; i++){
					selectCount = i;%>
					var index = <%=i%>
					<% int selectedNewsId = userScrapList.get(i).getNewsId();
					String tagName = "SelectedPtag" + selectedNewsId; %>
					
					
					if(document.getElementsByName("userSelectedNews")[index].checked == true){
						opener.document.getElementById("<%=tagName%>").style.display = "";
						opener.document.getElementsByName("checkBoxSelectedNews")[index].checked = true;
					}else{
						opener.document.getElementById("<%=tagName%>").style.display = "none";
						opener.document.getElementsByName("checkBoxSelectedNews")[index].checked = false;
					}
			<%}%>
			
		
		opener.document.getElementById("selectedNewsFrame").style.display = "";
		
		window.close();
	}
</script>

</html>