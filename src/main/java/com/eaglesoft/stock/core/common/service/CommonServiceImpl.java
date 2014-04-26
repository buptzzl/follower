package com.eaglesoft.stock.core.common.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaglesoft.stock.core.common.dao.ICommonDao;



@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	public ICommonDao commonDao = null;
	

	public String like(String str){
		return "%".concat(str).concat("%");
	}
	
	
	public Integer getAllDbTableSize() {
		return commonDao.getAllDbTableSize();
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public <T> void save(T entity) {
		commonDao.save(entity);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	@Override
	public <T> void delete(T entity) {
		commonDao.delete(entity);

	}

	/**
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}

	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}

	/**
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
	public <T> List<T> getList(Class clas) {
		return commonDao.loadAll(clas);
	}

	public <T> T getEntity(Class entityName, Serializable id) {
		return (T) commonDao.getEntity(entityName, id);
	}

	/**
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {

		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	/**
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}
	
	public <T> T singleResult(String hql)
	{
		return (T) commonDao.singleResult(hql);
	}

	/**
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	/**
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		commonDao.updateEntitie(pojo);

	}

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql,Object...obj) {
		return commonDao.findByQueryString(hql, obj);
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql) {
		return commonDao.updateBySqlString(sql);
	}

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query) {
		return commonDao.findListbySql(query);
	}

	/**
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc) {
		return commonDao.findByPropertyisOrder(entityClass, propertyName, value, isAsc);
	}


	public Session getSession()

	{
		return commonDao.getSession();
	}

	public List findByExample(final String entityName, final Object exampleEntity) {
		return commonDao.findByExample(entityName, exampleEntity);
	}
	
	@Override
	public Integer executeSql(String sql, List<Object> param) {
		return commonDao.executeSql(sql, param);
	}
	@Override
	public Integer executeSql(String sql, Object... param) {
		return commonDao.executeSql(sql, param);
	}
	@Override
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		return commonDao.findForJdbc(sql, page,rows);
	}
	@Override
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return commonDao.findForJdbc(sql, objs);
	}
	@Override
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		return commonDao.findForJdbcParam(sql, page, rows, objs);
	}
	@Override
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz) {
		return commonDao.findObjForJdbc(sql, page, rows, clazz);
	}
	@Override
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return commonDao.findOneForJdbc(sql, objs);
	}
	@Override
	public Long getCountForJdbc(String sql) {
		return commonDao.getCountForJdbc(sql);
	}
	@Override
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		return commonDao.getCountForJdbc(sql);
	}
	@Override
	public <T> void batchSave(List<T> entitys) {
		this.commonDao.batchSave(entitys);
	}
	@Override
	public Query createHQLQuery(String hql, Object... values) {
		return this.commonDao.createHQLQuery(hql, values);
	}
	@Override
	public SQLQuery createSQLQuery(String sql, Object... values) {
		return this.commonDao.createSQLQuery(sql, values);
	}
	
	 /**
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
    public List paginationQuery(Query query) {
    	return this.commonDao.paginationQuery(query);
    }
    
	
	@Override
	public String getSystemConfig(String key) {
		return commonDao.getSystemConfig(key);
	}
}
