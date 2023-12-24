package dev.mvc.reply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
  @Autowired
  private ReplyDAOInter replyDAO; 
  
  @Override
  public int create(ReplyVO replyVO) {
    int count = replyDAO.create(replyVO);
    return count;
  }

  @Override
  public List<ReplyVO> list() {
    List<ReplyVO> list = replyDAO.list();
    return list;
  }

  @Override
  public List<ReplyVO> list_by_bulletinidx(int bulletinidx) {
    List<ReplyVO> list = replyDAO.list_by_bulletinidx(bulletinidx);
    String content = "";
    
    // 특수 문자 변경
    for (ReplyVO replyVO:list) {
      content = replyVO.getReplytext();
      content = Tool.convertChar(content);
      replyVO.setReplytext(content);
    }
    return list;
  }

  @Override
  public List<ReplyMemberVO> list_by_bulletinidx_join(int bulletinidx) {
    List<ReplyMemberVO> list = replyDAO.list_by_bulletinidx_join(bulletinidx);
    String content = "";
    
    // 특수 문자 변경
    for (ReplyMemberVO replyMemberVO:list) {
      content = replyMemberVO.getReplytext();
      content = Tool.convertChar(content);
      replyMemberVO.setReplytext(content);
    }
    return list;
  }

  @Override
  public int checkPasswd(Map<String, Object> map) {
    int count = replyDAO.checkPasswd(map);
    return count;
  }

  @Override
  public int delete(int replyno) {
    int count = replyDAO.delete(replyno);
    return count;
  }
  
//  @Override
//  public List<ReplyMemberVO> list_by_bulletinidx_join_add(int bulletinidx) {
//      List<ReplyMemberVO> list = replyDAO.list_by_bulletinidx_join_add(bulletinidx);
//      String replytext = "";
//      
//      // 특수 문자 변경
//      for (ReplyMemberVO replyMemberVO:list) {
//        replytext = replyMemberVO.getReplytext();
//        replytext = Tool.convertChar(replytext);
//        replyMemberVO.setReplytext(replytext);
//      }
//      return list;
//  }
   
}