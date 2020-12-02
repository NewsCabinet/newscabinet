<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" 
	import="javax.servlet.ServletContext" 
	import="java.sql.Connection" 
	import="model.ManageRecord"
	import="java.sql.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
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
            			<%System.out.println(selectedValue);%>
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
			window.open("../Record/user/userChooseScap.jsp", "뉴스 선택하기", "width=600 height=500")
			
		}
	</script>
</head>
<body>
	<div class="box-area">
		<header class="head">
			<div class="wrapper">
				<div class="logo">
					<a href="/NewsCabinet/home.jsp"><b>N</b>ews<b>C</b>abinet</a>
				</div>
				<nav>

					<a href="/NewsCabinet/news/main">뉴스보기</a>
					<a href="/NewsCabinet/scrap/main">스크랩보기</a>
					<a class=headerA href="#">나의 </a>
					<a class=headerA href="#">다른 사람 </a>
					<a href="/NewsCabinet/UserRecord/write">기록작성</a>

				</nav>
			</div>
		</header>
	</div>
	<div class="basic_contentzone">
		<%	ServletContext sc = getServletContext();
			ResultSet userFolder = null;
			userFolder = (ResultSet)request.getAttribute("userFolder");
		%>
	
	
		<form method="post" action="/NewsCabinet/UserRecord/restore">
			<h2 style="text-align: left; margin-left: 30px">기록 작성하기</h2>
			<p>
				<input class="listWriteInput" type="text" name="recordTitle" placeholder="제목을 입력해주세요">
			</p>
			<br>
		
			<p>
				날짜 : <%=sc.getAttribute("todayDate") %>
			<br>
			</p>
			
				<p>
				카테고리 
				<select id="firstCategory">
		            <%
						for(int i = 0; i< categoryInJava.size(); i++){
							out.println("<option value='"+ i + "'>" + categoryInJava.get(i).toString() + "</option>");
						}
						%>
		        </select>
		        <select name= "subCategory" id="subCategory">
		        </select>

			</p>
			<br>
			<h3>폴더</h3>
			<%
				if (userFolder != null) {
				out.println("<select name='userFolder'>");
					while (userFolder.next()) {
						int folderId = userFolder.getInt(1);
						String folderName = userFolder.getString(2);
						out.println("<option value='" + folderId + "'>" + folderName + "</option>");
					}
				out.println("</select>");
				} else {
				out.print("Folder가 로딩되지 않음");
				}
			%> 
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
			<div id="parentDiv">
				
			</div>
			<br>
			<br>

			<textarea class="textBox" name="recordComment">글쓰기</textarea>
			<br>
			<br>
			<button class="push_button_Stoore" type="submit">저장하기</button>
		</form>
		<br>
	</div>
</body>
</html>