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


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import org.joda.time.Duration;
import org.joda.time.Period;


/**
 * <code>WallClock</code> is an immutable class representing the current
 * "wall clock" time.  The "wall clock" time is the time seen by an observer
 * on their clock, on their wall.  There is no concept of timezone in 
 * this class.
 * <p>
 *
 * Internally, the class holds values for the hours, minutes, and seconds
 * field as observed on "the wall clock" as well as the number of seconds
 * since the beginning of the day.  <code>WallClock</code> only supports
 * precision to the second (no milliseconds).
 *
 *
 *
 *
 * @author Tom Mahaffey
 */
public final class WallClock
  implements Comparable<WallClock> {

  private static final int SECONDS_PER_MINUTE = 60;
  private static final int SECONDS_PER_HOUR   = SECONDS_PER_MINUTE * 60;
  private static final int SECONDS_PER_DAY    = SECONDS_PER_HOUR   * 24;


  private final int hour;
  private final int minute;
  private final int second;
  private final int seconds;

  private WallClock(int sod) {
    checkArgument(sod >= 0 && sod <= 86400, "Seconds of day must be in [0...86400].");

    int secs = sod;

    int hs = secs / SECONDS_PER_HOUR;
    secs = secs - (hs * SECONDS_PER_HOUR);

    int ms = secs / SECONDS_PER_MINUTE;
    secs = secs - (ms * SECONDS_PER_MINUTE);

    int ss = secs;

    this.hour     = hs;
    this.minute   = ms;
    this.second   = ss;
    this.seconds  = sod;
  }


  /**
   * Gets the hour of the day.
   * <p>
   *
   * @return an <code>int</code> value
   */
  public int getHour() { return hour; }

  /**
   * Gets the minute of the hour.
   * <p>
   *
   * @return an <code>int</code> value
   */
  public int getMinute() { return minute; }

  /**
   * Gets the second of the minute.
   * <p>
   *
   * @return an <code>int</code> value
   */
  public int getSecond() { return second; }

  /**
   * Gets the seconds since the beginning of the day.
   * <p>
   *
   * @return an <code>int</code> value
   */
  public int getSeconds() { return seconds; }

  private static int sumFields(int hrs, int mins, int secs) {
    int sumSecs = 0;
    sumSecs = IntMath.checkedAdd(sumSecs, IntMath.checkedMultiply(hrs, SECONDS_PER_HOUR));
    sumSecs = IntMath.checkedAdd(sumSecs, IntMath.checkedMultiply(mins, SECONDS_PER_MINUTE));
    sumSecs = IntMath.checkedAdd(sumSecs, secs);
    return sumSecs;
  }



  /**
   * Creates a <code>WallClock</code> based on the number of seconds
   * since the start of the day.
   * <p>
   *
   * Acceptable values are between 0 and 86400 seconds.
   * <p>
   *
   * @param sod an <code>int</code> representing the seconds since the start of the day
   * @return a <code>WallClock</code> value
   */
  public static WallClock valueOf(int sod) {
    return new WallClock(sod);
  }

  /**
   * Creates a <code>WallClock</code> based on the hours, minutes, and
   * seconds since the start of the day.
   * <p>
   *
   * There are no limitations on values, so long as the field
   * arithmetic results in less thab a day's worth of seconds.  For
   * example:
   * <p>
   *
   * <pre>
   * WallClock.valueOf(0, 1, 0) == WallClock.valueOf(0, 0, 60) == WallClock.valueOf(0, 2, -60)
   * </pre>
   *
   *
   * @param hrs an <code>int</code> representing the number of hours
   * @param mins an <code>int</code> representing the number of minutes
   * @param secs an <code>int</code> representing the number of seconds
   * @return a <code>WallClock</code> value
   */
  public static WallClock valueOf(int hrs,
                                  int mins,
                                  int secs) {
    int sod = sumFields(hrs, mins, secs);
    return valueOf(sod);
  }


  /**
   * Creates a <code>WallClock</code> based on the hours, minutes, and
   * seconds since the start of the day.
   *
   *
   * @param hrs a <code>String</code> representing the number of hours
   * @param mins a <code>String</code> representing the number of minutes
   * @param secs a <code>String</code> representing the number of seconds
   * @return a <code>WallClock</code> value
   * @see #valueOf(int, int, int)
   */
  public static WallClock valueOf(String hrs,
                                  String mins,
                                  String secs) {
    int hInt = checkNotNull(Ints.tryParse(hrs),
                            "Hours '%s' is not a valid integer.", hrs).intValue();
    int mInt = checkNotNull(Ints.tryParse(mins),
                            "Minutes '%s' is not a valid integer.", mins).intValue();
    int sInt = checkNotNull(Ints.tryParse(secs),
                            "Seconds '%s' is not a valid integer.", secs).intValue();

    return valueOf(hInt, mInt, sInt);
  }


  /**
   * Creates a <code>WallClock</code> based on a formatted
   * <code>String</code> representing the hours, minutes, and seconds
   * since the start of the day.
   * <p>
   *
   * The format may be either basic (HHmmss) or extended (HH:mm:ss).
   * <p>
   *
   * @param s a <code>String</code> representing the 'clock' time
   * @return a <code>WallClock</code> value
   * @see #valueOf(int, int, int)
   */
  public static WallClock valueOf(String s) {
    return parse(s);
  }


  /**
   * Creates copy of a <code>WallClock</code>.
   * <p>
   *
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>WallClock</code> value
   * @see #valueOf(int)
   */
  public static WallClock valueOf(WallClock wc) {
    checkNotNull(wc, "Need a WallClock.");
    return valueOf(wc.getSeconds());
  }





  /**
   * Creates a <code>WallClock</code> based on a formatted <code>String</code>
   * representing the hours, minutes, and seconds since the start of the day.
   * <p>
   *
   * The format may be either basic (HHmmss) or extended (HH:mm:ss).
   * <p>
   *
   * @param s a <code>String</code> representing the 'clock' time
   * @return a <code>WallClock</code> value
   * @see #valueOf(int, int, int)
   */
  public static WallClock parse(String s) {
    checkNotNull(s, "Need a string.");
    if (s.length() == 6) { return parseBasic(s); }
    if (s.length() == 8) { return parseExtended(s); }
    throw new IllegalArgumentException("Expecting 6 (HHmmss) or 8 (HH:mm:ss) characters.");
  }


  /**
   * Creates a <code>WallClock</code> on a basic-formatted <code>String</code>.
   * <p>
   *
   * This method expects 'HHmmss' (six characters) exactly.  No effort
   * is made to resolve negative field values.
   * <p>
   *
   * @param s a <code>String</code> representing the 'clock' time
   * @return a <code>WallClock</code> value
   * @see #valueOf(int, int, int)
   */
  public static WallClock parseBasic(String s) {
    checkNotNull(s, "Need a string.");
    checkArgument(s.length() == 6, "Expecting 6 characters (HHmmss).");

    return valueOf(s.substring(0, 2),
                   s.substring(2, 4),
                   s.substring(4, 6));
  }


  /**
   * Creates a <code>WallClock</code> on an extended-formatted <code>String</code>.
   * <p>
   *
   * This method expects 'HH:mm:ss' (eight characters) exactly.  No effort is made to
   * resolve negative field values.
   *
   * <p>
   *
   * @param s a <code>String</code> representing the 'clock' time
   * @return a <code>WallClock</code> value
   * @see #valueOf(int, int, int)
   */
  public static WallClock parseExtended(String s) {
    checkNotNull(s, "Need a string.");
    checkArgument(s.length() == 8, "Expecting 8 characters (HH:mm:ss).");

    return valueOf(s.substring(0, 2),
                   s.substring(3, 5),
                   s.substring(6, 8));
  }





  /**
   * Compares this <code>WallClock</code> with another.
   * <p>
   *
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>boolean</code> value
   */
  public boolean isEqual(WallClock wc) {
    if (wc == null) { return false; }
    return getSeconds() == wc.getSeconds();
  }

  /**
   * Compares this <code>WallClock</code> with another.
   * <p>
   *
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>boolean</code> value
   */
  public boolean isBefore(WallClock wc) {
    if (wc == null) { return false; }
    return getSeconds() < wc.getSeconds();
  }

  /**
   * Compares this <code>WallClock</code> with another.
   * <p>
   *
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>boolean</code> value
   */
  public boolean isAfter(WallClock wc) {
    if (wc == null) { return false; }
    return getSeconds() > wc.getSeconds();
  }

  /**
   * Compares this <code>WallClock</code> with another.
   * <p>
   *
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>boolean</code> value
   */
  public boolean isBeforeOrEquals(WallClock wc) {
    return isBefore(wc) || isEqual(wc);
  }

  /**
   * Compares this <code>WallClock</code> with another.
   * <p>
   *
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>boolean</code> value
   */
  public boolean isAfterOrEquals(WallClock wc) {
    return isAfter(wc) || isEqual(wc);
  }





  /**
   * Prints the wall clock time with format 'HHmmss'.
   * <p>
   *
   *
   * @return a <code>boolean</code> value
   */
  public String print() {
    return printBasic();
  }

  /**
   * Prints the wall clock time with format 'HHmmss'.
   * <p>
   *
   *
   * @return a <code>boolean</code> value
   */
  public String printBasic() {
    return String.format("%02d%02d%02d",
                         getHour(),
                         getMinute(),
                         getSecond());
  }

  /**
   * Prints the wall clock time with format 'HH:mm:ss'.
   * <p>
   *
   *
   * @return a <code>boolean</code> value
   */
  public String printExtended() {
    return String.format("%02d:%02d:%02d",
                         getHour(),
                         getMinute(),
                         getSecond());
  }










  /**
   * Returns a copy of this <code>WallClock</code> plus
   * the specified number of seconds.
   * <p>
   *
   * @param secs an <code>int</code> representing the seconds
   * @return a <code>WallClock</code> value
   */
  public WallClock plus(int secs) {
    return valueOf(IntMath.checkedAdd(getSeconds(), secs));
  }

  /**
   * Returns a copy of this <code>WallClock</code> plus
   * the specified <code>Period</code>.
   * <p>
   *
   * <code>Period.toStandardDuration()</code> is used
   * to calculate seconds.
   * <p>
   *
   * @param p an <code>Period</code> representing the amount of time
   * @return a <code>WallClock</code> value
   */
  public WallClock plus(Period p) {
    checkNotNull(p, "Need a Period.");
    return plus(p.toStandardDuration());
  }

  /**
   * Returns a copy of this <code>WallClock</code> plus
   * the specified <code>Duration</code>.
   * <p>
   *
   * Uses <code>RoundingMode.FLOOR</code> to truncate the milliseconds
   * during conversion of milliseconds in the <code>Duration</code> to
   * seconds.
   * <p>
   *
   * @param d an <code>Duration</code> representing the amount of time
   * @return a <code>WallClock</code> value
   */
  public WallClock plus(Duration d) {
    return plus(d, RoundingMode.FLOOR);
  }


  /**
   * Returns a copy of this <code>WallClock</code> plus
   * the specified <code>Duration</code>.
   * <p>
   *
   *
   * @param d an <code>Duration</code> representing the amount of time
   * @param rm a <code>RoundingMode</code> used to deal with long to int division
   * @return a <code>WallClock</code> value
   */
  public WallClock plus(Duration d, RoundingMode rm) {
    checkNotNull(d, "Need a Duration.");
    checkNotNull(rm, "Need a RoundingMode.");
    return plus(Ints.checkedCast(LongMath.divide(d.getMillis(), 1000L, rm)));
  }



  /**
   * Returns a copy of this <code>WallClock</code> plus
   * the specified number of hours, minutes, and seconds.
   * <p>
   *
   *
   * @param hrs an <code>int</code> representing the number of hours
   * @param mins an <code>int</code> representing the number of minutes
   * @param secs an <code>int</code> representing the number of seconds
   * @return a <code>WallClock</code> value
   */
  public WallClock plus(int hrs, int mins, int secs) {
    return plus(sumFields(hrs, mins, secs));
  }

  /**
   * Returns a copy of this <code>WallClock</code> plus
   * the specified number of hours, minutes, and seconds.
   * <p>
   *
   *
   * @param hrs a <code>String</code> representing the number of hours
   * @param mins a <code>String</code> representing the number of minutes
   * @param secs a <code>String</code> representing the number of seconds
   * @return a <code>WallClock</code> value
   */
  public WallClock plus(String hrs, String mins, String secs) {
    return plus(checkNotNull(Ints.tryParse(hrs),
                             "Hours '%s' is not a valid integer.", hrs).intValue(),
                checkNotNull(Ints.tryParse(mins),
                             "Minutes '%s' is not a valid integer.", mins).intValue(),
                checkNotNull(Ints.tryParse(secs),
                             "Seconds '%s' is not a valid integer.", secs).intValue());
  }
  
  
  
  
  
  
  
  
  
  
  /**
   * Returns a copy of this <code>WallClock</code> minus
   * the specified number of seconds.
   * <p>
   *
   * @param secs an <code>int</code> representing the seconds
   * @return a <code>WallClock</code> value
   */
  public WallClock minus(int secs) {
    return valueOf(IntMath.checkedSubtract(getSeconds(), secs));
  }

  /**
   * Returns a copy of this <code>WallClock</code> minus
   * the specified <code>Period</code>.
   * <p>
   *
   * <code>Period.toStandardDuration()</code> is used
   * to calculate seconds.
   * <p>
   *
   * @param p an <code>Period</code> representing the amount of time
   * @return a <code>WallClock</code> value
   */
  public WallClock minus(Period p) {
    checkNotNull(p, "Need a Period.");
    return minus(p.toStandardDuration());
  }


  /**
   * Returns a copy of this <code>WallClock</code> minus
   * the specified <code>Duration</code>.
   * <p>
   *
   * Uses <code>RoundingMode.FLOOR</code> to truncate the milliseconds
   * during conversion of milliseconds in the <code>Duration</code> to
   * seconds.
   * <p>
   *
   * @param d an <code>Duration</code> representing the amount of time
   * @return a <code>WallClock</code> value
   */
  public WallClock minus(Duration d) {
    return minus(d, RoundingMode.FLOOR);
  }

  /**
   * Returns a copy of this <code>WallClock</code> minus
   * the specified <code>Duration</code>.
   * <p>
   *
   *
   * @param d an <code>Duration</code> representing the amount of time
   * @param rm a <code>RoundingMode</code> used to deal with long to int division
   * @return a <code>WallClock</code> value
   */
  public WallClock minus(Duration d, RoundingMode rm) {
    checkNotNull(d, "Need a Duration.");
    checkNotNull(rm, "Need a RoundingMode.");
    return minus(Ints.checkedCast(LongMath.divide(d.getMillis(), 1000L, rm)));
  }





  /**
   * Returns a copy of this <code>WallClock</code> minus
   * the specified number of hours, minutes, and seconds.
   * <p>
   *
   *
   * @param hrs an <code>int</code> representing the number of hours
   * @param mins an <code>int</code> representing the number of minutes
   * @param secs an <code>int</code> representing the number of seconds
   * @return a <code>WallClock</code> value
   */
  public WallClock minus(int hrs, int mins, int secs) {
    return minus(sumFields(hrs, mins, secs));
  }

  /**
   * Returns a copy of this <code>WallClock</code> minus
   * the specified number of hours, minutes, and seconds.
   * <p>
   *
   *
   * @param hrs a <code>String</code> representing the number of hours
   * @param mins a <code>String</code> representing the number of minutes
   * @param secs a <code>String</code> representing the number of seconds
   * @return a <code>WallClock</code> value
   */
  public WallClock minus(String hrs, String mins, String secs) {
    return minus(checkNotNull(Ints.tryParse(hrs),
                              "Hours '%s' is not a valid integer.", hrs).intValue(),
                 checkNotNull(Ints.tryParse(mins),
                              "Minutes '%s' is not a valid integer.", mins).intValue(),
                 checkNotNull(Ints.tryParse(secs),
                              "Seconds '%s' is not a valid integer.", secs).intValue());
  }







  @Override
  public boolean equals(Object obj) {
    if (obj == null) { return false; }
    if (getClass() != obj.getClass()) { return false; }

    final WallClock impl = (WallClock) obj;
    return getSeconds() == impl.getSeconds();
  }

  @Override
  public int hashCode() {
    return getSeconds();
  }

  @Override
  public int compareTo(WallClock wc) {
    if (wc == null) { return 0; }
    return IntMath.checkedSubtract(getSeconds(), wc.getSeconds());
  }



}
