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
   top: 100px;
}
html, body { overflow: hidden; }
.folder {
        background-image: url( "/NewsCabinet/images/folder.png" );
        background-repeat : no-repeat;
        border: none;
        width: 150px;
        height: 150px;
        text-align : center;
        font-size : 20px;
        margin : 20px;
        float:left;
}
#contents{
	width: 80%;
	margin: 0 auto;
}
</style>
</head>
<jsp:include page="../../webHeader.jsp"></jsp:include>
<body>
	<div class="basic_contentzone" id="contents">
		<p>폴더별로 보기</p>
		<div>
			<button onclick="window.open('/NewsCabinet/UserRecord/main/folder','폴더 추가하기','width=430,height=500,location=no,status=no,scrollbars=yes, toolbar=0, menubar=no');">폴더 관리하기</button>
		</div>
		<div>
			
			<%
				ResultSet rs = (ResultSet) request.getAttribute("folders");
				if (rs != null) {
					while (true) {
						if (rs.next()) {
							String tmpForFolder = "location.href='../UserRecord/main?folderId=" + rs.getInt("folder_id")
									+ "'";
							out.println("<button name='folder' class='folder' onclick=" + tmpForFolder + ">" + rs.getString("folder_name")
									+ "</button>");
						} else {
							break;
						}
					}
				}
			%>
		</div>
		<div style="clear:both;">
		<%
			rs = (ResultSet) request.getAttribute("recordData");
			if (rs != null) {
				while (true) {
					if (rs.next()) {
					String tmpForScrap = "location.href='/NewsCabinet/UserRecord/record?recordId=" + rs.getInt("record_id") + "'";
			%>
							<div id='newsContents' style="cursor: pointer;" onclick="<%=tmpForScrap%>;">
							<%
							out.println("<p style='font-size: 10px'><b><i>");
							out.println(rs.getString("record_title"));
							out.println("</i></b><p>");
							out.println(rs.getString("record_date"));
							%>
							</p><br></div>
							<br/>
					<%}else{
						break;
					}
				}
			}
		%>
		</div>
	</div>
</body>
</html>