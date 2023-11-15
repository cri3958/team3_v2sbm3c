DROP TABLE FAVORITE CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 즐겨찾기 */
/**********************************/
CREATE TABLE FAVORITE(
		MEMBERNO                      		INTEGER(10)		 NOT NULL,
		CONTENTSNO                    		NUMBER(10)		 NOT NULL,
  PRIMARY KEY (MEMBERNO, CONTENTSNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (CONTENTSNO) REFERENCES CONTENTS (CONTENTSNO)
);

COMMENT ON TABLE FAVORITE is '즐겨찾기';
COMMENT ON COLUMN FAVORITE.MEMBERNO is '회원번호';
COMMENT ON COLUMN FAVORITE.CONTENTSNO is '컨텐츠 번호';