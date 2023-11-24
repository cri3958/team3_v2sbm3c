package dev.mvc.bulletin;

public class BulletinVO {
//  CREATE TABLE BULLETIN(
//      BULLETINIDX                       INTEGER(10)    NOT NULL    PRIMARY KEY,
//      BULLETINNAME                      VARCHAR2(200)    NOT NULL,
//      BULLETINTEXT                      VARCHAR2(500)    NULL ,
//      BULLETINPICTURE                   VARCHAR2(500)    NULL 
//  );
    /**게시글번호*/
    private int bulletinidx;
    /**제목*/
    private String bulletinname = "";
    /**내용*/
    private String bulletintext = "";
    /**사진주소*/
    private String bulletinpicture = "";
    
    @Override
    public String toString() {
        return "BulletionVO [bulletinidx=" + bulletinidx + ", bulletinname=" + bulletinname + ", bulletintext="
                + bulletintext + ", bulletinpicture=" + bulletinpicture + "]";
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
    public String getBulletinpicture() {
        return bulletinpicture;
    }
    public void setBulletinpicture(String bulletinpicture) {
        this.bulletinpicture = bulletinpicture;
    }
}
