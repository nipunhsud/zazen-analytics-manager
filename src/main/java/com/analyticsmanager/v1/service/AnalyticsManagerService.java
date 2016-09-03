package com.analyticsmanager.v1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.analyticsmanager.v1.configuration.AnalysticsManagerConstants;
import com.analyticsmanager.v1.dao.AnalyticsManagerDAO;
import com.analyticsmanager.v1.model.AnalyticsDataModel;
import com.analyticsmanager.v1.model.CompanyProfileModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnalyticsManagerService {

	protected Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AnalyticsManagerDAO analyticsManagerDAO;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public JsonNode getCompanyAnalyticsData(AnalyticsManagerRequest analyticsManagerRequest) throws Exception {
		JsonNode jsonNode = null;
		List<AnalyticsDataModel> lstAnalyticsDataModel=analyticsManagerDAO.getAnalyticsData();
		if(lstAnalyticsDataModel == null || lstAnalyticsDataModel.isEmpty()){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Analytics Data doesn't exist!");
		}
			
		jsonNode=generateResponseNode(lstAnalyticsDataModel);
		return jsonNode;
	}
	
	private JsonNode generateResponseNode(List<AnalyticsDataModel> lstAnalyticsDataModel)
	{
		JsonNode jsonNode;
		AnalyticsData analyticsData=new AnalyticsData();
		AnalyticsManagerResponse analyticsManagerResponse=new AnalyticsManagerResponse();
		analyticsData.setLstAnalyticsDataModel(lstAnalyticsDataModel);
		analyticsManagerResponse.setMessage(AnalysticsManagerConstants.SUCCESSMESSAGE);
		analyticsManagerResponse.setAnalyticsData(analyticsData);
		jsonNode=objectMapper.convertValue(analyticsManagerResponse, JsonNode.class);
		return jsonNode;
	}
}
