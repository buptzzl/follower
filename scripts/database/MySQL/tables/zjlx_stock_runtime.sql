CREATE TABLE zjlx_stock_runtime(
  `ID` varchar(64) NOT NULL,
  `extractTime` datetime NULL,
  `code` varchar(64) NULL,
  `name` varchar(64) NULL,
  `latestPrice` double(18, 2) NULL,
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