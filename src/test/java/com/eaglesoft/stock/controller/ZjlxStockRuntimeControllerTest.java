package com.eaglesoft.stock.controller;

import static org.junit.Assert.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.analyzer.TopXDeltaAnalyzer;
import com.eaglesoft.stock.core.common.dao.impl.ZjlxOfStockDao;
import com.eaglesoft.stock.event.ZjlxStockRuntimeEvent;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.stock.service.ZjlxOfStockService;

public class ZjlxStockRuntimeControllerTest {
    
    private static final Logger logger = LogManager.getLogger(ZjlxStockRuntimeControllerTest.class);
    
	ZjlxStockRuntimeController controller;

	@Before
	public void setUp() throws Exception {
		String[] configFiles = new String[]{
				"classpath:META-INF/spring/daos.xml",
				"classpath:META-INF/spring/services.xml",
				"classpath:META-INF/spring/parsers.xml",
				"classpath:META-INF/spring/analyzers.xml",
				"classpath:META-INF/spring/controllers.xml",
		};
		 ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFiles);
		 this.controller = (ZjlxStockRuntimeController) applicationContext.getBean("zjlxStockRuntimeController");
	     
	}
	
	@Test
	public void testRealProcess() throws Exception {

		long startTime = System.currentTimeMillis();

		controller.process(new ZjlxStockRuntimeEvent());

		logger.info("time spent:"
				+ (System.currentTimeMillis() - startTime));

	}

	@Test
	public void testProcess() throws Exception {

		long startTime = System.currentTimeMillis();

		ZjlxStockRuntimeController controller = new ZjlxStockRuntimeController();
		ZjlxOfStockService service = new ZjlxOfStockService();
		ZjlxOfStockDao dao = new ZjlxOfStockDao();
		service.setDao(dao);
		controller.setZjlxOfStockService(service);

		controller.setParser(new ZjlxDataParser());

		controller.process(new ZjlxStockRuntimeEvent());

		logger.info("time spent:"
				+ (System.currentTimeMillis() - startTime));

	}

}
