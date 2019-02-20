package com.iva.restrequesttodockerkafka.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaSender {

	private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

	private static final String TOPIC_NAME = "kafka_test_topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String payload) {

		logger.info("KafkaSerder triggered with payload: {}", payload);
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, payload);

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
