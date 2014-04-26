package com.eaglesoft.utils.http.proxy;

import java.net.Authenticator;
import java.net.Proxy;

public class HttpProxyUtil {
  
	public static void setupHttpProxy() {
		System.setProperty("http.proxyHost", "host");  
	   	System.setProperty("http.proxyPort", ""); 
	   	Authenticator.setDefault(new BasicAuthenticator("", ""));
	}
}
