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
	<section>
		<div>
			<p>내가 스크랩한 뉴스 정보</p>
			<% List cate = new ArrayList(); %>
			<%
				// TODO: application 으로 바꾸기
				ResultSet rs = (ResultSet) request.getAttribute("Categories");
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
									// TODO: application 으로 바꾸기
									rs = (ResultSet) request.getAttribute("Categories");
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
							for(int i = 0; i< cate.size(); i++){
								out.println("<option>"+ cate.get(i).toString() + "</option>");
							}
						%>
					</select> <select name='Step2'>
						<option>--하위 카테고리--</option>
					</select>
					<input type="submit" value="카테고리 선택 완료">
				</form>
			</div>
		</div>
	</section>
		<section>
			<%
				ResultSet scrapNews = (ResultSet) request.getAttribute("ScrapNews");
				if(scrapNews!=null){
					while(true){
						try {
							if(scrapNews.next()){
								//out.println(scrapNews.getString("headline"));
							}else{
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
</body>
</html>