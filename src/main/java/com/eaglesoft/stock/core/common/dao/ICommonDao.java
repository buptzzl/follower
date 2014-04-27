package com.eaglesoft.stock.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


public interface ICommonDao<T, PK extends Serializable> {

	public abstract Session getSession();

	/**
	 * 获取所有数据表
	 * @return
	 */
	public abstract Integer getAllDbTableSize();

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public abstract <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 按属性查找对象列表.
	 */
	public abstract <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 根据传入的实体持久化对象
	 */
	public abstract <T> void save(T entity);

	/**
	 * 批量保存数据
	 * @param <T>
	 * @param entitys 要持久化的临时实体对象集合
	 */
	public abstract <T> void batchSave(List<T> entitys);

	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */

	public abstract <T> void saveOrUpdate(T entity);

	/**
	 * 根据传入的实体删除对象
	 */
	public abstract <T> void delete(T entity);

	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * 删除全部的实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public abstract <T> void deleteAllEntitie(Collection<T> entitys);

	/**
	 * 根据Id获取对象。
	 */
	public abstract <T> T get(Class<T> entityClass, final Serializable id);

	/**
	 * 根据主键获取实体并加锁。
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @param lock
	 * @return
	 */
	public abstract <T> T getEntity(Class entityName, Serializable id);

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void updateEntitie(T pojo);

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void updateEntitie(String className, Object id);

	/**
	 * 根据主键更新实体
	 */
	public abstract <T> void updateEntityById(Class entityName, Serializable id);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param hql
	 * @return
	 */
	public abstract List<T> findByQueryString(final String hql, Object... obj);

	/**
	 * 获取得到HQLQuery对象
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public abstract Query createHQLQuery(String hql, Object... values);

	/**
	 * 获取得到SQLQuery对象
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public abstract SQLQuery createSQLQuery(String sql, Object... values);

	/**
	 * 获取SQLBuilder中query对象，
	 * 
	 * @param sqlBuilder
	 * @return
	 */
	public abstract Query getQuery(SQLBuilder sqlBuilder);

	/**
	 * 获取SQLBuilder中query对象，
	 * 
	 * @param sqlBuilder
	 * @return
	 */
	public abstract SQLQuery getSQLQuery(SQLBuilder sqlBuilder);

	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract <T> T singleResult(String hql, Object... params);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract Map<Object, Object> getHashMapbyQuery(String hql);

	/**
	 * 通过sql更新记录
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract int updateBySqlString(final String query);

	/**
	 * 通过sql查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract List<T> findListbySql(final String sql);

	public abstract <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * 根据属性名和属性值查询. 有排序
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public abstract <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc);

	/**
	 * 根据实体模版查找
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 */

	public abstract List findByExample(final String entityName,
			final Object exampleEntity);

	/**
	 * 调用存储过程
	 */
	public abstract void callableStatementByName(String proc);

	/**
	 * 查询指定实体的总记录数
	 * 
	 * @param clazz
	 * @return
	 */
	public abstract int getCount(Class<T> clazz);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	public abstract Long getCountForJdbc(String sql);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * 
	 */
	public abstract Long getCountForJdbcParam(String sql, Object[] objs);

	public abstract List<Map<String, Object>> findForJdbc(String sql,
			Object... objs);

	public abstract Integer executeSql(String sql, List<Object> param);

	public abstract Integer executeSql(String sql, Object... param);

	public abstract Integer countByJdbc(String sql, Object... param);

	public abstract Map<String, Object> findOneForJdbc(String sql,
			Object... objs);

}