DROP TABLE REPLY CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 댓글 */
/**********************************/
CREATE TABLE REPLY(
		REPLYNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		BULLETINIDX                   		NUMBER(10)		 NULL ,
		REPLYTEXT                     		VARCHAR2(500)	 NOT NULL,
        PASSWD                              VARCHAR2(15)        NOT NULL,
        RDATE                               DATE            NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (BULLETINIDX) REFERENCES BULLETIN (BULLETINIDX)
);

DROP SEQUENCE REPLY_REPLYNO_SEQ;

CREATE SEQUENCE REPLY_REPLYNO_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글 번호';
COMMENT ON COLUMN REPLY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN REPLY.BULLETINIDX is '커뮤니티 번호';
COMMENT ON COLUMN REPLY.REPLYTEXT is '댓글내용';
COMMENT ON COLUMN REPLY.PASSWD is '비밀번호';
COMMENT ON COLUMN REPLY.RDATE is '날짜';