package com.iva.kafkatolog.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(Processor.class)
@Slf4j
public class PayloadConverter {

	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String transform(Message<String> m) {
		log.info("Transformer, payload:  {} , headers: {}", m.getPayload(), m.getHeaders());
		return replaceCreditCardNumber(m.getPayload());
	}

	public static String replaceCreditCardNumber(String text) {
		final String MASKCARD = "$1xxx$2";
		final Pattern PATTERNCARD = Pattern.compile("([0-9]{4})[0-9]{0,9}([0-9]{4})");
		Matcher matcher = PATTERNCARD.matcher(text);
		if (matcher.find()) {
			return matcher.replaceAll(MASKCARD);
		}
		return text;

	}

}