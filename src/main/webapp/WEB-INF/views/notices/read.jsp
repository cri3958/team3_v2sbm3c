<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticesno" value="${noticesVO.noticesno }" />
<c:set var="noticenumber" value="${noticesVO.noticenumber }" />
<c:set var="imageurl" value="${noticesVO.imageurl }" />
<c:set var="receiptdate" value="${noticesVO.receiptdate }" />
<c:set var="state" value="${noticesVO.state }" />
<c:set var="publicnoticestart" value="${noticesVO.publicnoticestart }" />
<c:set var="publicnoticeend" value="${noticesVO.publicnoticeend }" />
<c:set var="species" value="${noticesVO.species }" />
<c:set var="gender" value="${noticesVO.gender }" />
<c:set var="discoveryplace" value="${noticesVO.discoveryplace }" />
<c:set var="characteristic" value="${noticesVO.characteristic }" />
<c:set var="colorcd" value="${noticesVO.colorcd }" />
<c:set var="age" value="${noticesVO.age }" />
<c:set var="weight" value="${noticesVO.weight }" />
<c:set var="orgnm" value="${noticesVO.orgnm }" />
<c:set var="careaddr" value="${noticesVO.careaddr }" />
<c:set var="officetel" value="${noticesVO.officetel }" />
<c:set var="carenm" value="${noticesVO.carenm }" />


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/notices/read.do?noticesno=${noticesno}</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<c:import url="/menu/top.do" />
<DIV class='title_line'><A href="./list_by_search.do?cateno=${cateVO.cateno }" class='title_link'>${cateVO.name }</A></DIV>

  <aside class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
      <a href="./create.do?cateno=${cateno }">등록</a>
      <span class='menu_divide' >│</span>
      <a href="./update_text.do?noticesno=${noticesno}&now_page=${param.now_page}&word=${param.word }">글 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./delete.do?noticesno=${noticesno}&now_page=${param.now_page}">삭제</a>  
      <span class='menu_divide' >│</span>

    <a href="javascript:location.reload();">새로고침</a>
            <span class='menu_divide' >│</span>    
    <a href="./list_by_search.do?now_page=${param.now_page}&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_search_grid.do?now_page=${param.now_page}&word=${param.word }">갤러리형</a>  
  </aside> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
        <c:choose> 
          <c:when test="${noticesVO.imageurl.startsWith('http://')}"> 
            <img src="${noticesVO.imageurl }" style='width:300px; height:250px; float: center; margin-top: 0.5%; margin-right: 1%; display : block; margin : auto;'>
          </c:when>
          <c:when test="${noticesVO.imageurl=='123'}"> 
            <img src="/images/none1.png" style='width:80%; float: center; margin-top: 0.5%; margin-right: 1%; display : block; margin : auto;'>
          </c:when>
          <c:otherwise>
            <img src="/notices/storage/${noticesVO.imageurl }" style='width:80%; float: center; margin-top: 0.5%; margin-right: 1%; display : block; margin : auto;'>
          </c:otherwise>
        </c:choose>
        
    </DIV>
    <br>
    
    <div style='text-align: center;'>
        <span>공고번호 : ${noticenumber}</span><br>
        <span>공고 접수일 : ${receiptdate}</span><br>
        <span>공고 상태 : ${state}</span><br>
        <span>공고 시작일 : ${publicnoticestart}</span><br>
        <span>공고 종료예정일 : ${publicnoticeend}</span><br>
        <span>유기동물 종 : ${species}</span><br>
        <span>유기동물 성별 : ${gender}</span><br>
        <span>유기동물 발견장소 : ${discoveryplace}</span><br>
        <span>유기동물 성격 : ${characteristic}</span><br>
        <span>유기동물 털색 : ${colorcd}</span><br>
        <span>유기동물 나이(추정) : ${age}</span><br>
        <span>유기동물 체중 : ${weight}</span><br>
        
        </DIV>
      </li>
    </ul>
    <ul>
     <li class="li_none">
        <DIV style='text-align: center;'>
            <span>보호기관 관할 : ${orgnm}</span><br>
            <span>보호기관 주소 : ${careaddr}</span><br>
            <span>보호기관 전화번호 : ${officetel}</span><br>
            <span>보호기관 명 : ${carenm}</span><br>
        </DIV>
      </li>
      </ul>
  </fieldset>

</DIV>

 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>