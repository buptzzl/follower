package com.eaglesoft.stock;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class ZjlxRecordTest extends TestCase {
	
	final static String AA1="{\"data\":[\"600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27\",\"600787,1,1,�д��ɷ�,15.23,2.63,1972��,9.37,975��,4.64,997��,4.74,-1046��,-4.97,-926��,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
	final static String BB1="600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27";
	final static String EXAMPLE_TING_PAI="002405,2,4,��άͼ��,-,0.00,0,0.00,0,0.00,0,0.00,0,0.00,0,0.00";
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testParse() {
		ZjlxStockRuntime record = new ZjlxDataParser().parseAsRecord(BB1);
		assertThat(record.getCode(),equalTo("600739"));
		assertThat(record.getName(),equalTo("�����ɴ�"));
		assertThat(record.getLatestPrice().doubleValue(),equalTo(15.44));
		assertThat(record.getJrzljlr().doubleValue(),equalTo(Double.valueOf(95960000l)));
	}

	public void testParseTheDataForTingPai() {
		ZjlxStockRuntime record = new ZjlxDataParser().parseAsRecord(EXAMPLE_TING_PAI);
		assertThat(record.getCode(),equalTo("002405"));
		assertThat(record.getName(),equalTo("��άͼ��"));
		assertThat(record.getLatestPrice().doubleValue(),equalTo(0d));
		assertThat(record.getJrzljlr().doubleValue(),equalTo(Double.valueOf(0d)));
	}
	
	public void testToBigDecimal() {
		assertEquals(BigDecimal.valueOf(10.1),ZjlxDataParser.toBigDecimal("10.1"));
		assertEquals(105100,ZjlxDataParser.toBigDecimal("10.51��").doubleValue(),1 );
		assertEquals(5100,ZjlxDataParser.toBigDecimal("0.51��").doubleValue(),1 );
		assertEquals(10000,ZjlxDataParser.toBigDecimal("1��").doubleValue(),1 );
		assertEquals(95960000,ZjlxDataParser.toBigDecimal("9596��").doubleValue(),1 );
		assertEquals(95960000,ZjlxDataParser.toBigDecimal("9596��").doubleValue(),1 );
		assertEquals(100000000l,ZjlxDataParser.toBigDecimal("1��").doubleValue(),1 );
		assertEquals(-52540000l,ZjlxDataParser.toBigDecimal("-5254��").doubleValue(),1 );
	}

}
