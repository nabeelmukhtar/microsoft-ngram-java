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
package com.microsoft.research.webngram.service;

import java.util.List;

/**
 * The Interface LookupService.
 */
public interface LookupService extends NgramServiceCommunicationClient {
	
	/**
	 * Gets the models.
	 * 
	 * @return the models
	 */
	public List<String> getModels();
	
	/**
	 * Gets the probability.
	 * 
	 * @param authorizationToken the authorization token
	 * @param modelUrn the model urn
	 * @param phrase the phrase
	 * 
	 * @return the probability
	 */
	public Double getProbability(String authorizationToken, String modelUrn, String phrase);
	
	/**
	 * Gets the probabilities.
	 * 
	 * @param authorizationToken the authorization token
	 * @param modelUrn the model urn
	 * @param phrases the phrases
	 * 
	 * @return the probabilities
	 */
	public List<Double> getProbabilities(String authorizationToken, String modelUrn, List<String> phrases);
	
	/**
	 * Gets the conditional probability.
	 * 
	 * @param authorizationToken the authorization token
	 * @param modelUrn the model urn
	 * @param phrase the phrase
	 * 
	 * @return the conditional probability
	 */
	public Double getConditionalProbability(String authorizationToken, String modelUrn, String phrase);
	
	/**
	 * Gets the conditional probabilities.
	 * 
	 * @param authorizationToken the authorization token
	 * @param modelUrn the model urn
	 * @param phrases the phrases
	 * 
	 * @return the conditional probabilities
	 */
	public List<Double> getConditionalProbabilities(String authorizationToken, String modelUrn, List<String> phrases);
}
