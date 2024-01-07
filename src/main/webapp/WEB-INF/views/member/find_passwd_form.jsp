<%@ page contentType="text/html; charset=UTF-8"%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>sms - jsp</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
 
<body>
  <div style='margin: 50px;'>
    <form name="smsForm" action="./find_passwd_proc.do" method="post">
      <input type="hidden" name="action" value="go"> 
      <input type="hidden" name="smsType" value="S"> <!-- 발송 타입 -->
      <input type="hidden" name="subject" value=""> <!-- 장문(LMS)인 경우(한글30자이내) -->
      
      <!-- 정상적으로 문자가 전송되고 나서 이동할 주소 -->
      <input type="hidden" name="returnurl" maxlength="64" value="./find_passwd_proc_next.do" size="80">
      
      <div class="form-group">
        <label>전화 번호 입력:
            <input type='text' name='rphone' value='010-' placeholder="전화 번호" autofocus="autofocus">
        예) 010-0112-1112 , '-' 포함해서 입력...
        </label>
        </div>
        
        <div class="form-group">
        <label>아이디 입력:
            <input type='text' name='rid' value='.' placeholder="아이디" autofocus="autofocus">
        해당 번호로 만든 아이디
        </label>
        </div>  
       <!-- 보내는 번호, SMS 서비스를 가입한 기업의 관리자 전화번호 -->
      <input type="hidden" name="sphone1" value="010"> <!-- 전화번호 첫째자리 -->
      <input type="hidden" name="sphone2" value="8607"> <!-- 전화번호 둘째자리 -->
      <input type="hidden" name="sphone3" value="7294"> <!-- 전화번호 셋째자리 -->
      <br><br>
      <input type="submit" value="전송">이통사 정책에 따라 발신번호와 수신번호가 같은 경우
      발송되지 않습니다.
    </form>
  </div>
</body>
 
</html>