package com.eaglesoft.stock.analyzer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.TestDataFactory;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntimeGroup;
import com.eaglesoft.stock.dispatcher.StockEventDispatcher;
import com.eaglesoft.stock.service.ZjlxOfStockService;

public class TopXDeltaAnalyzerTest {
    
    TopXDeltaAnalyzer topXDeltaAnalyzer;
    
    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext( new String[]{"META-INF/spring/analyzers.xml","META-INF/spring/daos.xml"});
        this.topXDeltaAnalyzer = (TopXDeltaAnalyzer) applicationContext.getBean("topXDeltaAnalyzer");
    }

    @Test
    public void testAnalyze() {
        fail("Not yet implemented");
    }

    @Test
    public void testTakeTopx() {
        fail("Not yet implemented");
    }

    @Test
    public void testSort() {
        fail("Not yet implemented");
    }

    @Test
    public void testCaculateDelta() {
        Map<String, ZjlxStockRuntimeGroup> groups = new HashMap<String, ZjlxStockRuntimeGroup>();
        topXDeltaAnalyzer.caculateDelta(groups);
    }

    @Test
    public void testFindData() {
        //this.zjlxOfStockService.getDao().
    }

    @Test
    public void testGroup() throws Exception {
        
        Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer.group(TestDataFactory.getZjlxStockRuntimeTestData1());
    }

}
