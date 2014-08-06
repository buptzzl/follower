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
@Table(name = "zjlx_stock_eod", schema = "")
@SuppressWarnings("serial")
public class ZjlxStockEod extends IdEntity {
	private java.util.Date businessDate;
	private java.lang.String code;
	private java.lang.String name;
	private BigDecimal openPrice;
	private BigDecimal closePrice;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private BigDecimal jrzdf;
	private BigDecimal jrzljlr;
	private BigDecimal rateOfJrzljlr;
	private BigDecimal jrcddjlr;
	private BigDecimal rateOfJrcddjlr;
	private BigDecimal jrddjlr;
	private BigDecimal rateOfJrddjlr;
	private BigDecimal jrzdjlr;
	private BigDecimal rateOfJrzdjlr;
	private BigDecimal jrxdjlr;
	private BigDecimal rateOfJrxdjlr;
	private BigDecimal volumn;

	@Column(name = "businessDate", nullable = true)
	public java.util.Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(java.util.Date businessDate) {
		this.businessDate = businessDate;
	}

	@Column(name = "code", nullable = true)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = true)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	@Column(name ="openPrice",nullable=true)
	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	@Column(name ="closePrice",nullable=true)
	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	@Column(name ="minPrice",nullable=true)
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name ="maxPrice",nullable=true)
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Column(name ="jrzdf",nullable=true)
	public BigDecimal getJrzdf() {
		return jrzdf;
	}

	public void setJrzdf(BigDecimal jrzdf) {
		this.jrzdf = jrzdf;
	}

	@Column(name ="jrzljlr",nullable=true)
	public BigDecimal getJrzljlr() {
		return jrzljlr;
	}

	public void setJrzljlr(BigDecimal jrzljlr) {
		this.jrzljlr = jrzljlr;
	}

	@Column(name ="rateOfJrzljlr",nullable=true)
	public BigDecimal getRateOfJrzljlr() {
		return rateOfJrzljlr;
	}

	public void setRateOfJrzljlr(BigDecimal rateOfJrzljlr) {
		this.rateOfJrzljlr = rateOfJrzljlr;
	}

	@Column(name ="jrcddjlr",nullable=true)
	public BigDecimal getJrcddjlr() {
		return jrcddjlr;
	}

	public void setJrcddjlr(BigDecimal jrcddjlr) {
		this.jrcddjlr = jrcddjlr;
	}

	@Column(name ="rateOfJrcddjlr",nullable=true)
	public BigDecimal getRateOfJrcddjlr() {
		return rateOfJrcddjlr;
	}

	public void setRateOfJrcddjlr(BigDecimal rateOfJrcddjlr) {
		this.rateOfJrcddjlr = rateOfJrcddjlr;
	}

	@Column(name ="jrddjlr",nullable=true)
	public BigDecimal getJrddjlr() {
		return jrddjlr;
	}

	public void setJrddjlr(BigDecimal jrddjlr) {
		this.jrddjlr = jrddjlr;
	}

	@Column(name ="rateOfJrddjlr",nullable=true)
	public BigDecimal getRateOfJrddjlr() {
		return rateOfJrddjlr;
	}

	public void setRateOfJrddjlr(BigDecimal rateOfJrddjlr) {
		this.rateOfJrddjlr = rateOfJrddjlr;
	}

	@Column(name ="jrzdjlr",nullable=true)
	public BigDecimal getJrzdjlr() {
		return jrzdjlr;
	}

	public void setJrzdjlr(BigDecimal jrzdjlr) {
		this.jrzdjlr = jrzdjlr;
	}

	@Column(name ="rateOfJrzdjlr",nullable=true)
	public BigDecimal getRateOfJrzdjlr() {
		return rateOfJrzdjlr;
	}

	public void setRateOfJrzdjlr(BigDecimal rateOfJrzdjlr) {
		this.rateOfJrzdjlr = rateOfJrzdjlr;
	}

	@Column(name ="jrxdjlr",nullable=true)
	public BigDecimal getJrxdjlr() {
		return jrxdjlr;
	}

	public void setJrxdjlr(BigDecimal jrxdjlr) {
		this.jrxdjlr = jrxdjlr;
	}

	@Column(name ="rateOfJrxdjlr",nullable=true)
	public BigDecimal getRateOfJrxdjlr() {
		return rateOfJrxdjlr;
	}

	public void setRateOfJrxdjlr(BigDecimal rateOfJrxdjlr) {
		this.rateOfJrxdjlr = rateOfJrxdjlr;
	}

	@Column(name ="volumn",nullable=true)
    public BigDecimal getVolumn() {
        return volumn;
    }

    public void setVolumn(BigDecimal volumn) {
        this.volumn = volumn;
    }

}
