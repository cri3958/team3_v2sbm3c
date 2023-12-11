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

<div class='title_line'>
    전체 글 목록
    <c:if test="${param.word.length() > 0 }">
      > 「${param.word }」 검색
    </c:if> 
    ${search_count } 건
  </div>
  
  <aside class="aside_right">
    <a href="./create.do">등록하기</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  
    <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_search.do'>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어를 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value=''>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- 검색 상태하면 '검색 취소' 버튼을 출력 --%>
        <button type='button' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;"
                    onclick="location.href='./list_by_search.do?page_now=1&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
  
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <thead>
      <tr>
        <th style='text-align: center;'>사진</th>
        <th style='text-align: center;'>공고번호</th>
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
          <c:when test="${noticesVO.imageurl=='123'}"> 
            <img src="/images/none1.png" style="width: 120px; height: 90px;">
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
 <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
