package dev.mvc.favorite;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.notices.NoticesVO;

public interface FavoriteDAOInter {
  public ArrayList<NoticesVO> list_by_memberno(FavoriteVO favoriteVO);
  
  public int like(FavoriteVO favoriteVO);
  
  public int unlike(FavoriteVO favoriteVO);
  
  public int search_count(HashMap<String, Object> hashMap);
}
