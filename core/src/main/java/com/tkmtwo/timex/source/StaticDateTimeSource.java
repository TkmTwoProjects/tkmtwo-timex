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

import static com.google.common.base.Preconditions.checkNotNull;

import com.tkmtwo.timex.DateTimes;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * A <code>DateTimeSource</code> which always returns a static
 * <code>DateTime</code>.
 *
 *
 * @author Tom Mahaffey
 */
public class StaticDateTimeSource
  implements DateTimeSource, InitializingBean, FactoryBean, DisposableBean {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  private DateTime dateTime;



  public static StaticDateTimeSource getInstance(DateTime dt) {
    StaticDateTimeSource sdts = new StaticDateTimeSource();
    sdts.setDateTime(dt);
    sdts.afterPropertiesSet();
    return sdts;
  }

  public StaticDateTimeSource() { ; }
  public StaticDateTimeSource(DateTime dt) {
    setDateTime(dt);
  }
  
  /**
   * Sets the static DateTime.
   *
   * 
   * @param dt  a DateTime to be returned on all calls to getDateTime(...)
   */
  public void setDateTime(DateTime dt) {
    dateTime = checkNotNull(dt, "Reference DateTime can not be null.");
  }


  public DateTime getDateTime() { return dateTime; }
  public DateTime getDateTime(DateTime dt) { return dateTime; }
  
  
  
  
  
  
  
  
  @Override
  public void afterPropertiesSet() {
    logger.info("Initializing.");
    checkNotNull(getDateTime(), "Need a DateTime.");
    logger.info("Initialized static DateTime as {}.",
                DateTimes.printExtended(getDateTime()));
  }
  

  @Override
  public Object getObject() { return this; }

  @Override
  public Class getObjectType() { return StaticDateTimeSource.class; }

  @Override
  public boolean isSingleton() { return true; }

  @Override
  public void destroy() { logger.info("Destroying."); }

}


