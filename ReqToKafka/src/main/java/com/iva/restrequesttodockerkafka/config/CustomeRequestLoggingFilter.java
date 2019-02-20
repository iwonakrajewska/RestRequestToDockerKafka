package com.iva.restrequesttodockerkafka.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.iva.restrequesttodockerkafka.web.service.KafkaSender;

@Configuration
public class CustomeRequestLoggingFilter extends CommonsRequestLoggingFilter {

	@Autowired
	KafkaSender kafkaSender;

	public CustomeRequestLoggingFilter() {
		super.setIncludeQueryString(true);
		super.setIncludePayload(true);
		super.setMaxPayloadLength(10000);
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		logger.debug("CustomeRequestLoggingFilter-before:" + message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		logger.debug("CustomeRequestLoggingFilter-after" + message);
		kafkaSender.send(MDC.get(Slf4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY) + "|" + message);
	}

}