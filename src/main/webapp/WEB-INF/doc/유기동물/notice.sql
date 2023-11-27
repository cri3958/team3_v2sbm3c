DROP TABLE NOTICES;

CREATE TABLE NOTICES(
		NOTICESNO                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NOTICENUMBER                  		VARCHAR2(100)		 NOT NULL,
		IMAGEURL                      		VARCHAR2(100)		 NOT NULL ,
		RECEIPTDATE                   		VARCHAR2(20)		 NOT NULL ,
		SPECIES														VARCHAR2(200)			NOT NULL ,
		GENDER														VARCHAR2(20)			NOT NULL ,
		DISCOVERYPLACE														VARCHAR2(500)			NOT NULL ,
		CHARACTERISTIC														VARCHAR2(500)			NOT NULL ,
		COLORCD														VARCHAR2(100)			NOT NULL ,
        STATE                         		VARCHAR2(100)		 NOT NULL ,
		PUBLICNOTICESTART             		VARCHAR2(20)		 NOT NULL ,
		PUBLICNOTICEEND               		VARCHAR2(20)		 NOT NULL ,
		AGE														VARCHAR2(20)			NOT NULL ,
		WEIGHT														VARCHAR2(20)			NOT NULL ,
		ORGNM														VARCHAR2(300)			NOT NULL ,
		CAREADDR														VARCHAR2(300)			NOT NULL ,
		OFFICETEL														VARCHAR2(100)			NOT NULL ,
		CARENM														VARCHAR2(300)			NOT NULL 
);

DROP SEQUENCE NOTICES_SEQ;

CREATE SEQUENCE NOTICES_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

COMMIT;

COMMENT ON TABLE NOTICE is '유기동물 공고 데이터';
COMMENT ON COLUMN NOTICE.NOTICESNO is '컨텐츠 번호';
COMMENT ON COLUMN NOTICE.NOTICENUMBER is '공고번호';
COMMENT ON COLUMN NOTICE.IMAGEURL is '이미지';
COMMENT ON COLUMN NOTICE.RECEIPTDATE is '공고접수일';
COMMENT ON COLUMN NOTICE.SPECIES is '유기동물 종';
COMMENT ON COLUMN NOTICE.GENDER is '유기동물 성별';
COMMENT ON COLUMN NOTICE.DISCOVERYPLACE is '유기동물 발견장소';
COMMENT ON COLUMN NOTICE.CHARACTERISTIC is '유기동물 특징';
COMMENT ON COLUMN NOTICE.COLORCD is '유기동물 색감';
COMMENT ON COLUMN NOTICE.STATE is '공고 상태';
COMMENT ON COLUMN NOTICE.PUBLICNOTICESTART is '공고 시작일';
COMMENT ON COLUMN NOTICE.PUBLICNOTICEEND is '공고 종료일';
COMMENT ON COLUMN NOTICE.AGE is '유기동물 나이';
COMMENT ON COLUMN NOTICE.WEIGHT is '유기동물 몸무게';
COMMENT ON COLUMN NOTICE.ORGNM is '유기동물 보호기관 관할';
COMMENT ON COLUMN NOTICE.CAREADDR is '유기동물 보호기관 주소';
COMMENT ON COLUMN NOTICE.OFFICETEL is '유기동물 보호기관 전화번호';
COMMENT ON COLUMN NOTICE.CARENM is '유기동물 보호기관 명';


SELECT *
FROM NOTICES;

SELECT noticesno, noticenumber, imageurl, receiptdate, species, gender, discoveryplace, characteristic, state, publicnoticestart, publicnoticeend, colorcd, age, weight, orgnm, careaddr, officetel, carenm
FROM NOTICES
ORDER BY noticesno DESC;

INSERT INTO NOTICES(NOTICESNO, NOTICENUMBER, IMAGEURL, RECEIPTDATE, SPECIES, GENDER, DISCOVERYPLACE, CHARACTERISTIC,
					 STATE, PUBLICNOTICESTART, PUBLICNOTICEEND, COLORCD, AGE, WEIGHT, ORGNM, CAREADDR, OFFICETEL, CARENM)
VALUES (NOTICES_SEQ.nextval, '경기-고양-2023-00972','http://www.animal.go.kr/files/shelter/2023/11/202311261111476.jpg','20231126','[개] 믹스견','M','용두로63 부근','까만목줄, 녹색리드줄',
    '보호중','20231127','20231207','흰색','2023','7.7(Kg)','경기도 고양시','경기도 고양시 덕양구 고양대로 1695 (원흥동, 고양시 농업기술센터) 고양시동물보호센터','$031-8075-4657','$고양시동물보호센터');

COMMIT;