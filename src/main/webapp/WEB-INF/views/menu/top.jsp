<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
  .top_menu_link:link{  /* 방문전 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #FFFFFF;
    font-weight: bold;
  }

  .top_menu_link:visited{  /* 방문후 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #FFFFFF;
    font-weight: bold;
  }

  .top_menu_link:hover{  /* A 태그에 마우스가 올라간 상태 */
    text-decoration: none; /* 밑줄 출력 */
    color: #FFFFFF;
    font-size: 1.05em;
  }
</style> 

<div class='container_main'>
  <div class='top_img'>
    <div class="top_menu_label"></div>      
  </div> <!-- <div class='top_img'></div> 종료 -->
  

  <nav class="navbar navbar-expand-md navbar-dark " style="background-color:#FF7945; margin-top:5px;">
      <a class="navbar-brand" href="/"><img src='/css/images/home.png' title="시작페이지" style='display: block; padding-left: 5px;'></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle Navigation">
        <span class="navbar-toggler-icon"></span>
      </button>    

      <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
          
          <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="/notices/list_by_search.do?now_page=1">공고목록</a>
            </li>
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>

              <c:choose>
                  <c:when test="${sessionScope.id == null }">
                    
                  </c:when>
                  <c:otherwise>
                          <a class="nav-link top_menu_link" href="/favorite/list_by_memberno.do?now_page=1">공고 즐겨찾기</a>
                  </c:otherwise>
                </c:choose>
            </li>
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>

              <c:choose>
                  <c:when test="${sessionScope.id == null }">
                    
                  </c:when>
                  <c:otherwise>
                          <a class="nav-link top_menu_link" href="http://3.34.34.132:5000/chatbot?memberno=${sessionScope.memberno }">챗봇</a>
                  </c:otherwise>
                </c:choose>
            </li>
          
              <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
                <a class="nav-link top_menu_link" href="/bulletin/list_all.do?now_page=1">커뮤니티</a> 
              </li>



            <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
              <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">회원</a>
              <div class="dropdown-menu">
                <c:choose>
                  <c:when test="${sessionScope.id == null }">
                    <a class="dropdown-item" href="/member/create.do">회원 가입</a>
                    <a class="dropdown-item" href="/member/find_id_form.do">아이디 찾기</a>
                    <a class="dropdown-item" href="/member/find_passwd_form.do">비밀번호 찾기</a>
                  </c:when>
                  <c:otherwise>
                  <a class="dropdown-item" href="http://3.37.75.65:8000/ais/recommend_form/?memberno=${sessionScope.memberno }">관심분야 등록하고 추천받기</a>
                    <a class="dropdown-item" href="/member/read.do">가입 정보</a>
                    <a class="dropdown-item" href="/member/passwd_update.do">비밀번호 변경</a>
                    <a class="dropdown-item" href="/member/read.do">회원 정보 수정</a>
                    <a class="dropdown-item" href="javascript: alert('개발 예정')">로그인 내역</a>
                    <a class="dropdown-item" href="#">회원 탈퇴</a>
                  </c:otherwise>
                </c:choose>
              </div>
            </li>
          
            <c:choose>
              <c:when test="${sessionScope.admin_id == null }">
                <li class="nav-item">
                  <a class="nav-link top_menu_link" href="/admin/login.do">관리자 로그인</a>
                </li>
              </c:when>
              <c:otherwise>
                <li class="nav-item dropdown"> <%-- 관리자 서브 메뉴 --%>
                  <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">관리자</a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href='/member/list.do'>회원 목록</a>
                    <a class="dropdown-item" href='/recommend/list.do'>추천 목록</a>
                    <a class="dropdown-item" href='/admin/logout.do'>관리자 ${sessionScope.admin_id } 로그아웃</a>
                  </div>
                </li>
              </c:otherwise>
            </c:choose>
            
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <c:choose>
                  <c:when test="${sessionScope.id == null}">
                      <a class="nav-link top_menu_link" href="/member/login.do">로그인</a>
                  </c:when>
                  <c:otherwise>
                      <a class="nav-link top_menu_link" href='/member/logout.do'>${sessionScope.id } 로그아웃</a>
                  </c:otherwise>
              </c:choose>
            </li>
            <li class="nav-item">
                <a class="nav-link top_menu_link" href="http://3.34.34.132:3000">긴급 공지사항</a>
            </li>     
          </ul>
      </div>    
  </nav>
    
  <div class='content_body'> <!--  내용 시작 -->