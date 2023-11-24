package dev.mvc.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public int update(BulletinVO bulletinVO) {
        int cnt = this.bulletinDAO.update(bulletinVO);
        return cnt;
    }

    @Override
    public int delete(int bulletinidx) {
        int cnt = this.bulletinDAO.delete(bulletinidx);
        return cnt;
    }
}
