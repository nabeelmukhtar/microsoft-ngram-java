package com.microsoft.research.webngram.service.impl;

import java.util.List;

import com.microsoft.research.webngram.service.LookupService;

public class LookupServiceImpl extends BaseNgramService implements
		LookupService {

	public LookupServiceImpl(String applicationId) {
		super(applicationId);
	}

	@Override
	public List<Double> getConditionalProbabilities(String authorizationToken,
			String modelUrn, List<String> phrases) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getConditionalProbability(String authorizationToken,
			String modelUrn, String phrase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Double> getProbabilities(String authorizationToken,
			String modelUrn, List<String> phrases) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getProbability(String authorizationToken, String modelUrn,
			String phrase) {
		// TODO Auto-generated method stub
		return null;
	}
}
