package com.secureapi.api.jwt;

import com.secureapi.api.context.LoginContext;

public class ContextRequestCache {

	private LoginContext loginContext;
	
	private static ThreadLocal<ContextRequestCache> requestThreadLocal = new ThreadLocal<ContextRequestCache>() {
		protected ContextRequestCache initialValue() {
			return new ContextRequestCache();
		}
	};
	
	private ContextRequestCache() {}
	
	public static ContextRequestCache get() {
		return requestThreadLocal.get();
	}
	
	public void startCache(LoginContext loginSession) {
		this.loginContext = loginSession;
	}
	
	public LoginContext getLoginContext() {
		return this.loginContext;
	}

}
