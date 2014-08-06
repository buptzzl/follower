package com.eaglesoft.stock.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;


/**
 * 
 * ��������DAO�㷺�ͻ���ӿ�
 * 
 * @author dingsoft
 * @date�� ���ڣ�2012-12-8 ʱ�䣺����05:37:33
 * @version 1.0
 */
public interface IGenericBaseCommonDao {
	/**
	 * ��ȡ�������ݿ��
	 * @return
	 */
	
	public Integer getAllDbTableSize();
	public <T> void save(T entity);
	public <T> void batchSave(List<T> entitys);

	public <T> void saveOrUpdate(T entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entitie
	 */
	public <T> void delete(T entitie);

	/**
	 * ����ʵ�����ƺ�������ȡʵ��
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);

	/**
	 * ����ʵ�����ֻ�ȡΨһ��¼
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * �����Բ��Ҷ����б�.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);
	/**
	 * ����ȫ��ʵ��
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * ����ʵ�����ƺ�������ȡʵ��
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);

	public <T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * ɾ��ʵ�弯��
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities);

	/**
	 * ����ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo);

	public <T> void updateEntityById(Class entityName, Serializable id);

	/**
	 * ͨ��hql ��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql,Object...obj);

	/**
	 * ͨ��hql��ѯΨһ����
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> T singleResult(String hql,Object...params);

	/**
	 * ����sql����
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql);

	/**
	 * ����sql����List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query);

	/**
	 * ͨ�����Գƻ�ȡʵ�������
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc);

	public Session getSession();
	public List findByExample(final String entityName, final Object exampleEntity);
	/**
	 * ͨ��hql ��ѯ������HashMap����
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Map<Object,Object> getHashMapbyQuery(String query);
	
	

	/**
	 * ִ��SQL
	 */
	public Integer executeSql(String sql, List<Object> param);
	
	/**
	 * ִ��SQL
	 */
	public Integer executeSql(String sql, Object... param);
	
	
	/**
	 * ͨ��JDBC���Ҷ��󼯺�
	 * ʹ��ָ���ļ�����׼�������ݷ�������
	 */
	public List<Map<String, Object>> findForJdbc(String sql,Object... objs);
	
	
	/**
	 * ͨ��JDBC���Ҷ��󼯺�
	 * ʹ��ָ���ļ�����׼�������ݷ�������
	 */
	public Map<String, Object> findOneForJdbc(String sql,Object... objs);
	
	/**
	 * ͨ��JDBC���Ҷ��󼯺�,����ҳ
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);
	

	/**
	 * ͨ��JDBC���Ҷ��󼯺�,����ҳ
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,Class<T> clazz) ;
	
	
	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������-����Ԥ����ʽ
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String  sql,  int page, int rows,Object... objs);
	
	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������For JDBC
	 */
	public Long getCountForJdbc(String  sql) ;
	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������For JDBC-����Ԥ����ʽ
	 * 
	 */
	public Long getCountForJdbcParam(String  sql,Object[] objs);
	
	//add by luos
	/**
     * ��ȡ�õ�SQLQuery����
     * 
     * @param sql
     * @param values
     * @return
     */
    public SQLQuery createSQLQuery(String sql, Object... values) ; 


    /**
     * ��ȡ�õ�HQLQuery����
     * 
     * @param sql
     * @param values
     * @return
     */
    public Query createHQLQuery(String hql, Object... values) ; 
    
    /**
     * ��ȡSQLBuilder��query����
     * 
     * @param sqlBuilder
     * @return
     */
    public Query getQuery(SQLBuilder sqlBuilder);

    /**
     * ��ȡSQLBuilder��query����
     * 
     * @param sqlBuilder
     * @return
     */
    public SQLQuery getSQLQuery(SQLBuilder sqlBuilder);
    /**
     * �ṩ����Query����ķ�ҳ��ѯ����dategrid������ʹ�ô˷�ҳ����
     * 
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
    public List paginationQuery(Query query) ;
}
