package com.eaglesoft.stock.analyzer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
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
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/analyzers.xml",
						"META-INF/spring/daos.xml" });
		this.topXDeltaAnalyzer = (TopXDeltaAnalyzer) applicationContext
				.getBean("topXDeltaAnalyzer");
	}

	@Test
	public void testAnalyze() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTakeTopx() throws Exception {
		Map<String, ZjlxStockRuntimeGroup> groups = getTestGroups();
		topXDeltaAnalyzer.caculateDelta(groups);
		List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups);
		List<ZjlxStockRuntimeGroup> top2Group = topXDeltaAnalyzer.takeTopN(sortedGroup, 2);
		assertThat(top2Group.size(),equalTo(2));
		assertThat(top2Group.get(0).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-10d));
		assertThat(top2Group.get(1).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-9d));
		
		assertThat(top2Group.get(0).getCode(),equalTo("000402"));
		assertThat(top2Group.get(1).getCode(),equalTo("600383"));

	}

	@Test
	public void testSort() throws Exception {
		Map<String, ZjlxStockRuntimeGroup> groups = getTestGroups();
		topXDeltaAnalyzer.caculateDelta(groups);
		List<ZjlxStockRuntimeGroup> sortedGroup =  topXDeltaAnalyzer.sort(groups);
		assertThat(sortedGroup.size(),equalTo(3));
		sortedGroup.get(0).getDeltaRateOfJrxdjlr();
		assertThat(sortedGroup.get(0).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-10d));
		assertThat(sortedGroup.get(1).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-9d));
		assertThat(sortedGroup.get(2).getDeltaRateOfJrxdjlr().doubleValue(),equalTo(-7d));

		assertThat(sortedGroup.get(0).getCode(),equalTo("000402"));
		assertThat(sortedGroup.get(1).getCode(),equalTo("600383"));
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
		List<ZjlxStockRuntime> list = topXDeltaAnalyzer.findData();
		assertThat(list.size(), equalTo(1));
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
