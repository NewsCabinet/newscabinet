<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" import="import javax.servlet.ServletContext"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../style.css" rel="stylesheet">
<title>기록작성하기</title>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#firstCategory").change(function() {
                var cate1 = ["정치", "청와대", "국회", "정당", "행정", "국방", "북한", "외교"];
                var cate2 = ["경제", "금융", "증권" , "산업", "재계", "부동산", "소비자", "취업", "스타트업", "주식"];
                var cate3 = ["국제", "아시아", "미국", "유럽", "중국", "일본", "중동", "아프리카", "국제기구", "국제경제"];
                var cate4 = ["사회", "여성", "노동", "환경", "장애인", "인권", "복지", "건강", "교육", "종교"];
                var cate5 = ["문화", "영화", "방송", "연예", "여행", "여가", "음악", "공연", "학술"];
                var cate6 = ["IT", "웹", "IOS", "안드로이드", "네트워크", "게임", "빅데이터", "인공지능", "클라우드", "보안", "반도체", "IOT"];
                var cate7 = ["과학", "로보틱스", "바이오", "의료", "천문학", "화학"];
                var cate8 = ["스포츠", "축구", "프리미어리그","야구", "MLB", "골프", "바둑", "배구", "e-sport"];
                
                var changeItem;
               
                switch (this.value){
                case '1': changeItem = cate1; break;
                case '2': changeItem = cate2; break;
                case '3': changeItem = cate3; break;
                case '4': changeItem = cate4; break;
                case '5': changeItem = cate5; break;
                case '6': changeItem = cate6; break;
                case '7': changeItem = cate7; break;
                case '8': changeItem = cate8; break;
                }
                
                $('#subCategory').empty();
                for (var count = 0; count < changeItem.length; count++) {
                    var option = $("<option>" + changeItem[count] + "</option>");
                    $('#subCategory').append(option);

                }
            });
        });
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
					<a href="/NewsCabinet/news/main">뉴스보기</a> <a href="#">스크랩보기</a> <a
						href="#">기록보기</a> <a
						href="/NewsCabinet/Record/user/writingPage.jsp">기록작성</a>
				</nav>
			</div>
		</header>
	</div>
	<div class="basic_contentzone">
		<%	ServletContext sc = getServletContext();
				request.setAttribute("newsId", 1);
			String newsUrl = (String) request.getAttribute("newsUrl");
			String newsSubcategory = (String) request.getAttribute("subCategoryName");
			int newsSubcategoryId = (Integer) request.getAttribute("subCategoryId");
			String todayDate = (String) request.getAttribute("todayDate");
			ResultSet userFolder = (ResultSet) request.getAttribute("userFolder");
			%>
	
	
		<form method="post" action="../../UserRecord/restore">
			<h2 style="text-align: left; margin-left: 30px">기록 작성하기</h2>
			<p>
				<input class="listWriteInput" type="text" name="recordTitle"
					placeholder="제목을 입력해주세요">
			</p>
			<br>
		
			<p>
				날짜 : <%= sc.getAttribute("todayDate") %>
			<br>
			<p>
				카테고리 
				<select name="firstCategory" id="firstCategory">
		            <option value='1'>정치</option>
		            <option value='2'>경제</option>
		            <option value='3'>국제</option>
		            <option value='4'>사회</option>
		            <option value='5'>문화</option>
		            <option value='6'>IT</option>
		            <option value='7'>과학</option>
		            <option value='8'>스포츠</option>
		        </select>
		        <select id="subCategory"></select>

			</p>
			<br>
			<h3>폴더</h3>
			<%
				if (userFolder != null) {
				out.println("<select name='userFolder' size='2'>");
					while (userFolder.next()) {
						String folderName = userFolder.getString(1);
						out.print("<option value='" + folderName + "'>" + folderName + "</option>");
					}
				out.println("</select>");
				} else {
				out.print("Folder가 로딩되지 않음");
				}
			%>
			<br>
			<p>
				공개 설정 : <input type="radio" name="recordPrivate" value="false"
					checked> 공개 <input type="radio" name="recordPrivate"
					value="true"> 비공개
			</p>
			<br>
			<p>
			<!-- 	<a href="<%=newsUrl%>">뉴스 보러가기:</a>-->
			</p>
			<br>
			<br>

		<!--  	<iframe class="newsBox" src="<%=newsUrl%>"> </iframe>-->
			<textarea class="textBox" name="recordComment">뉴스 url을 카테고리 처럼 선택할 수 있게 할까??</textarea>
			<br>
			<br>
			<button class="push_button_Stoore" type="button">저장하기</button>
		</form>
		<br>
	</div>
</body>
</html>