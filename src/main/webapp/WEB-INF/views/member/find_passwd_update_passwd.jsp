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
        <c:when test="${code == 'passwd_update_check_success'}"> <%-- Java if --%>
                      <form action="./find_passwd_confirm.do" method="post">
                <li class='li_none'>
                <input type="hidden" name="rid" value=${rid}>
                    <div class="form-group">
                        <label>패스워드*: 
                            <input type='password' class="form-control form-control-sm" name='passwd' id='passwd' value='1234' required="required" placeholder="패스워드" autofocus="autofocus">
                        </label>
                    </div>   

                    <div class="form-group">
                        <label>패스워드 확인*: 
                            <input type='password' class="form-control form-control-sm" name='passwd2' id='passwd2' value='1234' required="required" placeholder="패스워드 확인" autofocus="autofocus">
                        </label>
                    </div>
                    <input type="submit" value="변경">  
                </li> 
            </form>                                                                
        </c:when>   
        
        <c:when test="${code == 'passwd_update_check_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">패스워드 변경에 실패했습니다.</span>
          </li>                                                                      
        </c:when>  
        </c:choose>
                                                     
    </DIV>
 
<!-- -------------------------------------------- -->
</body>
<!-- -------------------------------------------- -->
</html>