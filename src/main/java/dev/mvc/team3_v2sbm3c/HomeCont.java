package dev.mvc.team3_v2sbm3c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.notices.NoticesProcInter;
import dev.mvc.notices.NoticesVO;

@Controller
public class HomeCont {
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Component("dev.mvc.admin.AdminProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.notices.NoticesProc") 
  private NoticesProcInter noticesProc;
  
  
  public HomeCont() {
    System.out.println("-> HomeCont created.");
  }
  
  // http://localhost:9091
  @RequestMapping(value= {"", "/", "/index.do", "/index.resort"}, method=RequestMethod.GET)
  public ModelAndView home(HttpSession session) {
    System.out.println("-> home() ver 2.0 ");
    
    ModelAndView mav = new ModelAndView();
    ArrayList<NoticesVO> list = new ArrayList<NoticesVO>();

    if (memberProc.isMember(session)) {
      int memberno = this.memberProc.getMemberno(session);
      System.out.println("-> FotoramaCont memberno: "+memberno);
      int index = this.noticesProc.getrecommendpetidx(memberno);
      String label="";
      if (index == 0) {
        label = "치와와";
      }else if(index==1){
        label = "코커스패니엘";
      }else if(index==2){
        label = "푸들";
      }else if(index==3){
        label = "퍼그";
      }else if(index==4){
        label = "페르시안";
      }else if(index==5){
        label = "포메라니안";
      }else if(index==6){
        label = "프렌치불독";
      }
      if(index<7) {
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("label", label);
        list.addAll(this.noticesProc.list_by_species(hashMap));
      }
    }
    list.addAll(this.noticesProc.end_recent());
    
    List<NoticesVO> list1 = list.subList(0, 10);
    
    list =  new ArrayList<>(list1);
    
    System.out.println("-> Fotorama list: "+list);
    
    mav.addObject("list", list);
    mav.setViewName("/index");
        
    return mav;
  }
  
  // http://localhost:9091/menu/top.do
  @RequestMapping(value= {"/menu/top.do"}, method=RequestMethod.GET)
  public ModelAndView top() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/menu/top"); // /WEB-INF/views/menu/top.jsp
    
    return mav;
  }
}




