package com.seqwa.dao;

import java.util.List;
import java.util.Map;

public class SearchResult {

	private List<Map<String, ?>> searchResults;
	private List<String> topTags;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getTopTags() {
		return topTags;
	}

	public void setTopTags(List<String> topTags) {
		this.topTags = topTags;
	}

	public List<Map<String, ?>> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Map<String, ?>> searchResults) {
		this.searchResults = searchResults;
	}

}
