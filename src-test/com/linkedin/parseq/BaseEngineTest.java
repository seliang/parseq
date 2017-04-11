/*
 * Copyright 2012 LinkedIn, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.linkedin.parseq;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


/**
 * A base class that builds an Engine with default configuration.
 *
 * This class creates new Engine and shuts it down before and after every test method, so it can't be used
 * to run tests in parallel.
 *
 * The difference between this class and {@link BaseEngineParTest} is that {@code BaseEngineParTest} creates and
 *
 * @author Chris Pettitt (cpettitt@linkedin.com)
 * @author Jaroslaw Odzga (jodzga@linkedin.com)
 *
 * @see ParSeqUnitTestHelper
 * @see BaseEngineParTest
 */
public class BaseEngineTest extends AbstractBaseEngineTest {

  @BeforeMethod
  public void setUp() throws Exception {
    getParSeqUnitTestHelper().setUp();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    if (getEngine() != null) {
      getParSeqUnitTestHelper().tearDown();
    }  else {
      throw new RuntimeException("Tried to shut down Engine but it either has not even been created or has "
          + "already been shut down. Please make sure you are not running unit tests in parallel. If you need to "
          + "run unit tests in parallel, then use BaseEngineParTest instead.");
    }
  }

  protected void customizeEngine(EngineBuilder engineBuilder) {
  }

}
