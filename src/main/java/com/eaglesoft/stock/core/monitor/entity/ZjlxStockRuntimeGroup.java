package com.eaglesoft.stock.core.monitor.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.util.ReflectionUtils;

import com.eaglesoft.utils.date.DateUtil;


/**
 * @Title: Entity
 * @Description: 
 * @author eagleSoft
 * @date 2014-04-21 13:59:35
 * @version V0.1
 * 
 */

public class ZjlxStockRuntimeGroup{
	private String code; //代码
	private String name; //名称
	private BigDecimal deltaLatestPrice;//最新价
	private BigDecimal deltaJrzdf;  //今日涨跌幅
	private BigDecimal deltaJrzljlr; //今日主力净流入净额
	private BigDecimal deltaRateOfJrzljlr;//今日主力净流入净占比
	private BigDecimal deltaJrcddjlr;//今日超大单流入净额
	private BigDecimal deltaRateOfJrcddjlr;//今日超大单流入净占比
	private BigDecimal deltaJrddjlr;//今日大单流入净额
	private BigDecimal deltaRateOfJrddjlr;//今日大单流入净占比
	private BigDecimal deltaJrzdjlr;//今日中单流入净额
	private BigDecimal deltaRateOfJrzdjlr;//今日中单流入净占比
	private BigDecimal deltaJrxdjlr;//今日中小流入净额
	private BigDecimal deltaRateOfJrxdjlr;//今日小单流入净占比
	private BigDecimal deltaVolumn;//delta volumn
	private BigDecimal deltaJrzdfPerYi;//delta Jrzdf per billion
	
    private List<ZjlxStockRuntime> zjlxStockRuntimes = new ArrayList<ZjlxStockRuntime>();

	
	
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDeltaLatestPrice() {
        return deltaLatestPrice;
    }

    public void setDeltaLatestPrice(BigDecimal deltaLatestPrice) {
        this.deltaLatestPrice = deltaLatestPrice;
    }

    public BigDecimal getDeltaJrzdf() {
        return deltaJrzdf;
    }

    public void setDeltaJrzdf(BigDecimal deltaJrzdf) {
        this.deltaJrzdf = deltaJrzdf;
    }

    public BigDecimal getDeltaJrzljlr() {
        return deltaJrzljlr;
    }

    public void setDeltaJrzljlr(BigDecimal deltaJrzljlr) {
        this.deltaJrzljlr = deltaJrzljlr;
    }

    public BigDecimal getDeltaRateOfJrzljlr() {
        return deltaRateOfJrzljlr;
    }

    public void setDeltaRateOfJrzljlr(BigDecimal deltaRateOfJrzljlr) {
        this.deltaRateOfJrzljlr = deltaRateOfJrzljlr;
    }

    public BigDecimal getDeltaJrcddjlr() {
        return deltaJrcddjlr;
    }

    public void setDeltaJrcddjlr(BigDecimal deltaJrcddjlr) {
        this.deltaJrcddjlr = deltaJrcddjlr;
    }

    public BigDecimal getDeltaRateOfJrcddjlr() {
        return deltaRateOfJrcddjlr;
    }

    public void setDeltaRateOfJrcddjlr(BigDecimal deltaRateOfJrcddjlr) {
        this.deltaRateOfJrcddjlr = deltaRateOfJrcddjlr;
    }

    public BigDecimal getDeltaJrddjlr() {
        return deltaJrddjlr;
    }

    public void setDeltaJrddjlr(BigDecimal deltaJrddjlr) {
        this.deltaJrddjlr = deltaJrddjlr;
    }

    public BigDecimal getDeltaRateOfJrddjlr() {
        return deltaRateOfJrddjlr;
    }

    public void setDeltaRateOfJrddjlr(BigDecimal deltaRateOfJrddjlr) {
        this.deltaRateOfJrddjlr = deltaRateOfJrddjlr;
    }

    public BigDecimal getDeltaJrzdjlr() {
        return deltaJrzdjlr;
    }

    public void setDeltaJrzdjlr(BigDecimal deltaJrzdjlr) {
        this.deltaJrzdjlr = deltaJrzdjlr;
    }

    public BigDecimal getDeltaRateOfJrzdjlr() {
        return deltaRateOfJrzdjlr;
    }

    public void setDeltaRateOfJrzdjlr(BigDecimal deltaRateOfJrzdjlr) {
        this.deltaRateOfJrzdjlr = deltaRateOfJrzdjlr;
    }

    public BigDecimal getDeltaJrxdjlr() {
        return deltaJrxdjlr;
    }

    public void setDeltaJrxdjlr(BigDecimal deltaJrxdjlr) {
        this.deltaJrxdjlr = deltaJrxdjlr;
    }

