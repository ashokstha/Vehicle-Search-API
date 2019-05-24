package io.vehicle.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import io.vehicle.rest.resource.VehicleResource;

@Configuration
public class RestConfig extends ResourceConfig{
	
	public RestConfig() {
		this.register(VehicleResource.class);
		//this.register("com.cubic.rest.resource.providers");
	}

	
}
