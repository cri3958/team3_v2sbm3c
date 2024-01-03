package dev.mvc.reply;

public class ReplyMemberVO {
//    REPLYNO                             NUMBER(10)       NOT NULL        PRIMARY KEY,
//    MEMBERNO                            NUMBER(10)       NULL ,
//    BULLETINNUM                         NUMBER(10)       NULL ,
//    REPLYTEXT                           VARCHAR2(500)    NOT NULL,
//    PASSWD                              VARCHAR2(20)     NOT NULL,
//    RDATE                               DATE             NOT NULL,
    private int replyno;
    private int memberno;
    private int bulletinidx;
    private String replytext;
    private String passwd;
    private String rdate;
    private String mname;
    
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getRdate() {
        return rdate;
    }
    public void setRdate(String rdate) {
        this.rdate = rdate;
    }
    public int getReplyno() {
        return replyno;
    }
    public void setReplyno(int replyno) {
        this.replyno = replyno;
    }
    public int getMemberno() {
        return memberno;
    }
    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }
    public int getBulletinidx() {
        return bulletinidx;
    }
    public void setBulletinidx(int bulletinidx) {
        this.bulletinidx = bulletinidx;
    }
    public String getReplytext() {
        return replytext;
    }
    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }
    public String getMname() {
      return mname;
    }
    public void setMname(String mname) {
      this.mname = mname;
    }
    @Override
    public String toString() {
      return "ReplyMemberVO [replyno=" + replyno + ", memberno=" + memberno + ", bulletinidx=" + bulletinidx
          + ", replytext=" + replytext + ", passwd=" + passwd + ", rdate=" + rdate + ", mname=" + mname + "]";
    }
    
    
    
}
