package com.eaglesoft.stock.controller;

import com.eaglesoft.stock.event.ApplicationEvent;

public interface Controller {
	public void process(ApplicationEvent event) throws Exception;
}
