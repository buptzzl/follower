package com.eaglesoft.stock.core.common.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

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

import com.eaglesoft.stock.core.common.dao.IGenericBaseCommonDao;
import com.eaglesoft.stock.core.common.dao.SQLBuilder;


/**
 * 
 * 类描述： DAO层泛型基类
 * 
 * @author dingsoft
 * @date： 日期：2012-12-7 时间：上午10:16:48
 * @param <T>
 * @param <PK>
 * @version 1.0
 */
@SuppressWarnings("hiding")
public abstract class GenericBaseCommonDao<T, PK extends Serializable> implements IGenericBaseCommonDao {
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = Logger.getLogger(GenericBaseCommonDao.class);
	/**
	 * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport)
	 * **/
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		Session session = sessionFactory.getCurrentSession();
		//session.enableFilter("DelFlag").setParameter("delFlag", 0);
		return session;
	}

	/**
	 * 获得该类的属性和类型
	 * 
	 * @param entityName
	 *            注解的实体类
	 */
	private <T> void getProperty(Class entityName) {
		ClassMetadata cm = sessionFactory.getClassMetadata(entityName);
		String[] str = cm.getPropertyNames(); // 获得该类所有的属性名称
		for (int i = 0; i < str.length; i++) {
			String property = str[i];
			String type = cm.getPropertyType(property).getName(); // 获得该名称的类型
			System.out.println(property + "---&gt;" + type);
		}
	}
	
	/**
	 * 获取所有数据表
	 * @return
	 */
	public Integer getAllDbTableSize() {
		SessionFactory factory = getSession().getSessionFactory();
		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
		return metaMap.size();
	}

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据传入的实体持久化对象
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

	/**
	 * 批量保存数据
	 * @param <T>
	 * @param entitys 要持久化的临时实体对象集合
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
	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param <T>
	 * 
	 * @param entity
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

	/**
	 * 根据传入的实体删除对象
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

	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param <T>
	 * @param pojo
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

	/**
	 * 删除全部的实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public <T> void deleteAllEntitie(Collection<T> entitys) {
		for (Object entity : entitys) {
			getSession().delete(entity);
		}
	}

	/**
	 * 根据Id获取对象。
	 */
	public <T> T get(Class<T> entityClass, final Serializable id) {

		return (T) getSession().get(entityClass, id);

	}

	/**
	 * 根据主键获取实体并加锁。
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @param lock
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id) {

		T t = (T) getSession().get(entityName, id);
		if (t != null) {
			getSession().flush();
		}
		return t;
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		getSession().update(pojo);
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(String className, Object id) {
		getSession().update(className, id);
	}

	/**
	 * 根据主键更新实体
	 */
	public <T> void updateEntityById(Class entityName, Serializable id) {
		updateEntitie(get(entityName, id));
	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param hql
	 * @return
	 */
	public List<T> findByQueryString(final String hql,Object...obj) {

		Query queryObject = createHQLQuery(hql,obj);
		List<T> list = queryObject.list();
		if (list.size() > 0) {
			getSession().flush();
		}
		return list;

	}
	
	 /**
     * 获取得到HQLQuery对象
     * 
     * @param hql
     * @param values
     * @return
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
	
	 /**
     * 获取得到SQLQuery对象
     * 
     * @param sql
     * @param values
     * @return
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
    

    
   
    /**
     * 获取SQLBuilder中query对象，
     * 
     * @param sqlBuilder
     * @return
     */
    @Override
    public Query getQuery(SQLBuilder sqlBuilder) {
        Query query = null;
        query = getSession().createQuery(sqlBuilder.getHql());
        sqlBuilder.setParamsToQuery(query);
        return query;
    }

    /**
     * 获取SQLBuilder中query对象，
     * 
     * @param sqlBuilder
     * @return
     */
    @Override
    public SQLQuery getSQLQuery(SQLBuilder sqlBuilder) {
        SQLQuery query = null;
        query = getSession().createSQLQuery(sqlBuilder.getHql());
        sqlBuilder.setParamsToQuery(query);
        return query;
    }
    

	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
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

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
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

	/**
	 * 通过sql更新记录
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public int updateBySqlString(final String query) {

		Query querys = getSession().createSQLQuery(query);
		return querys.executeUpdate();
	}

	/**
	 * 通过sql查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
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

	

	/**
	 * 根据实体模版查找
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 */

	public List findByExample(final String entityName, final Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		Criteria executableCriteria = (entityName != null ? getSession().createCriteria(entityName) : getSession().createCriteria(exampleEntity.getClass()));
		executableCriteria.add(Example.create(exampleEntity));
		return executableCriteria.list();
	}

	/**
	 * 调用存储过程
	 */
	public void callableStatementByName(String proc) {
	}

	/**
	 * 查询指定实体的总记录数
	 * 
	 * @param clazz
	 * @return
	 */
	public int getCount(Class<T> clazz) {

		int count = DataAccessUtils.intResult(getSession().createQuery("select count(*) from " + clazz.getName()).list());
		return count;
	}

	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	

	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	public Long getCountForJdbc(String  sql) {
		return  this.jdbcTemplate.queryForLong(sql);
	}
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * 
	 */
	public Long getCountForJdbcParam(String  sql,Object[] objs) {
		return  this.jdbcTemplate.queryForLong(sql, objs);
	}

	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return this.jdbcTemplate.queryForList(sql,objs);
	}

	public Integer executeSql(String sql,List<Object> param) {
		return this.jdbcTemplate.update(sql,param);
	}

	public Integer executeSql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql,param);
	}

	public Integer countByJdbc(String sql, Object... param) {
		return this.jdbcTemplate.queryForInt(sql, param);
	}

	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		try{ 
			return this.jdbcTemplate.queryForMap(sql, objs);
		}catch (EmptyResultDataAccessException e) {   
		    return null;   
		}  
	}

}
