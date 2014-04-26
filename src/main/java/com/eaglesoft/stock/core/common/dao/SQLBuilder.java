package com.eaglesoft.stock.core.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

/**
 * 一个构造条件查询的Builder模式的可变条件查询构造器. 使用场景:当查询条件中的条件个数是动态变化的时候,可使用本Builder 适用于1 HQL可变条件查询;2 SQL可变条件查询. 注意两者之间的接口变化
 */
public class SQLBuilder {

	private StringBuilder sqlBuilder;

	private List<String> params;
	private List<Object> values;
	private List<Type> types;

	private List<String> paramsList;
	private List<Collection<Object>> valuesList;
	private List<Type> typesList;

	private List<String> paramsArray;
	private List<Object[]> valuesArray;
	private List<Type> typesArray;

	private int firstResult = 0;
	private int maxResults = 0;

	public static final String ROW_COUNT = "SELECT COUNT(*) ";
	public static final String FROM = "FROM";
	public static final String DISTINCT = "DISTINCT";
	public static final String HQL_FETCH = "FETCH";
	public static final String ORDER_BY = "ORDER";
	public static final String AND = "AND";
	public static final String WHERE = "WHERE";
	public static final String SELECT = "SELECT";

	public SQLBuilder() {
		sqlBuilder = new StringBuilder();
	}

	public SQLBuilder(String hql) {
		sqlBuilder = new StringBuilder(hql);
	}

	public SQLBuilder append(String hql) {
		sqlBuilder.append(hql);
		return this;
	}

	/**
	 * 获得原始hql语句
	 * 
	 * @return
	 */
	public String getHql() {
		String hql = sqlBuilder.toString().trim();
		if (hql.length() > 5 && hql.substring(hql.length() - 5).equalsIgnoreCase(WHERE)) {
			return hql.substring(0, hql.length() - 5);
		}
		// 检查where后面是否立即跟有AND, 如果有, 去掉
		int whereIndex = hql.indexOf(WHERE);
		if (whereIndex == -1) {
			return hql;
		}
		String leftPart = hql.substring(0, whereIndex + 5);
		String rightPart = hql.substring(whereIndex + 5);
		if (rightPart.trim().startsWith(AND)) {
			rightPart = rightPart.trim().substring(3);
			return leftPart + " " + rightPart;
		} else if (rightPart.trim().startsWith(ORDER_BY)) {
			// 如果WHERE后紧跟着ORDER BY 则删除WHERE
			return leftPart.substring(0, whereIndex) + rightPart;
		}
		return hql;
	}

	/**
	 * 获得原始Sql语句
	 * 
	 * @return
	 */
	public String getSql() {
		return getHql();
	}

	/**
	 * 获得查询数据库记录数的hql语句。
	 * 
	 * @return
	 */
	public String getRowCountHql() {
		String hql = sqlBuilder.toString();
		String projectionHql = hql;
		int fromIndex = hql.indexOf(FROM);
		if (fromIndex == -1) {
			fromIndex = hql.indexOf("from");
		}
		if (fromIndex != -1) {
			projectionHql = hql.substring(0, fromIndex);
			hql = hql.substring(fromIndex);
		}
		String rowCountHql = hql.replace(HQL_FETCH, "");
		int index = rowCountHql.indexOf(ORDER_BY);
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		return wrapProjection(projectionHql) + rowCountHql;
	}

	/**
	 * 获得查询数据库记录数的sql语句。
	 * 
	 * @return
	 */
	public String getRowCountSql() {
		String sql = sqlBuilder.toString();
		int fromIndex = sql.lastIndexOf(FROM);
		if (fromIndex == -1) {
			fromIndex = sql.lastIndexOf("from");
		}
		return ROW_COUNT + sql.substring(fromIndex, sql.length());
	}

