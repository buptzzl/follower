
	package com.eaglesoft.stock.domainObjects;

import java.math.BigDecimal;



public class ZjlxRecord {
	
	

	private String code; //����
	private String name; //����
	private BigDecimal latesPrice;//���¼�
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
		return "����:"+code
		       +" ����:"+name
		       +" ���¼�:"+latesPrice
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
