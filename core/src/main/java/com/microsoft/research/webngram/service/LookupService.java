package com.microsoft.research.webngram.service;

import java.util.List;

public interface LookupService extends NgramServiceCommunicationClient {
	public List<String> getModels();
	public Double getProbability(String authorizationToken, String modelUrn, String phrase);
	public List<Double> getProbabilities(String authorizationToken, String modelUrn, List<String> phrases);
	public Double getConditionalProbability(String authorizationToken, String modelUrn, String phrase);
	public List<Double> getConditionalProbabilities(String authorizationToken, String modelUrn, List<String> phrases);
}
