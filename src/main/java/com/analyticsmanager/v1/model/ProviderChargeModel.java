package com.analyticsmanager.v1.model;

public class ProviderChargeModel {

	private int drgId;
	private int providerId;
	private String hospitalReferralRegion;
	private int totalDischarge;
	private double averageCoveredCharge;
	private double averageTotalPayment;
	private double averageMedicarePayment;
	public int getDrgId() {
		return drgId;
	}
	public void setDrgId(int drgId) {
		this.drgId = drgId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getHospitalReferralRegion() {
		return hospitalReferralRegion;
	}
	public void setHospitalReferralRegion(String hospitalReferralRegion) {
		this.hospitalReferralRegion = hospitalReferralRegion;
	}
	public int getTotalDischarge() {
		return totalDischarge;
	}
	public void setTotalDischarge(int totalDischarge) {
		this.totalDischarge = totalDischarge;
	}
	public double getAverageCoveredCharge() {
		return averageCoveredCharge;
	}
	public void setAverageCoveredCharge(double averageCoveredCharge) {
		this.averageCoveredCharge = averageCoveredCharge;
	}
	public double getAverageTotalPayment() {
		return averageTotalPayment;
	}
	public void setAverageTotalPayment(double averageTotalPayment) {
		this.averageTotalPayment = averageTotalPayment;
	}
	public double getAverageMedicarePayment() {
		return averageMedicarePayment;
	}
	public void setAverageMedicarePayment(double averageMedicarePayment) {
		this.averageMedicarePayment = averageMedicarePayment;
	}
}
