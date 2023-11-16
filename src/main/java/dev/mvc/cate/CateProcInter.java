package dev.mvc.cate;

import java.util.ArrayList;

public interface CateProcInter {
  /**
   * 등록, 추상 메소드 -> Spring Boot이 구현함.
   * @param cateVO 객체
   * @return 등록된 레코드 갯수
   */
  public int create(CateVO cateVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<CateVO> list_all();
  
  /**
   * 조회
   * @param cateno
   * @return
   */
  public CateVO read(int cateno);
  
  /**
   * 수정   
   * @param cateVO
   * @return 수정된 레코드 갯수
   */
  public int update(CateVO cateVO);

  /**
   * 삭제
   * @param cateno 삭제할 레코드 PK 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int cateno);

  /**
   * 우선 순위 높임, 10 등 -> 1 등   
   * @param cateno
   * @return 수정된 레코드 갯수
   */
  public int update_seqno_forward(int cateno);

  /**
   * 우선 순위 낮춤, 1 등 -> 10 등   
   * @param cateno
   * @return 수정된 레코드 갯수
   */
  public int update_seqno_backward(int cateno);
  
  /**
   * 카테고리 공개 설정
   * @param cateno
   * @return
   */
  public int update_visible_y(int cateno);
  
  /**
   * 카테고리 비공개 설정
   * @param cateno
   * @return
   */
  public int update_visible_n(int cateno);
  
  /**
   * 비회원/회원 SELECT LIST
   * @return
   */
  public ArrayList<CateVO> list_all_y();
  
}



