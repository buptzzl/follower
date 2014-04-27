package com.eaglesoft.stock.analyzer;

import static org.junit.Assert.fail;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.TestDataFactory;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntimeGroup;

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
    	List<ZjlxStockRuntime> list = topXDeltaAnalyzer.findData();
    	assertThat(list.size(), equalTo(1));
    	assertThat(list.get(0).getCode(), equalTo("600548"));
    }
    

    @Test
    public void testFindData2() {
    	List<ZjlxStockRuntime> list = topXDeltaAnalyzer.findData2();
    	assertThat(list.size(), equalTo(100));
    	assertThat(list.get(0).getCode(), equalTo("600548"));
    }

    @Test
    public void testGroup() throws Exception {
        
        Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer.group(TestDataFactory.getZjlxStockRuntimeTestData1());
    }

}
