<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
<head>
<link href="style.css?ver=1" rel="stylesheet" type="text/css">
<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<jsp:include page="webHeader.jsp"></jsp:include>
<body>
 <div class ="banner-area">
    <h2><b class="title1">own your mine!</b></h2>
      <script>
         $(function(){
            $(".title1").fadeIn(1500);
         });
         </script>
   </div>
   <div class ="index_content-area" style="padding-top:0px"></div>
</body>

</html>