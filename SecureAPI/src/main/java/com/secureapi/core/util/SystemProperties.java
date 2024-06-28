package com.secureapi.core.util;

public class SystemProperties {

	public static final String JWT_PRIVATE_KEY = "JWTPrivateKey";
	public static final String JWT_TOKEN_EXPIRATION_TIME_MINUTES = "JWTTokenExpirationTimeMinutes";
	
	public static Long getJwtTokenExpirationTimeMinutes() {
		return Long.getLong(JWT_TOKEN_EXPIRATION_TIME_MINUTES, 60);
	}
	
	public static String getJWTPrivateKey() {
		String jwtPrivateKey = System.getProperty(JWT_PRIVATE_KEY, "batman-batman-batman");
		validateRequiredPropertyValue(jwtPrivateKey, JWT_PRIVATE_KEY);
		return jwtPrivateKey;
	}
	
	private static void validateRequiredPropertyValue(String value, String propertyName) {
		if(value == null || value.isBlank()) {
			throw new RuntimeException("System Property " +  propertyName + " not found");
		}
	}

}
