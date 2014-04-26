package com.eaglesoft.stock;

import org.junit.Before;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class HibernateTest {

	final static String BB1 = "600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27";

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void givenAZjlxRecordShouldBeableToSaveIntoDB() throws Exception {
		ZjlxStockRuntime zjlxStockRuntime = new ZjlxDataParser().parseAsRecord(BB1);
		Configuration config = new AnnotationConfiguration();
		config.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(zjlxStockRuntime);
		session.getTransaction().commit();

	}

}
