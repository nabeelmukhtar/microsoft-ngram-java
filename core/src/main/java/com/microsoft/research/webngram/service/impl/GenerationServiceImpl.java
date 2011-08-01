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
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.constant.NgramServiceApiUrls;
import com.microsoft.research.webngram.service.constant.ParameterNames;
import com.microsoft.research.webngram.service.constant.NgramServiceApiUrls.NgramServiceApiUrlBuilder;
import com.microsoft.research.webngram.service.exception.NgramServiceException;

/**
 * The Class GenerationServiceImpl.
 */
public class GenerationServiceImpl extends BaseNgramService implements
		GenerationService {

	/**
	 * Instantiates a new generation service impl.
	 * 
	 * @param applicationId the application id
	 */
	public GenerationServiceImpl(String applicationId) {
		super(applicationId);
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.GenerationService#generate(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public TokenSet generate(String authorizationToken, String modelUrn,
			String phraseContext, Integer maxN, String cookie) {
		try {
			NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.GENERATE_URL);
			String apiUrl = urlBuilder.withParameter(ParameterNames.USER_TOKEN, authorizationToken)
									.withField(ParameterNames.MODEL_URL, modelUrn)
									.withParameter(ParameterNames.PHRASE, phraseContext)
									.withParameter(ParameterNames.MAX_TOKENS, maxN.toString())
									.withParameter(ParameterNames.COOKIE, cookie).buildUrl();
			TokenSet tokenSet = new TokenSet();
			JSONObject object = new JSONObject(convertStreamToString(callApiGet(apiUrl)));
			tokenSet.setBackoff(object.getDouble("backoff"));
			tokenSet.setCookie(object.getString("cookie"));
			JSONArray probabilities = object.getJSONArray("probabilities");
			for (int i = 0; i < probabilities.length(); i++) {
				tokenSet.getProbabilities().add(probabilities.getDouble(i));
			}
			JSONArray words = object.getJSONArray("words");
			for (int i = 0; i < words.length(); i++) {
				tokenSet.getWords().add(words.getString(i));
			}
			return tokenSet;
		} catch (Exception e) {
			throw new NgramServiceException("An error occurred while generating token set.", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.microsoft.research.webngram.service.GenerationService#getModels()
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
}
