package com.analyticsmanager.v1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.analyticsmanager.v1.model.DRGModel;
import com.analyticsmanager.v1.model.ProviderChargeModel;
import com.analyticsmanager.v1.model.ProviderModel;

public interface DataImportDAO {

	final String INSERT_PROVIDERS="<script>"
			+ "INSERT INTO PROVIDER VALUES "
			+ "<foreach collection ='lstProviderModel' item='providerModel' separator=','> "
			+ "(#{providerModel.npi, jdbcType=BIGINT},#{providerModel.providerId, jdbcType=VARCHAR },#{providerModel.providerName, jdbcType=VARCHAR },#{providerModel.providerStreetAddress, jdbcType=VARCHAR },#{providerModel.providerCity, jdbcType=VARCHAR},#{providerModel.providerState},#{providerModel.providerZip})"
			+ "</foreach>"
			+ "</script>";
	
	final String INSERT_DRG="<script>"
			+ "INSERT INTO DRG VALUES "
			+ "<foreach collection ='lstDRGModels' item='drgModel' separator=','> "
			+ "(#{drgModel.drgId, jdbcType=INTEGER},#{drgModel.drgDefinition, jdbcType=VARCHAR },#{drgModel.drgDescription, jdbcType=VARCHAR })"
			+ "</foreach>"
			+ "</script>";
	
	final String INSERT_PROVIDER_CHARGE="<script>"
			+ "INSERT INTO PROVIDER_CHARGE (drg_id,provider_id,hospital_referral_region,total_discharge,average_covered_charge,average_total_payment,average_medicare_payment) VALUES" 
			+ "<foreach collection ='lstProviderChargeModels' item='providerChargeModel' separator=','> "
			+ "(#{providerChargeModel.drgId, jdbcType=INTEGER},#{providerChargeModel.providerId, jdbcType=INTEGER },#{providerChargeModel.hospitalReferralRegion, jdbcType=VARCHAR },#{providerChargeModel.totalDischarge, jdbcType=INTEGER},#{providerChargeModel.averageCoveredCharge, jdbcType=DOUBLE},#{providerChargeModel.averageTotalPayment, jdbcType=DOUBLE},#{providerChargeModel.averageMedicarePayment, jdbcType=DOUBLE})"
			+ "</foreach>"
			+ "</script>";
	
	@Insert(INSERT_PROVIDERS)
	@Options(useGeneratedKeys=true,keyProperty="npi",flushCache=true)
	public int insertProviders(@Param("lstProviderModel")List<?> lstProviderModel);
	
	@Insert(INSERT_DRG)
	@Options(useGeneratedKeys=false,keyProperty="drgId",flushCache=true)
	public int insertDRG(@Param("lstDRGModels")List<?> lstDRGModels);
	

	@Insert(INSERT_PROVIDER_CHARGE)
	@Options(flushCache=true)
	public int insertProviderCharge(@Param("lstProviderChargeModels")List<?> lstProviderChargeModels);
}
