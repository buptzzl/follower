
	package com.eaglesoft.stock.domainObjects;

import java.math.BigDecimal;



public class ZjlxRecord {
	
	

	private String code; //代码
	private String name; //名称
	private BigDecimal latesPrice;//最新价
	private BigDecimal jrzdf;  //今日涨跌幅
	private BigDecimal jrzljlr; //今日主力净流入净额
	private BigDecimal rateOfJrzljlr;//今日主力净流入净占比
	private BigDecimal jrcddjlr;//今日超大单流入净额
	private BigDecimal rateOfJrcddjlr;//今日超大单流入净占比
	private BigDecimal jrddjlr;//今日大单流入净额
	private BigDecimal rateOfJrddjlr;//今日大单流入净占比
	private BigDecimal jrzdjlr;//今日中单流入净额
	private BigDecimal rateOfJrzdjlr;//今日中单流入净占比
	private BigDecimal  jrxdjlr;//今日中小流入净额
	private BigDecimal  rateOfJrxdjlr;//今日小单流入净占比

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

	public BigDecimal getLatesPrice() {
		return latesPrice;
	}

	public void setLatesPrice(BigDecimal latesPrice) {
		this.latesPrice = latesPrice;
	}

	public BigDecimal getJrzdf() {
		return jrzdf;
	}

	public void setJrzdf(BigDecimal jrzdf) {
		this.jrzdf = jrzdf;
	}

	public BigDecimal getJrzljlr() {
		return jrzljlr;
	}

	public void setJrzljlr(BigDecimal jrzljlr) {
		this.jrzljlr = jrzljlr;
	}

	public BigDecimal getRateOfJrzljlr() {
		return rateOfJrzljlr;
	}

	public void setRateOfJrzljlr(BigDecimal rateOfJrzljlr) {
		this.rateOfJrzljlr = rateOfJrzljlr;
	}

	public BigDecimal getJrcddjlr() {
		return jrcddjlr;
	}

	public void setJrcddjlr(BigDecimal jrcddjlr) {
		this.jrcddjlr = jrcddjlr;
	}

	public BigDecimal getRateOfJrcddjlr() {
		return rateOfJrcddjlr;
	}

	public void setRateOfJrcddjlr(BigDecimal rateOfJrcddjlr) {
		this.rateOfJrcddjlr = rateOfJrcddjlr;
	}

	public BigDecimal getJrddjlr() {
		return jrddjlr;
	}

	public void setJrddjlr(BigDecimal jrddjlr) {
		this.jrddjlr = jrddjlr;
	}

	public BigDecimal getRateOfJrddjlr() {
		return rateOfJrddjlr;
	}

	public void setRateOfJrddjlr(BigDecimal rateOfJrddjlr) {
		this.rateOfJrddjlr = rateOfJrddjlr;
	}

	public BigDecimal getJrzdjlr() {
		return jrzdjlr;
	}

	public void setJrzdjlr(BigDecimal jrzdjlr) {
		this.jrzdjlr = jrzdjlr;
	}

	public BigDecimal getRateOfJrzdjlr() {
		return rateOfJrzdjlr;
	}

	public void setRateOfJrzdjlr(BigDecimal rateOfJrzdjlr) {
		this.rateOfJrzdjlr = rateOfJrzdjlr;
	}

	public BigDecimal getJrxdjlr() {
		return jrxdjlr;
	}

	public void setJrxdjlr(BigDecimal jrxdjlr) {
		this.jrxdjlr = jrxdjlr;
	}

	public BigDecimal getRateOfJrxdjlr() {
		return rateOfJrxdjlr;
	}

	public void setRateOfJrxdjlr(BigDecimal rateOfJrxdjlr) {
		this.rateOfJrxdjlr = rateOfJrxdjlr;
	}
	
	public String toString() {
		return "代码:"+code
		       +" 名称:"+name
		       +" 最新价:"+latesPrice
		       +" 今日涨跌幅:"+jrzdf
		       +" 今日主力净流入净额:"+jrzljlr
		       +" 今日主力净流入净占比:"+rateOfJrzljlr
		       +" 今日超大单流入净额:"+jrcddjlr
		       +" 今日超大单流入净占比:"+rateOfJrcddjlr
		       +" 今日大单流入净额:"+jrddjlr
		       +" 今日大单流入净占比:"+rateOfJrddjlr
		       +" 今日中单流入净额:"+jrzdjlr
		       +" 今日中单流入净占比"+rateOfJrzdjlr
		       +" 今日中小流入净额:"+jrxdjlr
		       +" 今日小单流入净占比:"+rateOfJrxdjlr;	  
	}
}
