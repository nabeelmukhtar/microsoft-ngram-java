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

import java.nio.charset.Charset;
import java.util.HashMap;

import com.microsoft.research.webngram.service.constant.ApplicationConstants;
import com.microsoft.research.webngram.service.constant.ParameterNames;
import com.microsoft.research.webngram.service.constant.NgramServiceApiUrls.NgramServiceApiUrlBuilder;

/**
 * The Class BaseNgramService.
 */
public abstract class BaseNgramService extends
		NgramServiceApiGateway {

	/** The Constant UTF_8_CHAR_SET. */
	protected static final Charset UTF_8_CHAR_SET = Charset
			.forName(ApplicationConstants.CONTENT_ENCODING);

	/** The api url builder. */
	protected NgramServiceApiUrlBuilder apiUrlBuilder;

	/**
	 * Instantiates a new base ngram service.
	 * 
	 * @param applicationId the application id
	 */
	public BaseNgramService(String applicationId) {
		super();
		super.setApplicationKey(applicationId);
		requestHeaders = new HashMap<String, String>();

		// by default we compress contents
		requestHeaders.put("Accept-Encoding", "gzip, deflate");
	}

	/**
	 * Instantiates a new base ngram service.
	 * 
	 * @param applicationId the application id
	 * @param apiVersion the api version
	 */
	public BaseNgramService(String applicationId, String apiVersion) {
		this(applicationId);
		super.setApiVersion(apiVersion);
	}

	/**
	 * Creates the ngram service api url builder.
	 * 
	 * @param urlFormat the url format
	 * 
	 * @return the ngram service api url builder
	 */
	protected NgramServiceApiUrlBuilder createNgramServiceApiUrlBuilder(
			String urlFormat) {
		NgramServiceApiUrlBuilder urlBuilder = new NgramServiceApiUrlBuilder(
				urlFormat);
		urlBuilder.withParameter(ParameterNames.USER_TOKEN, getApplicationKey());
		return urlBuilder;
	}
}
