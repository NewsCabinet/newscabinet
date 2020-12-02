<%@ page import="java.sql.*, java.util.*" %>
<div>
			<p>내가 스크랩한 뉴스 정보</p>
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
			<script>
				category = new Array();
				<%
					for(int i = 0; i< categoryInJava.size(); i++){%>
						category.push(<%=categoryInJava.get(i).toString()%>);
					<%}%>
				
				function changes1Step(fr) {
						<%for(int i = 0; i< categoryInJava.size(); i++){%>
		 					if (fr == "<%=categoryInJava.get(i)%>") {
								num = new Array();
								vnum = new Array();
								<%String nameTest = "testing";%>
								<%
									rs = (ResultSet) application.getAttribute("Categories");
									if(rs!=null){
										rs.beforeFirst();
										String tmp = categoryInJava.get(i).toString();
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
				<form name='form'>
					<select name='Step1' onchange='changes1Step(value)'>
						<option>--상위 카테고리--</option>
						<%
							for(int i = 0; i< categoryInJava.size(); i++){
								out.println("<option>"+ categoryInJava.get(i).toString() + "</option>");
							}
						%>
					</select> 
					<select name='Step2'>
						<option>--하위 카테고리--</option>
					</select>
				</form>
			</div>
		</div>