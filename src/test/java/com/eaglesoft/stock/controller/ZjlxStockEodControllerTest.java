package com.eaglesoft.stock.controller;

import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.core.common.dao.ZjlxOfStockDao;
import com.eaglesoft.stock.event.ZjlxStockEodEvent;
import com.eaglesoft.stock.parser.ZjlxStockEodParser;
import com.eaglesoft.stock.service.ZjlxOfStockService;

public class ZjlxStockEodControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcess() throws Exception {
		long startTime = System.currentTimeMillis();
		
		ZjlxStockEodController controller = new ZjlxStockEodController();
		ZjlxOfStockService service  = new ZjlxOfStockService();
		ZjlxOfStockDao dao = new ZjlxOfStockDao();
		service.setDao(dao);
		controller.setZjlxOfStockService(service);
		controller.setParser(new ZjlxStockEodParser());
		controller.process(new ZjlxStockEodEvent());
		
		System.out.print("time spent:"+(System.currentTimeMillis() - startTime));
	}

}
