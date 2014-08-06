package com.eaglesoft.stock.controller;

import java.util.Map;

import com.eaglesoft.stock.event.ApplicationEvent;
import com.eaglesoft.stock.event.EventType;

public class ControllerManager {

	private Map<EventType, Controller> controllerMapper;

	public Map<EventType, Controller> getControllerMapper() {
		return controllerMapper;
	}

	public void setControllerMapper(Map<EventType, Controller> controllerMapper) {
		this.controllerMapper = controllerMapper;
	}

	public Controller getController(ApplicationEvent event) {

		return controllerMapper.get(event.getType());

	}
}
