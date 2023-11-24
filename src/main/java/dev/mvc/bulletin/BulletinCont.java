package dev.mvc.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import dev.mvc.admin.AdminProcInter;

@Controller
public class BulletinCont {
    @Autowired
    @Qualifier("dev.mvc.bulletin.BulletinProc") // @Component("dev.mvc.admin.AdminProc")
    private BulletinProcInter bulletinProc;
    
    public BulletinCont(){
        System.out.println("-> BulletinCont created.");
   }
}
