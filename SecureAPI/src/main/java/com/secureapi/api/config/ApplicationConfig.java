package com.secureapi.api.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.reflections.Reflections;

import com.secureapi.api.provider.AuthenticationFilterProvider;
import com.secureapi.api.provider.ExceptionMapperProvider;
import com.secureapi.api.provider.ObjectMapperProvider;
import com.secureapi.core.rest.AbstractResource;

@ApplicationPath("v1")
public class ApplicationConfig extends Application {

	private static final String RESOURCES_PACKAGE = "com.secureapi.api.resource";

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		loadResourcesClass(resources, RESOURCES_PACKAGE);
		loadProvidersClass(resources);
		return resources;
	}
	
	private void loadResourcesClass(Set<Class<?>> resourcesClass, String packageName) {
		Set<Class<? extends AbstractResource>> resources = new Reflections(packageName).getSubTypesOf(AbstractResource.class);
		resourcesClass.addAll(resources);
	}
	
	private void loadProvidersClass(Set<Class<?>> resourcesClass) {
		resourcesClass.add(ObjectMapperProvider.class);
		resourcesClass.add(ExceptionMapperProvider.class);
		resourcesClass.add(AuthenticationFilterProvider.class);
	}

}
