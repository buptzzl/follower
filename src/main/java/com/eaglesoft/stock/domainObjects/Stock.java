package com.eaglesoft.stock.domainObjects;

public class Stock {
	private String id;
	private String sedo;
	private String isin;
	private String secure;
	private String name;//
	private String desc;//
	public void setSecure(String secure) {
		this.secure = secure;
	}
	public String getSecure() {
		return secure;
	}
	public void setSedo(String sedo) {
		this.sedo = sedo;
	}
	public String getSedo() {
		return sedo;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getIsin() {
		return isin;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}
