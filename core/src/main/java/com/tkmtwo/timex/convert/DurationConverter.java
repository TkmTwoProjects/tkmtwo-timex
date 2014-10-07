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

package com.tkmtwo.timex.convert;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;


/**
 * Coverts a <code>String</code> to a JodaTime <code>Duration</code>
 * consistent with Spring's <code>Converter</code> facility.
 *
 * <p>
 * Any expression which can be evaluated as a <code>long</code> value
 * is allowed.
 *
 * @author Tom Mahaffey
 * @see org.springframework.core.convert.converter.Converter
 * @see org.joda.time.format.ISODateTimeFormat#basicDateTimeNoMillis()
 */
public final class DurationConverter
  implements Converter<String, Duration> {

  private static final Logger logger = LoggerFactory.getLogger(DurationConverter.class);
  private static final ExpressionParser parser = new SpelExpressionParser();
  
  @Override
  public Duration convert(String s) {
    logger.debug("Converting '{}' to a Duration.", s);
    checkArgument(!isNullOrEmpty(s), "String may not be empty.");

    Long l = null;

    try {
      l = parser.parseExpression(s).getValue(Long.class);
    } catch (Exception ex) {
      throw new IllegalArgumentException("String '" + s + "' cannot be converted.", ex);
    }
    
    checkNotNull(l, "String '" + s + "' evaluated to null.");
    return new Duration(l.longValue());
  }
  
  
}
