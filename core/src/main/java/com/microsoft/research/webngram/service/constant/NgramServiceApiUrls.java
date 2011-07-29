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
import java.util.HashMap;
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
	public static final String LOOKUP_URL = academicSearchApiUrls
			.getProperty("com.microsoft.research.webngram.service.lookup");

	/**
	 * Instantiates a new academic search api urls.
	 */
	private NgramServiceApiUrls() {
	}

    /**
     * The Class SpringerApiUrlBuilder.
     */
    public static class NgramServiceApiUrlBuilder {
        
        /** The Constant API_URLS_PLACEHOLDER_START. */
        private static final char API_URLS_PLACEHOLDER_START = '{';

        /** The Constant API_URLS_PLACEHOLDER_END. */
        private static final char API_URLS_PLACEHOLDER_END = '}';
        
    	/** The url format. */
	    private String urlFormat;
	    
    	/** The parameters map. */
	    private Map<String, String> parametersMap = new HashMap<String, String>();
    	
		/** The fields map. */
		private Map<String, String> fieldsMap = new HashMap<String, String>();
	    
    	/**
	     * Instantiates a new springer api url builder.
	     * 
	     * @param urlFormat the url format
	     */
	    public NgramServiceApiUrlBuilder(String urlFormat) {
	    	this(urlFormat, ApplicationConstants.DEFAULT_API_VERSION, ApplicationConstants.DEFAULT_FORMAT);
    	}
    	
    	/**
	     * Instantiates a new springer api url builder.
	     * 
	     * @param urlFormat the url format
	     * @param format the format
	     */
	    public NgramServiceApiUrlBuilder(String urlFormat, String format) {
	    	this(urlFormat, ApplicationConstants.DEFAULT_API_VERSION, format);
    	}
	    
    	/**
	     * Instantiates a new springer api url builder.
	     * 
	     * @param urlFormat the url format
	     * @param apiVersion the api version
	     * @param format the format
	     */
	    public NgramServiceApiUrlBuilder(String urlFormat, String apiVersion, String format) {
    		this.urlFormat = urlFormat;
    		parametersMap.put(ParameterNames.FORMAT, format);
    	}
	    
    	/**
	     * With parameter.
	     * 
	     * @param name the name
	     * @param value the value
	     * 
	     * @return the springer api url builder
	     */
	    public NgramServiceApiUrlBuilder withParameter(String name, String value) {
	    	if (value != null && value.length() > 0) {
	    		parametersMap.put(name, encodeUrl(value));
	    	}
    		
    		return this;
    	}
    	
		/**
		 * With empty field.
		 * 
		 * @param name the name
		 * 
		 * @return the springer api url builder
		 */
		public NgramServiceApiUrlBuilder withEmptyField(String name) {
			fieldsMap.put(name, "");

			return this;
		}

		/**
		 * With field.
		 * 
		 * @param name the name
		 * @param value the value
		 * 
		 * @return the springer api url builder
		 */
		public NgramServiceApiUrlBuilder withField(String name, String value) {
			withField(name, value, false);

			return this;
		}

		/**
		 * With field.
		 * 
		 * @param name the name
		 * @param value the value
		 * @param escape the escape
		 * 
		 * @return the springer api url builder
		 */
		public NgramServiceApiUrlBuilder withField(String name, String value,
				boolean escape) {
			if (escape) {
				fieldsMap.put(name, encodeUrl(value));
			} else {
				fieldsMap.put(name, value);
			}

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
			boolean firstParameter = true;
			for (int i = 0; i < urlFormat.length(); i++) {
				if (urlFormat.charAt(i) == API_URLS_PLACEHOLDER_START) {
					placeHolderBuilder = new StringBuilder();
					placeHolderFlag = true;
				} else if (placeHolderFlag
						&& urlFormat.charAt(i) == API_URLS_PLACEHOLDER_END) {
					String placeHolder = placeHolderBuilder.toString();
					if (fieldsMap.containsKey(placeHolder)) {
						urlBuilder.append(fieldsMap.get(placeHolder));
					} else if (parametersMap.containsKey(placeHolder)) {
						StringBuilder builder = new StringBuilder();
						if (firstParameter) {
							firstParameter = false;
						} else {
							builder.append("&");
						}
						builder.append(placeHolder);
						builder.append("=");
						builder.append(parametersMap.get(placeHolder));
						urlBuilder.append(builder.toString());
					} else {
						// we did not find a binding for the placeholder.
						// skip it.
						// urlBuilder.append(API_URLS_PLACEHOLDER_START);
						// urlBuilder.append(placeHolder);
						// urlBuilder.append(API_URLS_PLACEHOLDER_END);
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
         * @param original the original
         * 
         * @return the string
         */
        private static String encodeUrl(String original) {
        	try {
    			return URLEncoder.encode(original, ApplicationConstants.CONTENT_ENCODING);
    		} catch (UnsupportedEncodingException e) {
    			// should never be here..
    			return original;
    		}
        }
    }
}
