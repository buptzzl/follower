package com.eaglesoft.utils.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class HibernateUtilTest {

	final static String BB1 = "600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27";

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
