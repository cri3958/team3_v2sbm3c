package dev.mvc.favorite;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.notices.Notices;
import dev.mvc.notices.NoticesProcInter;
import dev.mvc.notices.NoticesVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class FavoriteCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.notices.NoticesProc") 
  private NoticesProcInter noticesProc;
  
  @Autowired
  @Qualifier("dev.mvc.favorite.FavoriteProc") 
  private FavoriteProcInter favoriteProc;
  
  public FavoriteCont () {
    System.out.println("-> FavoriteCont created.");
  }
  
  @RequestMapping(value="/favorite/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName(url);
    return mav;
  }
  
  @RequestMapping(value = "/favorite/list_by_memberno.do", method = RequestMethod.GET)
  public ModelAndView list_by_memberno(HttpSession session,FavoriteVO favoriteVO) {
    ModelAndView mav = new ModelAndView();
    
    int id = memberProc.getMemberno(session);
    System.out.println("-> id : "+id);
    favoriteVO.setMemberno(id);
      // 검색 목록
      System.out.println("-> favoriteVO : "+favoriteVO.toString());
      ArrayList<NoticesVO> list = this.favoriteProc.list_by_memberno(favoriteVO);
      
      mav.addObject("list", list);
    
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", favoriteVO.getWord());
      hashMap.put("memberno", id);
      int search_count = this.favoriteProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
      mav.addObject("search_count", search_count);
      /*
       * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
       * 18 19 20 [다음]
       * @param cateno 카테고리번호
       * @param now_page 현재 페이지
       * @param word 검색어
       * @param list_file 목록 파일명
       * @return 페이징용으로 생성된 HTML/CSS tag 문자열
       */
      String paging = favoriteProc.pagingBox(favoriteVO.getNow_page(), "list_by_memberno.do", search_count,favoriteVO.getMemberno());
      mav.addObject("paging", paging);
    
      // mav.addObject("now_page", now_page);
      
      mav.setViewName("/favorite/list_by_memberno");  // /contents/list_by_cateno.jsp
    
    return mav;
  }
  
  @RequestMapping(value = "/favorite/like.do", method = RequestMethod.GET)
  public ModelAndView like(HttpSession session, FavoriteVO favoriteVO) {
    ModelAndView mav = new ModelAndView();
    System.out.println("->like favoriteVO : "+favoriteVO.toString());
    if(memberProc.isMember(session) || (favoriteVO.getMemberno()==0)) {
      int cnt = this.favoriteProc.like(favoriteVO); 
         
          if (cnt == 1) {
              mav.addObject("code", "like_success");
          } else {
              mav.addObject("code", "like_fail");
          }
          mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
          
          mav.addObject("url", "/favorite/msg"); // msg.jsp, redirect parameter 적용
          mav.setViewName("redirect:/favorite/msg.do"); // Post -> Get - param...      
      

    } else {
      mav.addObject("url", "/member/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/favorite/msg.do"); 
    }
    return mav; 
  }
  
  @RequestMapping(value = "/favorite/unlike.do", method = RequestMethod.GET)
  public ModelAndView unlike(HttpSession session, FavoriteVO favoriteVO) {
    ModelAndView mav = new ModelAndView();
    System.out.println("->like favoriteVO : "+favoriteVO.toString());
    if(memberProc.isMember(session) || (favoriteVO.getMemberno()==0)) {
      int cnt = this.favoriteProc.unlike(favoriteVO); 
         
          if (cnt == 1) {
              mav.addObject("code", "unlike_success");
          } else {
              mav.addObject("code", "unlik_fail");
          }
          mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
          
          mav.addObject("url", "/favorite/msg"); // msg.jsp, redirect parameter 적용
          mav.setViewName("redirect:/favorite/msg.do"); // Post -> Get - param...      
      

    } else {
      mav.addObject("url", "/member/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/favorite/msg.do"); 
    }
    return mav; 
  }
}
