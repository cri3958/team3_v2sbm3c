<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticesno" value="${noticesVO.noticesno }" />
<c:set var="noticenumber" value="${noticesVO.noticenumber }" />
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

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/notices/update_text.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<c:import url="/menu/top.do" />
  
  <div class='menu_line'></div>
  
  <form name='frm' method='post' action='./update_text.do'>
  <input type="hidden" name="noticesno" value="${noticesno}">
    
    <div>
       <label>공고 번호 </label>
       <label>'${noticenumber}'</label>
    </div>
    <div>
       <label>접수일</label>
       <input type='text' name='receiptdate' value="${receiptdate }" required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    
    <div>
       <label>종</label>
       <input type='text' name='species' value="${species}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>성별</label>
       <input type='text' name='gender' value="${gender}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>발견 장소</label>
       <input type='text' name='discoveryplace' value="${discoveryplace}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>특징</label>
       <input type='text' name='characteristic' value="${characteristic}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>털색</label>
       <input type='text' name='colorcd' value="${colorcd}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>공고 상황</label>
       <input type='text' name='state' value="${state}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>공고 시작일</label>
       <input type='text' name='publicnoticestart' value="${publicnoticestart}" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>공고 종료일</label>
       <input type='text' name='publicnoticeend' value="${publicnoticeend }" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    <div>
      <label>나이(추정)</label>
      <input type='text' name='age' value='${age }' required="required" 
                class="form-control" style='width: 100%;'>
    </div>
        <div>
      <label>몸무게</label>
      <input type='text' name='weight' value='${weight }' required="required" 
                class="form-control" style='width: 100%;'>
    </div>
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-secondary btn-sm">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-secondary btn-sm">취소</button>
    </div>
  
  </FORM>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>