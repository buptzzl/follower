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
	 * ��ȡ�������ݱ�
	 * @return
	 */
	public abstract Integer getAllDbTableSize();

	/**
	 * ����ʵ�����ֻ�ȡΨһ��¼
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public abstract <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * �����Բ��Ҷ����б�.
	 */
	public abstract <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * ���ݴ����ʵ��־û�����
	 */
	public abstract <T> void save(T entity);

	/**
	 * ������������
	 * @param <T>
	 * @param entitys Ҫ�־û�����ʱʵ����󼯺�
	 */
	public abstract <T> void batchSave(List<T> entitys);

	/**
	 * ���ݴ����ʵ����ӻ���¶���
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */

	public abstract <T> void saveOrUpdate(T entity);

	/**
	 * ���ݴ����ʵ��ɾ������
	 */
	public abstract <T> void delete(T entity);

	/**
	 * ��������ɾ��ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * ɾ��ȫ����ʵ��
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public abstract <T> void deleteAllEntitie(Collection<T> entitys);

	/**
	 * ����Id��ȡ����
	 */
	public abstract <T> T get(Class<T> entityClass, final Serializable id);

	/**
	 * ����������ȡʵ�岢������
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @param lock
	 * @return
	 */
	public abstract <T> T getEntity(Class entityName, Serializable id);

	/**
	 * ����ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void updateEntitie(T pojo);

	/**
	 * ����ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void updateEntitie(String className, Object id);

	/**
	 * ������������ʵ��
	 */
	public abstract <T> void updateEntityById(Class entityName, Serializable id);

	/**
	 * ͨ��hql ��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param hql
	 * @return
	 */
	public abstract List<T> findByQueryString(final String hql, Object... obj);

	/**
	 * ��ȡ�õ�HQLQuery����
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public abstract Query createHQLQuery(String hql, Object... values);

	/**
	 * ��ȡ�õ�SQLQuery����
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public abstract SQLQuery createSQLQuery(String sql, Object... values);

	/**
	 * ��ȡSQLBuilder��query����
	 * 
	 * @param sqlBuilder
	 * @return
	 */
	public abstract Query getQuery(SQLBuilder sqlBuilder);

	/**
	 * ��ȡSQLBuilder��query����
	 * 
	 * @param sqlBuilder
	 * @return
	 */
	public abstract SQLQuery getSQLQuery(SQLBuilder sqlBuilder);

	/**
	 * ͨ��hql��ѯΨһ����
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract <T> T singleResult(String hql, Object... params);

	/**
	 * ͨ��hql ��ѯ������HashMap����
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract Map<Object, Object> getHashMapbyQuery(String hql);

	/**
	 * ͨ��sql���¼�¼
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract int updateBySqlString(final String query);

	/**
	 * ͨ��sql��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public abstract List<T> findListbySql(final String sql);

	public abstract <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * ����������������ֵ��ѯ. ������
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
	 * ����ʵ��ģ�����
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 */

	public abstract List findByExample(final String entityName,
			final Object exampleEntity);

	/**
	 * ���ô洢����
	 */
	public abstract void callableStatementByName(String proc);

	/**
	 * ��ѯָ��ʵ����ܼ�¼��
	 * 
	 * @param clazz
	 * @return
	 */
	public abstract int getCount(Class<T> clazz);

	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������For JDBC
	 */
	public abstract Long getCountForJdbc(String sql);

	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������For JDBC-����Ԥ����ʽ
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