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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;


/**
 * Enumeration for <code>DateTime</code> fields with behaviors.
 *
 * This is essentially a set of utility methods around <code>DateTime.Property</code>
 * which allows convenient access to rounding and adding.  The added benefit is that
 * the properties are enumerated, so the field-level access is more declarative.
 *
 * @author Tom Mahaffey
 * @version $Id$
 */
public enum DateTimeFields {

  /** To the millisecond. */
  MILLIS {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.millisOfSecond();
    }
  },
  /** To the second. */
  SECOND {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.secondOfMinute();
    }
  },
  /** To the minute. */
  MINUTE {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.minuteOfHour();
    }
  },
  /** To the hour. */
  HOUR {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.hourOfDay();
    }
  },
  /** To the day. */
  DAY {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.dayOfMonth();
    }
  },
  /** To the month. */
  MONTH {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.monthOfYear();
    }
  },
  /** To the year. */
  YEAR {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.yearOfCentury();
    }
  },
  /** To the century. */
  CENTURY {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.centuryOfEra();
    }
  };


  /**
   * Gets the <code>DateTime</code> property.
   *
   *
   * @param dateTime
   */
  public abstract DateTime.Property getProperty(DateTime dateTime);






  /**
   * Adds field units to a <code>DateTime</code>.
   *
   * @param dateTime a <code>DateTime</code>.
   * @param i a number of units to add.
   */
  public DateTime add(DateTime dateTime, int i) {
    return getProperty(dateTime).addToCopy(i);
  }



  /**
   * Rounds the current <code>DateTime</code> down.
   *
   * @return a rounded <code>DateTime</code>
   */
  public DateTime current() {
    return currentFloor();
  }
  public String currentString() {
    return currentFloorString();
  }





  /**
   * Rounds the current <code>DateTime</code> down.
   *
   * @return a rounded <code>DateTime</code>
   */
  public DateTime currentFloor() {
    return roundFloor(new DateTime());
  }
  public String currentFloorString() {
    return roundFloorString(new DateTime());
  }

  /**
   * Rounds the current <code>DateTime</code> up.
   *
   * @return a rounded <code>DateTime</code>
   */
  public DateTime currentCeiling() {
    return roundCeiling(new DateTime());
  }
  public String currentCeilingString() {
    return roundCeilingString(new DateTime());
  }













  /**
   * Rounds a <code>DateTime</code> down.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @return a rounded <code>DateTime</code>
   */
  public DateTime roundFloor(DateTime dateTime) {
    return getProperty(dateTime).roundFloorCopy();
  }
  public String roundFloorString(DateTime dateTime) {
    return roundFloorString(DateTimes.getFormatter(), dateTime);
  }
  public String roundFloorString(DateTimeFormatter dateTimeFormatter,
                                 DateTime dateTime) {
    return dateTimeFormatter.print(roundFloor(dateTime));
  }








  /**
   * Rounds a <code>DateTime</code> down and adds field units.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @param i a number of units to add.
   * @return a rounded <code>DateTime</code> with units added.
   */
  public DateTime roundFloorAdd(DateTime dateTime, int i) {
    DateTime dt = roundFloor(dateTime);
    return add(dt, i);
  }
  public String roundFloorAddString(DateTime dateTime,
                                    int i) {
    return roundFloorAddString(DateTimes.getFormatter(), dateTime, i);
  }

  public String roundFloorAddString(DateTimeFormatter dateTimeFormatter,
                                    DateTime dateTime,
                                    int i) {
    return dateTimeFormatter.print(roundFloorAdd(dateTime, i));
  }





  /**
   * Rounds a <code>DateTime</code> up.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @return a rounded <code>DateTime</code>
   */
  public DateTime roundCeiling(DateTime dateTime) {
    return getProperty(dateTime).roundCeilingCopy();
  }
  public String roundCeilingString(DateTime dateTime) {
    return roundCeilingString(DateTimes.getFormatter(), dateTime);
  }
  public String roundCeilingString(DateTimeFormatter dateTimeFormatter,
                                   DateTime dateTime) {
    return dateTimeFormatter.print(roundCeiling(dateTime));
  }



  /**
   * Rounds a <code>DateTime</code> up and adds field units.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @param i a number of units to add.
   * @return a rounded <code>DateTime</code> with units added.
   */
  public DateTime roundCeilingAdd(DateTime dateTime, int i) {
    DateTime dt = roundCeiling(dateTime);
    return add(dt, i);
  }
  public String roundCeilingAddString(DateTime dateTime,
                                      int i) {
    return roundCeilingAddString(DateTimes.getFormatter(), dateTime, i);
  }
  public String roundCeilingAddString(DateTimeFormatter dateTimeFormatter,
                                      DateTime dateTime,
                                      int i) {
    return dateTimeFormatter.print(roundCeilingAdd(dateTime, i));
  }


}
