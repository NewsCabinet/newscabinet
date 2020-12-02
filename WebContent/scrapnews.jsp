<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 스크랩한 뉴스보기</title>
<link href="../style.css" rel="stylesheet">
</head>
<jsp:include page="newsHeader.html"></jsp:include>
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
					<a href="/NewsCabinet/Record/user/writingPage.jsp">기록작성</a>
				</nav>
			</div>
		</header>
	</div>
	<div class="basic_contentzone">
		<section>
			<div>
				<p>내가 스크랩한 뉴스 정보</p>
				<% List cate = new ArrayList(); %>
				<%
					ResultSet rs = (ResultSet) application.getAttribute("Categories");
					if (rs != null) {
						rs.beforeFirst();
						String tmp = "";
						while (true) {
							if (rs.next()) {
						if (!tmp.equals(rs.getString("category_name"))) {
							cate.add(rs.getString("category_name"));
						}
						tmp = rs.getString("category_name");
							} else {
						break;
							}
						}
					}
				%>
				<script>
				cate = new Array();
				<%
					for(int i = 0; i< cate.size(); i++){%>
						cate.push("<%=cate.get(i).toString()%>");
					<%}
				%>
				function changes1Step(fr) {
						<%for(int i = 0; i< cate.size(); i++){%>
		 					if (fr == "<%=cate.get(i)%>") {
								num = new Array();
								vnum = new Array();
								<%String nameTest = "testing";%>
								<%
									rs = (ResultSet) application.getAttribute("Categories");
									if(rs!=null){
										rs.beforeFirst();
										String tmp = cate.get(i).toString();
										while(true){
											if(rs.next()){
												if(tmp.equals(rs.getString("category_name"))){
													nameTest = rs.getString("subcategory_name");%>
													var name = "<%=nameTest%>";
													num.push(name);
													vnum.push(name);
											<%	}
											}else{
												break;
											}
										}
									}
								%>
							}
		 				<%}%>

					for (i = 0; i < form.Step2.length; i++) {
						form.Step2.options[i] = null;
					}

					for (i = 0; i < num.length; i++) {
						form.Step2.options[i] = new Option(num[i], vnum[i]);
					}
				}
			</script>

				<div>
					<form name='form' method="POST" action="/NewsCabinet/scrap/main">
						<select name='Step1' onchange='changes1Step(value)'>
							<option>--상위 카테고리--</option>
							<%
								String selectedCategory = (String) request.getAttribute("selectedCategory");
								for(int i = 0; i< cate.size(); i++){
									if(cate.get(i).equals(selectedCategory)){
										out.println("<option value='"+cate.get(i).toString() + "' selected>"+ cate.get(i).toString() + "</option>");
									}else{
										out.println("<option value='"+cate.get(i).toString() + "'>"+ cate.get(i).toString() + "</option>");	
									}
								}
							%>
						</select> <select name='Step2'>
							<option>--하위 카테고리--</option>
						</select> <input type="submit" value="카테고리 선택 완료">
					</form>
				</div>
			</div>
		</section>
		<section>
				<%
					ResultSet scrapNews = (ResultSet) request.getAttribute("ScrapNews");
					if (scrapNews != null) {
						while (true) {
							try {
								if (scrapNews.next()) {
									out.println("<div id='newsContents'>");
									out.println("<p style='font-size: 10px'><b><i>");
									out.println(scrapNews.getString("headline"));
									out.println("</i></b><br/><p class='newsDescription'>");
									out.println("<br>");
									out.println(scrapNews.getString("description"));
									out.println("</p><a class='newsDescription' href='");
									out.println(scrapNews.getString("url"));
									out.println("' target='_blank'>");
									out.println(scrapNews.getString("url"));
									out.println("</a><br/><p class='newsDescription'>");
									out.println(scrapNews.getString("published_date"));
									out.println("</p><br></div>");
									out.println("<br/>");
								} else {
									break;
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				%>
		</section>
	</div>
</body>
</html>