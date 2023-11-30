package dev.mvc.notices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.contents.Contents;
import dev.mvc.contents.ContentsVO;
import dev.mvc.tool.Tool;

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
  
  @RequestMapping(value="/notices/create.do", method = RequestMethod.GET)
  public ModelAndView create(int noticesno) {  
    ModelAndView mav = new ModelAndView();

    NoticesVO noticesVO = this.noticesProc.read(noticesno); 
    mav.addObject("noticesVO", noticesVO);
    
    mav.setViewName("/notices/create"); 
    
    return mav;
  }
  
  @RequestMapping(value = "/notices/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
  
    ArrayList<NoticesVO> list = noticesProc.list_all();
    
    mav.addObject("list", list);
        
    mav.setViewName("/notices/list_all");
  
    return mav;
  }
  
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
