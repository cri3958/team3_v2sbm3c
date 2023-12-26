<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/bulletin/list_all.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>전체 글 목록</div>
  
  <aside class="aside_right">
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>
    <a href="./create.do">등록</a>
  </aside>
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="bulletinVO" items="${list }" varStatus="info">
          <c:set var="bulletinidx" value="${bulletinVO.bulletinidx }" />
          <c:set var="file1" value="${bulletinVO.file1 }" />
          <c:set var="passwd" value="${bulletinVO.passwd }" />
    
          <tr onclick="location.href='./read.do?bulletinidx=${bulletinidx}'" style="cursor: pointer;">
            <td>
              <c:choose>
                <c:when test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                  <%-- registry.addResourceHandler("/bulletin/storage/**").addResourceLocations("file:///" +  Contents.getUploadDir()); --%>
                  <img src="/bulletin/storage/${file1 }" style="width: 120px; height: 90px;">
                </c:when>
                <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/bulletin/images/none1.png -->
                  <img src="/images/none1.png" style="width: 120px; height: 90px;">
                </c:otherwise>
              </c:choose>
            </td>
            <td class="td_bs_left">
              <span style="font-weight: bold;">${bulletinVO.bulletinname }</span><br>
              <c:choose>
                <c:when test="${bulletinVO.bulletintext.length() > 160 }">
                  ${bulletinVO.bulletintext.substring(0, 160) }...
                </c:when>
                <c:otherwise>
                  ${bulletinVO.bulletintext }
                </c:otherwise>
              </c:choose>
            </td>
            <td class="td_bs">
              <a href="./delete.do?bulletinidx=${bulletinidx}" title="삭제"><img src="/images/delete.png" class="icon"></a>
            </td>
          </tr>
        </c:forEach>
    </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
