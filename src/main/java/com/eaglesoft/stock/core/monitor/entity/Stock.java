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
@Table(name = "stock", schema = "")
@SuppressWarnings("serial")
public class Stock extends IdEntity {
	
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String description;

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

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}
}
