package com.digitalharbor.eval.rest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author CHEYO
 *
 */
public class DateUtils {
	
	public static final String DATE_FORMAT = "MM/dd/yyyy" ;
	public static final String DATETIME_FORMAT = "MM/dd/yyyy HH:mm:ss" ;
	
	/**
	 * Valida el formato de fecha
	 * 
	 * @param fecha
	 * @return
	 */
	public static boolean esFechaValida(String fecha) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);
		try {
			Date valid = dateFormat.parse(fecha);
			return true ;
		} catch (ParseException e) {
			e.printStackTrace();
			return false ;
		}
		
	}
	
	
	/**
	 * Convierte de string a Date
	 * @param fecha
	 * @return
	 */
	public static Date convert(String fecha ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);
		Date valid;
		try {
			valid = dateFormat.parse(fecha);
			return valid ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date() ;
	}
	
	/**
	 * Convierte de Date a String 
	 * 
	 * @param fecha
	 * @return
	 */
	public static String convert (Date fecha) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);  
        return dateFormat.format(fecha);  
	}
	
	
	public static Date currentDate() {
		return java.util.Calendar.getInstance().getTime();  

	}

}
