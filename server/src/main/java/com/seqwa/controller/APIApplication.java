package com.seqwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seqwa.dao.RequestObject;
import com.seqwa.dao.SearchResult;
import com.seqwa.service.SeqwaSearchService;

@RestController
@RequestMapping(value = "/api")
public class APIApplication {

	@Autowired
	private SeqwaSearchService searchService;

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public SearchResult search(@ModelAttribute RequestObject request) {
		return searchService.search(request);
	}

}
