package dev.mvc.notices;

import java.util.ArrayList;

public interface NoticesDAOInter {

  public int create(NoticesVO noticesVO);
  
  public ArrayList<NoticesVO> list_all();
  
  public NoticesVO read(int noticesno);
  
  public int update_text(NoticesVO noticesVO);
  
  public int delete(int noticesno);
}
