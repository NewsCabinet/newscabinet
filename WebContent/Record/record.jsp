<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, model.RecordData, model.UserScrapNewsData"  %>
<jsp:include page="../webHeader.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>특정 기록 보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<body>
	
	<%
		boolean isUserLikeRecord = (Boolean)request.getAttribute("isUserLikeRecord");
		System.out.println("like = " + isUserLikeRecord);
		
		boolean isCheckMyRecord = (Boolean)request.getAttribute("isCheckMyRecord");
		RecordData recordData = (RecordData)request.getAttribute("selectedRecordData");
		ArrayList<UserScrapNewsData> scrapNewsList = (ArrayList)request.getAttribute("scrapNewsList");
		
		String firstCategoryName = "";
		String subCategoryName = "";
		String recordTitle = "";
		String recordDate = "";
		int recordCount = -1;
		int recordId = -1;
		String recordComment = "";
		
		if(recordData != null){
			firstCategoryName = recordData.getFirstCategoryName();
			subCategoryName = recordData.getSubcategoryName();
			recordTitle = recordData.getRecordTitle();
			recordDate = recordData.getRecordDate();
			recordId = recordData.getRecordId();
			recordCount = recordData.getRecordCount();
			recordComment = recordData.getRecordComment();
		}
		
		String likeRecordUrl = "/NewsCabinet/record/specific?id=" + recordId+"&like=click";
		String tmpForRemove = "/NewsCabinet/record/user/remove?id="+ recordId;
		String tmpForEdit = "/NewsCabinet/record/user/edit?id="+ recordId;
	%>

	<div class="basic_contentzone">
		<section>
			<div id="btnAlign" style="display:none">
				<input class="FindButton" type="button" value="기록 수정하기" onclick="location.href='<%=tmpForEdit%>'">
				<input class="FindButton" type="button" value="기록 삭제하기" onclick="location.href='<%=tmpForRemove%>'"><br/>
			</div>
		</section>
		<section id="defaultAlign">
			<br>
			<h2><%=firstCategoryName %> | <%=subCategoryName %></h2>
		</section>
		
		<div class="SRContentArea">
			<h2> <%=recordTitle %> </h2>
			<div class="SRInfoContecnt">
				<%=recordDate %> &nbsp;| &nbsp; 조회수 <%=recordCount %> &nbsp; | &nbsp; 
				<img src="/NewsCabinet/images/emptyHeart.png" id="likeButton" name ="likeButton" width="40px" 
				onclick="location.href='<%=likeRecordUrl%>'"> 
			</div>
			<div class="SRCommnet">
				<%=recordComment %>
			</div>	
			<br>
			<br>
			<div class="SRScrapNews">
				<h3>참고한 뉴스</h3>
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
		</div>
		
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
function goBack(){
		window.history.back();
	}



window.onload = function(){
	var check = <%=isCheckMyRecord%>
	if(check == false)
		document.getElementById("btnAlign").style.display="none";
	else
		document.getElementById("btnAlign").style.display="";
	
	
	var likeButton = <%=isUserLikeRecord%>
	if(likeButton == true){
		document.getElementById("likeButton").src = "/NewsCabinet/images/fullHeart.png";
		
	}else if(likeButton == false){
		document.getElementById("likeButton").src = "/NewsCabinet/images/emptyHeart.png";
	}
	
	console.log(likeButton)	
	
} 
</script>

</html>