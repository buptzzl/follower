package com.eaglesoft.stock.controller;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockEod;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.event.ApplicationEvent;
import com.eaglesoft.stock.parser.ZjlxStockEodParser;
import com.eaglesoft.stock.service.ZjlxOfStockService;
import com.eaglesoft.utils.JasonUtil;
import com.eaglesoft.utils.http.proxy.HttpProxyUtil;

public class ZjlxStockEodController extends AbstractController {

	private ZjlxStockEodParser parser;
	public ZjlxOfStockService zjlxOfStockService;

	private boolean isHttpProxyRequired = true;

	public ZjlxOfStockService getZjlxOfStockService() {
		return zjlxOfStockService;
	}

	public void setZjlxOfStockService(ZjlxOfStockService zjlxOfStockService) {
		this.zjlxOfStockService = zjlxOfStockService;
	}


	public ZjlxStockEodParser getParser() {
		return parser;
	}

	public void setParser(ZjlxStockEodParser parser) {
		this.parser = parser;
	}


	@Override
	public void process(ApplicationEvent event) throws Exception {
		if (isHttpProxyRequired)
			HttpProxyUtil.setupHttpProxy();

		String jsonData = JasonUtil.retrieveData(event.getUrlOfData());
		ZjlxAggregator<ZjlxStockEod> aggregator =  parser.parse(jsonData);
		zjlxOfStockService.getDao().save( aggregator.getData());
		
	}
}
