package com.eaglesoft.stock.parser;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.utils.CharUtil;


public class ZjlxDataParser extends DataParser {
    
    private static final Logger logger = LogManager.getLogger(ZjlxDataParser.class);
	
	protected SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
	
	
	public <T> ZjlxAggregator<T> parse(String str) throws Exception{
		return parse(str, new Date());
	}
	
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
				ZjlxStockRuntime zjlx= parseAsRecord(stockList.getString(i));
				zjlx.setExtractTime(now);
				aggregator.getData().add((T)zjlx);
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aggregator;
	   }

	public static boolean isEmpty(String str) {
		
		return str == null || "".equals(str);
	}

	public static boolean isYiIncluded(String str) {
		return CharUtil.isCharIncluded(str,"[\\u4ebf]");
	}
	
	public static boolean isWanIncluded(String str) {
		return CharUtil.isCharIncluded(str,"[\\u4e07]");
	}
	
	public static BigDecimal convertChineseUnit(String str) {
		
		BigDecimal result = BigDecimal.ZERO;
		
		if(!ZjlxDataParser.isEmpty(str)) {
			if(isWanIncluded(str)) {
				
				double valueWithoutUnit = Double.parseDouble(removeChinese(str));
				result = BigDecimal.valueOf(valueWithoutUnit).movePointRight(4);
				
			} else if (isYiIncluded(str)) {
				double valueWithoutUnit =Double.parseDouble(removeChinese(str));
				result = BigDecimal.valueOf(valueWithoutUnit).movePointRight(8);
			}else {
				try{
					result= new BigDecimal( BigDecimal.valueOf(Double.parseDouble(str)).toPlainString()) ;
				} catch (Exception exp)
				{
				    logger.error("string:"+str);
					return BigDecimal.ZERO;
				}
			}
		}
		
		return result;
	}
	
	public static boolean isWanYiIncluded(String str) {
		return isYiIncluded(str)||isWanIncluded(str);
	}

	 public static String removeChinese(String strName) {
			char[] ch = strName.toCharArray();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				if ("-0123456789.".indexOf(c)!=-1) {
					sb.append(c);
				}
			}
			return sb.toString();
	}
	
	public static BigDecimal toBigDecimal(String str){
		
			return ZjlxDataParser.convertChineseUnit(str) ;
		
	   }

	@SuppressWarnings("unchecked")
	public  ZjlxStockRuntime parseAsRecord(String str){
		   int index =0;
		   ZjlxStockRuntime result = new ZjlxStockRuntime();
		   String[] attributes =  str.split(",");
		   result.setCode(attributes[index]);
		   index = index+1;
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
