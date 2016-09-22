package com.analyticsmanager.v1.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.analyticsmanager.utils.ByteArrayParser;
import com.analyticsmanager.v1.dao.DataImportDAO;
import com.analyticsmanager.v1.model.DRGAndProviderChargeModel;
import com.analyticsmanager.v1.model.DRGModel;
import com.analyticsmanager.v1.model.ProviderModel;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class AnalyticsDataImportService {

	@Autowired
	DataImportDAO dataImportDAO;
	
	protected Logger log=LoggerFactory.getLogger(AnalyticsDataImportService.class.getSimpleName());
	
	
	public JsonNode importDataFromFile(byte[] byteFileContents,String tableName)
	{
		JsonNode jsonNode=null;
		try {
			if(tableName.equals("Providers"))
			{
				List<ProviderModel> lstProviderModel=ByteArrayParser.parseByteArrayToGenerateProviderModel(byteFileContents);
				log.debug("Size of Dataset : " + lstProviderModel.size());
				int sizeOfList=lstProviderModel.size();
				int startIndex=0;
				if(sizeOfList > 5000)
				{
					int counter=sizeOfList/5000;
					int endIndex=5000;
					for(int i=0;i<counter;i++)
					{
						if(i + 1 == counter)
						{
							dataImportDAO.insertProviders(lstProviderModel.subList(startIndex, sizeOfList));
							break;
						}
						dataImportDAO.insertProviders(lstProviderModel.subList(startIndex, endIndex));
						log.debug("Sucessfully inserted records : {}",i);
						startIndex=endIndex;
						endIndex+=5000;
					}
				}
				else
				{
					dataImportDAO.insertProviders(lstProviderModel);
				}
			}
			else if(tableName.equals("MedicareProviderCharge"))
			{
				DRGAndProviderChargeModel drgAndProviderChargeModels=ByteArrayParser.parseByteArraytoGenerateDRGAndProviderChargeModel(byteFileContents);
				log.info("Total Recorsds for DRG: {}",drgAndProviderChargeModels.getLstDRGModels().size());
				insertImportData(drgAndProviderChargeModels.getLstDRGModels(),"DRG");
				insertImportData(drgAndProviderChargeModels.getLstProviderChargeModels(), "DRGProviderModel");
				
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage());
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
		 catch (Exception e) {
			 System.out.println(e.getMessage());
			 	log.error(e.getMessage());
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
			}
		return jsonNode;
	}
	
	@SuppressWarnings("unchecked")
	private void insertImportData(List<?> lstData,String tableName)
	{
		int sizeOfList=lstData.size();
		int startIndex=0;
		if(sizeOfList > 5000)
		{
			int counter=sizeOfList/5000;
			int endIndex=5000;
			for(int i=0;i<counter;i++)
			{
				if(i + 1 == counter)
				{
					if(tableName.equals("Providers"))
						dataImportDAO.insertProviders(lstData.subList(startIndex, sizeOfList));
					else if (tableName.equals("DRG"))
						dataImportDAO.insertDRG(lstData.subList(startIndex, sizeOfList));
					else if(tableName.equals("DRGProviderModel"))
						dataImportDAO.insertProviderCharge(lstData.subList(startIndex, sizeOfList));
					break;
				}
				if(tableName.equals("Providers"))
					dataImportDAO.insertProviders(lstData.subList(startIndex, endIndex));
				else if (tableName.equals("DRG"))
					dataImportDAO.insertDRG(lstData.subList(startIndex, endIndex));
				else if(tableName.equals("DRGProviderModel"))
					dataImportDAO.insertProviderCharge(lstData.subList(startIndex, endIndex));
				
				log.debug("Sucessfully inserted records : {}",i);
				startIndex=endIndex;
				endIndex+=5000;
			}
		}
		else
		{
			if(tableName.equals("Providers"))
				dataImportDAO.insertProviders(lstData);
			else if (tableName.equals("DRG"))
			{
				dataImportDAO.insertDRG(lstData);
			}
			else if(tableName.equals("DRGProviderModel"))
				dataImportDAO.insertProviderCharge(lstData);
		}
		
	}
	
}
