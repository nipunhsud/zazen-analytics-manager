package com.analyticsmanager.v1.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyticsDataModel {

	@JsonProperty("Incurred Month")
	private String incurredMonth;
	
	@JsonProperty("Person ID Unencrypted")
	private long personId;
	
	public String getIncurredMonth() {
		return incurredMonth;
	}
	public void setIncurredMonth(String incurredMonth) {
		this.incurredMonth = incurredMonth;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public int getRateMeasureCode() {
		return rateMeasureCode;
	}
	public void setRateMeasureCode(int rateMeasureCode) {
		this.rateMeasureCode = rateMeasureCode;
	}
	public int getPatientRuleMeetingNumerator() {
		return patientRuleMeetingNumerator;
	}
	public void setPatientRuleMeetingNumerator(int patientRuleMeetingNumerator) {
		this.patientRuleMeetingNumerator = patientRuleMeetingNumerator;
	}
	public int getPatientRuleMeetingDenominator() {
		return patientRuleMeetingDenominator;
	}
	public void setPatientRuleMeetingDenominator(int patientRuleMeetingDenominator) {
		this.patientRuleMeetingDenominator = patientRuleMeetingDenominator;
	}
	public String getRuleIndicator() {
		return ruleIndicator;
	}
	public void setRuleIndicator(String ruleIndicator) {
		this.ruleIndicator = ruleIndicator;
	}
	public Date getLastRuleEventDate() {
		return lastRuleEventDate;
	}
	public void setLastRuleEventDate(Date lastRuleEventDate) {
		this.lastRuleEventDate = lastRuleEventDate;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@JsonProperty("Rule Measure Code")
	private int rateMeasureCode;
	
	@JsonProperty("Patients Rules Meeting Numerator {QM}")
	private int patientRuleMeetingNumerator;
	
	@JsonProperty("Patients Rules Meeting Denominator {QM}")
	private int patientRuleMeetingDenominator;
	
	@JsonProperty("Rule CE Met Indicator")
	private String ruleIndicator;
	@JsonProperty("Date of Last Rule Even")
	private Date lastRuleEventDate;
	@JsonProperty("Date of Birth")
	private Date dateOfBirth;
}
