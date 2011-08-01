/*
 * Copyright 2011 Nabeel Mukhtar 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package com.microsoft.research.webngram.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.microsoft.research.webngram.service.LookupService;
import com.microsoft.research.webngram.service.constant.NgramServiceApiUrls;
import com.microsoft.research.webngram.service.constant.ParameterNames;
import com.microsoft.research.webngram.service.constant.NgramServiceApiUrls.NgramServiceApiUrlBuilder;
import com.microsoft.research.webngram.service.exception.NgramServiceException;

/**
 * The Class LookupServiceImpl.
 */
public class LookupServiceImpl extends BaseNgramService implements
		LookupService {

	/**
	 * Instantiates a new lookup service impl.
	 * 
	 * @param applicationId the application id
	 */
	public LookupServiceImpl(String applicationId) {
		super(applicationId);
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.LookupService#getConditionalProbabilities(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public List<Double> getConditionalProbabilities(String authorizationToken,
			String modelUrn, List<String> phrases) {
		try {
			List<Double> probabilities = new ArrayList<Double>();
			NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.GET_CONDITIONAL_PROBABILITIES_URL);
			String apiUrl = urlBuilder.withField(ParameterNames.MODEL_URL, modelUrn).buildUrl();
			Map<String, List<String>> parameters = new HashMap<String, List<String>>();
			parameters.put(ParameterNames.USER_TOKEN, Collections.singletonList(authorizationToken));
			parameters.put(ParameterNames.PHRASE, phrases);
			JSONArray array = new JSONArray(convertStreamToString(callApiPost(apiUrl, parameters)));
			for (int i = 0; i < array.length(); i++) {
				probabilities.add(array.getDouble(i));
			}
			return probabilities;
		} catch (Exception e) {
			throw new NgramServiceException("An error occurred while getting conditional probabilities.", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.LookupService#getConditionalProbability(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Double getConditionalProbability(String authorizationToken,
			String modelUrn, String phrase) {
		NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.GET_CONDITIONAL_PROBABILITIES_URL);
		String apiUrl = urlBuilder.withParameter(ParameterNames.USER_TOKEN, authorizationToken)
								.withField(ParameterNames.MODEL_URL, modelUrn)
								.withParameter(ParameterNames.PHRASE, phrase).buildUrl();
		return Double.parseDouble(convertStreamToString(callApiGet(apiUrl)));
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.LookupService#getModels()
	 */
	@Override
	public List<String> getModels() {
		try {
			List<String> models = new ArrayList<String>();
			NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.LOOKUP_URL);
			String apiUrl = urlBuilder.buildUrl();
			JSONArray array = new JSONArray(convertStreamToString(callApiGet(apiUrl)));
			for (int i = 0; i < array.length(); i++) {
				models.add(array.getString(i));
			}
			return models;
		} catch (Exception e) {
			throw new NgramServiceException("An error occurred while getting models.", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.LookupService#getProbabilities(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public List<Double> getProbabilities(String authorizationToken,
			String modelUrn, List<String> phrases) {
		try {
			List<Double> probabilities = new ArrayList<Double>();
			NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.GET_PROBABILITIES_URL);
			String apiUrl = urlBuilder.withField(ParameterNames.MODEL_URL, modelUrn).buildUrl();
			Map<String, List<String>> parameters = new HashMap<String, List<String>>();
			parameters.put(ParameterNames.USER_TOKEN, Collections.singletonList(authorizationToken));
			parameters.put(ParameterNames.PHRASE, phrases);
			JSONArray array = new JSONArray(convertStreamToString(callApiPost(apiUrl, parameters)));
			for (int i = 0; i < array.length(); i++) {
				probabilities.add(array.getDouble(i));
			}
			return probabilities;
		} catch (Exception e) {
			throw new NgramServiceException("An error occurred while getting probabilities.", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.LookupService#getProbability(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Double getProbability(String authorizationToken, String modelUrn,
			String phrase) {
		NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.GET_PROBABILITIES_URL);
		String apiUrl = urlBuilder.withParameter(ParameterNames.USER_TOKEN, authorizationToken)
								.withField(ParameterNames.MODEL_URL, modelUrn)
								.withParameter(ParameterNames.PHRASE, phrase).buildUrl();
		return Double.parseDouble(convertStreamToString(callApiGet(apiUrl)));
	}
}
