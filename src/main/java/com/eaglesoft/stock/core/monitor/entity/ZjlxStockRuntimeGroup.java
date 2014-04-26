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
	private String code; //����
	private String name; //����
	private BigDecimal deltaLatestPrice;//���¼�
	private BigDecimal deltaJrzdf;  //�����ǵ���
	private BigDecimal deltaJrzljlr; //�������������뾻��
	private BigDecimal deltaRateOfJrzljlr;//�������������뾻ռ��
	private BigDecimal deltaJrcddjlr;//���ճ������뾻��
	private BigDecimal deltaRateOfJrcddjlr;//���ճ������뾻ռ��
	private BigDecimal deltaJrddjlr;//���մ����뾻��
	private BigDecimal deltaRateOfJrddjlr;//���մ����뾻ռ��
	private BigDecimal deltaJrzdjlr;//�����е����뾻��
	private BigDecimal deltaRateOfJrzdjlr;//�����е����뾻ռ��
	private BigDecimal deltaJrxdjlr;//������С���뾻��
	private BigDecimal deltaRateOfJrxdjlr;//����С�����뾻ռ��
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
		return "����:"+code
		       +" ����:"+name
		       +" Delta���¼�:"+deltaLatestPrice
		       +" Delta�����ǵ���:"+deltaJrzdf
		       +" Delta�������������뾻��:"+deltaJrzljlr
		       +" Delta�������������뾻ռ��:"+deltaRateOfJrzljlr
		       +" Delta���ճ������뾻��:"+deltaJrcddjlr
		       +" Delta���ճ������뾻ռ��:"+deltaRateOfJrcddjlr
		       +" Delta���մ����뾻��:"+deltaJrddjlr
		       +" Delta���մ����뾻ռ��:"+deltaRateOfJrddjlr
		       +" Delta�����е����뾻��:"+deltaJrzdjlr
		       +" Delta�����е����뾻ռ��"+deltaRateOfJrzdjlr
		       +" Delta������С���뾻��:"+deltaJrxdjlr
		       +" Delta����С�����뾻ռ��:"+deltaRateOfJrxdjlr;	  
	}
}
