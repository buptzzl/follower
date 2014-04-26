package com.eaglesoft.stock.service;

import com.eaglesoft.stock.core.common.dao.impl.ZjlxOfStockDao;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class ZjlxOfStockService {
	
	private ZjlxOfStockDao dao;

	public ZjlxOfStockDao getDao() {
		return dao;
	}

	public void setDao(ZjlxOfStockDao dao) {
		this.dao = dao;
	}

	
	
	public ZjlxAggregator parse(String str2) throws Exception {
		ZjlxDataParser parser = new ZjlxDataParser();
		ZjlxAggregator aggregator = (ZjlxAggregator) parser.parse(str2);
		return aggregator;
	}
/*
	public void handleEvent(ZjlxStockRuntimeEvent event)
			throws  Exception {
		String jsonData = retrieveData(event.getUrlOfData());
	
		parseAndSave(jsonData);
	}

	public void handleEvent(ZjlxStockEodEvent event)
			throws  Exception {
		String jsonData = retrieveData(event.getUrlOfData());
	
		parseAndSave(jsonData);
	}
*/
}
