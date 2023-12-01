<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/notices/create.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<c:import url="/menu/top.do" />
 
  <div class='title_line'>공고 글 등록</div>
  
  <aside class="aside_right">
    <a href="./create.do">등록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
 
  
  <div class='menu_line'></div>
  
  <form name='frm' method='post' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="imageurl" value="temp">
    <div>
       <label>공고 번호 </label>
       <input type='text' name='noticenumber' value="kd-team3-2023-00000" required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>이미지</label>
       <input type='file' class="form-control" name='imagemf' id='imagemf' value='' placeholder="파일 선택">
    </div>
    <div>
       <label>접수일</label>
       <input type='text' name='receiptdate' value="20240101" required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    
    <div>
       <label>종</label>
       <input type='text' name='species' value="[개] 리트리버" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>성별(M/F)</label>
       <input type='text' name='gender' value="M" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>발견 장소</label>
       <input type='text' name='discoveryplace' value="인근 공원" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>특징</label>
       <input type='text' name='characteristic' value="순함" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>털색</label>
       <input type='text' name='colorcd' value="갈색" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>공고 상황</label>
       <input type='text' name='state' value="보호중" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
        <div>
       <label>공고 시작일</label>
       <input type='text' name='publicnoticestart' value="20240101" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>공고 종료일</label>
       <input type='text' name='publicnoticeend' value="20240111" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    <div>
      <label>탄생년도(추정)</label>
      <input type='text' name='age' value="2023" required="required" 
                class="form-control" style='width: 100%;'>
    </div>
     <div>
      <label>몸무게</label>
      <input type='text' name='weight' value="5(kg)" required="required" 
                class="form-control" style='width: 100%;'>
    </div>
    <div>
      <label>보호기관 관할</label>
      <input type='text' name='orgnm' value="서울특별시 종로구" required="required" 
                class="form-control" style='width: 100%;'>
    </div>
    <div>
      <label>보호기관 주소</label>
      <input type='text' name='careaddr' value="종로 12길 15 코아빌딩" required="required" 
                class="form-control" style='width: 100%;'>
    </div>
    <div>
      <label>보호기관 전화번호</label>
      <input type='text' name='officetel' value="02-6901-7001" required="required" 
                class="form-control" style='width: 100%;'>
    </div>
    <div>
      <label>보호기관 명</label>
      <input type='text' name='carenm' value="솔데스크" required="required" 
                class="form-control" style='width: 100%;'>
    </div>
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-secondary btn-sm">등록</button>
      <button type="button" onclick="location.href='/notices/list_all.do'" class="btn btn-secondary btn-sm">목록</button>
    </div>
  
  </form>


 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>