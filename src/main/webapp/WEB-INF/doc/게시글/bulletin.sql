DROP TABLE BULLETIN CASCADE CONSTRAINTS;


/**********************************/
/* Table Name: 게시글 */
/**********************************/
CREATE TABLE BULLETIN(
        BULLETINIDX                   		INTEGER		 NOT NULL		 PRIMARY KEY,
		BULLETINNAME                  		VARCHAR2(200)		 NOT NULL,
		BULLETINTEXT                  		VARCHAR2(500)		 NULL ,
		BULLETINPICTURE               		VARCHAR2(500)		 NULL
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
COMMENT ON COLUMN BULLETIN.BULLETINPICTURE is '사진주소';

INSERT INTO bulletin(bulletinidx, bulletinname, bulletintext, bulletinpicture)
VALUES (BULLETIN_BULLETINIDX_SEQ.nextval, 'test', 'test', 'test');

SELECT bulletinidx, bulletinname, bulletintext, bulletinpicture
FROM bulletin
WHERE bulletinidx = 1;

UPDATE bulletin
SET bulletinname='test2', bulletintext='test2', bulletinpicture='test2'
WHERE bulletinidx=1;

DELETE FROM bulletin
WHERE bulletinidx=1;