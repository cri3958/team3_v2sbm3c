<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/notices/list_all_grid.do</title>
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
        <span class='menu_divide' >│</span>    
    <a href="./list_by_search.do?now_page=${param.now_page}&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_search_grid.do?now_page=${param.now_page}&word=${param.word }">갤러리형</a>  
  </aside>
  
    <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_search_grid.do'>
      
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
                    onclick="location.href='./list_by_search_grid.do?page_now=1&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
  
  <div class="menu_line"></div> 
  
  
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="noticesVO" items="${list }" varStatus="status">
        <c:set var="noticesno" value="${noticesVO.noticesno }" />
        
      <!-- 4기준 하나의 이미지, 24 * 4 = 96% -->
      <!-- 5기준 하나의 이미지, 19.2 * 5 = 96% -->
      <div onclick="location.href='./read.do?noticesno=${noticesno}&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" 
             style='width: 19%; height: 200px; float: left; margin: 0.5%; padding: 0.5%; background-color: #EEEFFF; text-align: left; cursor: pointer;'>
        
        <c:choose> 
          <c:when test="${noticesVO.imageurl.startsWith('http://')}"> 
            <img src="${noticesVO.imageurl }" style="width: 100%; height: 140px;">
          </c:when>
          <c:when test="${noticesVO.imageurl=='123'}"> 
            <img src="/images/none1.png" style="width: 100%; height: 140px;">
          </c:when>
          <c:otherwise>
            <img src="/notices/storage/${noticesVO.imageurl }" style="width: 100%; height: 140px;">
          </c:otherwise>
        </c:choose>
     
     <div style="font-size:15px; text-align:center;margin-top:3px;">
        ${noticesVO.noticenumber }<br>
        ${noticesVO.species }
    </div>
       
      </div>
              
      <%-- 하나의 행에 이미지를 5개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.count % 5 == 0}"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
      </c:if>
      
    </c:forEach>
  </div>


 
<!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
