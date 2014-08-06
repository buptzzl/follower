package com.eaglesoft.stock.dispatcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.eaglesoft.stock.controller.ControllerManager;
import com.eaglesoft.stock.event.ApplicationEvent;

public class StockEventDispatcher {
    
    private static final Logger logger = LogManager.getLogger(StockEventDispatcher.class);
    
	ControllerManager controllerManager;
	
	public StockEventDispatcher(){
	    logger.info("initializing StockEventDispatcher");
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
