package dev.mvc.resort_v1sbm3c;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
import dev.mvc.cate.CateProcInter;
import dev.mvc.contents.ContentsProcInter;
import dev.mvc.member.MemberProc;

@SpringBootTest
public class MemberTest {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private AdminProcInter adminProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당

  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")  // @Component("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc") // @Component("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Component("dev.mvc.member.MemberProc")
  private MemberProc memberProc;
  
  @Test
  public void testCheckID() {
    System.out.println("-> cnt: " + this.memberProc.checkID("user1@gmail.com"));
    System.out.println("-> cnt: " + this.memberProc.checkID("user5@gmail.com"));  
  }
}





