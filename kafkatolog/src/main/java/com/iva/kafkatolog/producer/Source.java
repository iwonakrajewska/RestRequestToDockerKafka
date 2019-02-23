package com.iva.kafkatolog.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {
	
	@Output("input-source")
	MessageChannel sampleSource();
	
}
