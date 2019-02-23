package com.iva.kafkatolog.producer;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;


@EnableBinding(Source.class)
class TestSource {

	private AtomicBoolean semaphore = new AtomicBoolean(true);

	@Bean
	@InboundChannelAdapter(channel = "input-source", poller = @Poller(fixedDelay = "10000"))
	public MessageSource<String> sendTestData() {
		return () ->
				new GenericMessage<>(this.semaphore.getAndSet(!this.semaphore.get()) ? "foo" : "bar");
	}
	
}