package com.eaglesoft.stock;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.eaglesoft.stock.core.monitor.entity.ZjlxStockRuntime;
import com.eaglesoft.stock.parser.ZjlxDataParser;

/**
 * Hello world!
 *
 */
public class App 
{
	final static String AA1="{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9596万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
	
    public static void main( String[] args )
    {
        //testString();
    	try {
			//testRetrieveFromUrl();
    		jasonToObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private static void jasonToObject() throws Exception {

		
        String str = AA1;
        JSONObject obj;
		try {
			obj = new JSONObject(str);
			JSONArray stockList = obj.getJSONArray("data");
			for (int i = 0; i < stockList.length(); i++){
				System.out.println(stockList.getString(i));
				ZjlxStockRuntime zjlx= ZjlxDataParser.parseAsRecord(stockList.getString(i));
				System.out.println(zjlx);
			}
			    
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
    }
    
    private static void testRetrieveFromUrl() throws Exception {
    	// build a URL
        String s = "http://maps.google.com/maps/api/geocode/json?" +
                        "sensor=false&address=NewYork";
        s += URLEncoder.encode("", "UTF-8");
        URL url = new URL(s);
     
        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();
        System.out.println("lat: " +str);
        // build a JSON object
        JSONObject obj = new JSONObject(str);
        if (! obj.getString("status").equals("OK"))
            return;
     
        // get the first result
        JSONObject res = obj.getJSONArray("results").getJSONObject(0);
        System.out.println(res.getString("formatted_address"));
        JSONObject loc =
            res.getJSONObject("geometry").getJSONObject("location");
        System.out.println("lat: " + loc.getDouble("lat") +
                            ", lng: " + loc.getDouble("lng"));
    }
    
	private static void testString() {
		System.out.println( "Hello World!" );
        String str = "{ \"name\": \"Alice\", \"age\": 20 }";
        JSONObject obj;
		try {
			obj = new JSONObject(str);
			String n = obj.getString("name");
	        int a = obj.getInt("age");
	        System.out.println(n + " " + a);  // prints "Alice 20"
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
