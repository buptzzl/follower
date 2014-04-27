package com.eaglesoft.stock.core.common.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.eaglesoft.stock.core.common.dao.ICommonDao;
import com.eaglesoft.stock.core.common.dao.SQLBuilder;
import com.eaglesoft.utils.hibernate.HibernateUtil;


/**
 * 
 * 类描述： DAO层泛型基类
 * 
 * @author eaglesoft
 * @date： 日期：2012-12-7 时间：上午10:16:48
 * @param <T>
 * @param <PK>
 * @version 1.0
 */
@SuppressWarnings("hiding")
public  class GenericBaseCommonDao<T, PK extends Serializable> implements ICommonDao<T, PK>  {
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = LogManager.getLogger(GenericBaseCommonDao.class);

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	

	
	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getSession()
	 */
	public Session getSession() {
		return HibernateUtil.getSession();
		/*// 事务必须是开启的(Required)，否则获取不到
		Session session = sessionFactory.getCurrentSession();
		//session.enableFilter("DelFlag").setParameter("delFlag", 0);
		return session;*/
	}

	/**
	 * 获得该类的属性和类型
	 * 
	 * @param entityName
	 *            注解的实体类
	 */
	private <T> void getProperty(Class entityName) {
		ClassMetadata cm = getSession().getSessionFactory().getClassMetadata(entityName);
		String[] str = cm.getPropertyNames(); // 获得该类所有的属性名称
		for (int i = 0; i < str.length; i++) {
			String property = str[i];
			String type = cm.getPropertyType(property).getName(); // 获得该名称的类型
			System.out.println(property + "---&gt;" + type);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getAllDbTableSize()
	 */
	public Integer getAllDbTableSize() {
		SessionFactory factory = getSession().getSessionFactory();
		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
		return metaMap.size();
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findUniqueByProperty(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findByProperty(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#save(T)
	 */
	public <T> void save(T entity) {
		try {
			getSession().save(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("保存实体异常", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#batchSave(java.util.List)
	 */
	public <T> void batchSave(List<T> entitys) {
		for (int i=0; i<entitys.size();i++) {
			getSession().save(entitys.get(i));
//			if (i % 20 == 0) {
//				//20个对象后才清理缓存，写入数据库
//				getSession().clear();
//			}
			
		}
	}
	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#saveOrUpdate(T)
	 */

	public <T> void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("exception", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#delete(T)
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("删除成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除异常", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#deleteEntityById(java.lang.Class, java.io.Serializable)
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		Table table = (Table) entityName.getAnnotation(Table.class);
		if (table != null) {
			String tableName = table.name();
			String sql = "delete from " + tableName + " where id = ?";
			this.createSQLQuery(sql, id).executeUpdate();
			logger.info("delete: " + entityName + ",id="+id);
		}else{
			throw new IllegalStateException("缺少必要的Hibernate 映射。" + entityName);
		}
		//delete(get(entityName, id));
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#deleteAllEntitie(java.util.Collection)
	 */
	public <T> void deleteAllEntitie(Collection<T> entitys) {
		for (Object entity : entitys) {
			getSession().delete(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#get(java.lang.Class, java.io.Serializable)
	 */
	public <T> T get(Class<T> entityClass, final Serializable id) {

		return (T) getSession().get(entityClass, id);

	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getEntity(java.lang.Class, java.io.Serializable)
	 */
	public <T> T getEntity(Class entityName, Serializable id) {

		T t = (T) getSession().get(entityName, id);
		if (t != null) {
			getSession().flush();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#updateEntitie(T)
	 */
	public <T> void updateEntitie(T pojo) {
		getSession().update(pojo);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#updateEntitie(java.lang.String, java.lang.Object)
	 */
	public <T> void updateEntitie(String className, Object id) {
		getSession().update(className, id);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#updateEntityById(java.lang.Class, java.io.Serializable)
	 */
	public <T> void updateEntityById(Class entityName, Serializable id) {
		updateEntitie(get(entityName, id));
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findByQueryString(java.lang.String, java.lang.Object)
	 */
	public List<T> findByQueryString(final String hql,Object...obj) {

		Query queryObject = createHQLQuery(hql,obj);
		List<T> list = queryObject.list();
		if (list.size() > 0) {
			getSession().flush();
		}
		return list;

	}
	
	 /* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#createHQLQuery(java.lang.String, java.lang.Object)
	 */
    public Query createHQLQuery(String hql, Object... values)  {
        //Assert.hasText(sql);
        Query queryObject = null;
        queryObject = getSession().createQuery(hql);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }
	
	 /* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#createSQLQuery(java.lang.String, java.lang.Object)
	 */
    public SQLQuery createSQLQuery(String sql, Object... values)  {
        //Assert.hasText(sql);
        SQLQuery queryObject = null;
        queryObject = getSession().createSQLQuery(sql);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }
    

    
   
    /* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getQuery(com.eaglesoft.stock.core.common.dao.SQLBuilder)
	 */
    @Override
    public Query getQuery(SQLBuilder sqlBuilder) {
        Query query = null;
        query = getSession().createQuery(sqlBuilder.getHql());
        sqlBuilder.setParamsToQuery(query);
        return query;
    }

    /* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getSQLQuery(com.eaglesoft.stock.core.common.dao.SQLBuilder)
	 */
    @Override
    public SQLQuery getSQLQuery(SQLBuilder sqlBuilder) {
        SQLQuery query = null;
        query = getSession().createSQLQuery(sqlBuilder.getHql());
        sqlBuilder.setParamsToQuery(query);
        return query;
    }
    

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#singleResult(java.lang.String, java.lang.Object)
	 */
	public <T> T singleResult(String hql,Object... params) {
		T t = null;
		Query queryObject = createHQLQuery(hql,params);
		List<T> list = queryObject.list();
		if (list.size() == 1) {
			getSession().flush();
			t = list.get(0);
		} else if (list.size() > 0) {
			throw new RuntimeException("查询结果数:" + list.size() + "大于1");
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getHashMapbyQuery(java.lang.String)
	 */
	public Map<Object, Object> getHashMapbyQuery(String hql) {

		Query query = getSession().createQuery(hql);
		List list = query.list();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0].toString(), tm[1].toString());
		}
		return map;

	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#updateBySqlString(java.lang.String)
	 */
	public int updateBySqlString(final String query) {

		Query querys = getSession().createSQLQuery(query);
		return querys.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findListbySql(java.lang.String)
	 */
	public List<T> findListbySql(final String sql) {
		Query querys = getSession().createSQLQuery(sql);
		return querys.list();
	}

	/**
	 * 创建Criteria对象，有排序功能。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc("asc"));
		} else {
			criteria.addOrder(Order.desc("desc"));
		}
		return criteria;
	}

	/**
	 * 创建Criteria对象带属性比较
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#loadAll(java.lang.Class)
	 */
	public <T> List<T> loadAll(final Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return (List<T>) criteria.list();
	}

	/**
	 * 创建单一Criteria对象
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findByPropertyisOrder(java.lang.Class, java.lang.String, java.lang.Object, boolean)
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass, isAsc, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值 查询 且要求对象唯一.
	 * 
	 * @return 符合条件的唯一对象.
	 */
//	public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
//		Assert.hasText(propertyName);
//		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
//	}

	

	/**
	 * 批量插入实体
	 * 
	 * @param clas
	 * @param values
	 * @return
	 */
//	public <T> int batchInsertsEntitie(List<T> entityList) {
//		int num = 0;
//		for (int i = 0; i < entityList.size(); i++) {
//			save(entityList.get(i));
//			num++;
//		}
//		return num;
//	}

	

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findByExample(java.lang.String, java.lang.Object)
	 */

	public List findByExample(final String entityName, final Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		Criteria executableCriteria = (entityName != null ? getSession().createCriteria(entityName) : getSession().createCriteria(exampleEntity.getClass()));
		executableCriteria.add(Example.create(exampleEntity));
		return executableCriteria.list();
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#callableStatementByName(java.lang.String)
	 */
	public void callableStatementByName(String proc) {
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getCount(java.lang.Class)
	 */
	public int getCount(Class<T> clazz) {

		int count = DataAccessUtils.intResult(getSession().createQuery("select count(*) from " + clazz.getName()).list());
		return count;
	}

	
	
	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getCountForJdbc(java.lang.String)
	 */
	public Long getCountForJdbc(String  sql) {
		return  this.jdbcTemplate.queryForLong(sql);
	}
	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#getCountForJdbcParam(java.lang.String, java.lang.Object[])
	 */
	public Long getCountForJdbcParam(String  sql,Object[] objs) {
		return  this.jdbcTemplate.queryForLong(sql, objs);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findForJdbc(java.lang.String, java.lang.Object)
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return this.jdbcTemplate.queryForList(sql,objs);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#executeSql(java.lang.String, java.util.List)
	 */
	public Integer executeSql(String sql,List<Object> param) {
		return this.jdbcTemplate.update(sql,param);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#executeSql(java.lang.String, java.lang.Object)
	 */
	public Integer executeSql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql,param);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#countByJdbc(java.lang.String, java.lang.Object)
	 */
	public Integer countByJdbc(String sql, Object... param) {
		return this.jdbcTemplate.queryForInt(sql, param);
	}

	/* (non-Javadoc)
	 * @see com.eaglesoft.stock.core.common.dao.impl.ICommonDao#findOneForJdbc(java.lang.String, java.lang.Object)
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		try{ 
			return this.jdbcTemplate.queryForMap(sql, objs);
		}catch (EmptyResultDataAccessException e) {   
		    return null;   
		}  
	}

}
