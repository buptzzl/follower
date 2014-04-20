package com.eaglesoft.stock.domainObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;

public class ZjlxAggregator {
	private List<ZjlxStockRuntime> data = new ArrayList<ZjlxStockRuntime>();
   
	private int totalPages;
    private int pageSize;
    private int count;
    private double exTime;
    private Date update;
    private Date datatime;
    
    public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getExTime() {
		return exTime;
	}

	public void setExTime(double exTime) {
		this.exTime = exTime;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Date getDatatime() {
		return datatime;
	}

	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}

	
	public void setData(List<ZjlxStockRuntime> data) {
		this.data = data;
	}
	
	public List<ZjlxStockRuntime> getData() {
		return data;
	}
  
	public void sort(){
		
	}
}
