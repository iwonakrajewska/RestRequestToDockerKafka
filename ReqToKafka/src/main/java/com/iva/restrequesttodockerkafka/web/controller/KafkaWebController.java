package com.iva.restrequesttodockerkafka.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iva.restrequesttodockerkafka.web.service.KafkaSender;

@Controller
public class KafkaWebController {

	private static final Logger logger = LoggerFactory.getLogger(KafkaWebController.class);

	@Autowired
	KafkaSender kafkaSender;

	@PostMapping(path = "/kafka", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> sendToTopic(@RequestBody String message) {
		logger.info("Controller did receive POST message: {}" , message);
		return ResponseEntity.ok("POST processed");
	}

	@GetMapping(path = "/kafka", produces = "application/json")
	public ResponseEntity<String> testrequest() {
		logger.info("Controller did receive GET equest");
		return ResponseEntity.ok("GET processed");
	}

}
