package dev.mvc.bulletin;

import java.util.ArrayList;

import dev.mvc.contents.ContentsVO;
import dev.mvc.member.MemberVO;

public interface BulletinDAOInter {
    /**
     * 글 작성
     * @param memberVO
     * @return 추가한 레코드 갯수
     */
    public int create(BulletinVO bulletinVO);
    
    /**
     * bulletinidx로 게시글 정보 조회
     * @param bulletinidx
     * @return
     */
    public BulletinVO read(int bulletinidx);
    
    /**
     * 글 정보 수정
     * @param bulletinVO
     * @return 처리된 레코드 갯수
     */
    public int update_text(BulletinVO bulletinVO);

    /**
     * 파일 정보 수정
     * @param bulletinVO
     * @return 처리된 레코드 갯수
     */
    public int update_file(BulletinVO bulletinVO);
    
    /**
     * 게시글 삭제 처리
     * @param bulletinidx
     * @return
     */
    public int delete(int bulletinidx);
    
    /**
     * 모든 등록된 게시글목록
     * @return
     */
    public ArrayList<BulletinVO> list_all();

}
