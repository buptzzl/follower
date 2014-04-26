package com.eaglesoft.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class JasonUtil {
	final static String AA1="{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9598万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";
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
