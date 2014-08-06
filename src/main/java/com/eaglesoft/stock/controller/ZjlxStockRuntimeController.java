package com.eaglesoft.stock.controller;

import java.util.Date;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.event.ApplicationEvent;
import com.eaglesoft.stock.event.EventStatus;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.stock.service.ZjlxOfStockService;
import com.eaglesoft.utils.JasonUtil;
import com.eaglesoft.utils.http.proxy.HttpProxyUtil;

public class ZjlxStockRuntimeController extends AbstractController {

	private ZjlxOfStockService zjlxOfStockService;

	private ZjlxDataParser parser;

	private boolean isHttpProxyRequired = true;
	
	public ZjlxOfStockService getZjlxOfStockService() {
		return zjlxOfStockService;
	}

	public void setZjlxOfStockService(ZjlxOfStockService zjlxOfStockService) {
		this.zjlxOfStockService = zjlxOfStockService;
	}

	public ZjlxDataParser getParser() {
		return parser;
	}

	public void setParser(ZjlxDataParser parser) {
		this.parser = parser;
	}

	public boolean isHttpProxyRequired() {
		return isHttpProxyRequired;
	}

	public void setHttpProxyRequired(boolean isHttpProxyRequired) {
		this.isHttpProxyRequired = isHttpProxyRequired;
	}


	@Override
	public void process(ApplicationEvent event) throws Exception {
		if (isHttpProxyRequired)
			HttpProxyUtil.setupHttpProxy();

		String jsonData = JasonUtil.retrieveData(event.getUrlOfData());
		Date extractTime = new Date();
		ZjlxAggregator<ZjlxStockRuntime> aggregator = parser.parse(jsonData, extractTime);
		zjlxOfStockService.getDao().save(aggregator.getData());
		event.getResponse().put("extractTime", extractTime);
		event.setStatus(EventStatus.SUCCESS);
		
	}


}
