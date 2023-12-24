<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="bulletinidx" value="${bulletinVO.bulletinidx }" />
<c:set var="bulletinname" value="${bulletinVO.bulletinname }" />
<c:set var="bulletintext" value="${bulletinVO.bulletintext }" />
<c:set var="file1" value="${bulletinVO.file1 }" />
<c:set var="file1saved" value="${bulletinVO.file1saved }" />
<c:set var="thumb1" value="${bulletinVO.thumb1 }" />
<c:set var="size1" value="${bulletinVO.size1 }" />
<c:set var="passwd" value="${bulletinVO.passwd }" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/bulletin/read.do?bulletinidx=${bulletinidx}</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<c:import url="/menu/top.do" />
<DIV class='title_line'></DIV>

  <aside class="aside_right">
    <a href="./create.do">등록</a>
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.admin_id != null }">
      <span class='menu_divide' >│</span>
      <a href="./update_text.do?bulletinidx=${bulletinidx}">글 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./update_file.do?bulletinidx=${bulletinidx}">사진 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./delete.do?bulletinidx=${bulletinidx}">삭제</a>  
      <span class='menu_divide' >│</span>
    </c:if>

    <a href="javascript:location.reload();">새로고침</a>
  </aside> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
        <img src="/bulletin/storage/${bulletinVO.file1}" style='width:60%; float: left; margin-top: 0.5%; margin-right: 1%;'>
        </DIV>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
        <span>게시글 번호 : ${bulletinidx}</span><br>
        <span>게시글 제목 : ${bulletinname}</span><br>
        <hr>
        <span>${bulletintext}</span><br>
  </fieldset>

</DIV>

 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>