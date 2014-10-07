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


import static com.google.common.base.TkmTwoConditions.checkNotBlank;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.springframework.core.convert.converter.Converter;



/**
 * Coverts a <code>String</code> to a JodaTime <code>Period</code>
 * consistent with Spring's <code>Converter</code> facility.
 *
 * <p>
 * This converter uses JodaTime's <code>ISOPeriodFormat.standard()</code>.
 * It expects periods in the format <code>PyYmMwWdDThHmMsS</code>.
 *
 * @author Tom Mahaffey
 * @see org.springframework.core.convert.converter.Converter
 * @see org.joda.time.format.ISODateTimeFormat#basicDateTimeNoMillis()
 */
public final class IsoPeriodConverter
  implements Converter<String, Period> {

  @Override
  public Period convert(String s) {
    return
      ISOPeriodFormat
      .standard()
      .parsePeriod(checkNotBlank(s,
                                 "String for period may not be blank."));
  }
}
