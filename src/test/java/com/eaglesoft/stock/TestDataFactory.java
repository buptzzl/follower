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
        final static String TEST_DATA1 = "{\"data\":[\"600739,1,1,�����ɴ�,15.44,3.90,9596��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27\",\"600787,1,1,�д��ɷ�,15.23,2.63,1972��,9.37,975��,4.64,997��,4.74,-1046��,-4.97,-926��,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
        final static String TEST_DATA2 = "{\"data\":[\"600383,1,1,��ؼ���,11.22,8.30,1.51��,6.34,3.24��,11.45,-1.73��,-7.11,-5641��,-1.99,-9590��,-3.35\",\"000402,2,2,���ڽ�,5.76,6.67,1.36��,12.73,1.99��,18.67,-6338��,-5.94,-6453��,-6.04,-7137��,-6.69\",\"600036,1,1,��������,10.07,0.80,6416��,8.69,4206��,5.70,2210��,2.99,-3971��,-5.38,-2445��,-3.31\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"2627\",\"exTime\":\"21.3813\",\"update\":\"2014-04-27 21:01:04\",\"datatime\":\"2014-04-25\"};";
        final static String TEST_DATA3 = "{\"data\":[\"600383,1,1,��ؼ���,10.22,9.30,1.51��,5.34,3.24��,11.45,-1.73��,-6.11,-5641��,-1.99,-9490��,-12.35\",\"000402,2,2,���ڽ�,5.76,6.67,1.36��,12.73,1.99��,18.67,-6338��,-5.94,-6453��,-6.04,-7137��,-16.69\",\"600036,1,1,��������,10.07,0.80,6416��,8.69,4206��,5.70,2210��,2.99,-3971��,-5.38,-2445��,-10.31\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"2627\",\"exTime\":\"21.3813\",\"update\":\"2014-04-27 21:01:04\",\"datatime\":\"2014-04-25\"};";
        final static String TEST_DATA_STOCK_CATEGORY = "{\"data\":[\"600383,1,1,��ؼ���,10.22,9.30,1.51��,5.34,3.24��,11.45,-1.73��,-6.11,-5641��,-1.99,-9490��,-12.35\",\"000402,2,2,���ڽ�,5.76,6.67,1.36��,12.73,1.99��,18.67,-6338��,-5.94,-6453��,-6.04,-7137��,-16.69\",\"600036,1,1,��������,10.07,0.80,6416��,8.69,4206��,5.70,2210��,2.99,-3971��,-5.38,-2445��,-10.31\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"2627\",\"exTime\":\"21.3813\",\"update\":\"2014-04-27 21:01:04\",\"datatime\":\"2014-04-25\"};";
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
