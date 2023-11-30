package dev.mvc.notices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.notices.NoticesProc")
public class NoticesProc implements NoticesProcInter {
  @Autowired  
  private NoticesDAOInter noticesDAO;
  @Override
  public int create(NoticesVO noticesVO) {
    int cnt = this.noticesDAO.create(noticesVO);
    return cnt;
  }

  @Override
  public ArrayList<NoticesVO> list_all() {
    ArrayList<NoticesVO> list = this.noticesDAO.list_all();
    return list;
  }

  @Override
  public NoticesVO read(int noticesno) {
    NoticesVO noticesVo = this.noticesDAO.read(noticesno);
    return noticesVo;
  }

  @Override
  public int update_text(NoticesVO noticesVO) {
    int cnt = this.noticesDAO.update_text(noticesVO);
    return cnt;
  }

  @Override
  public int delete(int noticesno) {
    int cnt = this.noticesDAO.delete(noticesno);
    return cnt;
  }

}
