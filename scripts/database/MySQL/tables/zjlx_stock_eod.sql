CREATE TABLE zjlx_stock_eod(
  `ID` varchar(64) NOT NULL,
  `businessDate` datetime NULL,
  `code` varchar(64) NULL,
  `name` varchar(64) NULL,
  `openPrice` double(18, 2) NULL,
  `closePrice` double(18, 2) NULL,
  `minPrice` double(18, 2) NULL,
  `maxPrice` double(18, 2) NULL,
  `jrzdf` double(18, 2) NULL,
  `jrzljlr` double(18, 2) NULL,
  `rateOfJrzljlr` double(18, 2) NULL,
  `jrcddjlr` double(18, 2) NULL,
  `rateOfJrcddjlr` double(18, 2) NULL,
  `jrddjlr` double(18, 2) NULL,
  `rateOfJrddjlr` double(18, 2) NULL,
  `jrzdjlr` double(18, 2) NULL,
  `rateOfJrzdjlr` double(18, 2) NULL,
  `jrxdjlr` double(18, 2) NULL,
  `rateOfJrxdjlr` double(18, 2) NULL,
  `volumn` double(18, 2) NULL,
	PRIMARY KEY (`ID`)
) 
;