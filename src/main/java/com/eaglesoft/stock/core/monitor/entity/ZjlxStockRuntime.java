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
	private String code; //����
	private String name; //����
	private BigDecimal latestPrice;//���¼�
	private BigDecimal jrzdf;  //�����ǵ���
	private BigDecimal jrzljlr; //�������������뾻��
	private BigDecimal rateOfJrzljlr;//�������������뾻ռ��
	private BigDecimal jrcddjlr;//���ճ������뾻��
	private BigDecimal rateOfJrcddjlr;//���ճ������뾻ռ��
	private BigDecimal jrddjlr;//���մ����뾻��
	private BigDecimal rateOfJrddjlr;//���մ����뾻ռ��
	private BigDecimal jrzdjlr;//�����е����뾻��
	private BigDecimal rateOfJrzdjlr;//�����е����뾻ռ��
	private BigDecimal  jrxdjlr;//������С���뾻��
	private BigDecimal  rateOfJrxdjlr;//����С�����뾻ռ��


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
		return "����:"+code
		       +" ����:"+name
		       +" ���¼�:"+latestPrice
		       +" �����ǵ���:"+jrzdf
		       +" �������������뾻��:"+jrzljlr
		       +" �������������뾻ռ��:"+rateOfJrzljlr
		       +" ���ճ������뾻��:"+jrcddjlr
		       +" ���ճ������뾻ռ��:"+rateOfJrcddjlr
		       +" ���մ����뾻��:"+jrddjlr
		       +" ���մ����뾻ռ��:"+rateOfJrddjlr
		       +" �����е����뾻��:"+jrzdjlr
		       +" �����е����뾻ռ��"+rateOfJrzdjlr
		       +" ������С���뾻��:"+jrxdjlr
		       +" ����С�����뾻ռ��:"+rateOfJrxdjlr;	  
	}
	
	
}
