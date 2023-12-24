package dev.mvc.notices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.contents.ContentsVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class NoticesCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // @Component("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Component("dev.mvc.admin.AdminProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.notices.NoticesProc") 
  private NoticesProcInter noticesProc;
  
  public NoticesCont () {
    System.out.println("-> NoticesCont created.");
  }
  
  @RequestMapping(value="/notices/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName(url);
    return mav;
  }
  
  //등록 폼
  @RequestMapping(value="/notices/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {  
    ModelAndView mav = new ModelAndView();
    if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      
      mav.setViewName("/notices/create"); 
    } else {
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/contents/msg.do"); 
    }
    return mav;
  }
  
  //등록 처리
  @RequestMapping(value = "/notices/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session,NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
    if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // preview image
      System.out.println("-> image url:"+noticesVO.getImageurl());

      String upDir =  Notices.getUploadDir(); // 파일을 업로드할 폴더 준비
      
      MultipartFile mf = noticesVO.getImagemf();
      
      file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
      System.out.println("check");
      if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
        long size1 = mf.getSize();  // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          System.out.println("check2");
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
            System.out.println("check3");
            thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
            System.out.println("check4");
          }
          
        }
        noticesVO.setImageurl(file1);
      }else {
        noticesVO.setImageurl("123");
      }
      System.out.println("check5");

      int cnt = this.noticesProc.create(noticesVO); 
         
          if (cnt == 1) {
              mav.addObject("code", "create_success");
          } else {
              mav.addObject("code", "create_fail");
          }
          mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
          
          mav.addObject("url", "/notices/msg"); // msg.jsp, redirect parameter 적용
          mav.setViewName("redirect:/notices/msg.do"); // Post -> Get - param...      
      

    } else {
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/contents/msg.do"); 
    }
    return mav; // forward
  }
  
  //목록 출력
  @RequestMapping(value = "/notices/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<NoticesVO> list = noticesProc.list_all();
   
    mav.addObject("list", list);
    
    int search_count = list.size();  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
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
    String paging = this.noticesProc.pagingBox(noticesVO.getNow_page(), "list_by_cateno.do", search_count);
    mav.addObject("paging", paging);
    
    mav.addObject("list", list);
        
    mav.setViewName("/notices/list_all");
  
    return mav;
  }
  
  //읽어오기
  @RequestMapping(value="/notices/read.do", method = RequestMethod.GET)
  public ModelAndView read(int noticesno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notices/read"); 
    
    NoticesVO noticesVO = this.noticesProc.read(noticesno);
    
    mav.addObject("noticesVO",noticesVO);    
    
    
    return mav;
  }
  
  //수정 폼
  @RequestMapping(value="/notices/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(HttpSession session,int noticesno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notices/update_text"); 
    
    if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      NoticesVO noticesVO = this.noticesProc.read(noticesno);
      
      mav.addObject("noticesVO",noticesVO);
      mav.addObject("noticesno",noticesVO.getNoticesno());
    } else {
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/notices/msg.do"); 
    }
    return mav;
  }
  
  //수정 처리
  @RequestMapping(value = "/notices/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(HttpSession session,NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
 
    // mav 객체 이용
    if (this.adminProc.isAdmin(session)) { // 관리자 로그인 확인
      this.noticesProc.update_text(noticesVO); // 글수정
  
      mav.addObject("noticesno", noticesVO.getNoticesno());
      mav.setViewName("redirect:/notices/read.do?"); // 페이지 자동 이동 
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/notices/msg.do"); 
    }
    

    return mav; // forward
  }
  
  //삭제폼
  @RequestMapping(value = "/notices/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session,int noticesno) {
    ModelAndView mav = new ModelAndView();
    if (this.adminProc.isAdmin(session)) { // 관리자 로그인 확인
      NoticesVO noticesVO = this.noticesProc.read(noticesno);
      mav.addObject("noticesVO", noticesVO);
        
      mav.setViewName("/notices/delete");
  } else { // 정상적인 로그인이 아닌 경우 로그인 유도
    mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    mav.setViewName("redirect:/notices/msg.do"); 
  }
    return mav; // forward
  }
  
  @RequestMapping(value = "/notices/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpSession session,NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
    if (this.adminProc.isAdmin(session)) { // 관리자 로그인 확인
      this.noticesProc.delete(noticesVO.getNoticesno()); // DBMS 삭제
  
      mav.setViewName("redirect:/notices/list_by_search.do"); 
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/notices/msg.do"); 
    }
    
    return mav;
  }   
  

  @RequestMapping(value = "/notices/list_by_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_search(HttpSession session,NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
  
    // 검색 목록
    System.out.println("->noticesVO : "+noticesVO.toString());
    ArrayList<NoticesVO> list = this.noticesProc.list_by_search_paging(noticesVO);
    for (int i=0;i<list.size();i++) {
      int noticesno = list.get(i).getNoticesno();
      int memberno = memberProc.getMemberno(session);
      
      HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
      hashMap1.put("memberno", memberno);
      hashMap1.put("noticesno", noticesno);
      
      int cnt = this.noticesProc.favorite(hashMap1);
      if(cnt>=1) {
        list.get(i).setMemberno(memberno);
      }
    }
    mav.addObject("list", list);
  
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", noticesVO.getWord());
    
    int search_count = this.noticesProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
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
    String paging = noticesProc.pagingBox(noticesVO.getNow_page(), "list_by_search.do", search_count);
    mav.addObject("paging", paging);
  
    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/notices/list_by_search");  // /contents/list_by_cateno.jsp
  
    return mav;
  }
  
}
