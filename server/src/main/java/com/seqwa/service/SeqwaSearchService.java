package com.seqwa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.seqwa.dao.RequestObject;
import com.seqwa.dao.SearchResult;

@Service
public class SeqwaSearchService {

	@Value("${seqwa.indexid}")
	private String indexId;
	@Value("${seqwa.search.url}")
	private String searchURL;
	@Value("${seqwa.search.apikey}")
	private String searchAPIKey;

	private final RestTemplate restTemplate;

	public SeqwaSearchService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public SearchResult search(RequestObject request) {
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(searchURL).queryParam("index", "{index}")
				.queryParam("query", "{query}").queryParam("context", "{context}").queryParam("type", "{type}").queryParam("fields", "{fields}")
				.queryParam("highlightField", "{highlightField}").queryParam("maxResults", "{maxResults}").encode()
				.toUriString();
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("index", indexId);
		uriVariables.put("query", request.getQuery() != null ? request.getQuery() : "");
		uriVariables.put("context", request.getContext() != null ? request.getContext() : "");
		uriVariables.put("type", request.getType() != null ? request.getType() : "");
		uriVariables.put("fields", request.getFields() != null ? request.getFields() : "title,price,link,image");
		uriVariables.put("highlightField", request.getHighlightField() != null ? request.getHighlightField() : "title");
		uriVariables.put("maxResults", request.getMaxResults() != null ? request.getMaxResults() : "200");

		HttpHeaders headers = new HttpHeaders();
		headers.set("seqwa-api-key", searchAPIKey);
		headers.set("Content-Type", "application/json");

		HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
		return restTemplate.exchange(urlTemplate, HttpMethod.GET, httpEntity, SearchResult.class, uriVariables)
				.getBody();
	}

}
