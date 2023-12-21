DROP TABLE FAVORITE CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 즐겨찾기 */
/**********************************/
CREATE TABLE FAVORITE(
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
		NOTICESNO                    		NUMBER(10)		 NOT NULL,
  PRIMARY KEY (MEMBERNO, NOTICESNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (NOTICESNO) REFERENCES NOTICES (NOTICESNO)
);

COMMENT ON TABLE FAVORITE is '즐겨찾기';
COMMENT ON COLUMN FAVORITE.MEMBERNO is '회원번호';
COMMENT ON COLUMN FAVORITE.NOTICESNO is '공고번호';


//즐겨찾기 추가
INSERT INTO FAVORITE(memberno,noticesno) VALUES(7,328240);

//즐겨찾기 삭제
delete from favorite where memberno=7 and noticesno = 328238;

COMMIT;

//전체 조회
SELECT favorite.memberno
FROM NOTICES
LEFT OUTER JOIN favorite ON NOTICES.noticesno = favorite.noticesno
ORDER BY notices.noticesno DESC;

//회원별 조회
SELECT *
FROM NOTICES
LEFT OUTER JOIN favorite ON NOTICES.noticesno = favorite.noticesno
where favorite.memberno = 7
ORDER BY notices.noticesno DESC;

SELECT noticesno, noticenumber, imageurl, receiptdate, species, gender, discoveryplace, characteristic, state, publicnoticestart, publicnoticeend, colorcd, age, weight, orgnm, careaddr, officetel, carenm, memberno, r
   FROM (
              SELECT noticesno, noticenumber, imageurl, receiptdate, species, gender, discoveryplace, characteristic, state, publicnoticestart, publicnoticeend, colorcd, age, weight, orgnm, careaddr, officetel, carenm, memberno, rownum as r
              FROM (
                SELECT NOTICES.noticesno, noticenumber, imageurl, receiptdate, species, gender, discoveryplace, characteristic, state, publicnoticestart, publicnoticeend, colorcd, age, weight, orgnm, careaddr, officetel, carenm, FAVORITE.memberno
                FROM NOTICES
                LEFT OUTER JOIN FAVORITE ON NOTICES.noticesno = FAVORITE.noticesno
                WHERE FAVORITE.memberno = 7
                ORDER BY FAVORITE.noticesno DESC
                )
    )
    WHERE <![CDATA[ r >= #{start_num} AND r <= #{end_num} ]]>

//읽기
SELECT *
FROM NOTICES
LEFT OUTER JOIN favorite ON NOTICES.noticesno = favorite.noticesno
WHERE notices.noticesno = 328238 and favorite.memberno = 7
ORDER BY notices.noticesno DESC;