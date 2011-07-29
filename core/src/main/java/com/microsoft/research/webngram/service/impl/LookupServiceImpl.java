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

public class LookupServiceImpl extends BaseNgramService implements
		LookupService {

	public LookupServiceImpl(String applicationId) {
		super(applicationId);
	}

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

	@Override
	public Double getConditionalProbability(String authorizationToken,
			String modelUrn, String phrase) {
		NgramServiceApiUrlBuilder urlBuilder = createNgramServiceApiUrlBuilder(NgramServiceApiUrls.GET_CONDITIONAL_PROBABILITIES_URL);
		String apiUrl = urlBuilder.withParameter(ParameterNames.USER_TOKEN, authorizationToken)
								.withField(ParameterNames.MODEL_URL, modelUrn)
								.withParameter(ParameterNames.PHRASE, phrase).buildUrl();
		return Double.parseDouble(convertStreamToString(callApiGet(apiUrl)));
	}

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
