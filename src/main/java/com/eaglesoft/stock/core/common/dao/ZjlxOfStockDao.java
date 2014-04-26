package com.eaglesoft.stock.core.common.dao;

import java.util.List;

import org.hibernate.Session;

import com.eaglesoft.utils.hibernate.HibernateUtil;

public class ZjlxOfStockDao {

	public <T> void  save( List<T> zjlxStockRuntimes) {
		
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        int i=0;
        for( T record :zjlxStockRuntimes) {
    		session.save(record);
    		i++;
    		if(i%100==0)  
    	    {
    	        session.flush();  
    	        session.clear();  
    	    }
        }
        session.getTransaction().commit();
	}
}
