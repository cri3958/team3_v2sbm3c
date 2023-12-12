package dev.mvc.bulletin;

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
import dev.mvc.bulletin.Bulletin;
import dev.mvc.bulletin.BulletinVO;
import dev.mvc.bulletin.Bulletin;
import dev.mvc.bulletin.BulletinVO;
import dev.mvc.bulletin.BulletinVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class BulletinCont {
    @Autowired
    @Qualifier("dev.mvc.bulletin.BulletinProc")
    private BulletinProcInter bulletinProc;
    
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

        String upDir = Tool.getRealPath(request, "/bulletin/storage"); // 파일을 업로드할 폴더 준비
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
          System.out.println("->check1");
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
      
      if (this.adminProc.isAdmin(session) == true) {
        mav.setViewName("/bulletin/list_all"); // /WEB-INF/views/bulletin/list_all.jsp
        
        ArrayList<BulletinVO> list = this.bulletinProc.list_all();
       
        // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
        for (BulletinVO bulletinVO : list) {
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
    
    @RequestMapping(value="/bulletin/read.do", method = RequestMethod.GET)
    public ModelAndView read(int bulletinidx) { 
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/bulletin/read"); 
      
      BulletinVO bulletinVO = this.bulletinProc.read(bulletinidx);
      
      mav.addObject("bulletinVO",bulletinVO);    
      
      
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
}
