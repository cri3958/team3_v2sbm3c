package dev.mvc.cate;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.contents.Contents;
import dev.mvc.contents.ContentsProcInter;
import dev.mvc.contents.ContentsVO;
import dev.mvc.tool.Tool;

@Controller
public class CateCont {
  @Autowired // CateProcInter interface 구현한 객체를 만들어 자동으로 할당해라.
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc") // @Component("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private AdminProcInter adminProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당
    
  public CateCont() {
    System.out.println("-> CateCont created.");  
  }

//  // FORM 출력, http://localhost:9091/cate/create.do
//  @RequestMapping(value="/cate/create.do", method = RequestMethod.GET)
//  @ResponseBody // 단순 문자열로 출력, jsp 파일명 조합이 발생하지 않음.
//  public String create() {
//    return "<h3>GET 방식 FORM 출력</h3>";
//  }

  // FORM 출력, http://localhost:9091/cate/create.do
  @RequestMapping(value="/cate/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cate/create"); // /WEB-INF/views/cate/create.jsp
    
    return mav;
  }
  
  // FORM 데이터 처리, http://localhost:9091/cate/create.do
  @RequestMapping(value="/cate/create.do", method = RequestMethod.POST)
  public ModelAndView create(CateVO cateVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.create(cateVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      // mav.addObject("code", "create_success"); // 키, 값
      // mav.addObject("name", cateVO.getName()); // 카테고리 이름 jsp로 전송
      mav.setViewName("redirect:/cate/list_all.do"); // 주소 자동 이동
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 전체 목록
   * http://localhost:9091/cate/list_all.do
   * @return
   */
  @RequestMapping(value="/cate/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      mav.setViewName("/cate/list_all"); // /WEB-INF/views/cate/list_all.jsp
      
      ArrayList<CateVO> list = this.cateProc.list_all();
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      
    }
    
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9091/cate/read.do?cateno=1
   * @return
   */
  @RequestMapping(value="/cate/read.do", method = RequestMethod.GET)
  public ModelAndView read(int cateno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cate/read"); // /WEB-INF/views/cate/read.jsp
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    return mav;
  }

  /**
   * 수정폼
   * http://localhost:9091/cate/update.do?cateno=1
   * @return
   */
  @RequestMapping(value="/cate/update.do", method = RequestMethod.GET)
  public ModelAndView update(HttpSession session, int cateno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      // mav.setViewName("/cate/update"); // /WEB-INF/views/cate/update.jsp
      mav.setViewName("/cate/list_all_update"); // /WEB-INF/views/cate/list_all_update.jsp
      
      CateVO cateVO = this.cateProc.read(cateno);
      mav.addObject("cateVO", cateVO);
      
      ArrayList<CateVO> list = this.cateProc.list_all();
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      
    }
        
    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9091/cate/update.do
   * @param cateVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/cate/update.do", method = RequestMethod.POST)
  public ModelAndView update(CateVO cateVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update(cateVO); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      // mav.addObject("code", "update_success"); // 키, 값
      // mav.addObject("name", cateVO.getName()); // 카테고리 이름 jsp로 전송
      mav.setViewName("redirect:/cate/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 삭제폼
   * http://localhost:9091/cate/delete.do?cateno=1
   * @return
   */
  @RequestMapping(value="/cate/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session, int cateno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      // mav.setViewName("/cate/delete"); // /WEB-INF/views/cate/delete.jsp
      mav.setViewName("/cate/list_all_delete"); // /WEB-INF/views/cate/list_all_delete.jsp
      
      CateVO cateVO = this.cateProc.read(cateno);
      mav.addObject("cateVO", cateVO);
      
      ArrayList<CateVO> list = this.cateProc.list_all();
      mav.addObject("list", list);
      
      // 특정 카테고리에 속한 레코드 갯수를 리턴
      int count_by_cateno = this.contentsProc.count_by_cateno(cateno);
      mav.addObject("count_by_cateno", count_by_cateno);
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
   
    }
    
    return mav;
  }
  
 // 삭제 처리, 수정 처리를 복사하여 개발
 // 자식 테이블 레코드 삭제 -> 부모 테이블 레코드 삭제
 /**
  * 카테고리 삭제
  * @param session
  * @param cateno 삭제할 카테고리 번호
  * @return
  */
 @RequestMapping(value="/cate/delete.do", method=RequestMethod.POST)
 public ModelAndView delete_proc(HttpSession session, int cateno) { // <form> 태그의 값이 자동으로 저장됨
//   System.out.println("-> cateno: " + cateVO.getCateno());
//   System.out.println("-> name: " + cateVO.getName());
   
   ModelAndView mav = new ModelAndView();
   
   if (this.adminProc.isAdmin(session) == true) {
     ArrayList<ContentsVO> list = this.contentsProc.list_by_cateno(cateno); // 자식 레코드 목록 읽기
     
     for(ContentsVO contentsVO : list) { // 자식 레코드 관련 파일 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 시작
       // -------------------------------------------------------------------
       String file1saved = contentsVO.getFile1saved();
       String thumb1 = contentsVO.getThumb1();
       
       String uploadDir = Contents.getUploadDir();
       Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
       Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 종료
       // -------------------------------------------------------------------
     }
     
     this.contentsProc.delete_by_cateno(cateno); // 자식 레코드 삭제     
           
     int cnt = this.cateProc.delete(cateno); // 카테고리 삭제
     
     if (cnt == 1) {
       mav.setViewName("redirect:/cate/list_all.do");       // 자동 주소 이동, Spring 재호출
       
     } else {
       mav.addObject("code", "delete_fail");
       mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
     }
     
     mav.addObject("cnt", cnt);
     
   } else {
     mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
   }
   
   return mav;
 }
  
  /**
   * 우선 순위 높임, 10 등 -> 1 등, http://localhost:9091/cate/update_seqno_forward.do?cateno=1
   * @param cateVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/cate/update_seqno_forward.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_forward(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_seqno_forward(cateno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/cate/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 우선 순위 낮춤, 1 등 -> 10 등, http://localhost:9091/cate/update_seqno_backward.do?cateno=1
   * @param cateno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/cate/update_seqno_backward.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_backward(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_seqno_backward(cateno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/cate/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 카테고리 공개 설정, http://localhost:9091/cate/update_visible_y.do?cateno=1
   * @param cateno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/cate/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int cateno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_visible_y(cateno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/cate/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 카테고리 비공개 설정, http://localhost:9091/cate/update_visible_n.do?cateno=1
   * @param cateno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/cate/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int cateno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_visible_n(cateno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/cate/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/cate/msg"); // /WEB-INF/views/cate/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
}






