<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
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
 
    <DIV style="margin: 50px; font-size: 16px;">
        <c:choose>
            <c:when test="${code == 'find_passwd_success'}"> <%-- Java if --%>
                <li class='li_none'>
                    <div class="form-group">
                        <label>패스워드*: 
                            <input type='password' class="form-control form-control-sm" name='passwd' id='passwd' value='1234' required="required" placeholder="패스워드">
                        </label>
                        <span id='passwd_msg'></span>
                    </div>   

                    <div class="form-group">
                        <label>패스워드 확인*: 
                            <input type='password' class="form-control form-control-sm" name='passwd2' id='passwd2' value='1234' required="required" placeholder="패스워드 확인">
                        </label>
                        <span id='passwd2_msg'></span>
                    </div>
                    <button type="button" id='btn_send' onclick="send()" class="btn btn-primary btn-sm">비밀번호 변경</button>          
                </li>                                                       
            </c:when>
            <c:when test="${code  == 'find_passwd_fail'}"> <%-- Java if --%>
                <li class='li_none'>
                    <span class="span_fail">비밀번호 찾기에 실패하였습니다.</span>     
                </li>                                                            
            </c:when>
        </c:choose>
    </DIV>
 
<!-- -------------------------------------------- -->
</body>
<!-- -------------------------------------------- -->
</html>