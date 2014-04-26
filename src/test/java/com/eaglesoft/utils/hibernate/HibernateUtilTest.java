package com.eaglesoft.utils.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class HibernateUtilTest {

	final static String BB1 = "600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSession() {
		ZjlxStockRuntime zjlxStockRuntime = new ZjlxDataParser().parseAsRecord(BB1);
		zjlxStockRuntime.setExtractTime(new Date());
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(zjlxStockRuntime);
		session.getTransaction().commit();
	}

}
