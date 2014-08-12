package com.eaglesoft.stock.parser;

import java.math.BigDecimal;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.eaglesoft.stock.core.monitor.entity.StockCategory;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockEod;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;

public class StockCategoryParser extends ZjlxDataParser {

	private String type;
	@Override
	public <T> ZjlxAggregator<T> parse(String str, Date now) throws Exception{
        JSONObject obj;
        ZjlxAggregator<T> aggregator = new ZjlxAggregator<T>();
        
		try {
			obj = new JSONObject(str);
			JSONArray stockCategoryList = obj.getJSONArray("data");
			//aggregator.setTotalPages(obj.getInt("pages"));
			//aggregator.setPageSize(obj.getInt("pageSize"));
			//aggregator.setCount(obj.getInt("count"));
			//aggregator.setExTime(obj.getDouble("exTime"));
			//aggregator.setUpdate(dateFormat.parse(obj.getString("update")));
			//aggregator.setUpdate(dateFormat2.parse(obj.getString("datatime")));
			
			for (int i = 0; i < stockCategoryList.length(); i++){
				StockCategory item= parseItem(stockCategoryList.getString(i),this.type);
				
				aggregator.getData().add((T)item);
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aggregator;
	   }
	
	

	public  <T>  T parseItem(String str,String type){
		   int index =0;
		   StockCategory item = new StockCategory();
		   String[] attributes =  str.split(",");
		   
		   item.setCode(attributes[index++]);
		   item.setName(attributes[index++]);
		   item.setType(type);
		   return (T) item;
	   }



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}

}
