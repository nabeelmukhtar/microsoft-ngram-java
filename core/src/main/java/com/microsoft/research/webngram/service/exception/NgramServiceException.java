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
package com.microsoft.research.webngram.service.exception;

/**
 * The Class AcademicSearchException.
 */
public class NgramServiceException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2392119987027760999L;
	
	/** The result code. */
	private int resultCode;

	/**
	 * Instantiates a new academic search exception.
	 */
	public NgramServiceException() {
	}

	/**
	 * Instantiates a new academic search exception.
	 * 
	 * @param message the message
	 */
	public NgramServiceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new academic search exception.
	 * 
	 * @param message the message
	 * @param resultCode the result code
	 */
	public NgramServiceException(String message, int resultCode) {
		super(message);
		this.resultCode = resultCode;
	}
	
	/**
	 * Instantiates a new academic search exception.
	 * 
	 * @param cause the cause
	 */
	public NgramServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new academic search exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public NgramServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Gets the result code.
	 * 
	 * @return the result code
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * Sets the result code.
	 * 
	 * @param resultCode the new result code
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
