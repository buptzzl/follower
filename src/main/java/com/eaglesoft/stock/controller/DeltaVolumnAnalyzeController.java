package com.eaglesoft.stock.controller;

import java.util.Date;

import com.eaglesoft.stock.analyzer.TopXDeltaAnalyzer;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.event.ApplicationEvent;
import com.eaglesoft.stock.event.EventStatus;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.stock.service.ZjlxOfStockService;
import com.eaglesoft.utils.JasonUtil;
import com.eaglesoft.utils.http.proxy.HttpProxyUtil;

public class DeltaVolumnAnalyzeController extends AbstractController {

	private TopXDeltaAnalyzer topXDeltaAnalyzer;

	private Date getStartTime(ApplicationEvent event) {
	    return (Date) event.getParameters().get("START_TIME");
	}

	private Date getEndTime(ApplicationEvent event) {
        return (Date) event.getParameters().get("END_TIME");
    }

	private String getField(ApplicationEvent event) {
        return (String) event.getParameters().get("FIELD");
    }
	
	@Override
	public void process(ApplicationEvent event) throws Exception {
	    topXDeltaAnalyzer.analyze(getStartTime(event), getEndTime(event), getField(event));
	}


    public TopXDeltaAnalyzer getTopXDeltaAnalyzer() {
        return topXDeltaAnalyzer;
    }


    public void setTopXDeltaAnalyzer(TopXDeltaAnalyzer topXDeltaAnalyzer) {
        this.topXDeltaAnalyzer = topXDeltaAnalyzer;
    }


}
