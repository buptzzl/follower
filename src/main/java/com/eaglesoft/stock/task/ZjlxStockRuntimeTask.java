package com.eaglesoft.stock.task;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.eaglesoft.stock.event.ApplicationEvent;
import com.eaglesoft.stock.event.DeltaVolumnAnalyzeEvent;
import com.eaglesoft.stock.event.EventStatus;
import com.eaglesoft.stock.event.ZjlxStockEodEvent;
import com.eaglesoft.stock.event.ZjlxStockRuntimeEvent;
import com.eaglesoft.utils.date.DateUtil;

public class ZjlxStockRuntimeTask extends Task {
    
    private static final Logger logger = LogManager.getLogger(ZjlxStockRuntimeTask.class);
    
	public void extractZjlxStock() {
	    logger.info("processing  ZjlxStockRuntimeEvent at " +DateUtil.DF_YYYY_MM_DD_HH_MI_SS.format(new Date()));
		ApplicationEvent event = new ZjlxStockRuntimeEvent();
		submit(event);
		
		if (EventStatus.SUCCESS == event.getStatus() ) {
		    Date endtime = (Date) event.getResponse().get("extractTime");
		    Date startTime = DateUtil.minusMinutes(endtime, 15);
		    DeltaVolumnAnalyzeEvent deltaVolumnAnalyzeEvent = new DeltaVolumnAnalyzeEvent(startTime,endtime, "DELTAVOLUMN,DELTAJRZDFPERYI");
		    submit(deltaVolumnAnalyzeEvent);
		}
	}
	
	public void extractEodZjlxStock() {
        logger.info("processing  ZjlxStockEodEvent at " +DateUtil.DF_YYYY_MM_DD_HH_MI_SS.format(new Date()));
        
        ApplicationEvent event = new ZjlxStockEodEvent();
        submit(event);
        
        logger.info("processing  ZjlxStockEodEvent is done at " +DateUtil.DF_YYYY_MM_DD_HH_MI_SS.format(new Date()));
    }
}
