package com.eaglesoft.stock.parser;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.domainObjects.ZjlxAggregator;

public class ZjlxDataParserTest {
	
	final static String AA1 = "{\"data\":[\"600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27\",\"600787,1,1,�д��ɷ�,15.23,2.63,1972��,9.37,975��,4.64,997��,4.74,-1046��,-4.97,-926��,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseString() throws Exception{
		ZjlxDataParser parser = new ZjlxDataParser();
		ZjlxAggregator aggregator = (ZjlxAggregator)parser.parse(AA1);
		assertThat(aggregator.getCount(),equalTo(1));
		assertThat(aggregator.getPageSize(),equalTo(50));
		assertThat(aggregator.getData().size(),equalTo(2));
	}

	

}
