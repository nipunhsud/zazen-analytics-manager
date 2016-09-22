package com.analyticsmanager.v1.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.analyticsmanager.v1.service.AnalyticsDataImportService;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class AnalyticsDataImportManagerController {

	@Autowired
	private AnalyticsDataImportService analyticsDataImportService;
	
	protected Logger log=LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/v1/dataimports/{tablename}", method=RequestMethod.POST,produces="application/json")
	public ResponseEntity<JsonNode> insertData(@RequestBody byte[] byteFile,HttpServletRequest httpServletRequest, @PathVariable("tablename") String tableName) throws Exception
	{
		log.info("Received Table Name: {}",tableName);
		
		JsonNode jsonNode=analyticsDataImportService.importDataFromFile(byteFile,tableName);
		return new ResponseEntity<JsonNode>(jsonNode, HttpStatus.OK);
	}
}
