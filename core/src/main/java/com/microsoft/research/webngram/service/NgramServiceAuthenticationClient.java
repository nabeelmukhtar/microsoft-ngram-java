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

/**
 * The Interface NgramServiceAuthenticationClient.
 */
public interface NgramServiceAuthenticationClient extends
		NgramServiceCommunicationClient {

	/**
	 * Sets the application key.
	 * 
	 * @param applicationKey the new application key
	 */
	public void setApplicationKey(String applicationKey);

	/**
	 * Gets the application key.
	 * 
	 * @return the application key
	 */
	public String getApplicationKey();

	/**
	 * Sets the user ip address.
	 * 
	 * @param userIpAddress the new user ip address
	 */
	public void setUserIpAddress(String userIpAddress);

	/**
	 * Sets the referrer.
	 * 
	 * @param referrer the new referrer
	 */
	public void setReferrer(String referrer);
}
