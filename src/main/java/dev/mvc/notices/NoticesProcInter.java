package dev.mvc.notices;

import java.util.ArrayList;

public interface NoticesProcInter {
  
  public int create(NoticesVO noticesVO);
  
  public ArrayList<NoticesVO> list_all();
  
  public NoticesVO read(int noticesno);
  
  public int update(NoticesVO noticesVO);
  
  public int delete(int noticesno);
}
