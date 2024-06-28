package com.secureapi.api.provider;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secureapi.api.json.ObjectMapperFactory;

public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return ObjectMapperFactory.getObjectMapper();
	}
	
}
