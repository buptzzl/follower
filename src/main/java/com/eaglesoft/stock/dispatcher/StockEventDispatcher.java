package com.eaglesoft.stock.dispatcher;

import com.eaglesoft.stock.controller.ControllerManager;
import com.eaglesoft.stock.event.ApplicationEvent;

public class StockEventDispatcher {
	ControllerManager controllerManager;
	
	public StockEventDispatcher(){
		System.out.print("initializing StockEventDispatcher");
	}
  
	@SuppressWarnings("static-access")
	public void dispatch(ApplicationEvent event) {
    	
    	try {
    		controllerManager.getController(event).process(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	  public ControllerManager getControllerManager() {
			return controllerManager;
		}
		public void setControllerManager(ControllerManager controllerManager) {
			this.controllerManager = controllerManager;
		}
}
