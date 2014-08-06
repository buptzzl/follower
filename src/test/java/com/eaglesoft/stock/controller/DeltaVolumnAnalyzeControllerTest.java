package com.eaglesoft.stock.controller;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.event.DeltaVolumnAnalyzeEvent;
import com.eaglesoft.utils.date.DateUtil;

public class DeltaVolumnAnalyzeControllerTest {

    private DeltaVolumnAnalyzeController controller;
    
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
         this.controller = (DeltaVolumnAnalyzeController) applicationContext.getBean("deltaVolumnAnalyzeController");
    }

    @Test
    public void testProcess() throws Exception {
        Date endtime = new Date();
        Date startTime = DateUtil.minusMinutes(endtime, 40);
        DeltaVolumnAnalyzeEvent deltaVolumnAnalyzeEvent = new DeltaVolumnAnalyzeEvent(startTime,endtime, "DELTAVOLUMN,DELTAJRZDFPERYI");
        controller.process(deltaVolumnAnalyzeEvent);
       
    }


}
