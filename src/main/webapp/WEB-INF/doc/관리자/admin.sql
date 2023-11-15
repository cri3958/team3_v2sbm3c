DROP TABLE ADMIN CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE ADMIN(
		ADMINNO                       		NUMBER(5)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(20)		 NOT NULL,
		PASSWD                        		VARCHAR2(15)		 NOT NULL,
		MNAME                         		VARCHAR2(20)		 NOT NULL,
		MDATE                         		DATE		 NOT NULL,
		GRADE                         		NUMBER(2)		 NOT NULL,
  CONSTRAINT SYS_C007060 UNIQUE (ID)
);

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN.ID is '아이디';
COMMENT ON COLUMN ADMIN.PASSWD is '패스워드';
COMMENT ON COLUMN ADMIN.MNAME is '성명';
COMMENT ON COLUMN ADMIN.MDATE is '가입일';
COMMENT ON COLUMN ADMIN.GRADE is '등급';