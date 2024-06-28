package com.secureapi.api.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.secureapi.api.context.LoginContext;
import com.secureapi.api.dto.TokenDTO;
import com.secureapi.api.json.ObjectMapperFactory;
import com.secureapi.core.util.SystemProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWT {

	public static <T> TokenDTO buildTokenDTO(T payload) {
		try {
			Date dateExpiration = getDateExpiration();
			String jwtToken = getJWTToken(payload, dateExpiration);
	        return new TokenDTO(jwtToken);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized Jws<Claims> validateToken(String token) {
		try {
			return Jwts.parser().setSigningKey(getJWTPrivateKey()).parseClaimsJws(token);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public static synchronized <T> T convertToken(Jws<Claims> claims, Class<T> clazz) {
		try {
			String payload = claims.getBody().getSubject();
			return ObjectMapperFactory.getObjectMapper().readValue(payload, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
    public static LoginContext getLoginContext(String token) {
    	Jws<Claims> claims = validateToken(token);
    	return convertToken(claims, LoginContext.class);
	}
	
    private static Date getDateExpiration() {
    	Long jwtTokenExpirationTimeMinutes = SystemProperties.getJwtTokenExpirationTimeMinutes();
    	LocalDateTime currentDatePlusMinutes = LocalDateTime.now().plusMinutes(jwtTokenExpirationTimeMinutes);
    	return Date.from(currentDatePlusMinutes.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	private static <T> String getJWTToken(T payload, Date dateExpiration) throws JsonProcessingException {
		return Jwts.builder()
		        .setSubject(buildPayload(payload))
		        .signWith(SignatureAlgorithm.HS512, getJWTPrivateKey())
		        .setExpiration(dateExpiration)
		        .compact();
	}
	
	private static <T> String buildPayload(T payload) throws JsonProcessingException {
		return ObjectMapperFactory.getObjectMapper().writeValueAsString(payload);
	}
	
	private static String getJWTPrivateKey() {
		return SystemProperties.getJWTPrivateKey();
	}

	
}
