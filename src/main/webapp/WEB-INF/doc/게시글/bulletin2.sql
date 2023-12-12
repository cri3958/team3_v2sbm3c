DROP TABLE BULLETIN CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 게시글 */
/**********************************/
CREATE TABLE BULLETIN(
		BULLETINIDX                   		INTEGER(10)		 NOT NULL		 PRIMARY KEY,
		BULLETINNAME                  		VARCHAR2(200)		 NOT NULL,
		BULLETINTEXT                  		VARCHAR2(500)		 NULL ,
		FILE1                         		VARCHAR2(100)		 NULL ,
		FILE1SAVED                    		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 DEFAULT 0		 NULL 
);

DROP SEQUENCE BULLETIN_BULLETINIDX_SEQ;

CREATE SEQUENCE BULLETIN_BULLETINIDX_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지

COMMENT ON TABLE BULLETIN is '게시글';
COMMENT ON COLUMN BULLETIN.BULLETINIDX is '게시글번호';
COMMENT ON COLUMN BULLETIN.BULLETINNAME is '제목';
COMMENT ON COLUMN BULLETIN.BULLETINTEXT is '내용';
COMMENT ON COLUMN BULLETIN.FILE1 is '메인이미지';
COMMENT ON COLUMN BULLETIN.FILE1SAVED is '실제메인이미지';
COMMENT ON COLUMN BULLETIN.THUMB1 is '메인이미지프리뷰';
COMMENT ON COLUMN BULLETIN.SIZE1 is '메인이미지크기';