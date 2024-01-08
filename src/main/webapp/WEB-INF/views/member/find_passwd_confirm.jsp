<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
          
 
<script type="text/javascript">
  $(function(){ 
     
  });
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0"> 
<!-- ----------------------------------------- -->
<c:import url="/menu/top.do" />
 
    <DIV style="margin: 50px; font-size: 16px;">
        <c:choose>
        <c:when test="${code == 'passwd_update_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">패스워드를 변경했습니다.</span>
          </li>   
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='/'"
                         class="btn btn-primary btn-sm">확인</button>
          </li>                                                                     
        </c:when>   
        
        <c:when test="${code == 'passwd_update_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">패스워드 변경에 실패했습니다.</span>
          </li>                                                                      
        </c:when>  
        </c:choose>
    </DIV>
 <jsp:include page="../menu/bottom.jsp" flush='false' />
<!-- -------------------------------------------- -->
</body>
<!-- -------------------------------------------- -->
</html>