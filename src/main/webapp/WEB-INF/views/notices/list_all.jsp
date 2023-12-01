<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/notices/list_all.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>전체 글 목록</div>
  
  <aside class="aside_right">
    <a href="./create.do">등록하기</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>관리메뉴</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="noticesVO" items="${list }" varStatus="info">
          <c:set var="noticesno" value="${noticesVO.noticesno }" />
    
          <tr onclick="location.href='./read.do?noticesno=${noticesno}'" style="cursor: pointer;">
            <td>
         <c:choose> 
          <c:when test="${noticesVO.imageurl.startsWith('http://')}"> 
            <img src="${noticesVO.imageurl }" style="width: 120px; height: 90px;">
          </c:when>
          <c:otherwise>
            <img src="/notices/storage/${noticesVO.imageurl }" style="width: 120px; height: 90px;">
          </c:otherwise>
        </c:choose>
            </td>
            <td class="td_bs_left">
              <span style="font-weight: bold;">${noticesVO.noticenumber }</span><br>
            </td>
            <td class="td_bs">
              <a href="/notices/update_text.do?noticesno=${noticesno }" title="수정"><img src="/images/update.png" class="icon"></a>
              <a href="/notices/delete.do?noticesno=${noticesno }" title="삭제"><img src="/images/delete.png" class="icon"></a>
            </td>
          </tr>
        </c:forEach>
    </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
