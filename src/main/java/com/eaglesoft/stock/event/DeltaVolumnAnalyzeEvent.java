package com.eaglesoft.stock.event;

import java.util.Date;
import java.util.HashMap;

public class DeltaVolumnAnalyzeEvent extends ApplicationEvent {
	
	public DeltaVolumnAnalyzeEvent() {
		super.setType(EventType.DELTA_VOLUMN_ANALYZE);
		super.setParameters(new HashMap<String, Object>());
		super.setResponse(new HashMap<String, Object>());
	}
	
	
	public DeltaVolumnAnalyzeEvent(Date startTime, Date endTime,String field) {
        super.setType(EventType.DELTA_VOLUMN_ANALYZE);
        super.setParameters(new HashMap<String, Object>());
        super.setResponse(new HashMap<String, Object>());
        
        this.getParameters().put("START_TIME", startTime);
        this.getParameters().put("END_TIME", endTime);
        this.getParameters().put("FIELD", field);
        //this.getParameters().put("FIELD", "DELTAVOLUMN,DELTAJRZDFPERYI");
    }
}
