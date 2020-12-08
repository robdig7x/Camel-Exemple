package com.robsu.camel;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RecordsProcessor implements Processor {

	final AtomicLong counter = new AtomicLong();

	@Override
	public void process(Exchange exchange) throws Exception {
		final String name = exchange.getIn().getHeader("name",String.class);
		exchange.getIn().setBody(new Student(counter.incrementAndGet(),name,"Camel + SpringBoot"));
	}

}
