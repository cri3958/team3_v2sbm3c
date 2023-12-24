package dev.mvc.bulletin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.contents.ContentsVO;
import dev.mvc.member.MemberVO;
import dev.mvc.reply.ReplyDAOInter;
import dev.mvc.reply.ReplyMemberVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.bulletin.BulletinProc")
public class BulletinProc implements BulletinProcInter{
    @Autowired
    private BulletinDAOInter bulletinDAO;
    
    @Autowired
    private ReplyDAOInter replyDAO;

    @Override
    public int create(BulletinVO bulletinVO) {
      System.out.println("-> Proc bulletin : "+bulletinVO);
        int cnt = this.bulletinDAO.create(bulletinVO);
        System.out.println("-> Proc check" );
        return cnt;
    }

    @Override
    public BulletinVO read(int bulletinidx) {
        BulletinVO bulletinVO = this.bulletinDAO.read(bulletinidx);
        return bulletinVO;
    }

    @Override
    public int delete(int bulletinidx) {
        int cnt = this.bulletinDAO.delete(bulletinidx);
        return cnt;
    }
    
    @Override
    public int password_check(HashMap<String, Object> hashMap) {
      int cnt = this.bulletinDAO.password_check(hashMap);
      return cnt;
    }

    @Override
    public int update_text(BulletinVO bulletinVO) {
        int cnt = this.bulletinDAO.update_text(bulletinVO);
        return cnt;
    }

    @Override
    public int update_file(BulletinVO bulletinVO) {
        int cnt = this.bulletinDAO.update_file(bulletinVO);
        return cnt;
    }

    @Override
    public ArrayList<BulletinVO> list_all() {
      ArrayList<BulletinVO> list = this.bulletinDAO.list_all();
      return list;
    }

    @Override
    public int increaseReplycnt(int bulletinidx) {
        int count = bulletinDAO.increaseReplycnt(bulletinidx);
        return count;
    }

    @Override
    public int decreaseReplycnt(int bulletinidx) {
        int count = bulletinDAO.decreaseReplycnt(bulletinidx);
        return count;
    }


}
