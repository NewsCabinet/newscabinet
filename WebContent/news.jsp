<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <link href="style.css" rel="stylesheet" type="text/css">
    <title>뉴스보기</title>
</head>

<body class="totalbox">
    <header id ="container"><p id="title"><b style="color:#d49466">
        N</b>ews <b style="color:#d49466">C</b>abinet</p></header>
        <hr color="#2E404E">
    <section>
       <nav id ="menuNav">
          <div id = "profile">My own Newsroom</div>
          <div id = "listDiv">
           <ul id ="ulList">
               <li class="list"><a href="">홈</a></li>
               <li class="list"><a href="">뉴스 보기</a></li>
               <li class="list"><a href="">스크랩 보기</a></li>
               <li class="list"><a href="">기록 보기</a></li>
               <li class="list"><a href="">기록 작성</a></li>
           </ul>
           </div>
       </nav>
       <div id="mainDiv">
                <h2>뉴스 보기</h2> 
          <div id="subCategoryBox">       
          <ul id ="subCategoryList">
              <li class="subCategoryElement"><a href="">정치</a></li>
              <li class="subCategoryElement"><a href="">과학</a></li>
              <li class="subCategoryElement"><a href="">IT</a></li>
              <li class="subCategoryElement"><a href="">경제</a></li>
              <li class="subCategoryElement"><a href="">시사</a></li>
          </ul> 
          </div>  
          
          <div id="newsContents">
                   <p style="font-size: 18px"><i>주식형펀드서 사흘째 자금 순유출<% out.println(request.getAttribute("title")); %></i></p>
                    <p>국내 <b>주식</b>형 펀드에서 사흘째 자금이 빠져나갔다. 
                    26일 금융투자협회에 따르면 지난 22일 상장지수펀드(ETF)를 제외한 국내 <b>주식</b>형 펀드에서 126억원이 순유출됐다. 
                    472억원이 들어오고 598억원이 펀드...</p>
                    <p style="color: gray; font-size: 12px" >Mon, 26 Sep 2016</p>
              
          </div>
          <div id="newsContents">
                   <p style="font-size: 18px"><i>주식형펀드서 사흘째 자금 순유출</i></p>
                    <p>국내 <b>주식</b>형 펀드에서 사흘째 자금이 빠져나갔다. 
                    26일 금융투자협회에 따르면 지난 22일 상장지수펀드(ETF)를 제외한 국내 <b>주식</b>형 펀드에서 126억원이 순유출됐다. 
                    472억원이 들어오고 598억원이 펀드...</p>
                    <p style="color: gray; font-size: 12px" >Mon, 26 Sep 2016</p>
              
          </div>
         </div>
       </section>
</html>