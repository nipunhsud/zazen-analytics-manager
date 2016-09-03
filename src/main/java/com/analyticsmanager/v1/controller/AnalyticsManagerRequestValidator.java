package com.analyticsmanager.v1.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.client.HttpClientErrorException;

import com.analyticsmanager.v1.service.AnalyticsManagerRequest;

@Component
public class AnalyticsManagerRequestValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AnalyticsManagerRequest.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		if(errors.hasErrors()){
			return;
		}
		AnalyticsManagerRequest analyticsManagerRequest=(AnalyticsManagerRequest)obj;
		validateUserId(analyticsManagerRequest.getUserid());
	}
	
	private void validateUserId(long userId)
	{
		boolean isEmpty=StringUtils.isEmpty(String.valueOf(userId));
		if(isEmpty)
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"User Id is a required field");
	}

}
