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
package com.microsoft.research.webngram.service.constant;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class AcademicSearchApiUrls.
 */
public final class NgramServiceApiUrls {

	/** The Constant API_URLS_FILE. */
	public static final String API_URLS_FILE = "NgramServiceApiUrls.properties";

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(NgramServiceApiUrls.class.getCanonicalName());

	/** The Constant googleApiUrls. */
	private static final Properties academicSearchApiUrls = new Properties();

	static {
		try {
			academicSearchApiUrls.load(NgramServiceApiUrls.class
					.getResourceAsStream(API_URLS_FILE));
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "An error occurred while loading urls.", e);
		}
	}

	/** The Constant SEARCH_URL. */
	public static final String GET_PROBABILITIES_URL = academicSearchApiUrls
			.getProperty("com.microsoft.research.webngram.service.getProbabilities");
	public static final String GET_CONDITIONAL_PROBABILITIES_URL = academicSearchApiUrls
			.getProperty("com.microsoft.research.webngram.service.getConditionalProbabilities");
	public static final String GENERATE_URL = academicSearchApiUrls
			.getProperty("com.microsoft.research.webngram.service.generate");

	/**
	 * Instantiates a new academic search api urls.
	 */
	private NgramServiceApiUrls() {
	}

	/**
	 * The Class AcademicSearchApiUrlBuilder.
	 */
	public static class NgramServiceApiUrlBuilder {

		/** The Constant API_URLS_PLACEHOLDER_START. */
		private static final char API_URLS_PLACEHOLDER_START = '{';

		/** The Constant API_URLS_PLACEHOLDER_END. */
		private static final char API_URLS_PLACEHOLDER_END = '}';

		/** The Constant QUERY_PARAMETERS_PLACEHOLDER. */
		private static final String QUERY_PARAMETERS_PLACEHOLDER = "queryParameters";

		/** The url format. */
		private String urlFormat;

		/** The parameters map. */
		private Map<String, Collection<String>> parametersMap = new HashMap<String, Collection<String>>();

		/**
		 * Instantiates a new academic search api url builder.
		 * 
		 * @param urlFormat
		 *            the url format
		 */
		public NgramServiceApiUrlBuilder(String urlFormat) {
			this(urlFormat, ApplicationConstants.DEFAULT_API_VERSION);
		}

		/**
		 * Instantiates a new academic search api url builder.
		 * 
		 * @param urlFormat
		 *            the url format
		 * @param apiVersion
		 *            the api version
		 */
		public NgramServiceApiUrlBuilder(String urlFormat, String apiVersion) {
			this.urlFormat = urlFormat;
		}

		/**
		 * With parameter.
		 * 
		 * @param name
		 *            the name
		 * @param value
		 *            the value
		 * 
		 * @return the academic search api url builder
		 */
		public NgramServiceApiUrlBuilder withParameter(String name,
				String value) {
			if (value != null && value.length() > 0) {
				Collection<String> values = parametersMap.get(name);
				if (values == null) {
					values = new ArrayList<String>();
					parametersMap.put(name, values);
				}
				values.add(encodeUrl(value));
			}

			return this;
		}

		/**
		 * With parameter.
		 * 
		 * @param name
		 *            the name
		 * @param value
		 *            the value
		 * 
		 * @return the academic search api url builder
		 */
		public NgramServiceApiUrlBuilder withParameter(String name, int value) {
			Collection<String> values = parametersMap.get(name);
			if (values == null) {
				values = new ArrayList<String>();
				parametersMap.put(name, values);
			}
			values.add(encodeUrl(String.valueOf(value)));

			return this;
		}

		/**
		 * With parameter suffix.
		 * 
		 * @param name
		 *            the name
		 * @param suffix
		 *            the suffix
		 * 
		 * @return the academic search api url builder
		 */
		public NgramServiceApiUrlBuilder withParameterSuffix(String name,
				String suffix) {
			if (suffix != null && suffix.length() > 0) {
				Collection<String> values = parametersMap.get(name);
				if (values != null) {
					List<String> updatedValues = new ArrayList<String>(values
							.size());
					for (String value : values) {
						updatedValues.add(encodeUrl(suffix) + value);
					}
					parametersMap.put(name, updatedValues);
				}
			}

			return this;
		}

		/**
		 * With parameters.
		 * 
		 * @param name
		 *            the name
		 * @param values
		 *            the values
		 * 
		 * @return the academic search api url builder
		 */
		public NgramServiceApiUrlBuilder withParameters(String name,
				Collection<String> values) {
			List<String> encodedValues = new ArrayList<String>(values.size());
			for (String value : values) {
				encodedValues.add(encodeUrl(value));
			}
			parametersMap.put(name, encodedValues);

			return this;
		}

		/**
		 * Builds the url.
		 * 
		 * @return the string
		 */
		public String buildUrl() {
			StringBuilder urlBuilder = new StringBuilder();
			StringBuilder placeHolderBuilder = new StringBuilder();
			boolean placeHolderFlag = false;
			for (int i = 0; i < urlFormat.length(); i++) {
				if (urlFormat.charAt(i) == API_URLS_PLACEHOLDER_START) {
					placeHolderBuilder = new StringBuilder();
					placeHolderFlag = true;
				} else if (placeHolderFlag
						&& urlFormat.charAt(i) == API_URLS_PLACEHOLDER_END) {
					String placeHolder = placeHolderBuilder.toString();
					if (QUERY_PARAMETERS_PLACEHOLDER.equals(placeHolder)) {
						StringBuilder builder = new StringBuilder();
						if (!parametersMap.isEmpty()) {
							Iterator<String> iter = parametersMap.keySet()
									.iterator();
							while (iter.hasNext()) {
								String name = iter.next();
								Collection<String> parameterValues = parametersMap
										.get(name);
								Iterator<String> iterParam = parameterValues
										.iterator();
								while (iterParam.hasNext()) {
									builder.append(name);
									builder.append("=");
									builder.append(iterParam.next());
									if (iterParam.hasNext()) {
										builder.append("&");
									}
								}
								if (iter.hasNext()) {
									builder.append("&");
								}
							}
						}
						urlBuilder.append(builder.toString());
					} else {
						// we did not find a binding for the placeholder.
						// append it as it is.
						urlBuilder.append(API_URLS_PLACEHOLDER_START);
						urlBuilder.append(placeHolder);
						urlBuilder.append(API_URLS_PLACEHOLDER_END);
					}
					placeHolderFlag = false;
				} else if (placeHolderFlag) {
					placeHolderBuilder.append(urlFormat.charAt(i));
				} else {
					urlBuilder.append(urlFormat.charAt(i));
				}
			}

			return urlBuilder.toString();
		}

		/**
		 * Encode url.
		 * 
		 * @param original
		 *            the original
		 * 
		 * @return the string
		 */
		private static String encodeUrl(String original) {
			try {
				return URLEncoder.encode(original,
						ApplicationConstants.CONTENT_ENCODING);
			} catch (UnsupportedEncodingException e) {
				// should never be here..
				return original;
			}
		}
	}
}
