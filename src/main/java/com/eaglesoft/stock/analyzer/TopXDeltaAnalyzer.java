package com.eaglesoft.stock.analyzer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eaglesoft.stock.core.common.dao.impl.ZjlxOfStockDao;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntimeGroup;
import com.eaglesoft.utils.date.DateUtil;

public class TopXDeltaAnalyzer {
    private ZjlxOfStockDao dao;
    
    public ZjlxOfStockDao getDao() {
		return dao;
	}

	public void setDao(ZjlxOfStockDao dao) {
		this.dao = dao;
	}

	public  void analyze(){
        List<ZjlxStockRuntime> list = findData();
        Map<String,ZjlxStockRuntimeGroup>  zjlxRuntimeGroups = group(list);
        caculateDelta(zjlxRuntimeGroups);
        sort(zjlxRuntimeGroups);
        takeTopN(sort(zjlxRuntimeGroups),10);
    }

    public List<ZjlxStockRuntimeGroup> takeTopN(List<ZjlxStockRuntimeGroup> groups, int n) {
       return  groups.subList(0, n);
    }

    public List<ZjlxStockRuntimeGroup> sort( Map<String,ZjlxStockRuntimeGroup> groups) {
       
       
        ArrayList<ZjlxStockRuntimeGroup> arrayOfGroups = new  ArrayList<ZjlxStockRuntimeGroup> (groups.values());
        Collections.sort(arrayOfGroups,  new Comparator<ZjlxStockRuntimeGroup>() {
                @Override
                public int compare(ZjlxStockRuntimeGroup o1, ZjlxStockRuntimeGroup o2) {
                    return o1.getDeltaRateOfJrxdjlr().compareTo(o2.getDeltaRateOfJrxdjlr());
                }
            } );
        
        return arrayOfGroups;
        
    }

    public void caculateDelta( Map<String,ZjlxStockRuntimeGroup> groups) {
        
        for(ZjlxStockRuntimeGroup group:groups.values() ) {
            
            List<ZjlxStockRuntime> list = group.getZjlxStockRuntimes();
            Collections.sort(list, new Comparator<ZjlxStockRuntime>() {
                @Override
                public int compare(ZjlxStockRuntime o1, ZjlxStockRuntime o2) {
                    return o2.getExtractTime().compareTo(o1.getExtractTime());
                }
            } );
           
            ZjlxStockRuntime first =  list.get(0);
            ZjlxStockRuntime last =  list.get(list.size()-1);
            
            group.setDeltaLatestPrice(last.getLatestPrice().subtract(first.getLatestPrice()));
            group.setDeltaJrzdf(last.getJrzdf().subtract(first.getJrzdf()));
            group.setDeltaJrzljlr(last.getJrzljlr().subtract(first.getJrzljlr()));
            group.setDeltaRateOfJrzljlr(last.getRateOfJrzljlr().subtract(first.getRateOfJrzljlr()));
            group.setDeltaJrcddjlr(last.getJrcddjlr().subtract(first.getJrcddjlr()));
            group.setDeltaRateOfJrcddjlr(last.getRateOfJrcddjlr().subtract(first.getRateOfJrcddjlr()));
            group.setDeltaJrddjlr(last.getJrddjlr().subtract(first.getJrddjlr()));
            group.setDeltaRateOfJrddjlr(last.getRateOfJrddjlr().subtract(first.getRateOfJrddjlr()));
            group.setDeltaJrzdjlr(last.getJrzdjlr().subtract(first.getJrzdjlr()));
            group.setDeltaRateOfJrzdjlr(last.getRateOfJrzdjlr().subtract(first.getRateOfJrzdjlr()));
            group.setDeltaJrxdjlr(last.getJrxdjlr().subtract(first.getJrxdjlr()));
            group.setDeltaRateOfJrxdjlr(last.getRateOfJrxdjlr().subtract(first.getRateOfJrxdjlr()));
            
        }
        
    }

    @SuppressWarnings("unchecked")
	public <T> List<T> findData() {
    	
    	String hsqlQuery = "from ZjlxStockRuntime as runtime where runtime.code=? ";
        return (List<T>) dao.findByQueryString(hsqlQuery, "600548");
        //
    }
    
    @SuppressWarnings("unchecked")
	public <T> List<T> findData2() {
    	
    	String hsqlQuery = "from ZjlxStockRuntime as runtime where runtime.extractTime >=? and runtime.extractTime <=? ";
    	Date startDate = null;
    	Date endDate = null;
    	try {
			startDate = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-04-27 08:00:00");
			endDate = DateUtil.DF_YYYY_MM_DD_HH_MI_SS.parse("2014-04-27 10:00:00");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

        return (List<T>) dao.findByQueryString(hsqlQuery, startDate,endDate);
    }
    
    
    public Map<String,ZjlxStockRuntimeGroup> group(List<ZjlxStockRuntime> list){
        
        Map<String,ZjlxStockRuntimeGroup> groups = new HashMap<String,ZjlxStockRuntimeGroup>();
        
        for(ZjlxStockRuntime record: list){
            ZjlxStockRuntimeGroup group = groups.get(record.getCode());
            if (group==null){
                group = new ZjlxStockRuntimeGroup();
                group.setCode(record.getCode());
                group.setName(record.getName());
                groups.put(record.getCode(), group);
            }
                   
            group.getZjlxStockRuntimes().add(record);
            
        }
        
        return groups;
    }
}
