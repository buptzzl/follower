package com.eaglesoft.stock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.parser.ZjlxDataParser;

public class TestDataFactory {
    final static String TEST_DATA1 = "{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
    
    
    public static List<ZjlxStockRuntime> getZjlxStockRuntimeTestData1() throws Exception{
        ZjlxDataParser parser = new ZjlxDataParser();
        ZjlxAggregator<ZjlxStockRuntime> aggregator = parser.parse(TEST_DATA1);
        List<ZjlxStockRuntime>  list= aggregator.getData();
        return list;
        
    }

}
