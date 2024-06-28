package com.secureapi.core.rest;

import com.secureapi.api.context.LoginContext;
import com.secureapi.api.jwt.ContextRequestCache;

public abstract class AbstractResource {

	public LoginContext getLoginContext() {
		return ContextRequestCache.get().getLoginContext();
	}
	
}