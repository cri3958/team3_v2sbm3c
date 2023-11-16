package dev.mvc.resort_v1sbm3c;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.cate.CateDAOInter;
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;

@SpringBootTest
class ResortV1sbm3cApplicationTests {
  @Autowired // CateDAOInter interface 구현한 객체를 만들어 자동으로 할당해라.
  private CateDAOInter cateDAO;

  @Autowired // CateProcInter interface 구현한 객체를 만들어 자동으로 할당해라.
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
	@Test
	void contextLoads() {
	  
	}
	
//	@Test
//	public void testCreate() {
//	  CateVO cateVO = new CateVO();
//	  // cateVO.setName("경상남도");
//	  // cateVO.setName("충청북도");
//	  // cateVO.setName("전라북도");
//	  // cateVO.setName("전라남도");
//	  cateVO.setName("경기도");
//	  
////	  int cnt = this.cateDAO.create(cateVO);
////	  System.out.println("-> cnt: " + cnt);
//	  
//	  int cnt = this.cateProc.create(cateVO);
//	  System.out.println("-> cnt: " + cnt);
//	  
//	}
	
//	@Test
//	public void testList_all() {
//	  ArrayList<CateVO> list = this.cateProc.list_all();
//	  for (CateVO cateVO : list) {
//	    System.out.println(cateVO.toString());
//	  }
//	}
	
//	@Test
//	public void testRead() {
//	  CateVO cateVO = this.cateProc.read(2);
//	  System.out.println(cateVO.toString());
//    
//	}

	 @Test
	  public void testUpdate() {
      CateVO cateVO = new CateVO();
      cateVO.setCateno(2);
      cateVO.setName("가평");
	    cateVO.setCnt(5);
	    
	    int cnt = this.cateProc.update(cateVO);
	    System.out.println("-> " + cnt);
	    
	  }
	 
}




