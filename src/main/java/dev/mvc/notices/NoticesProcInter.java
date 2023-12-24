package dev.mvc.notices;

import java.util.ArrayList;
import java.util.HashMap;

public interface NoticesProcInter {
  
  public int create(NoticesVO noticesVO);
  
  public ArrayList<NoticesVO> list_all();
  
  public NoticesVO read(int noticesno);
  
  public int update_text(NoticesVO noticesVO);
  
  public int delete(int noticesno);
  
  public ArrayList<NoticesVO> list_by_search(HashMap<String, Object> hashMap);

  public int search_count(HashMap<String, Object> hashMap);

  public ArrayList<NoticesVO> list_by_search_paging(NoticesVO noticesVO);
  
  public String pagingBox(int now_page, String list_file, int search_count);
  
  public int favorite(HashMap<String,Object>hashMap);
}
