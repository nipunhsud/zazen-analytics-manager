package com.analyticsmanager.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.analyticsmanager.v1.model.DRGAndProviderChargeModel;
import com.analyticsmanager.v1.model.DRGModel;
import com.analyticsmanager.v1.model.ProviderChargeModel;
import com.analyticsmanager.v1.model.ProviderModel;

public class ByteArrayParser {

	public static List<ProviderModel> parseByteArrayToGenerateProviderModel(byte[] byteFileContents) throws IOException{
		List<ProviderModel> lstProviderModel=new ArrayList<ProviderModel>();
		
		long id=1000000000l;
		boolean startOfFile=true;
		List<CSVRecord> lstCsvRecords=csvDataParser(byteFileContents);
		Set<Integer> setForId=new HashSet<Integer>();
		for(CSVRecord csvRecord:lstCsvRecords)
		{
			if(startOfFile)
			{
				startOfFile=false;
				continue;
				
			}
			
			int providerId=new Integer(csvRecord.get(0));
			if(setForId.contains(providerId))
				continue;
			else
			{
				setForId.add(providerId);
				ProviderModel providerModel=new ProviderModel();										
				providerModel.setNpi(id);				
				providerModel.setProviderId(providerId);				
				providerModel.setProviderName(csvRecord.get(1));				
				providerModel.setProviderStreetAddress(csvRecord.get(2));				
				providerModel.setProviderCity(csvRecord.get(3));				
				providerModel.setProviderState(csvRecord.get(4));				
				providerModel.setProviderZip(new Integer(csvRecord.get(5)));									
				id++;			
				lstProviderModel.add(providerModel);
			}
		}
		return lstProviderModel;
	}
	
	public static DRGAndProviderChargeModel parseByteArraytoGenerateDRGAndProviderChargeModel(byte[] byteFileContents) throws IOException
	{
		DRGAndProviderChargeModel drgAndProviderChargeModel=new DRGAndProviderChargeModel();
		List<DRGModel> lstDRGModels=new ArrayList<DRGModel>();
		List<ProviderChargeModel> lstProviderChargeModel=new ArrayList<ProviderChargeModel>();
		List<CSVRecord> lstCSVRecords=csvDataParser(byteFileContents);
		HashSet<String> hshDrgDefinitions=new HashSet<String>();
		boolean startOfFile=true;
		for(CSVRecord csvRecord:lstCSVRecords)
		{
			
			
			if(startOfFile)
			{
				startOfFile=false;
				continue;				
			}
			String drgDefination=csvRecord.get(0);
			DRGModel drgModel=new DRGModel();
			ProviderChargeModel providerChargeModel=new ProviderChargeModel();
			String[] splitDrgDefinitions=drgDefination.split("-", 2);
			if(hshDrgDefinitions.contains(drgDefination))
			{
				providerChargeModel.setDrgId(Integer.parseInt(splitDrgDefinitions[0].trim()));
				providerChargeModel.setProviderId(new Integer(csvRecord.get(1)));
				providerChargeModel.setHospitalReferralRegion(csvRecord.get(2));
				providerChargeModel.setTotalDischarge(Integer.parseInt(csvRecord.get(3)));
				providerChargeModel.setAverageCoveredCharge(Double.parseDouble(csvRecord.get(4).trim()));
				providerChargeModel.setAverageTotalPayment(Double.parseDouble(csvRecord.get(5).trim()));
				providerChargeModel.setAverageMedicarePayment(Double.parseDouble(csvRecord.get(6).trim()));
				lstProviderChargeModel.add(providerChargeModel);
			}
			else
			{
				hshDrgDefinitions.add(drgDefination);								
				for(int i=0;i<splitDrgDefinitions.length;i++)
				{
					if(i%2 == 0)
						drgModel.setDrgId(Integer.parseInt(splitDrgDefinitions[i].trim()));
					else if(i%2 == 1){
						String drgValue=splitDrgDefinitions[i].trim();
						drgModel.setDrgDescription(drgValue);	
						drgModel.setDrgDefinition(drgValue);
					}
				}												
				providerChargeModel.setDrgId(drgModel.getDrgId());
				providerChargeModel.setProviderId(new Integer(csvRecord.get(1)));
				providerChargeModel.setHospitalReferralRegion(csvRecord.get(2));
				providerChargeModel.setTotalDischarge(Integer.parseInt(csvRecord.get(3).trim()));
				providerChargeModel.setAverageCoveredCharge(Double.parseDouble(csvRecord.get(4).trim()));
				providerChargeModel.setAverageTotalPayment(Double.parseDouble(csvRecord.get(5).trim()));
				providerChargeModel.setAverageMedicarePayment(Double.parseDouble(csvRecord.get(6).trim()));
				lstDRGModels.add(drgModel);
				lstProviderChargeModel.add(providerChargeModel);
				
			}
			
			
			
		}
		drgAndProviderChargeModel.setLstDRGModels(lstDRGModels);
		drgAndProviderChargeModel.setLstProviderChargeModels(lstProviderChargeModel);
		return drgAndProviderChargeModel;
	}
	
	private static List<CSVRecord> csvDataParser(byte[] byteFileContents) throws IOException
	{
		InputStream inputStream=new ByteArrayInputStream(byteFileContents);
		BufferedReader buffReader=new BufferedReader(new InputStreamReader(inputStream));
		CSVParser csvParser=new CSVParser(buffReader, CSVFormat.EXCEL);
		List<CSVRecord> lstCsvRecords= csvParser.getRecords();
		csvParser.close();
		return lstCsvRecords;
	}
}
