package com.eaglesoft.stock.parser;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;


public class ZjlxDataParser extends DataParser {
	
	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Override
	public Object parse(String str) throws Exception{
        JSONObject obj;
        ZjlxAggregator aggregator = new ZjlxAggregator();
        
		try {
			obj = new JSONObject(str);
			JSONArray stockList = obj.getJSONArray("data");
			aggregator.setTotalPages(obj.getInt("pages"));
			aggregator.setPageSize(obj.getInt("pageSize"));
			aggregator.setCount(obj.getInt("count"));
			aggregator.setExTime(obj.getDouble("exTime"));
			aggregator.setUpdate(dateFormat.parse(obj.getString("update")));
			aggregator.setUpdate(dateFormat2.parse(obj.getString("datatime")));
			
			for (int i = 0; i < stockList.length(); i++){
				ZjlxStockRuntime zjlx= ZjlxDataParser.parseAsRecord(stockList.getString(i));
				aggregator.getData().add(zjlx);
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aggregator;
	   }

	public static boolean isEmpty(String str) {
		
		return str == null || "".equals(str);
	}

	public static boolean isChineseUnit(String str) {
		boolean result = false;
		
		if(!ZjlxDataParser.isEmpty(str)) {
			result = str.indexOf("亿")!=-1||str.indexOf("万")!=-1;
		}
		
		return result;
	}

	public static double convertChineseUnit(String str) {
		
		double result = 0;
		
		if(!ZjlxDataParser.isEmpty(str)) {
			if(str.indexOf("万")!=-1) {
				double valueWithoutUnit = Double.parseDouble(str.substring(0, str.indexOf("万")));
				result = valueWithoutUnit * 10000;
			} else if (str.indexOf("亿")!=-1) {
				double valueWithoutUnit = Double.parseDouble(str.substring(0, str.indexOf("亿")));
				result = valueWithoutUnit * 100000000;
			}else {
				result= Double.parseDouble(str);
			}
		}
		
		return result;
	}

	public static BigDecimal toBigDecimal(String str){
		  if(ZjlxDataParser.isChineseUnit(str)){
			  return  BigDecimal.valueOf(ZjlxDataParser.convertChineseUnit(str));
		  }else {
			  return BigDecimal.valueOf(Double.parseDouble(str)); 
		  }
		 
	   }

	public static ZjlxStockRuntime parseAsRecord(String str){
		   int index =0;
		   ZjlxStockRuntime result = new ZjlxStockRuntime();
		   String[] attributes =  str.split(",");
		   result.setCode(attributes[index]);
		   index = index+3;
		   result.setName(attributes[index++]);
		   result.setLatestPrice(toBigDecimal(attributes[index++]));
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
		   
		   return result;
	   }
	
}
