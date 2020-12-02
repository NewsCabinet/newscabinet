<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 기록 보기</title>
<link href="../style.css" rel="stylesheet">
</head>
<body>
	<div class="box-area">
		<header class="head">
			<div class="wrapper">
				<div class="logo">
					<a href="/NewsCabinet/home.jsp"><b>N</b>ews<b>C</b>abinet</a>
				</div>
				<nav>
					<a href="/NewsCabinet/news/main">뉴스보기</a> <a
						href="/NewsCabinet/scrap/main">스크랩보기</a> <a class=headerA href="#">나의
					</a> <a class=headerA href="#">다른 사람 </a> <a
						href="/NewsCabinet/Record/user/writingPage.jsp">기록작성</a>
				</nav>
			</div>
		</header>
	</div>
	<div class="basic_contentzone">
		<p>폴더별로 보기</p>
		<p>
			폴더 :
			<%
			ResultSet rs = (ResultSet) request.getAttribute("folders");
				if(rs!= null){
			while(true){
				if(rs.next()){
					String tmpForFolder = "location.href='../UserRecord/main?folderId="+ rs.getInt("folder_id")+ "'";
					out.println("<button name='folder' onclick=" + tmpForFolder + ">"+rs.getString("folder_name") + "</button>");
				}else{
					break;
				}
			}
				}
		%>
		</p>
		<%		
			rs = (ResultSet) request.getAttribute("recordData");
			if(rs!= null){
				while(true){
					if(rs.next()){
						//out.println(rs.getString("record_title")+ "<br/>");
						out.println("<div id='newsContents'>");
						out.println("<p style='font-size: 10px'><b><i>");
						out.println(rs.getString("record_title"));
						out.println("</i></b><p>");
						out.println(rs.getString("record_date"));
						out.println("</p><br></div>");
						out.println("<br/>");
					}else{
						break;
					}
				}
			}
		%>
	</div>
</body>
</html>