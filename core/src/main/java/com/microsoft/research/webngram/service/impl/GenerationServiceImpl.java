package com.microsoft.research.webngram.service.impl;

import java.util.List;

import com.microsoft.research.webngram.service.GenerationService;

public class GenerationServiceImpl extends BaseNgramService implements
		GenerationService {

	public GenerationServiceImpl(String applicationId) {
		super(applicationId);
	}

	@Override
	public TokenSet generate(String authorizationToken, String modelUrn,
			String phraseContext, Integer maxN, String cookie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getModels() {
		// TODO Auto-generated method stub
		return null;
	}
}
