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

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * A <code>DateTimeSource</code> which returns <code>Duration</code>-shifted
 * values.
 *
 *
 * @author Tom Mahaffey
 */
public class DurationDateTimeSource
  implements DateTimeSource, InitializingBean, FactoryBean, DisposableBean {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  private Duration duration;

  public Duration getDuration() { return duration; }
  public void setDuration(Duration d) {
    duration = checkNotNull(d, "Duration may not be null for DurationDateTimeSource.");
  }
  
  public DateTime getDateTime() { return getDateTime(new DateTime()); }
  public DateTime getDateTime(DateTime dt) {
    checkNotNull(dt, "Need a reference DateTime.");
    return dt.plus(getDuration());
  }












  //for InitializingBean
  public void afterPropertiesSet() {
    logger.info("Initializing.");
    checkNotNull(getDuration(), "Need a Duration.");
    logger.info("Initialized.");
  }


  @Override
  public Object getObject() { return this; }

  @Override
  public Class getObjectType() { return DurationDateTimeSource.class; }

  @Override
  public boolean isSingleton() { return true; }

  @Override
  public void destroy() { logger.info("Destroying."); }
}


