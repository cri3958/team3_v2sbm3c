package dev.mvc.bulletin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
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
import dev.mvc.bulletin.Bulletin;
import dev.mvc.bulletin.BulletinVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.contents.Contents;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class BulletinCont {
    @Autowired
    @Qualifier("dev.mvc.bulletin.BulletinProc")
    private BulletinProcInter bulletinProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    public BulletinCont(){
        System.out.println("-> BulletinCont created.");
   }
    
    @Autowired
    @Qualifier("dev.mvc.admin.AdminProc")
    private AdminProcInter adminProc;
    /**
     * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
     * POST → url → GET → 데이터 전송
     * @return
     */
    @RequestMapping(value="/bulletin/msg.do", method=RequestMethod.GET)
    public ModelAndView msg(String url){
      ModelAndView mav = new ModelAndView();

      mav.setViewName(url); // forward
      
      return mav; // forward
    }
    
    @RequestMapping(value="/bulletin/create.do", method = RequestMethod.GET)
    public ModelAndView create() {
  //  public ModelAndView create(HttpServletRequest request,  int cateno) {
      ModelAndView mav = new ModelAndView();

      //CateVO cateVO = this.cateProc.read(cateno); // create.jsp에 카테고리 정보를 출력하기위한 목적
      //mav.addObject("cateVO", cateVO);
//      request.setAttribute("cateVO", cateVO);
      
      mav.setViewName("/bulletin/create"); // /webapp/WEB-INF/views/bulletin/create.jsp
      
      return mav;
    }
    
    /**
     * 등록 처리 http://localhost:9091/bulletin/create.do
     * 
     * @return
     */
    @RequestMapping(value = "/bulletin/create.do", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest request, BulletinVO bulletinVO) {
      System.out.println("-> bulletinVO : "+bulletinVO.toString());
      ModelAndView mav = new ModelAndView();
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = "";          // 원본 파일명 image
        String file1saved = "";   // 저장된 파일명, image
        String thumb1 = "";     // preview image

        String upDir =  Bulletin.getUploadDir(); // 파일을 업로드할 폴더 준비
        //String upDir = Tool.getRealPath(request, "/bulletin/storage"); // 파일을 업로드할 폴더 준비
        System.out.println("-> upDir: " + upDir);
        
        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="파일 선택">
        MultipartFile mf = bulletinVO.getFile1MF();
        
        file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
        System.out.println("-> 원본 파일명 산출 file1: " + file1);
        
        if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
          long size1 = mf.getSize();  // 파일 크기
          
          if (size1 > 0) { // 파일 크기 체크
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir); 
            
            if (Tool.isImage(file1saved)) { // 이미지인지 검사
              // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
              thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
            }
            
          }    
          
          bulletinVO.setFile1(file1);   // 순수 원본 파일명
          bulletinVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
          bulletinVO.setThumb1(thumb1);      // 원본이미지 축소판
          bulletinVO.setSize1(size1);  // 파일 크기
          // ------------------------------------------------------------------------------
          // 파일 전송 코드 종료
          // ------------------------------------------------------------------------------
          
          // Call By Reference: 메모리 공유, Hashcode 전달
          //int adminno = (int)session.getAttribute("adminno"); // adminno FK
          //bulletinVO.setAdminno(adminno);
          System.out.println("->check1 : "+bulletinVO.toString());
          int cnt = this.bulletinProc.create(bulletinVO); 
          System.out.println("->check2");
          
          // ------------------------------------------------------------------------------
          // PK의 return
          // ------------------------------------------------------------------------------
          // System.out.println("--> bulletinidx: " + bulletinVO.getBulletinidx());
          // mav.addObject("bulletinidx", bulletinVO.getBulletinidx()); // redirect parameter 적용
          // ------------------------------------------------------------------------------
          
          if (cnt == 1) {
              mav.addObject("code", "create_success");
              // cateProc.increaseCnt(bulletinVO.getBulletinidx()); // 글수 증가
          } else {
              mav.addObject("code", "create_fail");
          }
          mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
          
          // System.out.println("--> bulletinidx: " + bulletinVO.getBulletinidx());
          // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
          //mav.addObject("bulletinidx", bulletinVO.getBulletinidx()); // redirect parameter 적용
          
          mav.addObject("url", "/bulletin/msg"); // msg.jsp, redirect parameter 적용
          mav.setViewName("redirect:/bulletin/msg.do"); // Post -> Get - param...        
        } else {
          mav.addObject("cnt", "0"); // 업로드 할 수 없는 파일
          mav.addObject("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
          mav.addObject("url", "/bulletin/msg"); // msg.jsp, redirect parameter 적용
          mav.setViewName("redirect:/bulletin/msg.do"); // Post -> Get - param...        
        }
      
      
      return mav; // forward
    }

    /**
     * 전체 목록, 관리자만 사용 가능
     * http://localhost:9091/bulletin/list_all.do
     * @return
     */
    @RequestMapping(value="/bulletin/list_all.do", method = RequestMethod.GET)
    public ModelAndView list_all(HttpSession session) {
      ModelAndView mav = new ModelAndView();
      
      if (this.memberProc.isMember(session) == true) {
        mav.setViewName("/bulletin/list_all"); // /WEB-INF/views/bulletin/list_all.jsp
        
        ArrayList<BulletinVO> list = this.bulletinProc.list_all();
       
        // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
        for (BulletinVO bulletinVO : list) {
          System.out.println(bulletinVO.toString());
          String title = bulletinVO.getBulletinname();
          String content = bulletinVO.getBulletintext();
          
          title = Tool.convertChar(title);  // 특수 문자 처리
          content = Tool.convertChar(content); 
          
          bulletinVO.setBulletinname(title);
          bulletinVO.setBulletintext(content);  

        }
        
        mav.addObject("list", list);
        
      } else {
        mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        
      }
      
      return mav;
    }
    
    /**
     * 조회
     * @return
     */
    @RequestMapping(value="/bulletin/read.do", method=RequestMethod.GET )
    public ModelAndView read_ajax(HttpServletRequest request, int bulletinidx) {
      // public ModelAndView read(int bulletinidx, int now_page) {
      // System.out.println("-> now_page: " + now_page);
      
      ModelAndView mav = new ModelAndView();

      BulletinVO bulletinVO = this.bulletinProc.read(bulletinidx);
      mav.addObject("bulletinVO", bulletinVO); // request.setAttribute("bulletinVO", bulletinVO);
      
      // 단순 read
      // mav.setViewName("/bulletin/read"); // /WEB-INF/views/bulletin/read.jsp
      
      // 쇼핑 기능 추가
      // mav.setViewName("/bulletin/read_cookie"); // /WEB-INF/views/bulletin/read_cookie.jsp
      
      // 댓글 기능 추가 
      mav.setViewName("/bulletin/read_cookie_reply"); // /WEB-INF/views/bulletin/read_cookie_reply.jsp

      
      // -------------------------------------------------------------------------------
      // 쇼핑 카트 장바구니에 상품 등록전 로그인 폼 출력 관련 쿠기  
      // -------------------------------------------------------------------------------
      Cookie[] cookies = request.getCookies();
      Cookie cookie = null;

      String ck_id = ""; // id 저장
      String ck_id_save = ""; // id 저장 여부를 체크
      String ck_passwd = ""; // passwd 저장
      String ck_passwd_save = ""; // passwd 저장 여부를 체크

      if (cookies != null) {  // Cookie 변수가 있다면
        for (int i=0; i < cookies.length; i++){
          cookie = cookies[i]; // 쿠키 객체 추출
          
          if (cookie.getName().equals("ck_id")){
            ck_id = cookie.getValue();                                 // Cookie에 저장된 id
          }else if(cookie.getName().equals("ck_id_save")){
            ck_id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
          }else if (cookie.getName().equals("ck_passwd")){
            ck_passwd = cookie.getValue();                          // Cookie에 저장된 password
          }else if(cookie.getName().equals("ck_passwd_save")){
            ck_passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
          }
        }
      }
      
      System.out.println("-> ck_id: " + ck_id);
      
      mav.addObject("ck_id", ck_id); 
      mav.addObject("ck_id_save", ck_id_save);
      mav.addObject("ck_passwd", ck_passwd);
      mav.addObject("ck_passwd_save", ck_passwd_save);
      // -------------------------------------------------------------------------------
      
      return mav;
    }
    
    //삭제폼
    @RequestMapping(value = "/bulletin/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(HttpSession session,int bulletinidx) {
      ModelAndView mav = new ModelAndView();
      if (this.adminProc.isAdmin(session)) { // 관리자 로그인 확인
        BulletinVO bulletinVO = this.bulletinProc.read(bulletinidx);
        mav.addObject("bulletinVO", bulletinVO);
          
        mav.setViewName("/bulletin/delete");
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/bulletin/msg.do"); 
    }
      return mav; // forward
    }
    
    @RequestMapping(value = "/bulletin/delete.do", method = RequestMethod.POST)
    public ModelAndView delete(HttpSession session,BulletinVO bulletinVO) {
      ModelAndView mav = new ModelAndView();
      if (this.adminProc.isAdmin(session)) { // 관리자 로그인 확인
        this.bulletinProc.delete(bulletinVO.getBulletinidx()); // DBMS 삭제
    
        mav.setViewName("redirect:/bulletin/list_all.do"); 
      } else { // 정상적인 로그인이 아닌 경우 로그인 유도
        mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        mav.setViewName("redirect:/bulletin/msg.do"); 
      }
      
      return mav;
    }   
    
    /**
     * 수정 폼
     * http://localhost:9091/bulletin/update_text.do?bulletinidx=1
     * 
     * @return
     */
    @RequestMapping(value = "/bulletin/update_text.do", method = RequestMethod.GET)
    public ModelAndView update_text(HttpSession session, int bulletinidx) {
      ModelAndView mav = new ModelAndView();
      
      if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
        BulletinVO bulletinVO = this.bulletinProc.read(bulletinidx);
        mav.addObject("bulletinVO", bulletinVO);
       
        
        mav.setViewName("/bulletin/update_text"); // /WEB-INF/views/bulletin/update_text.jsp
        // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
        // mav.addObject("content", content);

      } else {
        mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        mav.setViewName("redirect:/bulletin/msg.do"); 
      }

      return mav; // forward
    }
    
    /**
     * 수정 처리
     * http://localhost:9091/bulletin/update_text.do?bulletinidx=1
     * 
     * @return
     */
    @RequestMapping(value = "/bulletin/update_text.do", method = RequestMethod.POST)
    public ModelAndView update_text(HttpSession session, BulletinVO bulletinVO) {
      ModelAndView mav = new ModelAndView();
      
      // System.out.println("-> word: " + bulletinVO.getWord());
      
      if (this.adminProc.isAdmin(session)) { // 관리자 로그인 확인
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("bulletinidx", bulletinVO.getBulletinidx());
        hashMap.put("passwd", bulletinVO.getPasswd());
        
        if (this.bulletinProc.password_check(hashMap) == 1) { // 패스워드 일치
          this.bulletinProc.update_text(bulletinVO); // 글수정  
           
          // mav 객체 이용
          mav.addObject("bulletinidx", bulletinVO.getBulletinidx());
          mav.setViewName("redirect:/bulletin/read.do"); // 페이지 자동 이동
          
        } else { // 패스워드 불일치
          mav.addObject("code", "passwd_fail");
          mav.addObject("cnt", 0);
          mav.addObject("url", "/bulletin/msg"); // msg.jsp, redirect parameter 적용
          mav.setViewName("redirect:/bulletin/msg.do");  // POST -> GET -> JSP 출력
        }
      } else { // 정상적인 로그인이 아닌 경우 로그인 유도
        mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        mav.setViewName("redirect:/bulletin/msg.do"); 
      }
      // URL에 파라미터의 전송
      // mav.setViewName("redirect:/bulletin/read.do?bulletinidx=" + bulletinVO.getBulletinidx() + "&cateno=" + bulletinVO.getCateno());             
      
      return mav; // forward
    }

    /**
     * 파일 수정 폼
     * http://localhost:9091/bulletin/update_file.do?bulletinidx=1
     * 
     * @return
     */
    @RequestMapping(value = "/bulletin/update_file.do", method = RequestMethod.GET)
    public ModelAndView update_file(HttpSession session, int bulletinidx) {
      ModelAndView mav = new ModelAndView();
      
      if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
        BulletinVO bulletinVO = this.bulletinProc.read(bulletinidx);
        mav.addObject("bulletinVO", bulletinVO);
       
        
        mav.setViewName("/bulletin/update_file"); // /WEB-INF/views/bulletin/update_file.jsp
        
      } else {
        mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        mav.setViewName("redirect:/bulletin/msg.do"); 
      }


      return mav; // forward
    }
    
    /**
     * 파일 수정 처리 http://localhost:9091/bulletin/update_file.do
     * 
     * @return
     */
    @RequestMapping(value = "/bulletin/update_file.do", method = RequestMethod.POST)
    public ModelAndView update_file(HttpSession session, BulletinVO bulletinVO) {
      ModelAndView mav = new ModelAndView();
      
      if (this.adminProc.isAdmin(session)) {
        // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
        BulletinVO bulletinVO_old = bulletinProc.read(bulletinVO.getBulletinidx());
        
        // -------------------------------------------------------------------
        // 파일 삭제 시작
        // -------------------------------------------------------------------
        String file1saved = bulletinVO_old.getFile1saved();  // 실제 저장된 파일명
        String thumb1 = bulletinVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
        long size1 = 0;
           
        String upDir =  Bulletin.getUploadDir(); // C:/kd/deploy/resort_v3sbm3c/bulletin/storage/
        
        Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
        Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료
        // -------------------------------------------------------------------
            
        // -------------------------------------------------------------------
        // 파일 전송 시작
        // -------------------------------------------------------------------
        String file1 = "";          // 원본 파일명 image

        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="파일 선택">
        MultipartFile mf = bulletinVO.getFile1MF();
            
        file1 = mf.getOriginalFilename(); // 원본 파일명
        size1 = mf.getSize();  // 파일 크기
            
        if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
            thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
          }
          
        } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
          file1="";
          file1saved="";
          thumb1="";
          size1=0;
        }
            
        bulletinVO.setFile1(file1);
        bulletinVO.setFile1saved(file1saved);
        bulletinVO.setThumb1(thumb1);
        bulletinVO.setSize1(size1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
            
        this.bulletinProc.update_file(bulletinVO); // Oracle 처리

        mav.addObject("bulletinidx", bulletinVO.getBulletinidx());
        mav.setViewName("redirect:/bulletin/read.do"); // request -> param으로 접근 전환
                  
      } else {
        mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
        mav.setViewName("redirect:/bulletin/msg.do"); // GET
      }
     
      return mav; // forward
    }
}
