package com.eaglesoft.stock.event;

import java.util.HashMap;

public class ZjlxStockEodEvent extends ApplicationEvent {
	
	//public final static String URL_LSAG = "http://data.eastmoney.com/zjlx/data.aspx?type=detail&cate=7&day=1&sortType=6&sortRule=-1&pageSize=5000&page=1";
	
	public final static String URL_LSAG = "http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx?type=FF&sty=HFF&st=4&sr=true&p=1&ps=3000&mkt=2&stat=1";
	public ZjlxStockEodEvent() {
		super.setType(EventType.ZJLX_STOCK_EOD);
		super.setUrlOfData(URL_LSAG);
		super.setParameters(new HashMap<String, Object>());
		super.setResponse(new HashMap<String, Object>());
	}
}
