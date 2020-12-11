<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/NewsCabinet/style.css" rel="stylesheet">
</head>
<body>
	<%
		ArrayList<UserFolderData> userForderList = (ArrayList) request.getAttribute("folders");
	%>
	<div class="category">
		<p><h3>내가 만든 폴더 관리</h3></p>
		<br /> <br />
		<p><h4>폴더 추가</h4></p>
		<form id="resultHiddenForm" action="" method="post">
			<input class="FindButton" type="button" value="폴더 추가하기" onClick="addFolder()"><br>
			<input type="hidden" name="folder" value="" />
		</form>
		<br /> <br />
		<p><h4>폴더 삭제</h4>('default' 폴더는 삭제할 수 없음)</p>
		<form id="cateListForEdit" method="post"
			action="/NewsCabinet/UserRecord/main/folder/management">
			<%
				if (userForderList != null) {
					for (int i = 0; i < userForderList.size(); i++) {
			%>
			<label>
				<input type="checkbox" name="removeFolders" value="<%=userForderList.get(i).getFolderId()%>"> <%=userForderList.get(i).getFolderName()%>
			</label>
			<%
					}
				}
			%>
			<p>
				<input class="FindButton" type="submit" value="폴더 삭제하기"onClick="reloadSelf()">
				<input class="FindButton" type="reset" value="선택 초기화">
			</p>
		</form>
		<br /> <br /> <br />
		<input class="FindButton" type="button" value="닫기" onClick="closeWindow()">
	</div>
</body>
<script type="text/javascript">
	function closeWindow(){
		window.opener.parent.location.reload();
		self.close();
	}
	function addFolder() {
		var inputString = prompt("추가할 폴더 이름을 입력하세요", '내 폴더');
		alert(inputString+"가 폴더에 추가되었습니다.");
		 var form = document.getElementById("resultHiddenForm");
		 form.folder.value = inputString;
		 form.submit();
	}
	function reloadSelf() {
		location.reload();
	}
</script>
</html>