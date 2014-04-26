ALTER TABLE zjlx_stock_runtime DROP PRIMARY KEY CASCADE;

DROP TABLE zjlx_stock_runtime CASCADE CONSTRAINTS;

CREATE TABLE zjlx_stock_runtime (
  ID VARCHAR2(64 CHAR)      NOT NULL,
  extractTime DATE  DEFAULT sysdate,
  code VARCHAR2(64 CHAR)  NULL,
  name VARCHAR2(64 CHAR) NULL,
  latestPrice NUMBER(10,4) NULL,
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
  rateOfJrxdjlr NUMBER(10,4) NULL,
  DB_UPDATE_TSP          TIMESTAMP(6)           DEFAULT CURRENT_TIMESTAMP     NOT NULL
  )
  PARTITION BY RANGE (extractTime) (  
  PARTITION PD21000101 VALUES LESS THAN (to_date('21000101','YYYYMMDD')));


  
ALTER TABLE zjlx_stock_runtime ADD (
  CONSTRAINT ZJLX_STOCK_RUNTIME_PK_01  PRIMARY KEY  (ID));


