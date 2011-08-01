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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Interface GenerationService.
 */
public interface GenerationService extends NgramServiceCommunicationClient {
	
	/**
	 * Gets the models.
	 * 
	 * @return the models
	 */
	public List<String> getModels();
	
	/**
	 * Generate.
	 * 
	 * @param authorizationToken the authorization token
	 * @param modelUrn the model urn
	 * @param phraseContext the phrase context
	 * @param maxN the max n
	 * @param cookie the cookie
	 * 
	 * @return the token set
	 */
	public TokenSet generate(String authorizationToken, String modelUrn, String phraseContext, Integer maxN, String cookie);

	/**
	 * The Class TokenSet.
	 */
	public static class TokenSet implements Serializable {

		/** The Constant serialVersionUID. */
		private final static long serialVersionUID = 2461660169443089969L;
		
		/** The backoff. */
		protected Double backoff;
		
		/** The cookie. */
		protected String cookie;
		
		/** The probabilities. */
		protected List<Double> probabilities;
		
		/** The words. */
		protected List<String> words;

		/**
		 * Gets the backoff.
		 * 
		 * @return the backoff
		 */
		public Double getBackoff() {
			return backoff;
		}

		/**
		 * Sets the backoff.
		 * 
		 * @param value the new backoff
		 */
		public void setBackoff(Double value) {
			this.backoff = value;
		}

		/**
		 * Gets the cookie.
		 * 
		 * @return the cookie
		 */
		public String getCookie() {
			return cookie;
		}

		/**
		 * Sets the cookie.
		 * 
		 * @param value the new cookie
		 */
		public void setCookie(String value) {
			this.cookie = value;
		}

		/**
		 * Gets the probabilities.
		 * 
		 * @return the probabilities
		 */
		public List<Double> getProbabilities() {
			if (probabilities == null) {
				probabilities = new ArrayList<Double>();
			}
			return probabilities;
		}

		/**
		 * Sets the probabilities.
		 * 
		 * @param value the new probabilities
		 */
		public void setProbabilities(List<Double> value) {
			this.probabilities = value;
		}

		/**
		 * Gets the words.
		 * 
		 * @return the words
		 */
		public List<String> getWords() {
			if (words == null) {
				words = new ArrayList<String>();
			}
			return words;
		}

		/**
		 * Sets the words.
		 * 
		 * @param value the new words
		 */
		public void setWords(List<String> value) {
			this.words = value;
		}
	}
}
