package com.analyticsmanager.v1.model;

import java.util.List;

public class DRGAndProviderChargeModel {

	private List<DRGModel> lstDRGModels;
	private List<ProviderChargeModel> lstProviderChargeModels;
	public List<DRGModel> getLstDRGModels() {
		return lstDRGModels;
	}
	public void setLstDRGModels(List<DRGModel> lstDRGModels) {
		this.lstDRGModels = lstDRGModels;
	}
	public List<ProviderChargeModel> getLstProviderChargeModels() {
		return lstProviderChargeModels;
	}
	public void setLstProviderChargeModels(List<ProviderChargeModel> lstProviderChargeModels) {
		this.lstProviderChargeModels = lstProviderChargeModels;
	}
}
