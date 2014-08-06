package com.eaglesoft.stock.analyzer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.TestDataFactory;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntimeGroup;
import com.eaglesoft.utils.JasonUtil;
import com.eaglesoft.utils.date.DateUtil;

public class TopXDeltaAnalyzerTest {
    
    private static final Logger logger = LogManager.getLogger(TopXDeltaAnalyzerTest.class);

	TopXDeltaAnalyzer topXDeltaAnalyzer;

	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/analyzers.xml",
						"META-INF/spring/daos.xml" });
		this.topXDeltaAnalyzer = (TopXDeltaAnalyzer) applicationContext
				.getBean("topXDeltaAnalyzer");
	}

	@Test
	public void testAnalyze() throws Exception{
	    Date startTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-21 09:00:00");
        Date endTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-21 15:00:00");
	    topXDeltaAnalyzer.analyze(startTime, endTime,  "DELTAVOLUMN,DELTAJRZDFPERYI");
	}

	
	@Test
    public void testTakeTopxRealData() throws Exception {
	    
	    
        Date startTime = null;
        Date endTime = null;
           try {
               startTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-21 09:00:00");
               endTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-21 15:00:00");
           } catch (ParseException e1) {
               e1.printStackTrace();
           }

	    
	    List<ZjlxStockRuntime> list= topXDeltaAnalyzer.findData(startTime,endTime);
	    
        Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer.group(list);;
        String sortedByField = "DELTAVOLUMN";
        topXDeltaAnalyzer.caculateDelta(groups);
        List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
        List<ZjlxStockRuntimeGroup> top2Group = topXDeltaAnalyzer.takeTopN(sortedGroup, 200);
        topXDeltaAnalyzer.generateTopXReport(top2Group,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
       
    }
	
	

    @Test
    public void testTakeTopxRealDataJRZFPerYi() throws Exception {
        
        
        Date startTime = null;
        Date endTime = null;
           try {
               startTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-19 09:00:00");
               endTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-19 15:00:00");
           } catch (ParseException e1) {
               e1.printStackTrace();
           }

        
        List<ZjlxStockRuntime> list= topXDeltaAnalyzer.findData(startTime,endTime);
        
        Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer.group(list);;
        String sortedByField = "DELTAJRZDFPERYI";
        topXDeltaAnalyzer.caculateDelta(groups);
        List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
        List<ZjlxStockRuntimeGroup> top2Group = topXDeltaAnalyzer.takeTopN(sortedGroup, 200);
        
        topXDeltaAnalyzer.generateTopXReport(top2Group,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
       
    }
	 
	


    @Test
    public void testTakeTopxRealDataRateOfJrxdjlr() throws Exception {
        
        
        Date startTime = null;
        Date endTime = null;
           try {
               startTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-06 09:40:00");
               endTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-05-06 15:00:00");
           } catch (ParseException e1) {
               e1.printStackTrace();
           }

        
        List<ZjlxStockRuntime> list= topXDeltaAnalyzer.findData(startTime,endTime);
        
        Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer.group(list);;
        String sortedByField = "DELTARATEOFJRXDJLR";
        topXDeltaAnalyzer.caculateDelta(groups);
        List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
        List<ZjlxStockRuntimeGroup> top2Group = topXDeltaAnalyzer.takeTopN(sortedGroup, 200);
        
        topXDeltaAnalyzer.generateTopXReport(top2Group,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
       
    }
    
	
	@Test
	public void testSort() throws Exception {
		Map<String, ZjlxStockRuntimeGroup> groups = getTestGroups();
		topXDeltaAnalyzer.caculateDelta(groups);
		List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups, ZjlxStockRuntimeGroup.Field.valueOf("DELTARATEOFJRXDJLR") );
		assertThat(sortedGroup.size(),equalTo(3));
		assertThat(sortedGroup.get(0).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-10d));
		assertThat(sortedGroup.get(1).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-9d));
		assertThat(sortedGroup.get(2).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-7d));

		assertThat(sortedGroup.get(0).getCode(),equalTo("000402"));
		assertThat(sortedGroup.get(1).getCode(),equalTo("600383"));
		assertThat(sortedGroup.get(2).getCode(),equalTo("600036"));
	}
	
	@Test
    public void testSortByDeltaVolumn() throws Exception {
        Map<String, ZjlxStockRuntimeGroup> groups = getTestGroups();
        topXDeltaAnalyzer.caculateDelta(groups);
        List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups, ZjlxStockRuntimeGroup.Field.valueOf("DELTAVOLUMN") );
        assertThat(sortedGroup.size(),equalTo(3));
        assertThat(sortedGroup.get(0).getDeltaVolumn().doubleValue(),equalTo(-2094265514d));
        assertThat(sortedGroup.get(1).getDeltaVolumn().doubleValue(),equalTo(-639194813d));
        assertThat(sortedGroup.get(2).getDeltaVolumn().doubleValue(),equalTo(-5.01522295E8d));

        assertThat(sortedGroup.get(0).getCode(),equalTo("600383"));
        assertThat(sortedGroup.get(1).getCode(),equalTo("000402"));
        assertThat(sortedGroup.get(2).getCode(),equalTo("600036"));
    }

	@Test
	public void testCaculateDelta() throws Exception {
		Map<String, ZjlxStockRuntimeGroup> groups = getTestGroups();
		topXDeltaAnalyzer.caculateDelta(groups);
		assertThat(groups.get("600383").getDeltaRateOfJrxdjlr().doubleValue(), equalTo(-9d));
		assertThat(groups.get("000402").getDeltaRateOfJrxdjlr().doubleValue(), equalTo(-10d));
		assertThat(groups.get("600036").getDeltaRateOfJrxdjlr().doubleValue(), equalTo(-7d));
	}

	@Test
	public void testFindData() {
	    Date startTime = null;
        Date endTime = null;
           try {
               startTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-04-30 09:00:00");
               endTime = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-04-30 15:00:00");
           } catch (ParseException e1) {
               e1.printStackTrace();
           }

		List<ZjlxStockRuntime> list = topXDeltaAnalyzer.findData(startTime,endTime);
		assertThat(list.size(), greaterThan(1));
		assertThat(list.get(0).getCode(), equalTo("600548"));
	}

	@Test
	public void testFindData2() {
		/*List<ZjlxStockRuntime> list = topXDeltaAnalyzer.findData2();
		assertThat(list.size(), equalTo(100));
		assertThat(list.get(0).getCode(), equalTo("600548"));*/
	}

	@Test
	public void testGroup() throws Exception {
		List<ZjlxStockRuntime> wholeList = new ArrayList<ZjlxStockRuntime>();
		List<ZjlxStockRuntime> list2 = TestDataFactory
				.getZjlxStockRuntimeTestData2();
		List<ZjlxStockRuntime> list3 = TestDataFactory
				.getZjlxStockRuntimeTestData3();
		assertThat(list2.size(), equalTo(3));
		assertThat(list3.size(), equalTo(3));
		wholeList.addAll(list2);
		wholeList.addAll(list3);
		assertThat(wholeList.size(), equalTo(6));
		Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer
				.group(wholeList);
		assertThat(groups.values().size(), equalTo(3));
		assertThat(groups.get("600383").getZjlxStockRuntimes().size(), equalTo(2));
		//assertThat(groups.get("600383").getDeltaRateOfJrxdjlr().doubleValue(), equalTo(9d));
	}

	public Map<String, ZjlxStockRuntimeGroup> getTestGroups()  throws Exception  {
		List<ZjlxStockRuntime> wholeList = new ArrayList<ZjlxStockRuntime>();
		List<ZjlxStockRuntime> list2 = TestDataFactory
				.getZjlxStockRuntimeTestData2();
		List<ZjlxStockRuntime> list3 = TestDataFactory
				.getZjlxStockRuntimeTestData3();
		wholeList.addAll(list2);
		wholeList.addAll(list3);
		Map<String, ZjlxStockRuntimeGroup> groups = topXDeltaAnalyzer
				.group(wholeList);
		
		return groups;
	}

}
