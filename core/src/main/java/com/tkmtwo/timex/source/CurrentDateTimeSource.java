/*
 *
 * Copyright 2014 Tom Mahaffey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tkmtwo.timex.source;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * A <code>DateTimeSource</code> which always returns the current system
 * time.
 *
 *
 * @author Tom Mahaffey
 */
public class CurrentDateTimeSource
  implements DateTimeSource, InitializingBean, FactoryBean, DisposableBean {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  
  
  public CurrentDateTimeSource() { ; }
  

  /**
   *
   * Return the current system DateTime.
   *
   * @return the current system DateTime
   */
  public DateTime getDateTime() { return new DateTime(); }


  /**
   *
   * Return the current system DateTime.
   *
   * @return the current system DateTime
   */
  public DateTime getDateTime(DateTime dt) { return new DateTime(); }
  
  
  
  
  

  @Override
  public void afterPropertiesSet() {
    logger.info("Initializing.");
    logger.info("Initialized CurrentDateTimeSource.");
  }


  @Override
  public Object getObject() { return this; }

  @Override
  public Class getObjectType() { return CurrentDateTimeSource.class; }

  @Override
  public boolean isSingleton() { return true; }

  @Override
  public void destroy() { logger.info("Destroying."); }
}


