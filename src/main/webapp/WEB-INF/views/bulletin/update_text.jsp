<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="bulletinidx" value="${bulletinVO.bulletinidx }" />
<c:set var="bulletinname" value="${bulletinVO.bulletinname }" />
<c:set var="bulletintext" value="${bulletinVO.bulletintext }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'> ${bulletinname } > 수정</DIV>
  
  <aside class="aside_right">
    <a href="./create.do">등록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  
  <div class='menu_line'></div>
  
  <form name='frm' method='post' action='./update_text.do'>
    <input type="hidden" name="bulletinidx" value="${bulletinidx }">
    
    <div>
       <label>제목</label>
       <input type='text' name='bulletinname' value='${bulletinname }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='bulletintext' required="required" class="form-control" rows="12" style='width: 100%;'>${bulletintext }</textarea>
    </div>
    
    <div>
      <label>패스워드</label>
      <input type='password' name='passwd' value='' required="required" 
                class="form-control" style='width: 50%;'>
    </div>
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-secondary btn-sm">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-secondary btn-sm">취소</button>
    </div>
  
  </FORM>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

