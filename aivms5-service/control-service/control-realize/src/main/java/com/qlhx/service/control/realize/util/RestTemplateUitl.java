package com.qlhx.service.control.realize.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUitl<RequestType,ResonseType> {
	
	public ResonseType postByJson(RestTemplate restTemplate, String url, RequestType req,ResonseType resp) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		HttpEntity<RequestType> formEntity = new HttpEntity<RequestType>(req, headers);
		ResonseType result = (ResonseType) restTemplate.postForObject(url, formEntity,resp.getClass());
		return result;
	}

}
