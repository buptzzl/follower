package com.eaglesoft.stock.core.common.dao;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface ICommonDao extends IGenericBaseCommonDao{
	
	public String getSystemConfig(String key);
	
}

