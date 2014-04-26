package com.eaglesoft.stock.event;

import java.util.HashMap;

public class ZjlxStockRuntimeEvent extends ApplicationEvent {
	
	public final static String URL_LSAG = "http://data.eastmoney.com/zjlx/data.aspx?type=detail&cate=7&day=1&sortType=6&sortRule=-1&pageSize=5000&page=1";
	
	public ZjlxStockRuntimeEvent() {
		super.setType(EventType.ZJLX_STOCK_RUNTIME);
		super.setUrlOfData(URL_LSAG);
		super.setParameters(new HashMap<String, Object>());
	}
}
