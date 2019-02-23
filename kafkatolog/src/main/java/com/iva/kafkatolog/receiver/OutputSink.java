package com.iva.kafkatolog.receiver;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;


@EnableBinding(Sink.class)
@Slf4j
public class OutputSink {

	@StreamListener("output-sink")
	public void receive(Message<String> m) {
		log.info("Data received, payload:  {} , headers: {}", m.getPayload(), m.getHeaders());
	}
	
}