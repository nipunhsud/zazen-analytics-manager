package com.analyticsmanager.v1.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyticsManagerResponse {

	@JsonProperty("ResponseMessge")
	private String message;
	@JsonProperty("Anaytics")
	private AnalyticsData analyticsData;
	public AnalyticsData getAnalyticsData() {
		return analyticsData;
	}
	public void setAnalyticsData(AnalyticsData analyticsData) {
		this.analyticsData = analyticsData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
