ALTER TABLE zjlx_stock_eod DROP PRIMARY KEY CASCADE;

DROP TABLE zjlx_stock_eod CASCADE CONSTRAINTS;

CREATE TABLE zjlx_stock_eod(
  ID VARCHAR2(64 CHAR)  NOT NULL,
  businessDate DATE NOT NULL,
  code VARCHAR2(64 CHAR)  NULL,
  name VARCHAR2(64 CHAR)  NULL,
  openPrice NUMBER(10,4) NULL,
  closePrice NUMBER(10,4) NULL,
  minPrice NUMBER(10,4) NULL,
  maxPrice NUMBER(10,4) NULL,
  jrzdf NUMBER(10,4) NULL,
  jrzljlr NUMBER(19,4) NULL,
  rateOfJrzljlr NUMBER(10,4) NULL,
  jrcddjlr NUMBER(19,4) NULL,
  rateOfJrcddjlr NUMBER(10,4) NULL,
  jrddjlr NUMBER(19,4) NULL,
  rateOfJrddjlr NUMBER(10,4) NULL,
  jrzdjlr NUMBER(19,4) NULL,
  rateOfJrzdjlr NUMBER(10,4) NULL,
  jrxdjlr NUMBER(19,4) NULL,
  rateOfJrxdjlr NUMBER(10,4) NULL
)PARTITION BY RANGE (businessDate) (  
  PARTITION PD21000101 VALUES LESS THAN (to_date('21000101','YYYYMMDD')));

ALTER TABLE zjlx_stock_eod ADD (
  CONSTRAINT ZJLX_STOCK_EOD_PK_01  PRIMARY KEY  (ID));
