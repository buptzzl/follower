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

	final static String BB1 = "600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27";

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
		stock.setName("�ɷɼ���");
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
		categore.setName("������Ϣ");
		categore.setType("��ҵ���");
		categore.setDescription("test stock category can be delete anytime");
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(categore);
		session.getTransaction().commit();
	}

}