    public BigDecimal getDeltaRateOfJrxdjlr() {
        return deltaRateOfJrxdjlr;
    }

    public void setDeltaRateOfJrxdjlr(BigDecimal deltaRateOfJrxdjlr) {
        this.deltaRateOfJrxdjlr = deltaRateOfJrxdjlr;
    }


    public List<ZjlxStockRuntime> getZjlxStockRuntimes() {
        return zjlxStockRuntimes;
    }

    public void setZjlxStockRuntimes(List<ZjlxStockRuntime> zjlxStockRuntimes) {
        this.zjlxStockRuntimes = zjlxStockRuntimes;
    }

    public BigDecimal getDeltaVolumn() {
        return deltaVolumn;
    }

    public void setDeltaVolumn(BigDecimal deltaVolumn) {
        this.deltaVolumn = deltaVolumn;
    }

    public BigDecimal getDeltaJrzdfPerYi() {
        return deltaJrzdfPerYi;
    }

    public void setDeltaJrzdfPerYi(BigDecimal deltaJrzdfPerYi) {
        this.deltaJrzdfPerYi = deltaJrzdfPerYi;
    }

    @Override
    public String toString() {
        return toFormat1();
    }
    
    
    public String describle(Method method) {
        //Method method = ReflectionUtils.findMethod(ZjlxStockRuntime.class,"get"+deltaField.substring(0, 1).toUpperCase() + deltaField.substring(1));
        //return new StringBuffer().append(toFormat1()).append(format(zjlxStockRuntimes,method)).toString();
        return new StringBuffer().append(toFormat1()).toString();
    }
    
    
    public String describleWithoutDetails(Method method) {
        //Method method = ReflectionUtils.findMethod(ZjlxStockRuntime.class,"get"+deltaField.substring(0, 1).toUpperCase() + deltaField.substring(1));
        return new StringBuffer().append(toFormat1()).toString();
    }
    
    
    public String toFormat1() {
       return new StringBuffer().append(code + ",")
                          .append(name + ",")
                          .append(deltaLatestPrice + ",")
                          .append(deltaJrzdf + ",")
                          .append(deltaJrzljlr + ",")
                          .append(deltaRateOfJrzljlr + ",")
                          .append(deltaJrcddjlr + ",")
                          .append(deltaRateOfJrcddjlr + ",")
                          .append(deltaJrddjlr + ",")
                          .append(deltaRateOfJrddjlr + ",")
                          .append(deltaJrzdjlr + ",")
                          .append(deltaRateOfJrzdjlr + ",")
                          .append(deltaJrxdjlr + ",")
                          .append(deltaRateOfJrxdjlr  + ",")
                          .append(deltaVolumn   + "," )
                           .append(deltaJrzdfPerYi  )
                          
                          .toString();
    }
    
    public String formatTitle() {
        return new StringBuffer().append("code,")
                .append("name,")
                .append("deltaLatestPrice,")
                .append("deltaJrzdf,")
                .append("deltaJrzljlr,")
                .append("deltaRateOfJrzljlr,")
                .append("deltaJrcddjlr,")
                .append("deltaRateOfJrcddjlr,")
                .append("deltaJrddjlr,")
                .append("deltaRateOfJrddjlr,")
                .append("deltaJrzdjlr,")
                .append("deltaRateOfJrzdjlr,")
                .append("deltaJrxdjlr,")
                .append("deltaRateOfJrxdjlr," )
                .append("volumn," )
                .append("deltaJrzdfPerYi," )
                .append(formatTitle(zjlxStockRuntimes))
                .toString();
     }
    
    public String toFormat2() {
        return "ZjlxStockRuntimeGroup [code=" + code + ", name=" + name + ", deltaLatestPrice=" + deltaLatestPrice + ", deltaJrzdf=" + deltaJrzdf +
                ", deltaJrzljlr=" + deltaJrzljlr + ", deltaRateOfJrzljlr=" + deltaRateOfJrzljlr + ", deltaJrcddjlr=" + deltaJrcddjlr +
                ", deltaRateOfJrcddjlr=" + deltaRateOfJrcddjlr + ", deltaJrddjlr=" + deltaJrddjlr + ", deltaRateOfJrddjlr=" + deltaRateOfJrddjlr +
                ", deltaJrzdjlr=" + deltaJrzdjlr + ", deltaRateOfJrzdjlr=" + deltaRateOfJrzdjlr + ", deltaJrxdjlr=" + deltaJrxdjlr + ", deltaRateOfJrxdjlr=" +
                deltaRateOfJrxdjlr + "]";
    }
    
    
    public String format(List<ZjlxStockRuntime> runtimes, Method method ){

        StringBuffer sb = new StringBuffer();
     
            List<ZjlxStockRuntime> list = runtimes;
            Collections.sort(list, new Comparator<ZjlxStockRuntime>() {
                @Override
                public int compare(ZjlxStockRuntime o1, ZjlxStockRuntime o2) {
                    return o1.getExtractTime().compareTo(o2.getExtractTime());
                }
            } );
           
            BigDecimal value1 = BigDecimal.ZERO;
            BigDecimal value2 = BigDecimal.ZERO;
            for(int i=1;i<runtimes.size();i++){
                
                try {
                     value1 = (BigDecimal) method.invoke(runtimes.get(i));
                     value2 = (BigDecimal) method.invoke(runtimes.get(i-1));
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sb.append("," + value1.subtract(value2).doubleValue()); 
            }
    
        return sb.toString();
    }
    
    public String format(List<ZjlxStockRuntime> runtimes ){

        StringBuffer sb = new StringBuffer();
     
            List<ZjlxStockRuntime> list = runtimes;
            Collections.sort(list, new Comparator<ZjlxStockRuntime>() {
                @Override
                public int compare(ZjlxStockRuntime o1, ZjlxStockRuntime o2) {
                    return o1.getExtractTime().compareTo(o2.getExtractTime());
                }
            } );
           
            for(int i=1;i<runtimes.size();i++){
  
                sb.append("," + runtimes.get(i).getRateOfJrxdjlr().subtract( runtimes.get(i-1).getRateOfJrxdjlr()).doubleValue()); 
            }
    
        return sb.toString();
    }
    

    public String formatTitle(List<ZjlxStockRuntime> runtimes ){

        StringBuffer sb = new StringBuffer();
     
            List<ZjlxStockRuntime> list = runtimes;
            Collections.sort(list, new Comparator<ZjlxStockRuntime>() {
                @Override
                public int compare(ZjlxStockRuntime o1, ZjlxStockRuntime o2) {
                    return o2.getExtractTime().compareTo(o1.getExtractTime());
                }
            } );
           
            for(int i=1;i<runtimes.size();i++){
                String start = DateUtil.DF_HH_MI_SS.format(runtimes.get(i-1).getExtractTime());
                
                String end = DateUtil.DF_HH_MI_SS.format(runtimes.get(i).getExtractTime());
                
                sb.append("," + start +"-"+end); 
            }
    
        return sb.toString();
    }
    

    public static  class BigDecimalFieldComparator implements Comparator<ZjlxStockRuntimeGroup> {
            Method method;
            boolean sortAscending;
        public BigDecimalFieldComparator(String methodName){
            this(methodName, true);
        }
        
        public BigDecimalFieldComparator(String methodName,  boolean sortAscending){
            this.sortAscending = sortAscending;
            method = ReflectionUtils.findMethod(ZjlxStockRuntimeGroup.class,"get"+methodName.substring(0, 1).toUpperCase() + methodName.substring(1));
        }
        
        @Override
        public int compare(ZjlxStockRuntimeGroup o1, ZjlxStockRuntimeGroup o2) {
            try {
              
                BigDecimal valueOfo1 = (BigDecimal) ReflectionUtils.invokeMethod(method, o1);
                BigDecimal valueOfo2 = (BigDecimal) ReflectionUtils.invokeMethod(method, o2);
                if(sortAscending){
                    return valueOfo1.compareTo(valueOfo2);
                } else {
                    return valueOfo2.compareTo(valueOfo1);
                }
                
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return 0;
        }
    }
    
    public static enum Field {
        
        DELTALATESTPRICE("deltaLatestPrice", false),                        
        DELTAJRZDF("deltaJrzdf",false),
        DELTAJRZLJLR("deltaJrzljlr",false),
        DELTARATEOFJRZLJLR("deltaRateOfJrzljlr",false),
        DELTAJRCDDJLR("deltaJrcddjlr",false),
        DELTARATEOFJRCDDJLR("deltaRateOfJrcddjlr",false),
        DELTAJRDDJLR("deltaJrddjlr",false),
        DELTARATEOFJRDDJLR("deltaRateOfJrddjlr",false),
        DELTAJRZDJLR("deltaJrzdjlr",true),
        DELTARATEOFJRZDJLR("deltaRateOfJrzdjlr",true),
        DELTAJRXDJLR("deltaJrxdjlr",true),
        DELTARATEOFJRXDJLR("deltaRateOfJrxdjlr",true),
        DELTAVOLUMN("deltaVolumn",false),
        DELTAJRZDFPERYI("deltaJrzdfPerYi",false);
        
        private final String name;
        
        private final boolean sortAscending;
        
        public boolean isSortAscending() {
            return sortAscending;
        }

        private Field(String fieldName, boolean sortAscending) {
            this.name = fieldName;
            this.sortAscending = sortAscending;
        }
        
        public String getName() {
            return this.name;
        }
    }
}

