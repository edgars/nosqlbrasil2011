package br.com.redhat.infinispan.sample.rest;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;

public class RestApp extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public RestApp(){
	     singletons.add(new Command());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
