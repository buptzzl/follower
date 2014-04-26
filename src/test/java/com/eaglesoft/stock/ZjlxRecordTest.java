package com.eaglesoft.stock;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class ZjlxRecordTest extends TestCase {
	
	final static String AA1="{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
	final static String BB1="600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27";
	final static String EXAMPLE_TING_PAI="002405,2,4,四维图新,-,0.00,0,0.00,0,0.00,0,0.00,0,0.00,0,0.00";
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testParse() {
		ZjlxStockRuntime record = new ZjlxDataParser().parseAsRecord(BB1);
		assertThat(record.getCode(),equalTo("600739"));
		assertThat(record.getName(),equalTo("辽宁成大"));
		assertThat(record.getLatestPrice().doubleValue(),equalTo(15.44));
		assertThat(record.getJrzljlr().doubleValue(),equalTo(Double.valueOf(95960000l)));
	}

	public void testParseTheDataForTingPai() {
		ZjlxStockRuntime record = new ZjlxDataParser().parseAsRecord(EXAMPLE_TING_PAI);
		assertThat(record.getCode(),equalTo("002405"));
		assertThat(record.getName(),equalTo("四维图新"));
		assertThat(record.getLatestPrice().doubleValue(),equalTo(0d));
		assertThat(record.getJrzljlr().doubleValue(),equalTo(Double.valueOf(0d)));
	}
	
	public void testToBigDecimal() {
		assertEquals(BigDecimal.valueOf(10.1),ZjlxDataParser.toBigDecimal("10.1"));
		assertEquals(105100,ZjlxDataParser.toBigDecimal("10.51万").doubleValue(),1 );
		assertEquals(5100,ZjlxDataParser.toBigDecimal("0.51万").doubleValue(),1 );
		assertEquals(10000,ZjlxDataParser.toBigDecimal("1万").doubleValue(),1 );
		assertEquals(95960000,ZjlxDataParser.toBigDecimal("9596万").doubleValue(),1 );
		assertEquals(95960000,ZjlxDataParser.toBigDecimal("9596万").doubleValue(),1 );
		assertEquals(100000000l,ZjlxDataParser.toBigDecimal("1亿").doubleValue(),1 );
		assertEquals(-52540000l,ZjlxDataParser.toBigDecimal("-5254万").doubleValue(),1 );
	}

}
