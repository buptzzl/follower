package com.eaglesoft.stock.task;

import com.eaglesoft.stock.App;
import com.eaglesoft.stock.event.ApplicationEvent;

public class Task {
	public void submit(ApplicationEvent event){
		App.getInstance().getDispatcher().dispatch(event);
	}
}
