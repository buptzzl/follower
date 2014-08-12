package com.eaglesoft.stock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;

import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.parser.StockCategoryParser;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.utils.date.DateUtil;

public class TestDataFactory {
        final static String TEST_DATA1 = "{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
        final static String TEST_DATA2 = "{\"data\":[\"600383,1,1,金地集团,11.22,8.30,1.51亿,6.34,3.24亿,11.45,-1.73亿,-7.11,-5641万,-1.99,-9590万,-3.35\",\"000402,2,2,金融街,5.76,6.67,1.36亿,12.73,1.99亿,18.67,-6338万,-5.94,-6453万,-6.04,-7137万,-6.69\",\"600036,1,1,招商银行,10.07,0.80,6416万,8.69,4206万,5.70,2210万,2.99,-3971万,-5.38,-2445万,-3.31\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"2627\",\"exTime\":\"21.3813\",\"update\":\"2014-04-27 21:01:04\",\"datatime\":\"2014-04-25\"};";
        final static String TEST_DATA3 = "{\"data\":[\"600383,1,1,金地集团,10.22,9.30,1.51亿,5.34,3.24亿,11.45,-1.73亿,-6.11,-5641万,-1.99,-9490万,-12.35\",\"000402,2,2,金融街,5.76,6.67,1.36亿,12.73,1.99亿,18.67,-6338万,-5.94,-6453万,-6.04,-7137万,-16.69\",\"600036,1,1,招商银行,10.07,0.80,6416万,8.69,4206万,5.70,2210万,2.99,-3971万,-5.38,-2445万,-10.31\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"2627\",\"exTime\":\"21.3813\",\"update\":\"2014-04-27 21:01:04\",\"datatime\":\"2014-04-25\"};";
        final static String TEST_DATA_STOCK_CATEGORY = "{\"data\":[\"600383,1,1,金地集团,10.22,9.30,1.51亿,5.34,3.24亿,11.45,-1.73亿,-6.11,-5641万,-1.99,-9490万,-12.35\",\"000402,2,2,金融街,5.76,6.67,1.36亿,12.73,1.99亿,18.67,-6338万,-5.94,-6453万,-6.04,-7137万,-16.69\",\"600036,1,1,招商银行,10.07,0.80,6416万,8.69,4206万,5.70,2210万,2.99,-3971万,-5.38,-2445万,-10.31\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"2627\",\"exTime\":\"21.3813\",\"update\":\"2014-04-27 21:01:04\",\"datatime\":\"2014-04-25\"};";
    public static List<ZjlxStockRuntime> getZjlxStockRuntimeTestData1() throws Exception{
        ZjlxDataParser parser = new ZjlxDataParser();
        ZjlxAggregator<ZjlxStockRuntime> aggregator = parser.parse(TEST_DATA1);
        List<ZjlxStockRuntime>  list= aggregator.getData();
        return list;
        
    }
    
    public static List<ZjlxStockRuntime> getZjlxStockRuntimeTestData2() throws Exception{
        ZjlxDataParser parser = new ZjlxDataParser();
        ZjlxAggregator<ZjlxStockRuntime> aggregator = parser.parse(TEST_DATA2,DateUtil.DF_YYYY_MM_DD.parse("2014-04-16"));
        List<ZjlxStockRuntime>  list= aggregator.getData();
        return list;
    }

    public static List<ZjlxStockRuntime> getZjlxStockRuntimeTestData3() throws Exception{
        ZjlxDataParser parser = new ZjlxDataParser();
        ZjlxAggregator<ZjlxStockRuntime> aggregator = parser.parse(TEST_DATA3,DateUtil.DF_YYYY_MM_DD.parse("2014-04-27"));
        List<ZjlxStockRuntime>  list= aggregator.getData();
        return list;
    }

    public static List<ZjlxStockRuntime> getStockCategory() throws Exception{
        StockCategoryParser parser = new StockCategoryParser();
        parser.setType("3");
        ZjlxAggregator<ZjlxStockRuntime> aggregator = parser.parse(TEST_DATA_STOCK_CATEGORY,DateUtil.DF_YYYY_MM_DD.parse("2014-04-27"));
        List<ZjlxStockRuntime>  list= aggregator.getData();
        return list;
    }
    
}
