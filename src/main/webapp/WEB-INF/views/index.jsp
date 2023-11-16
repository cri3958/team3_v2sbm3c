<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link  href="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:import url="/menu/top.do" />

  <div style='margin: 0px auto;'>
  <!-- Fotorama data-ratio="100%/66%" -->
  <div class="fotorama"
         data-autoplay="2000"
         data-nav="thumbs"
         data-ratio="800/520"
         data-width="100%"
         data-height="200"
         data-fit="contain"
         data-transition="crossfade"
         data-keyboard="true"
         data-navposition="top"
         data-loop="true">
    <img src="/jquery/fotorama/images/winter01.jpg"> <!-- static 폴더 기준 -->     
    <img src="/jquery/fotorama/images/winter02.jpg">
    <img src="/jquery/fotorama/images/winter03.jpg">
    <img src="/jquery/fotorama/images/winter04.jpg">
    <img src="/jquery/fotorama/images/winter05.jpg">
    <img src="/jquery/fotorama/images/winter06.jpg">
    <img src="/jquery/fotorama/images/winter07.jpg">
    <img src="/jquery/fotorama/images/winter08.jpg">
    <img src="/jquery/fotorama/images/winter09.jpg">
    <img src="/jquery/fotorama/images/winter10.jpg">
    <img src="/jquery/fotorama/images/winter11.jpg">
    <img src="/jquery/fotorama/images/winter12.jpg">
    <img src="/jquery/fotorama/images/winter13.jpg">
    <img src="/jquery/fotorama/images/winter14.jpg">
    <img src="/jquery/fotorama/images/winter15.jpg">
    <img src="/jquery/fotorama/images/winter16.jpg">
    <img src="/jquery/fotorama/images/winter17.jpg">
    <img src="/jquery/fotorama/images/winter18.jpg">
    <img src="/jquery/fotorama/images/winter19.jpg">
    <img src="/jquery/fotorama/images/winter20.jpg">    
  </div>
</div>

<jsp:include page="./menu/bottom.jsp" flush='false' /> 
</body>
</html>
