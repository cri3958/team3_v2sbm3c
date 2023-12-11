package dev.mvc.bulletin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.contents.ContentsVO;
import dev.mvc.member.MemberVO;

@Component("dev.mvc.bulletin.BulletinProc")
public class BulletinProc implements BulletinProcInter{
    @Autowired
    private BulletinDAOInter bulletinDAO;

    @Override
    public int create(BulletinVO bulletinVO) {
        int cnt = this.bulletinDAO.create(bulletinVO);
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
}
