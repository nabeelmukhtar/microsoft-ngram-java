package com.microsoft.research.webngram.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface GenerationService extends NgramServiceCommunicationClient {
	
	public List<String> getModels();
	public TokenSet generate(String authorizationToken, String modelUrn, String phraseContext, Integer maxN, String cookie);

	public static class TokenSet implements Serializable {

		private final static long serialVersionUID = 2461660169443089969L;
		protected Double backoff;
		protected String cookie;
		protected List<Double> probabilities;
		protected List<String> words;

		/**
		 * Gets the value of the backoff property.
		 * 
		 * @return possible object is {@link Float }
		 * 
		 */
		public Double getBackoff() {
			return backoff;
		}

		/**
		 * Sets the value of the backoff property.
		 * 
		 * @param value
		 *            allowed object is {@link Float }
		 * 
		 */
		public void setBackoff(Double value) {
			this.backoff = value;
		}

		/**
		 * Gets the value of the cookie property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCookie() {
			return cookie;
		}

		/**
		 * Sets the value of the cookie property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCookie(String value) {
			this.cookie = value;
		}

		/**
		 * Gets the value of the probabilities property.
		 * 
		 * @return possible object is {@link ArrayOffloat }
		 * 
		 */
		public List<Double> getProbabilities() {
			if (probabilities == null) {
				probabilities = new ArrayList<Double>();
			}
			return probabilities;
		}

		/**
		 * Sets the value of the probabilities property.
		 * 
		 * @param value
		 *            allowed object is {@link ArrayOffloat }
		 * 
		 */
		public void setProbabilities(List<Double> value) {
			this.probabilities = value;
		}

		/**
		 * Gets the value of the words property.
		 * 
		 * @return possible object is {@link ArrayOfstring }
		 * 
		 */
		public List<String> getWords() {
			if (words == null) {
				words = new ArrayList<String>();
			}
			return words;
		}

		/**
		 * Sets the value of the words property.
		 * 
		 * @param value
		 *            allowed object is {@link ArrayOfstring }
		 * 
		 */
		public void setWords(List<String> value) {
			this.words = value;
		}
	}
}
