package com.iva.restrequesttodockerkafka.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class Slf4jMDCFilter extends OncePerRequestFilter {

	private final String responseHeader;
	private final String mdcTokenKey;
	private final String requestHeader;

	public Slf4jMDCFilter() {
		super();
		this.responseHeader = null;
		this.mdcTokenKey = null;
		this.requestHeader = null;
	}

	public Slf4jMDCFilter(String responseHeader, String mdcTokenKey, String requestHeader) {
		super();
		this.responseHeader = responseHeader;
		this.mdcTokenKey = mdcTokenKey;
		this.requestHeader = requestHeader;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String token;
			if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
				token = request.getHeader(requestHeader);
			} else {
				token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
			}
			MDC.put(mdcTokenKey, token);
			if (!StringUtils.isEmpty(responseHeader)) {
				response.addHeader(responseHeader, token);
			}
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove(mdcTokenKey);
		}
	}

}
