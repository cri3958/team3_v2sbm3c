package dev.mvc.bulletin;

import org.springframework.web.multipart.MultipartFile;

public class BulletinVO {
//    CREATE TABLE BULLETIN(
//            BULLETINIDX                        NUMBER(10)      NOT NULL        PRIMARY KEY,
//            BULLETINNAME                        VARCHAR2(200)        NOT NULL,
//            BULLETINTEXT                        VARCHAR2(500)        NULL ,
//            FILE1                               VARCHAR2(100)        NULL ,
//            FILE1SAVED                          VARCHAR2(100)        NULL ,
//            THUMB1                              VARCHAR2(100)        NULL ,
//            SIZE1                               NUMBER(10)       DEFAULT 0       NULL
//            REPLYCNT                               NUMBER(10)       DEFAULT 0      NOT NULL,
    
//    );
    /**게시글번호*/
    private int bulletinidx;
    /**제목*/
    private String bulletinname = "";
    /**내용*/
    private String bulletintext = "";
    /**
    이미지 파일
    <input type='file' class="form-control" name='file1MF' id='file1MF' 
               value='' placeholder="파일 선택">
    */
    private MultipartFile file1MF;
    /** 메인 이미지 크기 단위, 파일 크기 */
    private String size1_label = "";
    /** 메인 이미지 */
    private String file1 = "";
    /** 실제 저장된 메인 이미지 */
    private String file1saved = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 메인 이미지 크기 */
    private long size1;
    
    private long replycnt;
    
    public long getReplycnt() {
        return replycnt;
    }
    public void setReplycnt(long replycnt) {
        this.replycnt = replycnt;
    }
    /** 패스워드 */
    private String passwd = "";
    
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public MultipartFile getFile1MF() {
        return file1MF;
    }
    public void setFile1MF(MultipartFile file1mf) {
        file1MF = file1mf;
    }
    public String getSize1_label() {
        return size1_label;
    }
    public void setSize1_label(String size1_label) {
        this.size1_label = size1_label;
    }
    public void setSize1(long size1) {
        this.size1 = size1;
    }
    public String getFile1() {
        return file1;
    }
    public void setFile1(String file1) {
        this.file1 = file1;
    }
    public String getFile1saved() {
        return file1saved;
    }
    public void setFile1saved(String file1saved) {
        this.file1saved = file1saved;
    }
    public String getThumb1() {
        return thumb1;
    }
    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }
    public long getSize1() {
        return size1;
    }
    public void setSize1(int size1) {
        this.size1 = size1;
    }
    @Override
    public String toString() {
        return "BulletinVO [bulletinidx=" + bulletinidx + ", bulletinname=" + bulletinname + ", bulletintext="
                + bulletintext + ", file1MF=" + file1MF + ", size1_label=" + size1_label + ", file1=" + file1
                + ", file1saved=" + file1saved + ", thumb1=" + thumb1 + ", size1=" + size1 + ", replycnt=" + replycnt
                + ", passwd=" + passwd + "]";
    }
    public int getBulletinidx() {
        return bulletinidx;
    }
    public void setBulletinidx(int bulletinidx) {
        this.bulletinidx = bulletinidx;
    }
    public String getBulletinname() {
        return bulletinname;
    }
    public void setBulletinname(String bulletinname) {
        this.bulletinname = bulletinname;
    }
    public String getBulletintext() {
        return bulletintext;
    }
    public void setBulletintext(String bulletintext) {
        this.bulletintext = bulletintext;
    }
}
