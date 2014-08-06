package com.eaglesoft.stock.analyzer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import com.eaglesoft.stock.core.common.dao.impl.ZjlxOfStockDao;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntimeGroup;
import com.eaglesoft.utils.date.DateUtil;

public class TopXDeltaAnalyzer {
    
    private static final Logger logger = LogManager.getLogger(TopXDeltaAnalyzer.class);
    
    private ZjlxOfStockDao dao;
    
    public ZjlxOfStockDao getDao() {
		return dao;
	}

	public void setDao(ZjlxOfStockDao dao) {
		this.dao = dao;
	}

	/*public  void analyze(Date startTime, Date endTime, String sortedByField) throws IOException{
	    
        List<ZjlxStockRuntime> list = findData(startTime,endTime);
        Map<String,ZjlxStockRuntimeGroup>  zjlxRuntimeGroups = group(list);
        caculateDelta(zjlxRuntimeGroups);
        List<ZjlxStockRuntimeGroup> topNGroup = takeTopN(sort(zjlxRuntimeGroups, ZjlxStockRuntimeGroup.Field.valueOf(sortedByField) ),10);
        generateTopXReport(topNGroup,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
    }
*/
	
	   public  void analyze(Date startTime, Date endTime, String sortedByFields) throws IOException{
	        
	        List<ZjlxStockRuntime> list = findData(startTime,endTime);
	        Map<String,ZjlxStockRuntimeGroup>  zjlxRuntimeGroups = group(list);
	        caculateDelta(zjlxRuntimeGroups);
	        
	        for (String sortedByField : sortedByFields.split(",")) {
	            List<ZjlxStockRuntimeGroup> topNGroup = takeTopN(sort(zjlxRuntimeGroups, ZjlxStockRuntimeGroup.Field.valueOf(sortedByField) ),200);
	            generateTopXReport(topNGroup,ZjlxStockRuntimeGroup.Field.valueOf(sortedByField));
	        }
	        
	    }

    

    public List<ZjlxStockRuntimeGroup> takeTopN(List<ZjlxStockRuntimeGroup> groups, int n) {
        if(n<groups.size()){
            return  groups.subList(0, n);
        } else {
            return groups;
        }
    }

  
    public List<ZjlxStockRuntimeGroup> sort( Map<String,ZjlxStockRuntimeGroup> groups, ZjlxStockRuntimeGroup.Field field) {
        
        
        ArrayList<ZjlxStockRuntimeGroup> arrayOfGroups = new  ArrayList<ZjlxStockRuntimeGroup> (groups.values());
       //deltaRateOfJrxdjlr
        Collections.sort(arrayOfGroups, new ZjlxStockRuntimeGroup.BigDecimalFieldComparator(field.getName(),field.isSortAscending()));
        
        return arrayOfGroups;
        
    }

    public void caculateDelta( Map<String,ZjlxStockRuntimeGroup> groups) {
        
        for(ZjlxStockRuntimeGroup group:groups.values() ) {
            
            List<ZjlxStockRuntime> list = group.getZjlxStockRuntimes();
            Collections.sort(list, new Comparator<ZjlxStockRuntime>() {
                @Override
                public int compare(ZjlxStockRuntime o1, ZjlxStockRuntime o2) {
                    return o1.getExtractTime().compareTo(o2.getExtractTime());
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
            group.setDeltaVolumn(last.getVolumn().subtract(first.getVolumn()));
            if(!BigDecimal.ZERO.equals(group.getDeltaVolumn())) {
                group.setDeltaJrzdfPerYi(group.getDeltaJrzdf().movePointRight(8).divide(group.getDeltaVolumn(),9, BigDecimal.ROUND_HALF_EVEN));    
            }else {
                group.setDeltaJrzdfPerYi(BigDecimal.ZERO);
            }
            
            
        }
        
    }

    @SuppressWarnings("unchecked")
	public <T> List<T> findData(Date startTime, Date  endTime) {
        String hsqlQuery = "from ZjlxStockRuntime as runtime where runtime.extractTime >=? and runtime.extractTime <=? ";
        return (List<T>) dao.findByQueryString(hsqlQuery, startTime,endTime);
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

    public void generateTopXReport(List<ZjlxStockRuntimeGroup> topNGroup, com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntimeGroup.Field field)  throws IOException{
        String deltaField = field.getName().replaceAll("delta", "");
                
        String suffix = DateUtil.DF_YYYY_MM_DD_HH_MI_SS2.format(new Date());
        File file = new File("C:\\xxx\\dev\\stock\\analyzer\\"+field.getName()+"\\stock_"+field.getName()+suffix+".csv");
        
        PrintWriter sw =null;
        Writer out = null;
        try {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            logger.info(file.getName());
            file.createNewFile();
        }
        out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
        sw = new PrintWriter(out);
            Method method = ReflectionUtils.findMethod(ZjlxStockRuntime.class,"get"+deltaField.substring(0, 1).toUpperCase() + deltaField.substring(1));
            sw.println(topNGroup.get(0).formatTitle());
            for (ZjlxStockRuntimeGroup group:topNGroup ){
               
                sw.println(group.describle(method));
            }
            sw.flush();
        }
        catch(IOException exp){
            exp.printStackTrace();
        }
        finally {
          sw.close();
          out.close();
          
          
        }
        
    }
}