	/**
	 * 修改人：dumc 时间：20130710 获得查询数据库记录数的sql语句改进版。
	 * 暂时没完善
	 * @return
	 */
	public String getRowCountSql2(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			String tableName = table.name();
			return ROW_COUNT + tableName;
		} else {
			return "SELECT 1 ";
		}
	}

	@Deprecated
	public static String getRowCountSQL(String hql) {
		int index = hql.indexOf("FROM");
		if (index != -1) {
			return "SELECT COUNT(*) " + hql.substring(index);
		}
		return hql;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	public SQLBuilder setParam(String param, Object value) {
		return setParam(param, value, null);
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param param
	 * @param value
	 * @param type
	 * @return
	 */
	public SQLBuilder setParam(String param, Object value, Type type) {
		getParams().add(param);
		getValues().add(value);
		getTypes().add(type);
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param paramMap
	 * @return
	 */
	public SQLBuilder setParams(Map<String, Object> paramMap) {
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			setParam(entry.getKey(), entry.getValue());
		}
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SQLBuilder setParamList(String name, Collection vals, Type type) {
		getParamsList().add(name);
		getValuesList().add(vals);
		getTypesList().add(type);
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SQLBuilder setParamList(String name, Collection vals) {
		return setParamList(name, vals, null);
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @param type
	 * @return
	 */
	public SQLBuilder setParamList(String name, Object[] vals, Type type) {
		getParamsArray().add(name);
		getValuesArray().add(vals);
		getTypesArray().add(type);
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @return
	 */
	public SQLBuilder setParamList(String name, Object[] vals) {
		return setParamList(name, vals, null);
	}

	/**
	 * 将sqlBuilder中的参数设置到query中。
	 * 
	 * @param query
	 */
	public Query setParamsToQuery(Query query) {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (types.get(i) == null) {
					query.setParameter(params.get(i), values.get(i));
				} else {
					query.setParameter(params.get(i), values.get(i), types.get(i));
				}
			}
		}
		if (paramsList != null) {
			for (int i = 0; i < paramsList.size(); i++) {
				if (typesList.get(i) == null) {
					query.setParameterList(paramsList.get(i), valuesList.get(i));
				} else {
					query.setParameterList(paramsList.get(i), valuesList.get(i), typesList.get(i));
				}
			}
		}
		if (paramsArray != null) {
			for (int i = 0; i < paramsArray.size(); i++) {
				if (typesArray.get(i) == null) {
					query.setParameterList(paramsArray.get(i), valuesArray.get(i));
				} else {
					query.setParameterList(paramsArray.get(i), valuesArray.get(i), typesArray.get(i));
				}
			}
		}
		return query;
	}

	public Query createQuery(Session s) {
		return setParamsToQuery(s.createQuery(getHql()));
	}

	private String wrapProjection(String projection) {
		if (projection.indexOf(SELECT) == -1) {
			return ROW_COUNT;
		} else {
			return projection.replace(SELECT, "SELECT COUNT(") + ") ";
		}
	}

	private List<String> getParams() {
		if (params == null) {
			params = new ArrayList<String>();
		}
		return params;
	}

	private List<Object> getValues() {
		if (values == null) {
			values = new ArrayList<Object>();
		}
		return values;
	}

	private List<Type> getTypes() {
		if (types == null) {
			types = new ArrayList<Type>();
		}
		return types;
	}

	private List<String> getParamsList() {
		if (paramsList == null) {
			paramsList = new ArrayList<String>();
		}
		return paramsList;
	}

	private List<Collection<Object>> getValuesList() {
		if (valuesList == null) {
			valuesList = new ArrayList<Collection<Object>>();
		}
		return valuesList;
	}

	private List<Type> getTypesList() {
		if (typesList == null) {
			typesList = new ArrayList<Type>();
		}
		return typesList;
	}

	private List<String> getParamsArray() {
		if (paramsArray == null) {
			paramsArray = new ArrayList<String>();
		}
		return paramsArray;
	}

	private List<Object[]> getValuesArray() {
		if (valuesArray == null) {
			valuesArray = new ArrayList<Object[]>();
		}
		return valuesArray;
	}

	private List<Type> getTypesArray() {
		if (typesArray == null) {
			typesArray = new ArrayList<Type>();
		}
		return typesArray;
	}

}