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

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class NoticesCont {
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
  public ModelAndView create() {  
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/notices/create"); 
    
    return mav;
  }
  
  //등록 처리
  @RequestMapping(value = "/notices/create.do", method = RequestMethod.POST)
  public ModelAndView create(NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
    
    String file1 = "";          // 원본 파일명 image
    String file1saved = "";   // 저장된 파일명, image
    String thumb1 = "";     // preview image
    
    String upDir =  Notices.getUploadDir(); // 파일을 업로드할 폴더 준비
    System.out.println("-> upDir: " + upDir);
    
    MultipartFile mf = noticesVO.getImagemf();
    
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
    }
      noticesVO.setImageurl(file1);

    System.out.println("-> noticesVO:"+noticesVO.toString());
    
    int cnt = this.noticesProc.create(noticesVO); 
       
        if (cnt == 1) {
            mav.addObject("code", "create_success");
        } else {
            mav.addObject("code", "create_fail");
        }
        mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
        
        mav.addObject("url", "/notices/msg"); // msg.jsp, redirect parameter 적용
        mav.setViewName("redirect:/notices/msg.do"); // Post -> Get - param...        
     
    
    
    return mav; // forward
  }
  
  //목록 출력
  @RequestMapping(value = "/notices/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
  
    ArrayList<NoticesVO> list = noticesProc.list_all();
    
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
  public ModelAndView update_text(int noticesno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notices/update_text"); 
    
    NoticesVO noticesVO = this.noticesProc.read(noticesno);
    
    mav.addObject("noticesVO",noticesVO);
    mav.addObject("noticesno",noticesVO.getNoticesno());
    
    return mav;
  }
  
  //수정 처리
  @RequestMapping(value = "/notices/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
 
    // mav 객체 이용
    this.noticesProc.update_text(noticesVO); // 글수정

    mav.addObject("noticesno", noticesVO.getNoticesno());
    mav.setViewName("redirect:/notices/read.do?"); // 페이지 자동 이동 
        

    return mav; // forward
  }
  
  //삭제폼
  @RequestMapping(value = "/notices/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int noticesno) {
    ModelAndView mav = new ModelAndView();
    
    NoticesVO noticesVO = this.noticesProc.read(noticesno);
    mav.addObject("noticesVO", noticesVO);
      
    mav.setViewName("/notices/delete");

    return mav; // forward
  }
  
  @RequestMapping(value = "/notices/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(NoticesVO noticesVO) {
    ModelAndView mav = new ModelAndView();
    
    this.noticesProc.delete(noticesVO.getNoticesno()); // DBMS 삭제

    mav.setViewName("redirect:/notices/list_all.do"); 
    
    return mav;
  }   
  
}
