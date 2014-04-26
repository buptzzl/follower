package com.eaglesoft.stock;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.eaglesoft.socket.server.EchoServer;
import com.eaglesoft.socket.server.ServerSocketClient;
import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.dispatcher.StockEventDispatcher;
import com.eaglesoft.stock.event.ZjlxStockEodEvent;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.utils.http.proxy.BasicAuthenticator;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public  StockEventDispatcher dispatcher;
	public static App instance;
    public static void main( String[] args ) throws IOException
    {
    	System.out.println("starting app");
    	init();
    	System.out.println("app is up");
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

	private static void init() throws IOException {
		System.out.println("initializing app");
		App app = new App();
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext( "META-INF/spring/appConfig.xml");
		app.setDispatcher((StockEventDispatcher) applicationContext.getBean("dispatcher"));
		instance  = app;
		new Thread(new EchoServer()).start();

	}
	

}
