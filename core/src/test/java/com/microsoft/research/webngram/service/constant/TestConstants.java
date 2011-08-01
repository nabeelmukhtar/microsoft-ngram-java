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
import java.util.Properties;

/**
 * The Class TestConstants.
 */
public final class TestConstants {

    /** The Constant TEST_CONSTANTS_FILE. */
    public static final String TEST_CONSTANTS_FILE = "TestConstants.properties";

    /** The Constant testConstants. */
    private static final Properties testConstants = new Properties();

    static {
        try {
            testConstants.load(TestConstants.class.getResourceAsStream(TEST_CONSTANTS_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** The Constant TEST_API_KEY. */
    public static final String TEST_API_KEY =
        testConstants.getProperty("com.microsoft.research.webngram.service.apiKey");
    
    /** The Constant TEST_REFERRER. */
    public static final String TEST_REFERRER =
        testConstants.getProperty("com.microsoft.research.webngram.service.referrer");

    /** The Constant TEST_MODEL_URN. */
    public static final String TEST_MODEL_URN =
        testConstants.getProperty("com.microsoft.research.webngram.service.testModelUrn");
    
    /** The Constant TEST_QUERY. */
    public static final String TEST_QUERY =
        testConstants.getProperty("com.microsoft.research.webngram.service.testQuery");
	
    /**
     * Instantiates a new test constants.
     */
    private TestConstants() {}
}
