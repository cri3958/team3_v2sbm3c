package dev.mvc.reply;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import dev.mvc.notices.NoticesVO;
import dev.mvc.reply.ReplyVO;

public interface ReplyDAOInter {
    public int create(ReplyVO replyVO);
    
    public List<ReplyVO> list();
    
    public List<ReplyVO> list_by_bulletinidx(int bulletinidx);
    
    public List<ReplyMemberVO> list_by_bulletinidx_join(int bulletinidx);
    
    public int checkPasswd(Map<String, Object> map);

    public int delete(int replyno);    
    
    //public List<ReplyMemberVO> list_by_bulletinidx_join_add(int bulletinidx);
}
