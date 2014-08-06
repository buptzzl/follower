package com.eaglesoft.stock.task;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.controller.DeltaVolumnAnalyzeController;

public class ZjlxStockRuntimeTaskTest {

    
    private ZjlxStockRuntimeTask task;
    
    @Before
    public void setUp() throws Exception {
        String[] configFiles = new String[]{
                "classpath:META-INF/spring/daos.xml",
                "classpath:META-INF/spring/services.xml",
                "classpath:META-INF/spring/parsers.xml",
                "classpath:META-INF/spring/analyzers.xml",
                "classpath:META-INF/spring/controllers.xml",
                "classpath:META-INF/spring/tasks.xml",
        };
         ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFiles);
         this.task = (ZjlxStockRuntimeTask) applicationContext.getBean("zjlxStockRuntimeTask");
    }

    @Test
    public void testExtractZjlxStock() {
        task.extractZjlxStock();
    }

    

    @Test
    public void testExtractEodZjlxStock() {
        task.extractEodZjlxStock();
    }

}
