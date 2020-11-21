<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="model.NewsData"  import="model.SubcategoryData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.wrapper {
	width: 1170px;
	margin: 0 auto;
}

.head {
	height: 100px;
	background: #2E404E;
	width: 100%;
	z-index: 10;
	position: fixed;
}

.logo {
	width: 30%;
	float: left;
	line-height: 100px;
}

.logo a {
	text-decoration: none;
	font-size: 30px;
	font-family: poppins;
	color: #fff;
	letter-spacing: 5px;
}

nav {
	float: right;
	line-height: 100px;
}

nav a {
	text-decoration: none;
	font-family: poppins;
	letter-spacing: 4px;
	font-size: 20px;
	margin: 0 10px;
	color: #fff;
}

.banner-area {
	width: 100%;
	height: 500px;
	position: fixed;
	top: 100px;
	background-image: url(../images/news.jpg);
	background-size: cover;
	opacity: 0.9;
	background-position: center center;
}

.banner-area h2 {
	padding-top: 8%;
	font-size: 70px;
	font-family: poppins;
	text-transform: uppercase;
	color: black;
}

.content-area {
	width: 100%;
	position: relative;
	top: 450px;
	background: #ebebeb;
	height: 500px;
}

.content-area h2 {
	font-family: poppins;
	letter-spacing: 4px;
	padding-top: 30px;
	font-size: 40px;
	margin: 0;
}

.content-area p {
	padding: 2% 0px;
	font-family: poppins;
	line-height: 30px;
}

section, article {
	width: 100%;
	margin: 0;
}

#menuNav {
	width: 17%;
	float: left;
}

#profile {
	height: 200px;
	background-color: white;
}

#listDiv {
	padding: 5px;
}

#ulList {
	list-style-type: none;
	font-size: 20px;
	text-align: left;
}

.list {
	margin: 10px;
	text-decoration: none;
}

.list a {
	color: #2E404E;
}

#mainDiv {
	width: 80%;
	float: right;
	margin-left: 10px;
	height: 500px;
	border: 2px solid red;
}

#subCategoryBox {
	width: 100%;
	height: 50px;
	background-color: #2E404E;
}

#subCategoryBox li {
	margin: 5px;
	padding: 10px;
}

#subCategoryBox li:hover {
	background-color: #d49466;
	border-radius: 4px;
}

#subCategoryBox li a {
	color: white;
}

#subCategoryList {
	list-style-type: none;
	s padding-left: 0px;
}

.subCategoryElement {
	margin-right: 20px;
	color: white;
	float: left;
}

#newsContents {
	position : relative;
	top:200px;
	folat: right;
	width : 50%;
	margin: auto;
	padding-left: 25px;
	padding-right: 25px;
	border-bottom: 2px solid #2E404E;
}
.newsType {
	position : relative;
	top:150px;
	folat: right;
}
.newsDescription {
word-wrap: break-word; font-size: 10px;
}
</style>
<title>뉴스 보기</title>
</head>
<body class="totalbox">
	<jsp:include page="newsHeader.html" />
	</ul>
	</div>
	<div class = "newsType">
		<form method="post" action="../news/main">
			<%
					SubcategoryData[] subcateData = (SubcategoryData[]) request.getAttribute("subcateData");
					String subCategory = (String) request.getAttribute("subCategory");
					out.println("<div class='categoryBox'>\n<ul class='categoryList'>");
					for(int i = 0; i < subcateData.length; i++){
						out.println(subcateData[i].getSubcategoryName());
						if(subcateData[i].getSubcategoryName().equals(subCategory)){
							out.println("<input type='radio' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "' checked>");
						}else{
							out.println("<input type='radio' name='subCategory' value='" + subcateData[i].getSubcategoryName() + "'>");
						}
					}
					out.println("<p>");
					String newsType = (String) request.getAttribute("newsType");
					if (newsType.toString().equals("sim")) {
						out.println(
								"관련도순 <input type='radio' name='newsType' value='sim' checked>\n최근순 <input type='radio' name='newsType' value='date'>");
					} else if (newsType.toString().equals("date")) {
						out.println(
								"관련도순 <input type='radio' name='newsType' value='sim'>\n최근순 <input type='radio' name='newsType' value='date' checked>");
					}
			%>
			<input type="submit" value="선택 완료">
		</form>
	</div>
	
	
	<section>
		<%
			NewsData[] nd = (NewsData[]) request.getAttribute("newsdata");
			for(int i = 0; i < nd.length; i++){
				out.println("<div id='newsContents'>");
				String tmp = "location.href='../scrap/main?location=" + i + "'";
				out.println(i + " <button name='scrap' onclick=" + tmp + "> 스크랩하기 </button>");
				out.println("<p style='font-size: 10px'><b><i>");
				out.println(nd[i].getHeadline());
				out.println("</i></b><br/><p class='newsDescription'>");
				out.println(nd[i].getDescription());
				out.println("</p><a class='newsDescription' href='");
				out.println(nd[i].getUrl());
				out.println("' target='_blank'>");
				out.println(nd[i].getUrl());
				out.println("</a><br/><p class='newsDescription'>");
				out.println(nd[i].getPubDate());
				out.println("</p></div>");
			}
		%>
	</section>
</body>
</html>