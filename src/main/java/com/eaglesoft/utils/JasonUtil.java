package com.eaglesoft.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class JasonUtil {
	final static String AA1="{\"data\":[\"600739,1,1,�����ɴ�,15.44,3.90,9598��,13.72,7553��,10.80,2043��,2.92,-5208��,-7.44,-4388��,-6.27\",\"600787,1,1,�д��ɷ�,15.23,2.63,1972��,9.37,975��,4.64,997��,4.74,-1046��,-4.97,-926��,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
	public static String retrieveData(String addressOfData)
			throws UnsupportedEncodingException, MalformedURLException,
			IOException {
		return AA1;
		/*
		// build a URL
		String s = addressOfData;
		s += URLEncoder.encode("", "UTF-8");
		URL url = new URL(s);
		// url.
		System.out.println("starting: ");
		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		StringBuffer strBuffer = new StringBuffer();
		while (scan.hasNext())
			strBuffer.append(scan.nextLine());
		scan.close();

		String jasonData = strBuffer.substring(strBuffer.indexOf("{"));
		System.out.println("str2: " + jasonData);
		return jasonData; 
*/	}
}
