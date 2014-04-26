package com.eaglesoft.stock.core.monitor.entity;

import java.math.BigDecimal;

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
@Entity
@Table(name = "zjlx_stock_runtime", schema = "")
@SuppressWarnings("serial")
public class ZjlxStockRuntime extends IdEntity{
	private java.util.Date extractTime;
	private String code; //代码
	private String name; //名称
	private BigDecimal latestPrice;//最新价
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


	@Column(name = "extractTime", nullable = true, precision = 23, scale = 3, length = 8)
	public java.util.Date getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(java.util.Date extractTime) {
		this.extractTime = extractTime;
	}

	@Column(name = "code", nullable = true, precision = 64, scale = 0, length = 64)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = true, precision = 64, scale = 0, length = 64)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}


	@Column(name ="jrzdf",nullable=true,precision=10,scale=4)
	public BigDecimal getJrzdf() {
		return jrzdf;
	}

	public void setJrzdf(BigDecimal jrzdf) {
		this.jrzdf = jrzdf;
	}

	@Column(name ="latestPrice",nullable=true,precision=10,scale=4)
	public BigDecimal getLatestPrice() {
		return latestPrice;
	}
	
	public void setLatestPrice(BigDecimal latestPrice) {
		this.latestPrice = latestPrice;
	}

	

	@Column(name ="jrzljlr",nullable=true)
	public BigDecimal getJrzljlr() {
		return jrzljlr;
	}

	public void setJrzljlr(BigDecimal jrzljlr) {
		this.jrzljlr = jrzljlr;
	}

	@Column(name ="rateOfJrzljlr",nullable=true,precision=10,scale=4)
	public BigDecimal getRateOfJrzljlr() {
		return rateOfJrzljlr;
	}

	public void setRateOfJrzljlr(BigDecimal rateOfJrzljlr) {
		this.rateOfJrzljlr = rateOfJrzljlr;
	}

	@Column(name ="jrcddjlr",nullable=true,precision=19,scale=4)
	public BigDecimal getJrcddjlr() {
		return jrcddjlr;
	}

	public void setJrcddjlr(BigDecimal jrcddjlr) {
		this.jrcddjlr = jrcddjlr;
	}

	@Column(name ="rateOfJrcddjlr",nullable=true,precision=10,scale=4)
	public BigDecimal getRateOfJrcddjlr() {
		return rateOfJrcddjlr;
	}

	public void setRateOfJrcddjlr(BigDecimal rateOfJrcddjlr) {
		this.rateOfJrcddjlr = rateOfJrcddjlr;
	}

	@Column(name ="jrddjlr",nullable=true,precision=19,scale=4)
	public BigDecimal getJrddjlr() {
		return jrddjlr;
	}

	public void setJrddjlr(BigDecimal jrddjlr) {
		this.jrddjlr = jrddjlr;
	}

	@Column(name ="rateOfJrddjlr",nullable=true,precision=10,scale=4)
	public BigDecimal getRateOfJrddjlr() {
		return rateOfJrddjlr;
	}

	public void setRateOfJrddjlr(BigDecimal rateOfJrddjlr) {
		this.rateOfJrddjlr = rateOfJrddjlr;
	}

	@Column(name ="jrzdjlr",nullable=true,precision=19,scale=4)
	public BigDecimal getJrzdjlr() {
		return jrzdjlr;
	}

	public void setJrzdjlr(BigDecimal jrzdjlr) {
		this.jrzdjlr = jrzdjlr;
	}

	@Column(name ="rateOfJrzdjlr",nullable=true,precision=10,scale=4)
	public BigDecimal getRateOfJrzdjlr() {
		return rateOfJrzdjlr;
	}

	public void setRateOfJrzdjlr(BigDecimal rateOfJrzdjlr) {
		this.rateOfJrzdjlr = rateOfJrzdjlr;
	}

	@Column(name ="jrxdjlr",nullable=true,precision=19,scale=4)
	public BigDecimal getJrxdjlr() {
		return jrxdjlr;
	}

	public void setJrxdjlr(BigDecimal jrxdjlr) {
		this.jrxdjlr = jrxdjlr;
	}

	@Column(name ="rateOfJrxdjlr",nullable=true,precision=10,scale=4)
	public BigDecimal getRateOfJrxdjlr() {
		return rateOfJrxdjlr;
	}

	public void setRateOfJrxdjlr(BigDecimal rateOfJrxdjlr) {
		this.rateOfJrxdjlr = rateOfJrxdjlr;
	}

	public String toString() {
		return "代码:"+code
		       +" 名称:"+name
		       +" 最新价:"+latestPrice
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
