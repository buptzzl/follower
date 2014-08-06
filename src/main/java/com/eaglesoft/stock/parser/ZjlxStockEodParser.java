package com.eaglesoft.stock.parser;

import java.math.BigDecimal;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockEod;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;

public class ZjlxStockEodParser extends ZjlxDataParser {

	@Override
	public <T> ZjlxAggregator<T> parse(String str, Date now) throws Exception{
        JSONObject obj;
        ZjlxAggregator<T> aggregator = new ZjlxAggregator<T>();
        
		try {
			obj = new JSONObject(str);
			JSONArray stockList = obj.getJSONArray("data");
			//aggregator.setTotalPages(obj.getInt("pages"));
			//aggregator.setPageSize(obj.getInt("pageSize"));
			//aggregator.setCount(obj.getInt("count"));
			//aggregator.setExTime(obj.getDouble("exTime"));
			//aggregator.setUpdate(dateFormat.parse(obj.getString("update")));
			//aggregator.setUpdate(dateFormat2.parse(obj.getString("datatime")));
			
			for (int i = 0; i < stockList.length(); i++){
				ZjlxStockEod zjlx= parseAsZjlxStockEod(stockList.getString(i));
				zjlx.setBusinessDate(now);
				aggregator.getData().add((T)zjlx);
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aggregator;
	   }
	
	

	public  ZjlxStockEod parseAsZjlxStockEod(String str){
		   int index =0;
		   ZjlxStockEod result = new ZjlxStockEod();
		   String[] attributes =  str.split(",");
		   result.setCode(attributes[index]);
		   index = index+1;
		   result.setName(attributes[index++]);
		   result.setClosePrice(toBigDecimal(attributes[index++]));
		   result.setJrzdf(toBigDecimal(attributes[index++]));
		   result.setJrzljlr(toBigDecimal(attributes[index++]));
		   result.setRateOfJrzljlr(toBigDecimal(attributes[index++]));
		   result.setJrcddjlr(toBigDecimal(attributes[index++]));
		   result.setRateOfJrcddjlr(toBigDecimal(attributes[index++]));
		   result.setJrddjlr(toBigDecimal(attributes[index++]));
		   result.setRateOfJrddjlr(toBigDecimal(attributes[index++]));
		   result.setJrzdjlr(toBigDecimal(attributes[index++]));
		   result.setRateOfJrzdjlr(toBigDecimal(attributes[index++]));
		   result.setJrxdjlr(toBigDecimal(attributes[index++]));
		   result.setRateOfJrxdjlr(toBigDecimal(attributes[index++]));
		   if(BigDecimal.ZERO.compareTo(result.getRateOfJrxdjlr())!=0)
           {
               result.setVolumn(result.getJrxdjlr().movePointRight(2).divide(result.getRateOfJrxdjlr(),0, BigDecimal.ROUND_HALF_EVEN).abs());
           } 
		   else if(BigDecimal.ZERO.compareTo(result.getRateOfJrzdjlr())!=0) {
		       result.setVolumn(result.getJrzdjlr().movePointRight(2).divide(result.getRateOfJrzdjlr(),0, BigDecimal.ROUND_HALF_EVEN).abs());
		   }
		   else {
               result.setVolumn(BigDecimal.ZERO);
           }
		   return result;
	   }

}
