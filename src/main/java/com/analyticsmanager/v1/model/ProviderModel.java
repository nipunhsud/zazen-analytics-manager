package com.analyticsmanager.v1.model;

public class ProviderModel {

	private long npi;
	public long getNpi() {
		return npi;
	}
	public void setNpi(long npi) {
		this.npi = npi;
	}
	private int providerId;
	private String providerName;
	private String providerStreetAddress;
	private String providerCity;
	private String providerState;
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderStreetAddress() {
		return providerStreetAddress;
	}
	public void setProviderStreetAddress(String providerStreetAddress) {
		this.providerStreetAddress = providerStreetAddress;
	}
	public String getProviderCity() {
		return providerCity;
	}
	public void setProviderCity(String providerCity) {
		this.providerCity = providerCity;
	}
	public String getProviderState() {
		return providerState;
	}
	public void setProviderState(String providerState) {
		this.providerState = providerState;
	}
	public int getProviderZip() {
		return providerZip;
	}
	public void setProviderZip(int providerZip) {
		this.providerZip = providerZip;
	}
	private int providerZip;
}
