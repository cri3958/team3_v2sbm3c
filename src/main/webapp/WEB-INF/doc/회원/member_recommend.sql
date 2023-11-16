DROP TABLE MEMBER_RECOMMEND CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원별 추천 유기동물 종 */
/**********************************/
CREATE TABLE MEMBER_RECOMMEND(
		MEMBERNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		IMAGE                         		VARCHAR2(50)		 NULL ,
		RESULT                        		VARCHAR2(20)		 NULL ,
		RDATE                         		DATE		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE MEMBER_RECOMMEND is '회원별 추천 유기동물 종';
COMMENT ON COLUMN MEMBER_RECOMMEND.MEMBERNO is '회원번호';
COMMENT ON COLUMN MEMBER_RECOMMEND.IMAGE is '입력 이미지';
COMMENT ON COLUMN MEMBER_RECOMMEND.RESULT is '추천 결과';
COMMENT ON COLUMN MEMBER_RECOMMEND.RDATE is '추천일';