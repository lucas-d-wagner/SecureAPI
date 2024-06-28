package com.secureapi.api.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.secureapi.business.exception.BusinessException;

public class JsonDateFormat {

	private static final String PRIMARY_DATEFORMAT = "dd/MM/yyyy HH:mm:ss"; 
	private static final String SECONDARY_DATEFORMAT = "dd/MM/yyyy";

	public static Date parse(String date) {
		try {
			return tryParse(date);
		} catch (Throwable e) {
			throw new BusinessException("Formato de data inválido, use " + PRIMARY_DATEFORMAT + " ou " + SECONDARY_DATEFORMAT, e);
		}
	}
	
	private static Date tryParse(String date) throws Exception {
		try {
			return new SimpleDateFormat(PRIMARY_DATEFORMAT).parse(date);
		} catch (ParseException parseException) {
			return new SimpleDateFormat(SECONDARY_DATEFORMAT).parse(date);
		}
	}
	
	public static String format(Date date) {
		return new SimpleDateFormat(PRIMARY_DATEFORMAT).format(date);
	}
	
}
