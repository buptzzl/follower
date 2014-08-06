package com.eaglesoft.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.eaglesoft.stock.domainObjects.ZjlxAggregator;
import com.eaglesoft.stock.parser.ZjlxDataParser;
import com.eaglesoft.utils.http.proxy.HttpProxyUtil;

public class JasonUtil {

	private static final Logger logger = LogManager.getLogger(JasonUtil.class);

	final static String AA1 = "{\"data\":[\"600739,1,1,辽宁成大,15.44,3.90,9598万,13.72,7553万,10.80,2043万,2.92,-5208万,-7.44,-4388万,-6.27\",\"600787,1,1,中储股份,15.23,2.63,1972万,9.37,975万,4.64,997万,4.74,-1046万,-4.97,-926万,-4.40\"],\"page\":\"1\",\"pages\":\"53\",\"pageSize\":\"50\",\"count\":\"1\",\"exTime\":\"0.0374\",\"update\":\"2014-04-20 10:57:32\",\"datatime\":\"2014-04-18\"};";

	public static String retrieveData(String addressOfData)
			throws UnsupportedEncodingException, MalformedURLException,
			IOException {
		// return AA1;

		// build a URL
		return openPage(addressOfData);
	}

	private static String openPage(String addressOfData)
			throws UnsupportedEncodingException, MalformedURLException,
			IOException {
		String s = addressOfData;
		s += URLEncoder.encode("", "UTF-8");
		HttpProxyUtil.setupHttpProxy();
		URL url = new URL(s);
		// url.
		logger.info("starting: ");
		// read from the URL
		Scanner scan = new Scanner(url.openStream(), "UTF-8");
		StringBuffer strBuffer = new StringBuffer();
		while (scan.hasNext())
			strBuffer.append(scan.nextLine());
		scan.close();

		//String jasonData = strBuffer.substring(strBuffer.indexOf("{"));
		String jasonData = extractContent(strBuffer);
		logger.info("Json Data total size: " + jasonData.length());
		return jasonData;
	}

	private static String extractContent(StringBuffer sb) {
		Pattern p = Pattern.compile("\\[(.+)\\]");
		Matcher m = p.matcher(sb.toString());
		if(m.find()) {
			return "{\"data\":"+m.group()+"}";
		} else {
			return null;
		}
	}

}
