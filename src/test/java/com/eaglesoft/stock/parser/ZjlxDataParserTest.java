package com.eaglesoft.stock.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.eaglesoft.stock.domainObjects.ZjlxAggregator;

public class ZjlxDataParserTest {

	final static String TEST_DATA_FORMAT_1 = "{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";

	final static String TEST_DATA_FORMAT_2 = "([\"600030,中信证券,13.61,6.08,439650000,12.08,491320000,13.50,-51670000,-1.42,-235070000,-6.46,-204580000,-5.62\",\"000970,中科三环,14.36,10.04,382000000,27.21,346460000,24.68,35540000,2.53,-188810000,-13.45,-193180000,-13.76\"])";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseString() throws Exception {
		ZjlxDataParser parser = new ZjlxDataParser();
		ZjlxAggregator aggregator = (ZjlxAggregator) parser
				.parse(TEST_DATA_FORMAT_1);
		assertThat(aggregator.getData().size(), equalTo(2));
		
	}

	@Test
	public void testParsingDataFormat2() throws Exception {
		Pattern p = Pattern.compile("\\[(.+)\\]");
	
		Matcher m = p.matcher(TEST_DATA_FORMAT_2);
		System.out.println("match:"+m.find());
		System.out.println(m.group());
		System.out.println("2:"+"{\"data\":"+m.group()+"}");
		ZjlxDataParser parser = new ZjlxDataParser();
		ZjlxAggregator aggregator =
		(ZjlxAggregator)parser.parse("{\"data\":"+m.group()+"}");
		assertThat(aggregator.getData().size(),equalTo(2));
		//assertThat(aggregator.getPageSize(),equalTo(50));
		//assertThat(aggregator.getData().size(),equalTo(2));
	}

}
