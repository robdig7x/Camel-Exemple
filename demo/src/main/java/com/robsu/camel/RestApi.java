package com.robsu.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
class RestApi extends RouteBuilder {
	
    @Override
    public void configure() {
    	
    	restConfiguration()
			.component("servlet")
			.bindingMode(RestBindingMode.json);
	
			rest("/student").produces("application/json")
				.get("/hello/{name}")
				.route().transform().simple("Hello ${header.name}, Welcome to JavaOutOfBounds.com")
			.endRest()
				.get("/records/{name}").to("direct:records");
	
			from("direct:records")
			.process(new RecordsProcessor());
    	
    }
}