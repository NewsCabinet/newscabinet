<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
<style>
.category{
	align: float;
}
#cateListForEdit{
	background-color : #EEEEEE;
	width: 80%;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div class="category">
		<p><h3>내가 만든 카테고리 관리</h3></p>
		<br /> <br />
		<p><h4>카테고리 추가</h4></p>
		<form id="resultHiddenForm" action="" method="post">
			<input class="FindButton" type="button" value="나만의 카테고리 추가하기"
				onClick="addKeyword()"><br> <input type="hidden"
				name="keyword" value="" />
		</form>
		<br /> <br />
		<p><h4>카테고리 삭제</h4></p>
		<form id="cateListForEdit" method="post"
			action="/NewsCabinet/news/customCategory/management">
			<%
				List customCategories = (ArrayList) request.getAttribute("customCategories");
				if (customCategories != null) {
					for (int i = 0; i < customCategories.size(); i++) {
			%>
			<label>
				<input type="checkbox" name="removeCategory" value="<%=customCategories.get(i)%>"> <%=customCategories.get(i)%>
			</label>
			<%
					}
				}
			%>
			<p>
				<input class="FindButton" type="submit" value="카테고리 삭제"
					onClick="reloadSelf()"> <input class="FindButton"
					type="reset" value="선택 초기화">
			</p>
		</form>
		<br />
		<input class="FindButton" type="button" value="닫기" onClick="closeWindow()">
	</div>
</body>
<script type="text/javascript">
	function addKeyword() {
		var inputString = prompt("추가할 '내가 만든 카테고리'를 입력하세요", '키워드');
		alert(inputString+"가 '내가 만든 카테고리'에 추가되었습니다.");
		 var form = document.getElementById("resultHiddenForm");
		 form.keyword.value = inputString;
		 form.submit();
	}
	function reloadSelf() {
		location.reload();
	}
	function closeWindow() {
		window.opener.parent.location.reload();
		self.close();
	}
</script>
</html>