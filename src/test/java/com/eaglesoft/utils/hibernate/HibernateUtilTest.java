package com.eaglesoft.utils.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.core.monitor.entity.Stock;
import com.eaglesoft.stock.core.monitor.entity.StockCategory;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockEod;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.stock.parser.ZjlxStockEodParser;

public class HibernateUtilTest {

	final static String BB1 = "600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSaveZjlxStockRuntime() {
		ZjlxStockRuntime zjlxStockRuntime = new ZjlxDataParser().parseAsRecord(BB1);
		zjlxStockRuntime.setExtractTime(new Date());
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(zjlxStockRuntime);
		session.getTransaction().commit();
	}
	

	@Test
	public void testSaveZjlxStockEod() {
		ZjlxStockEod zjlxStockEod = new ZjlxStockEodParser().parseAsZjlxStockEod(BB1);
		zjlxStockEod.setBusinessDate(new Date());
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(zjlxStockEod);
		session.getTransaction().commit();
	}
	
	@Test
	public void testSaveStock() {
		Stock stock = new Stock();
		stock.setCode("002190");
		stock.setName("成飞集成");
		stock.setDescription("test stock code can be delete anytime");
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
	}
	
	@Test
	public void testSaveStockCategory() {
		StockCategory categore = new StockCategory();
		categore.setCode("1");
		categore.setName("电子信息");
		categore.setType("行业板块");
		categore.setDescription("test stock category can be delete anytime");
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(categore);
		session.getTransaction().commit();
	}

}
