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

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;



/**
 *
 * Utilities for Intervals.
 *
 *
 * @author Tom Mahaffey
 *
 */
public final class Intervals {
  
  
  public static Interval noMillis(Interval i) {
    return new Interval(DateTimes.noMillis(i.getStart()),
                        DateTimes.noMillis(i.getEnd()));
  }
  
  
  
  /**
   * Print the Interval in ISO basic format with no milliseconds
   * in the UTC time zone.
   *
   * The format is START/END with a slash in between.
   *
   * This is a good format to use for human-readable applications
   * like error messages or application logs.
   *
   * @param i an Interval value
   * @return a String value
   */
  public static String printBasic(Interval i) {

    checkNotNull(i, "Input interval is null.");
    return
      DateTimes.getBasicFormatter().print(i.getStart())
      + "/"
      + DateTimes.getBasicFormatter().print(i.getEnd());
    
  }
  
  
  
  
  
  /**
   * Print the Interval in ISO extended format with no milliseconds
   * in the UTC time zone.
   *
   * The format is START/END with a slash in between.
   *
   * This is a good format to use for human-readable applications
   * like error messages or application logs.
   *
   * @param i an Interval value
   * @return a String value
   */
  public static String printExtended(Interval i) {

    checkNotNull(i, "Input interval is null.");
    return
      DateTimes.getExtendedFormatter().print(i.getStart())
      + "/"
      + DateTimes.getExtendedFormatter().print(i.getEnd());
    
  }
  
  
  
  
  
  
  
