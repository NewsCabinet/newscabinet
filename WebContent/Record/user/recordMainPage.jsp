<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 기록 보기</title>
<link href="../style.css" rel="stylesheet">
<style>
.basic_contentzone {
   padding-top: 20px;
   position: relative;
   width: 100%;
   height: 500px;
   top: 100px;
}
</style>
</head>
<jsp:include page="../../webHeader.jsp"></jsp:include>
<body>
	<div class="basic_contentzone">
		<p>폴더별로 보기</p>
		<p>
			폴더 :
			<%
				ResultSet rs = (ResultSet) request.getAttribute("folders");
				if (rs != null) {
					while (true) {
						if (rs.next()) {
							String tmpForFolder = "location.href='../UserRecord/main?folderId=" + rs.getInt("folder_id")
									+ "'";
							out.println("<button name='folder' onclick=" + tmpForFolder + ">" + rs.getString("folder_name")
									+ "</button>");
						} else {
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
						String tmpForScrap = "location.href='/NewsCabinet/UserRecord/record?recordId="+ rs.getInt("record_id")+ "'";
						//System.out.println(tmpForScrap);
						%>
						<div id='newsContents' style="cursor: pointer;" onclick="<%=tmpForScrap%>;">
						<%
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