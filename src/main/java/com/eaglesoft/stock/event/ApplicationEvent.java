package com.eaglesoft.stock.event;

import java.util.Map;

public class ApplicationEvent {
	private EventType type;
	private String urlOfData;
	private Map<String, Object> parameters;

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getUrlOfData() {
		return urlOfData;
	}

	public void setUrlOfData(String urlOfData) {
		this.urlOfData = urlOfData;
	}

}
