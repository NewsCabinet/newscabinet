<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="style.css?ver=1" rel="stylesheet" type="text/css">
<title>HOME</title>
<script src="https://kit.fontawesome.com/faf91fea33.js"
	crossorigin="anonymous"></script>
</head>
<jsp:include page="webHeader.jsp"></jsp:include>
<body>
   <section id="banner">
				<div class="content">
                    <h1><b>OWN YOUR MIND!</b></h1>
				</div>
			</section>
	<section id="one" class="wrapper">
				<div class="inner flex flex-3">
					<div class="flex-item left">
						<div>
							<h3>뉴스보기</h3>
							<p>관심 있어 하는 분야를<b> 키워드 검색</b>을 통해 자신만의 카테고리를 만들어 보세요! 
							 </p>
						</div>
						<div>
							<h3>스크랩하기 </h3>
							<p>뉴스 기사를 보던 중 흥미가 있고 관심있는 기사가 있다면 <b>자유롭게 스크랩</b> 해보세요. </p>
						</div>
					</div>
					<div class="flex-item image fit round">
						<img class="roundImg" src="images/newsIcon.jpg" alt="" />
					</div>
					<div class="flex-item right">
						<div>
							<h3>기록 작성</h3>
							<p>스크랩한 뉴스를 바탕으로 <b>자신만의 견해를 담고, 요약 정리 해보세요.</b>카테고리를 분리해 파일 정리도 할 수 있어요!</p>
						</div>
						<div>
							<h3>기록 보기</h3>
							<p><b>내가 작성한</b> 기록들을 보고 <br /><b>다른 사람들이 작성한 기록</b>도 볼 수 있어요.</p>
						</div>
					</div>
				</div>
			</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>