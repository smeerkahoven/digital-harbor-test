package com.digitalharbor.eval.rest.security;

import com.digitalharbor.eval.rest.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000 ;// 10 dias
	public static final String TOKEN_PREFIX = "Bearer " ;
	public static final String HEADER_AUTH = "Authorization" ;
	public static final String SIGN_UP_URL = "/login" ;
	public static final String TOKEN_SECRET = "d1g1t4lh4rb0r";
	
	
	public static String  getTokenSecret() {
		AppProperties appProperties = (AppProperties)SpringApplicationContext.getBean("AppProperties") ;
		
		return appProperties.getTokenSecret() ;
	}
	
}
