<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, model.RecordData, model.UserScrapNewsData"  %>
<jsp:include page="../webHeader.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>특정 기록 보기</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">


<style type="text/css">
	/* SR 의미 specificReocrd --*/
	.SRContentArea{
		width: 60%;
		text-align: left;
		margin: 50px auto; 
		padding: 30px;
	}
	.SRInfoContecnt{
		font-size: small;
		text-align:right;
		padding: 10px;
		border-bottom: 2px solid #2E404E;
	}
	
	.SRTitle{
		font-weight: bold;
		font-size: larger;
		padding: 30px;
	}
	
	.SRCommnet{
		font-size: medium;
		margin: 30px auto; 
		padding: 20px;
		
	}
	
	.SRScrapNews{
		font-size: medium;
		margin: 30px auto; 
		padding: 20px;
		border-top: 2px solid #2E404E;
	}
	
	
	.SRScrapNewsItem{
		margin-top:15px;
		margin-bottom:10px;
	}
	
	.SRScrapNewsItem a{
		text-decoration: none;
		padding: 20px;
		color:black;
	}
	
	.SRScrapNewsItem a:hover{
		box-shadow: 1px 1px 10px #ddd;
	}
	
	#btnAlign{
		float: right;
		margin-right: 200px;
	}
	#defaultAlign{
		clear: both;
	}
	
</style>

</head>
<body>
	<%! boolean userLikeRecord = true;%>
	<%
		boolean isCheckMyRecord = (Boolean)request.getAttribute("isCheckMyRecord");
		RecordData recordData = (RecordData)request.getAttribute("selectedRecordData");
		ArrayList<UserScrapNewsData> scrapNewsList = (ArrayList)request.getAttribute("scrapNewsList");
		
		String firstCategoryName = "";
		String subCategoryName = "";
		String recordTitle = "";
		String recordDate = "";
		int recordCount = -1;
		String recordComment = "";
		
		if(recordData != null){
			firstCategoryName = recordData.getFirstCategoryName();
			subCategoryName = recordData.getSubcategoryName();
			recordTitle = recordData.getRecordTitle();
			recordDate = recordData.getRecordDate();
			recordCount = recordData.getRecordCount();
			recordComment = recordData.getRecordComment();
		}
		
		
	%>
	<%
		String tmpForRemove = "/NewsCabinet/UserRecord/record/remove?id="+ recordData.getRecordId();
		String tmpForEdit = "/NewsCabinet/UserRecord/record/edit?id="+ recordData.getRecordId();
	%>

	<div class="basic_contentzone">
		<section>
			<div id="btnAlign" style="display:none">
				<input class="FindButton" type="button" value="기록 수정하기" >
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
				<img src="/NewsCabinet/images/emptyHeart.png" id="likeButton" name ="likeButton" width="40px" onclick="likeToggle()">
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

</body>
<script type="text/javascript">
function goBack(){
		window.history.back();
	}

function likeToggle(){
	<%
		System.out.println("자바스크립트 눌림");
		if(userLikeRecord == true){
			userLikeRecord = false;
			System.out.println(userLikeRecord);
		}else{
			userLikeRecord = true;
			System.out.println(userLikeRecord);
		}
	%>
	
	var like = <%=userLikeRecord%>
	console.log(like)
	
	if(like == true){
		document.getElementById("likeButton").src = "/NewsCabinet/images/emptyHeart.png";
		console.log(like)
		
	}else if(like == false){
		document.getElementById("likeButton").src = "/NewsCabinet/images/fullHeart.png";
		console.log(like)	
	}
}

window.onload = function(){
	var check = <%=isCheckMyRecord%>
	if(check == false)
		document.getElementById("btnAlign").style.display="none";
	else
		document.getElementById("btnAlign").style.display="";
} 
</script>

</html>