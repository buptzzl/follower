package com.eaglesoft.stock.task;

import java.util.List;

import com.eaglesoft.stock.App;
import com.eaglesoft.stock.event.ApplicationEvent;

public class Task {
	public void submit(ApplicationEvent event){
		App.getInstance().getDispatcher().dispatch(event);
	}
	
	public void submit(List<ApplicationEvent> events){
	    for(ApplicationEvent event:events) {
	        App.getInstance().getDispatcher().dispatch(event);
	    }
    }
}
