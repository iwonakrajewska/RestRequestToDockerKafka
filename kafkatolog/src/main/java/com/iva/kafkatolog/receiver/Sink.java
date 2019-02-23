package com.iva.kafkatolog.receiver;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {
	
	@Input("output-sink")
	SubscribableChannel sampleSink();
	
}