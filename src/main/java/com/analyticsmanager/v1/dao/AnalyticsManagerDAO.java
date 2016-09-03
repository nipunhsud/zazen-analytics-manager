package com.analyticsmanager.v1.dao;

import org.apache.ibatis.annotations.Results;
import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.analyticsmanager.v1.model.AnalyticsDataModel;
import com.analyticsmanager.v1.model.CompanyProfileModel;

public interface AnalyticsManagerDAO {

	@Select("Select * from company_profile where company_id = #{companyId};")
	 @Results(value={@Result(property= "companyId", column ="company_id"),
		      @Result(property = "companyName", column = "company_name")
	 })
	CompanyProfileModel getCompanyDetails(String companyId);
	
	
	@Select("Select * from analytics_data;")
	 @Results(value={@Result(property= "incurredMonth", column ="Incurred_Month"),
		      @Result(property = "personId", column = "Person_ID"),
		      @Result(property = "rateMeasureCode", column = "Rule_Measure_Code"),
	  @Result(property = "patientRuleMeetingNumerator", column = "Patients_Rules_Meeting_Numerator"),
	  @Result(property = "patientRuleMeetingDenominator", column = "Patients_Rules_Meeting_Denominator"),
	  @Result(property = "ruleIndicator", column = "Rule_CE_Met_Indicator"),
	  @Result(property = "lastRuleEventDate", column = "Date_of_Last_Rule_Event"),
	  @Result(property = "dateOfBirth", column = "dob")
	 })
	List<AnalyticsDataModel> getAnalyticsData();
}
