package dev.mvc.bulletin;

import dev.mvc.member.MemberVO;

public interface BulletinProcInter {
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
     * 수정 처리
     * @param bulletinVO
     * @return
     */
    public int update(BulletinVO bulletinVO);
    
    /**
     * 게시글 삭제 처리
     * @param bulletinidx
     * @return
     */
    public int delete(int bulletinidx);

}
