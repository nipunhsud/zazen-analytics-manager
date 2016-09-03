package com.analyticsmanager.v1.service;

import java.util.List;

import com.analyticsmanager.v1.model.AnalyticsDataModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyticsData {

	@JsonProperty("AnalyticsData")
	private List<AnalyticsDataModel> lstAnalyticsDataModel;

	public List<AnalyticsDataModel> getLstAnalyticsDataModel() {
		return lstAnalyticsDataModel;
	}

	public void setLstAnalyticsDataModel(List<AnalyticsDataModel> lstAnalyticsDataModel) {
		this.lstAnalyticsDataModel = lstAnalyticsDataModel;
	}
}
