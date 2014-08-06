package com.eaglesoft.stock;

import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.eaglesoft.stock.core.common.dao.impl.GenericBaseCommonDao;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class HibernateTest {

	private static final Logger logger = LogManager.getLogger(HibernateTest.class);
	
	final static String BB1 = "600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27";

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void givenAZjlxRecordShouldBeableToSaveIntoDB() throws Exception {
		//BasicConfigurator.configure();
		logger.info("hello log4j");
		ZjlxStockRuntime zjlxStockRuntime = new ZjlxDataParser().parseAsRecord(BB1);
		Configuration config = new AnnotationConfiguration();
		config.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(zjlxStockRuntime);
		session.getTransaction().commit();
		logger.debug("log4j done");

	}

}
