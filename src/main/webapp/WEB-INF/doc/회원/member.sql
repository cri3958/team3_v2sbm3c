DROP TABLE MEMBER CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(20)		 NULL ,
		PW                            		VARCHAR2(15)		 NULL ,
		MNAME                         		VARCHAR2(20)		 NULL ,
		MAREA                         		VARCHAR2(20)		 NULL 
);

COMMENT ON TABLE MEMBER is '회원';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원 번호';
COMMENT ON COLUMN MEMBER.ID is '아이디';
COMMENT ON COLUMN MEMBER.PW is '패스워드';
COMMENT ON COLUMN MEMBER.MNAME is '회원명';
COMMENT ON COLUMN MEMBER.MAREA is '거주지역';