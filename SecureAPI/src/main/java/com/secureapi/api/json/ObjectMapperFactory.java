package com.secureapi.api.json;

import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperFactory {

	public static synchronized ObjectMapper getObjectMapper() {
		return configureObjectMapper(new ObjectMapper());
	}
	
	private static ObjectMapper configureObjectMapper(ObjectMapper objectMapper) {
		return objectMapper
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(getDateModule());
	}			
	
	private static SimpleModule getDateModule() {
		return new SimpleModule()
				.addSerializer(Date.class, new DateSerializer())
				.addDeserializer(Date.class, new DateDeserializer());
	}
	
}
