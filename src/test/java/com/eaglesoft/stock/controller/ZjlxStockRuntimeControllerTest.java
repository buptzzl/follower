package com.eaglesoft.stock.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.core.common.dao.impl.ZjlxOfStockDao;
import com.eaglesoft.stock.event.ZjlxStockRuntimeEvent;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.stock.service.ZjlxOfStockService;

public class ZjlxStockRuntimeControllerTest {
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcess() throws Exception {
		
			long startTime = System.currentTimeMillis();
			
			ZjlxStockRuntimeController controller = new ZjlxStockRuntimeController();
			ZjlxOfStockService service  = new ZjlxOfStockService();
			ZjlxOfStockDao dao = new ZjlxOfStockDao();
			service.setDao(dao); 
			controller.setZjlxOfStockService(service);
			
			controller.setParser(new ZjlxDataParser() );
			
			controller.process(new ZjlxStockRuntimeEvent());
			
			System.out.print("time spent:"+(System.currentTimeMillis() - startTime));
	
	}

}
