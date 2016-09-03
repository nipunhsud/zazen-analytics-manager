package com.analyticsmanager.v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.analyticsmanager.v1.service.AnalyticsManagerRequest;
import com.analyticsmanager.v1.service.AnalyticsManagerService;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class AnalyticsManagerController {

	@Autowired 
	private AnalyticsManagerService analyticsManagerService;
	
	protected Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AnalyticsManagerRequestValidator analyticsManagerRequestValidator;
	
	@InitBinder
	public void initAnalyticsManagerRequestBinder(WebDataBinder binder)
	{
		binder.setValidator(analyticsManagerRequestValidator);
	}
	
	@RequestMapping(value="/v1/analytics/users/{userid}")
	public ResponseEntity<JsonNode> getAnalyticsDataForAUser(HttpServletRequest httpServletRequest,@Valid AnalyticsManagerRequest analyticsManagerRequest) throws Exception
	{
		log.info("Received request : {}",analyticsManagerRequest.getUserid());
		JsonNode jsonNode= analyticsManagerService.getCompanyAnalyticsData(analyticsManagerRequest);
		ResponseEntity<JsonNode> responseEntity=new ResponseEntity<JsonNode>(jsonNode, HttpStatus.OK);
		return responseEntity;
	}
}
