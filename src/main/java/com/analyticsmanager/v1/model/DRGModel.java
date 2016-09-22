package com.analyticsmanager.v1.model;

public class DRGModel {

	private int drgId;
	private String drgDescription;
	public int getDrgId() {
		return drgId;
	}
	public void setDrgId(int drgId) {
		this.drgId = drgId;
	}
	public String getDrgDescription() {
		return drgDescription;
	}
	public void setDrgDescription(String drgDescription) {
		this.drgDescription = drgDescription;
	}
	public String getDrgDefinition() {
		return drgDefinition;
	}
	public void setDrgDefinition(String drgDefinition) {
		this.drgDefinition = drgDefinition;
	}
	private String drgDefinition;
}
