package dev.mvc.favorite;

public class FavoriteVO {
  private int noticesno;
  private int memberno;
  
//페이징 관련
  // -----------------------------------------------------------------------------------
  /** 시작 rownum */
  private int start_num;    
  /** 종료 rownum */
  private int end_num;    
  /** 현재 페이지 */
  private int now_page = 1;
  
  private String word="";

  public int getNoticesno() {
    return noticesno;
  }

  public void setNoticesno(int noticesno) {
    this.noticesno = noticesno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getStart_num() {
    return start_num;
  }

  public void setStart_num(int start_num) {
    this.start_num = start_num;
  }

  public int getEnd_num() {
    return end_num;
  }

  public void setEnd_num(int end_num) {
    this.end_num = end_num;
  }

  public int getNow_page() {
    return now_page;
  }

  public void setNow_page(int now_page) {
    this.now_page = now_page;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  @Override
  public String toString() {
    return "FavoriteVO [noticesno=" + noticesno + ", memberno=" + memberno + ", start_num=" + start_num + ", end_num="
        + end_num + ", now_page=" + now_page + ", word=" + word + "]";
  }

  
  
  
}
