package dev.mvc.fotorama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FotoramaCont {
 
  
  public FotoramaCont() {
    System.out.println("-> FotoramaCont created.");  
    
  }
  
  /**
   * FotoramaCont test
   * http://localhost:9091/jquery/fotorama/gallery.do
   * @return
   */
  @RequestMapping(value = "/jquery/fotorama/gallery.do", method = RequestMethod.GET)
  public ModelAndView pay_form() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/jquery/fotorama/gallery");  // /WEB-INF/views/jquery/fotorama/gallery.jsp

    return mav;
  }
  

}