package com.eaglesoft.stock;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.impl.StdScheduler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eaglesoft.stock.dispatcher.StockEventDispatcher;
import com.eaglesoft.stock.task.ZjlxStockRuntimeTask;
import com.eaglesoft.utils.date.DateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	
    private static final Logger logger = LogManager.getLogger(App.class);
    
    private boolean done =false;
	public  StockEventDispatcher dispatcher;
	public static App instance;
	protected List synchedList;
	protected ClassPathXmlApplicationContext applicationContect;

    public static void main( String[] args ) throws IOException
    {
        logger.info("starting app");
        init();
        logger.info("app is up");
       
            try {
                logger.info("shuting down quartz scheduler");
                while(!instance.isDone()){
                    try {
                        synchronized (instance.synchedList) {
                            ;
                            logger.info(DateUtil.DF_YYYY_MM_DD_HH_MI_SS.format(new Date())+":waiting synchedList");
                            instance.synchedList.wait(60000l);
                        
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
               
                }
            }
            finally {
                StdScheduler scheduler = (StdScheduler) instance.applicationContect.getBean("scheduler");
                logger.info("shuting down quartz scheduler");
                if (scheduler.isStarted()) {
                    scheduler.shutdown(true);
                }
            }
        
    }
    
	public  StockEventDispatcher getDispatcher() {
		return dispatcher;
	}
	public  void setDispatcher(StockEventDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	public static App getInstance() {
		if (instance ==null)
		{
			try {
                init();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
		return instance;
	}

	public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void init() throws IOException {
        logger.info("initializing app");
		
		App app = new App();
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext( "META-INF/spring/appConfig.xml");
		app.applicationContect = applicationContext;
		app.setDispatcher((StockEventDispatcher) applicationContext.getBean("dispatcher"));
		app.synchedList = Collections.synchronizedList(new LinkedList());
		instance  = app;
		//new Thread(new EchoServer()).start(); destroy-method="destroy"

	}
	
	protected void finalize() throws Throwable {
	    logger.info("destroying app");
	}	

}
