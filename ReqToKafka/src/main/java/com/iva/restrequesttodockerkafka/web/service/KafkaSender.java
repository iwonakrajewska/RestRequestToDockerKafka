package com.iva.restrequesttodockerkafka.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.iva.restrequesttodockerkafka.config.Slf4jMDCFilterConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaSender {

	private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

	private static final String TOPIC_NAME = "kafka_test_topic";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String payload) {

		Message<String> message = MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
				.setHeader(KafkaHeaders.MESSAGE_KEY, "999").setHeader(KafkaHeaders.PARTITION_ID, 0)
				.setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka")
				.setHeader("myThreadId", Thread.currentThread().getId())
				.setHeader("myThreadName", Thread.currentThread().getName())
				.setHeader("myUUID", MDC.get(Slf4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY)).build();

		log.info("KafkaSerder triggered with payload: '{}' to topic='{}'", payload, TOPIC_NAME);
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Message sent to Kafka=[" + payload + "] with offset=["
						+ result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.info("Unable to send message to Kafka=[ {} ] due to : {}", payload, ex.getMessage());
			}
		});
	}

}
