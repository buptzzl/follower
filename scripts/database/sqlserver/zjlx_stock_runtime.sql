CREATE TABLE [dbo].[zjlx_stock_runtime](
  [ID] [varchar](64) NOT NULL,
  [extractTime] [datetime] NULL,
  [code] [varchar](64) NULL,
  [name] [varchar](64) NULL,
  [latestPrice] [decimal](18, 2) NULL,
  [jrzdf] [decimal](18, 2) NULL,
  [jrzljlr] [decimal](18, 2) NULL,
  [rateOfJrzljlr] [decimal](18, 2) NULL,
  [jrcddjlr] [decimal](18, 2) NULL,
  [rateOfJrcddjlr] [decimal](18, 2) NULL,
  [jrddjlr] [decimal](18, 2) NULL,
  [rateOfJrddjlr] [decimal](18, 2) NULL,
  [jrzdjlr] [decimal](18, 2) NULL,
  [rateOfJrzdjlr] [decimal](18, 2) NULL,
  [jrxdjlr] [decimal](18, 2) NULL,
  [rateOfJrxdjlr] [decimal](18, 2) NULL,
  [volumn] [decimal](18, 2) NULL,
 CONSTRAINT [PK_ZJLX_STOCK_RUNTIME__3213E83F2704CA5F] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO