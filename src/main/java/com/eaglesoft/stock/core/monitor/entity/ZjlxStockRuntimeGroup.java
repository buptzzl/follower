package com.eaglesoft.stock.core.monitor.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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

    public String toString() {
		return "代码:"+code
		       +" 名称:"+name
		       +" Delta最新价:"+deltaLatestPrice
		       +" Delta今日涨跌幅:"+deltaJrzdf
		       +" Delta今日主力净流入净额:"+deltaJrzljlr
		       +" Delta今日主力净流入净占比:"+deltaRateOfJrzljlr
		       +" Delta今日超大单流入净额:"+deltaJrcddjlr
		       +" Delta今日超大单流入净占比:"+deltaRateOfJrcddjlr
		       +" Delta今日大单流入净额:"+deltaJrddjlr
		       +" Delta今日大单流入净占比:"+deltaRateOfJrddjlr
		       +" Delta今日中单流入净额:"+deltaJrzdjlr
		       +" Delta今日中单流入净占比"+deltaRateOfJrzdjlr
		       +" Delta今日中小流入净额:"+deltaJrxdjlr
		       +" Delta今日小单流入净占比:"+deltaRateOfJrxdjlr;	  
	}
}