  private static List<Interval> intervals(List<DateTime> dateTimes) {
    checkNotNull(dateTimes, "Need a list of DateTimes.");
    checkArgument(dateTimes.size() > 1, "Need more than one DateTime.");

    List<Interval> intervals = new ArrayList<Interval>();
    DateTime dtStart = dateTimes.get(0);
    for (int i = 1; i < dateTimes.size(); i++) {
      DateTime dtStop = dateTimes.get(i);
      Interval interval = new Interval(DateTimes.lesser(dtStart, dtStop),
                                       DateTimes.greater(dtStart, dtStop));
      intervals.add(interval);
      dtStart = dtStop;
    }
    return intervals;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static Interval fromSecs(Long startSecs, Long endSecs) {
    return new Interval(DateTimes.fromSecs(startSecs),
                        DateTimes.fromSecs(endSecs));
  }
  public static Interval fromSecs(long startSecs, long endSecs) {
    return new Interval(DateTimes.fromSecs(startSecs),
                        DateTimes.fromSecs(endSecs));
  }
  public static Interval fromSecs(Integer startSecs, Integer endSecs) {
    return new Interval(DateTimes.fromSecs(startSecs),
                        DateTimes.fromSecs(endSecs));
  }
  public static Interval fromSecs(int startSecs, int endSecs) {
    return new Interval(DateTimes.fromSecs(startSecs),
                        DateTimes.fromSecs(endSecs));
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  /*
   * INTERVALS BY INTERVAL
   * INTERVALS BY INTERVAL
   * INTERVALS BY INTERVAL
   */
  
  
  
  public static List<Interval> intervals(Interval interval,
                                         Period period) {
    return intervals(DateTimeDirection.FORWARD, interval, period);
  }
  public static List<Interval> intervals(DateTimeDirection dtDirection,
                                         Interval interval,
                                         Period period) {
    return intervals(dtDirection, interval, checkNotNull(period, "Need a period.").toStandardDuration());
  }
  
  
  
  public static List<Interval> intervals(Interval interval,
                                         Duration duration) {
    return intervals(DateTimeDirection.FORWARD, interval, duration);
  }
  public static List<Interval> intervals(DateTimeDirection dtDirection,
                                         Interval interval,
                                         Duration duration) {
    return intervals(dtDirection, interval, checkNotNull(duration, "Need a duration.").getMillis());
    
  }
  
  
  
  
  public static List<Interval> intervals(Interval interval,
                                         long duraMillis) {
    return intervals(DateTimeDirection.FORWARD, interval, duraMillis);
  }
  
  
  
  /**
   * Split an Interval into smaller Intervals.
   * <p>
   *
   * This is very similar to splits(DateTimeDirection, Interval, long)
   * in that an Interval is spit up.  This method goes on to create
   * intermediate Intervals.
   * <p>
   *
   * Using the same example from splits(DateTimeDirection, Interval, long):
   * <p>
   *
   * <pre>
   *    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
   *    Interval interval = new Interval(new DateTime(0L),
   *                                     new DateTime(240010L));
   *    long duraLong = 60000L;
   *
   *
   *    List<Interval> intervals = DateTimes.intervals(dtDirection,
   *                                                   interval,
   *                                                   duraLong);
   *
   *
   *    for (int i = 0; i &lt; intervals.size(); i++) {
   *      System.out.println(String.format("Interval %d is %s -> %s which is %8dms to %8dms.",
   *                                       i,
   *                                       intervals.get(i).getStart().toString(),
   *                                       intervals.get(i).getEnd().toString(),
   *                                       intervals.get(i).getStart().getMillis(),
   *                                       intervals.get(i).getEnd().getMillis()));
   *    }
   *
   * </pre>
   *
   * Will print:
   * <p>
   *
   * <pre>
   * Interval 0 is 1970-01-01T00:00:00.000Z -> 1970-01-01T00:01:00.000Z which is        0ms to    60000ms.
   * Interval 1 is 1970-01-01T00:01:00.000Z -> 1970-01-01T00:02:00.000Z which is    60000ms to   120000ms.
   * Interval 2 is 1970-01-01T00:02:00.000Z -> 1970-01-01T00:03:00.000Z which is   120000ms to   180000ms.
   * Interval 3 is 1970-01-01T00:03:00.000Z -> 1970-01-01T00:04:00.000Z which is   180000ms to   240000ms.
   * </pre>
   *
   * Or, using DateTimeDirection.REVERSE:
   *
   * <pre>
   * Interval 0 is 1970-01-01T00:03:00.010Z -> 1970-01-01T00:04:00.010Z which is   180010ms to   240010ms.
   * Interval 1 is 1970-01-01T00:02:00.010Z -> 1970-01-01T00:03:00.010Z which is   120010ms to   180010ms.
   * Interval 2 is 1970-01-01T00:01:00.010Z -> 1970-01-01T00:02:00.010Z which is    60010ms to   120010ms.
   * Interval 3 is 1970-01-01T00:00:00.010Z -> 1970-01-01T00:01:00.010Z which is       10ms to    60010ms.
   * </pre>
   *
   *
   *
   *
   *
   * @param dtDirection a DateTimeDirection value
   * @param interval an Interval value
   * @param duraMillis a long value specifying the duration between splits
   * @return a List&lt;Interval&gt; value
   *
   */
  public static List<Interval> intervals(DateTimeDirection dtDirection,
                                         Interval interval,
                                         long duraMillis) {
    return intervals(DateTimes.splits(dtDirection, interval, duraMillis));
  }
  
  
  
  
  
  
  
  
  /*
   * INTERVALS BY SIZE
   * INTERVALS BY SIZE
   * INTERVALS BY SIZE
   */

  public static List<Interval> intervals(DateTime start,
                                         long size,
                                         Period period) {
    return intervals(DateTimeDirection.FORWARD, start, size, period);
  }

  public static List<Interval> intervals(DateTimeDirection dtDirection,
                                         DateTime start,
                                         long size,
                                         Period period) {
    return intervals(dtDirection, start, size, checkNotNull(period, "Need a period.").toStandardDuration());
  }
  public static List<Interval> intervals(DateTime start,
                                         long size,
                                         Duration duration) {
    return intervals(DateTimeDirection.FORWARD, start, size, duration);
  }
  public static List<Interval> intervals(DateTimeDirection dtDirection,
                                         DateTime start,
                                         long size,
                                         Duration duration) {
    return intervals(dtDirection, start, size, checkNotNull(duration, "Need a duration.").getMillis());
  }
  public static List<Interval> intervals(DateTime start,
                                         long size,
                                         long duraMillis) {
    return intervals(DateTimeDirection.FORWARD, start, size, duraMillis);
  }
  public static List<Interval> intervals(DateTimeDirection dtDirection,
                                         DateTime start,
                                         long size,
                                         long duraMillis) {
    return intervals(DateTimes.splits(dtDirection, start, size + 1, duraMillis));
  }
  
  
  
  
  
}
