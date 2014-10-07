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


import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;




/**
 * Enumeration for <code>DateTime</code> direction.
 *
 *
 * @author Tom Mahaffey
 *
 */
public enum DateTimeDirection {

  FORWARD {
    public DateTime getStart(Interval interval) {
      return checkNotNull(interval, "Need an interval.").getStart();
    }
    public DateTime getEnd(Interval interval) {
      return checkNotNull(interval, "Need an interval.").getEnd();
    }


    public DateTime plus(DateTime dt,
                         long millis) {
      return checkNotNull(dt, "Need a DateTime.").plus(millis);
    }

    public DateTime minus(DateTime dt,
                          long millis) {
      return checkNotNull(dt, "Need a DateTime.").minus(millis);
    }

    public boolean lt(DateTime dtLeft,
                      DateTime dtRight) {
      return DateTimeComparison.LT.compare(dtLeft, dtRight);
    }
    public boolean lteq(DateTime dtLeft,
                        DateTime dtRight) {
      return DateTimeComparison.LTEQ.compare(dtLeft, dtRight);
    }
    public boolean eq(DateTime dtLeft,
                      DateTime dtRight) {
      return DateTimeComparison.EQ.compare(dtLeft, dtRight);
    }
    public boolean neq(DateTime dtLeft,
                       DateTime dtRight) {
      return DateTimeComparison.NEQ.compare(dtLeft, dtRight);
    }
    public boolean gteq(DateTime dtLeft,
                        DateTime dtRight){
      return DateTimeComparison.GTEQ.compare(dtLeft, dtRight);
    }
    public boolean gt(DateTime dtLeft,
                      DateTime dtRight) {
      return DateTimeComparison.GT.compare(dtLeft, dtRight);
    }

    public boolean compare(DateTimeComparison dtc,
                           DateTime dtLeft,
                           DateTime dtRight) {
      return dtc.compare(dtLeft, dtRight);
    }



  },

  REVERSE {
    public DateTime getStart(Interval interval) {
      return checkNotNull(interval, "Need an interval.").getEnd();
    }

    public DateTime getEnd(Interval interval) {
      return checkNotNull(interval, "Need an interval.").getStart();
    }

    public DateTime plus(DateTime dt,
                         long millis) {
      return checkNotNull(dt, "Need a DateTime.").minus(millis);
    }

    public DateTime minus(DateTime dt,
                          long millis) {
      return checkNotNull(dt, "Need a DateTime.").plus(millis);
    }

    public boolean lt(DateTime dtLeft,
                      DateTime dtRight) {
      return DateTimeComparison.GT.compare(dtLeft, dtRight);
    }
    public boolean lteq(DateTime dtLeft,
                        DateTime dtRight) {
      return DateTimeComparison.GTEQ.compare(dtLeft, dtRight);
    }
    public boolean eq(DateTime dtLeft,
                      DateTime dtRight) {
      return DateTimeComparison.EQ.compare(dtLeft, dtRight);
    }
    public boolean neq(DateTime dtLeft,
                       DateTime dtRight) {
      return DateTimeComparison.NEQ.compare(dtLeft, dtRight);
    }
    public boolean gteq(DateTime dtLeft,
                        DateTime dtRight){
      return DateTimeComparison.LTEQ.compare(dtLeft, dtRight);
    }
    public boolean gt(DateTime dtLeft,
                      DateTime dtRight) {
      return DateTimeComparison.LT.compare(dtLeft, dtRight);
    }
    public boolean compare(DateTimeComparison dtc,
                           DateTime dtLeft,
                           DateTime dtRight) {
      boolean b = false;
      switch(dtc) {
      case LT:
        b = DateTimeComparison.GT.compare(dtLeft, dtRight);
        break;
      case LTEQ:
        b = DateTimeComparison.GTEQ.compare(dtLeft, dtRight);
        break;
      case EQ:
        b = DateTimeComparison.EQ.compare(dtLeft, dtRight);
        break;
      case NEQ:
        b = DateTimeComparison.NEQ.compare(dtLeft, dtRight);
        break;
      case GTEQ:
        b = DateTimeComparison.LTEQ.compare(dtLeft, dtRight);
        break;
      case GT:
        b = DateTimeComparison.LT.compare(dtLeft, dtRight);
        break;
      default:
        throw new IllegalArgumentException("DateTimeComparison not recognized.");
      }
      return b;
    }


  };

  public abstract DateTime plus(DateTime dt,
                                long millis);
  public DateTime plus(DateTime dt, Duration duration) {
    return plus(dt, checkNotNull(duration, "Need a duration.").getMillis());
  }
  public DateTime plus(DateTime dt,
                       Period period) {
    return plus(dt, checkNotNull(period, "Need a period.").toStandardDuration());
  }


  public abstract DateTime minus(DateTime dt,
                                 long millis);
  public DateTime minus(DateTime dt,
                        Duration duration) {
    return minus(dt, checkNotNull(duration, "Need a duration.").getMillis());
  }
  public DateTime minus(DateTime dt,
                        Period period) {
    return minus(dt, checkNotNull(period, "Need a period.").toStandardDuration());
  }


  public abstract boolean compare(DateTimeComparison dtc, DateTime dtLeft, DateTime dtRight);
  public abstract DateTime getStart(Interval interval);
  public abstract DateTime getEnd(Interval interval);


  public abstract boolean lt(DateTime dtLeft, DateTime dtRight);
  public abstract boolean lteq(DateTime dtLeft, DateTime dtRight);
  public abstract boolean eq(DateTime dtLeft, DateTime dtRight);
  public abstract boolean neq(DateTime dtLeft, DateTime dtRight);
  public abstract boolean gt(DateTime dtLeft, DateTime dtRight);
  public abstract boolean gteq(DateTime dtLeft, DateTime dtRight);
}
