package com.eaglesoft.stock.event;

import java.util.Map;

public class ApplicationEvent {
	private EventType type;
	private String urlOfData;
	private EventStatus status;
	
    private Map<String, Object> parameters;
    private Map<String, Object> response;
	


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

	public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

	
	public String getUrlOfData() {
		return urlOfData;
	}

	public void setUrlOfData(String urlOfData) {
		this.urlOfData = urlOfData;
	}
	
	public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

}
