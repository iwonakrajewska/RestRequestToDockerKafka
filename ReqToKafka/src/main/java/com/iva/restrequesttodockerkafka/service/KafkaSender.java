package com.iva.restrequesttodockerkafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaSender {
	
	private static String TOPIC_NAME = "kafka_test_topic";
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send( String payload) {

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, payload);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println(
						"Sent message=[" + payload + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + payload + "] due to : " + ex.getMessage());
			}
		});
	}

}
