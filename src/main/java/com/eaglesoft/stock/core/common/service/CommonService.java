package com.eaglesoft.stock.core.common.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;



public interface CommonService {
	
	
	/**
	 * 获取�?��数据库表
	 * @return
	 */
	
	public Integer getAllDbTableSize();
	public <T> void save(T entity);
	public <T> void saveOrUpdate(T entity);
	public <T> void delete(T entity);
	public <T> void batchSave(List<T> entitys);
	/**
	 * 根据实体名称和主键获取实�?
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> class1, Serializable id);
	/**
	 * 根据实体名称和主键获取实�?
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);
	/**
	 * 根据实体名称和字段名称和字段值获取唯�?���?
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);
	/**
	 * 按属性查找对象列�?
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,String propertyName, Object value);
	/**
	 * 加载全部实体
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);
		

	/**
	 * 删除实体主键删除
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities);

	/**
	 * 更新指定的实�?
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql,Object... obj);
	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql);

	/**
	 * 根据sql查找List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query);
	/**
	 * 通过属�?称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc);
	
	public <T> List<T> getList(Class clas);
	
	public <T> T singleResult(String hql);
	
	
	public Session getSession();
	public List findByExample(final String entityName, final Object exampleEntity);
	
	
	
	
	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, List<Object> param);
	
	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, Object... param);
	
	
	/**
	 * 通过JDBC查找对象集合
	 * 使用指定的检索标准检索数据返回数�?
	 */
	public List<Map<String, Object>> findForJdbc(String sql,Object... objs);
	
	
	/**
	 * 通过JDBC查找对象集合
	 * 使用指定的检索标准检索数据返回数�?
	 */
	public Map<String, Object> findOneForJdbc(String sql,Object... objs);
	
	/**
	 * 通过JDBC查找对象集合,带分�?
	 * 使用指定的检索标准检索数据并分页返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);
	

	/**
	 * 通过JDBC查找对象集合,带分�?
	 * 使用指定的检索标准检索数据并分页返回数据
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,Class<T> clazz) ;
	
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方�?
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String  sql,  int page, int rows,Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	public Long getCountForJdbc(String  sql) ;
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方�?
	 * 
	 */
	public Long getCountForJdbcParam(String  sql,Object[] objs);
	
	
	//add by luos----------------------------------------------------------
	/**
     * 获取得到SQLQuery对象
     * 
     * @param sql
     * @param values
     * @return
     */
    public SQLQuery createSQLQuery(String sql, Object... values) ; 


    /**
     * 获取得到HQLQuery对象
     * 
     * @param sql
     * @param values
     * @return
     */
    public Query createHQLQuery(String hql, Object... values) ; 
    
    /**
     * 提供基于Query对象的分页查询，非dategrid场合请使用此分页方法
     * 
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
    public List paginationQuery(Query query) ;
    

   
    public String getSystemConfig(String key);
}
