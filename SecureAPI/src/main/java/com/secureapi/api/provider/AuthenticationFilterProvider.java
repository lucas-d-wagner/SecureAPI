package com.secureapi.api.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.secureapi.api.context.LoginContext;
import com.secureapi.api.jwt.ContextRequestCache;
import com.secureapi.api.jwt.JWT;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilterProvider implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(IsIgnoreAuthentication()) return;
		String tokenAuthorizationHeader = getTokenAuthorizationHeader(requestContext);
		validateToken(tokenAuthorizationHeader);
	}

	private String getTokenAuthorizationHeader(ContainerRequestContext requestContext) {
		String tokenHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		return Objects.toString(tokenHeader, "");
	}

	private void validateToken(String token) {
		if(invalidAuthorizationHeader(token)) {
			throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED));
		}
		try {
			token = prepareToken(token);
			LoginContext loginContext = JWT.getLoginContext(token);
			startContextRequestCache(loginContext);
		} catch (Exception e) {
			throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED));
		}
	}
	
	private void startContextRequestCache(LoginContext loginContext) {
		ContextRequestCache.get().startCache(loginContext);
	}
	
	private boolean IsIgnoreAuthentication() {
		Method resourceMethod = resourceInfo.getResourceMethod();
		return resourceMethod.isAnnotationPresent(PermitAll.class);
	}
	
	private String prepareToken(String token) {
		return token.replace("Bearer ", "");
	}
	
	private boolean invalidAuthorizationHeader(String token) {
		return !token.startsWith("Bearer ");
	}
}