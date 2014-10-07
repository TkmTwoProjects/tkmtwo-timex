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

package com.tkmtwo.timex;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;


/**
 * Utilities for time.
 *
 * @author Tom Mahaffey
 * @version 1.0
 */
public final class Periods {

  private static final PeriodFormatter PF = ISOPeriodFormat.standard();

  public static PeriodFormatter getFormatter() { return PF; }


  /**
   * Print the Period in ISO standard format.
   *
   * @param p a <code>Period</code> value
   * @return a <code>String</code> value
   */
  public static String print(Period p) {
    return getFormatter().print(p);
  }

  /**
   * Parse the Period expecting the ISO standard format.
   *
   * @param s a <code>String</code> value
   * @return a <code>Period</code> value
   */
  public static Period parse(String s) {
    return getFormatter().parsePeriod(s);
  }

}
