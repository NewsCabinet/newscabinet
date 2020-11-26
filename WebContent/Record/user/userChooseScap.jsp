<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.Connection" 
 		  import="model.ManageScrapNews"
 		  import="java.sql.ResultSet"
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	function sendSelectedData(){
		opener.document.getElementById("parentDiv").innerHTML = "";
		opener.document.getElementById("parentDiv").innerHTML += "<h3>선택한 뉴스</h3>";
		
		var len = $("input[name='userSelectedNews']:checked").length;
		var checkArr = [];
		var str = "";
		 
	    $("input[name='userSelectedNews']:checked").each(function(e){
	        var value = $(this).val();
	        var innerText = $(this).html();
	       // opener.newsIdArr.add(value);
	        checkArr.push(value);      
	        str = "<input type='radio' name='userSelectedNews' value='" + value +  "'>"
	        opener.document.getElementById("parentDiv").innerHTML += str + value + "<br>";
	    })
		
		//opener.document.getElementById("parentDiv").innerHTML += str;
		//console.log(checkArr);
		window.close();
	}

</script>

<body>
	<%!String newsHead = null; %>
	<%
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
	
		HttpSession userSession = request.getSession(false);
		int userId = -1;
		if(session != null){
			userId = (Integer)userSession.getAttribute("userId");
		}
		
		ResultSet userScrapResult = ManageScrapNews.searchAllUserScrapNewsForRecord(conn, userId);
		%>
	
	<form>
	<h3>뉴스를 선택해주세요</h3>
		<div>
			<%	if(userScrapResult!= null){
					while(userScrapResult.next()){
						int newsid = userScrapResult.getInt(1);
						newsHead = userScrapResult.getString(2);
						String str = "<input type='checkbox' name='userSelectedNews'" + 
									"value='" + newsid+"'>" + "[" + newsid +"]"+ newsHead + "<br><br>";
						out.println(str);
					}
					
					out.println("다불러왔다.");
					
				}else{
					out.println("스크랩한 뉴스 불러오는데 오류");
				}
				
				
			%>
		<br>
		<input type="button" value="선택하기" onclick="sendSelectedData()" >
		</div>
	</form>

</body>
</html>