package dev.mvc.resort_v1sbm3c;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;

@SpringBootTest
public class AdminTest {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private AdminProcInter adminProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당
  
  @Test
  public void testRead() {
    AdminVO adminVO = this.adminProc.read(1);
    System.out.println(adminVO.toString());
  }
}
