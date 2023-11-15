DROP TABLE ORGANIZATION CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 보호기관 */
/**********************************/
CREATE TABLE ORGANIZATION(
		ORGIDX                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ORGNM                         		VARCHAR2(50)		 NULL ,
		CAREADDR                      		VARCHAR2(50)		 NULL ,
		OFFICETEL                     		VARCHAR2(20)		 NULL ,
		CARENM                        		VARCHAR2(20)		 NULL 
);

DROP SEQUENCE ORGANIZATION_ORGIDX_SEQ;

CREATE SEQUENCE ORGANIZATION_ORGIDX_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER ORGANIZATION_ORGIDX_TRG
BEFORE INSERT ON ORGANIZATION
FOR EACH ROW
BEGIN
IF :NEW.ORGIDX IS NOT NULL THEN
  SELECT ORGANIZATION_ORGIDX_SEQ.NEXTVAL INTO :NEW.ORGIDX FROM DUAL;
END IF;
END;

COMMENT ON TABLE ORGANIZATION is '보호기관';
COMMENT ON COLUMN ORGANIZATION.ORGIDX is '보호기관 고유값';
COMMENT ON COLUMN ORGANIZATION.ORGNM is '보호기관 관할';
COMMENT ON COLUMN ORGANIZATION.CAREADDR is '보호기관 주소';
COMMENT ON COLUMN ORGANIZATION.OFFICETEL is '보호기관 전화번호';
COMMENT ON COLUMN ORGANIZATION.CARENM is '보호기관 시설명';