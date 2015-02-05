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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;




/**
 *
 * Utilities for DateTimes.
 *
 *
 * @author Tom Mahaffey
 *
 */
public final class DateTimes {

  private static final DateTimeFormatter DTF_BASIC =
    ISODateTimeFormat.basicDateTimeNoMillis().withZoneUTC();
  private static final DateTimeFormatter DTF_EXTENDED =
    ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();
  
  
  public static final String BASIC_DATETIME_NOMILLIS_SPEC =
    "\\d{8}T\\d{6}Z";
  public static final Pattern BASIC_DATETIME_NOMILLIS_PATTERN =
    Pattern.compile(BASIC_DATETIME_NOMILLIS_SPEC);

  public static DateTimeFormatter getFormatter() { return DTF_BASIC; }
  public static DateTimeFormatter getBasicFormatter() { return DTF_BASIC; }
  public static DateTimeFormatter getExtendedFormatter() { return DTF_EXTENDED; }


  /*
   * Return current DateTime without any milliseconds.
   *
   * @return a DateTime value
   */
  public static DateTime noMillis() {
    //return noMillis(new DateTime());
    return new DateTime().secondOfMinute().roundFloorCopy();
  }


  /**
   * Return the DateTime with no milliseconds.
   *
   * @param l a long value
   * @return a DateTime value
   */
  public static DateTime noMillis(long l) {
    //return noMillis(new DateTime(l));
    return new DateTime(l).secondOfMinute().roundFloorCopy();
  }

  /**
   * Return the DateTime with no milliseconds.
   *
   * @param dt a DateTime value
   * @return a DateTime value
   */
  public static DateTime noMillis(DateTime dt) {
    if (dt == null) { return null; }
    return dt.secondOfMinute().roundFloorCopy();
  }



  /**
   * Print the DateTime in ISO basic format with no milliseconds
   * in the UTC time zone (yyyyMMdd'T'HHmmssZ).
   *
   * This is a good format to use for data storage either in a
   * database or for file names.
   *
   *
   * @param ri a ReadableInstant value
   * @return a String value
   */
  public static String printBasic(ReadableInstant ri) {
    //checkNotNull(ri, "Input instant is null.");
    if (ri == null) { return ""; }
    return DTF_BASIC.print(ri);
  }


  /**
   * Parse the DateTime from the ISO basic format with no milliseconds
   * in the UTC time zone (yyyyMMdd'T'HHmmssZ).
   *
   * @param s a String value
   * @return a DateTime value
   */
  public static DateTime parseBasic(String s) {
    checkNotNull(s, "Input string is null.");
    return DTF_BASIC.parseDateTime(s);
  }





  /**
   * Print the DateTime in ISO extended format with no milliseconds
   * in the UTC time zone (yyyy-MM-dd'T'HH:mm:ssZ).
   *
   * This is a good format to use for human-readable applications
   * like error messages or application logs.
   *
   * @param ri a ReadableInstant value
   * @return a String value
   */
  public static String printExtended(ReadableInstant ri) {
    //checkNotNull(ri, "Input instant is null.");
    if (ri == null) { return ""; }
    return DTF_EXTENDED.print(ri);
  }
  
  
  

  /**
   * Parse the DateTime from the ISO extended format with no milliseconds
   * in the UTC time zone (yyyy-MM-dd'T'HH:mm:ssZ).
   *
   * @param s a String value
   * @return a DateTime value
   */
  public static DateTime parseExtended(String s) {
    checkNotNull(s, "Input string is null.");
    return DTF_EXTENDED.parseDateTime(s);
  }








  /**
   * Create a new DateTime from the
   * seconds of the day.
   *
   *
   * @param l a Long value
   * @return a DateTime value
   */
  public static DateTime fromSecs(Long l) {
    if (l == null) {
      return null;
    }
    return fromSecs(l.longValue());
  }

  /**
   * Create a new DateTime from the
   * seconds of the day.
   *
   * @param l a long value
   * @return a DateTime value
   */
  public static DateTime fromSecs(long l) {
    return new DateTime(l * 1000L);
  }




  /**
   * Create a new DateTime from the
   * seconds of the day.
   *
   * @param i an Integer value
   * @return a DateTime value
   */
  public static DateTime fromSecs(Integer i) {
    if (i == null) {
      return null;
    }
    return fromSecs(i.intValue());
  }

  /**
   * Create a new DateTime from the
   * seconds of the day.
   *
   * @param i an int value
   * @return a DateTime value
   */
  public static DateTime fromSecs(int i) {
    return new DateTime((long) i * 1000L);
  }









  /**
   * Get the seconds of an instant.
   *
   *
   * @param rt a ReadableInstant value
   * @return a long value
   */
  public static long getSecs(ReadableInstant rt) {
    return getSecsAsLong(rt);
  }

  /**
   * Get the seconds of an instant.
   *
   * @param rt a ReadableInstant value
   * @return a long value
   */
  public static long getSecsAsLong(ReadableInstant rt) {
    if (rt == null) {
      throw new IllegalArgumentException("Can not get seconds for null DateTime.");
    }
    return rt.getMillis() / 1000L;
  }

  /**
   * Get the seconds of an instant.
   *
   * @param rt a ReadableInstant value
   * @return an int value
   */
  public static int getSecsAsInt(ReadableInstant rt) {
    return (int) getSecsAsLong(rt);
  }


  /**
   * Get the seconds of an instant.
   *
   * @param rt a ReadableInstant value
   * @return a String value
   */
  public static String getSecsAsString(ReadableInstant rt) {
    return String.valueOf(getSecsAsLong(rt));
  }












  /**
   * Calculate split DateTimes over an Interval.
   * <p>
   *
   * Uses period.toStandardDuration().getMillis() for the number
   * of milliseconds between splits, and
   * DateTimeDirection.FORWARD for the direction.
   *
   * <p>
   *
   * @param interval an Interval value
   * @param period a Period value
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, Interval, long)
   *
   */
  public static List<DateTime> splits(Interval interval,
                                      Period period) {
    return splits(DateTimeDirection.FORWARD,
                  interval,
                  period);
  }


  /**
   * Calculate split DateTimes over an Interval.
   * <p>
   *
   * Uses period.toStandardDuration().getMillis() for the number
   * of milliseconds between splits.
   * <p>
   *
   * @param dtDirection a DateTimeDirection value
   * @param interval an Interval value
   * @param period a Period value
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, Interval, long)
   *
   */
  public static List<DateTime> splits(DateTimeDirection dtDirection,
                                      Interval interval,
                                      Period period) {
    return splits(dtDirection, interval, checkNotNull(period, "Need a period.").toStandardDuration());
  }


  /**
   * Calculate split DateTimes over an Interval.
   * <p>
   *
   * Uses duration.getMillis() for the number
   * of milliseconds between splits and
   * DateTimeDirection.FORWARD for the direction.
   * <p>
   *
   * @param interval an Interval value
   * @param duration a Duration value
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, Interval, long)
   *
   */
  public static List<DateTime> splits(Interval interval,
                                      Duration duration) {
    return splits(DateTimeDirection.FORWARD,
                  interval,
                  duration);
  }


  /**
   * Calculate split DateTimes over an Interval.
   * <p>
   *
   * Uses duration.getMillis() for the number
   * of milliseconds between splits.
   * <p>
   *
   * @param dtDirection a DateTimeDirection value
   * @param interval an Interval value
   * @param duration a Duration value
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, Interval, long)
   *
   */
  public static List<DateTime> splits(DateTimeDirection dtDirection,
                                      Interval interval,
                                      Duration duration) {
    return splits(dtDirection, interval, checkNotNull(duration, "Need a duration.").getMillis());
  }


  /**
   * Calculate split DateTimes over an Interval.
   * <p>
   *
   * Uses DateTimeDirection.FORWARD for the direction.
   * <p>
   *
   * @param interval an Interval value
   * @param duraMillis a long value specifying the duration between splits
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, Interval, long)
   *
   */
  public static List<DateTime> splits(Interval interval,
                                      long duraMillis) {
    return splits(DateTimeDirection.FORWARD, interval, duraMillis);
  }




  /**
   * Calculate split DateTimes over an Interval.
   * <p>
   *
   * This method will start at the beginning of the Interval and
   * add the specified number of milliseconds for each split.  Splits will
   * continue to be calculated until the end of the Interval is reached
   * or overshot.
   * <p>
   *
   * A DateTimeDirection will either start at the beginning of the
   * Interval and work forward, or start at the end and work backward.
   * <p>
   *
   * The end of the Interval may or may not be included in the splits.  This
   * depends on whether duraMillis divides into interval.getMillis()
   * evenly or not.  For example:
   * <p>
   *
   *
   *<pre>
   *
   *    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
   *    Interval interval = new Interval(new DateTime(0L),
   *                                     new DateTime(240010L));
   *    long duraLong = 60000L;
   *
   *    List<DateTime> dts = DateTimes.splits(dtDirection,
   *                                          interval,
   *                                          duraLong);
   *    for (int i = 0; i &lt; dts.size(); i++) {
   *      System.out.println(String.format("Split %d is %s which is %8dms.",
   *                                       i,
   *                                       dts.get(i).toString(),
   *                                       dts.get(i).getMillis()));
   *    }
   *
   *</pre>
   *
   * Will print:
   * <p>
   *
   * <pre>
   *
   * Split 0 is 1970-01-01T00:00:00.000Z which is        0ms.
   * Split 1 is 1970-01-01T00:01:00.000Z which is    60000ms.
   * Split 2 is 1970-01-01T00:02:00.000Z which is   120000ms.
   * Split 3 is 1970-01-01T00:03:00.000Z which is   180000ms.
   * Split 4 is 1970-01-01T00:04:00.000Z which is   240000ms.
   *
   * </pre>
   *
   * Even though the end of the Interval is 240010L.  Splits are calculated
   * based on the whole increments of duraMillis which will fit in
   * the Interval.  If the direction is reversed with
   * DateTimeDirection.REVERSE:
   * <p>
   *
   * <pre>
   *
   * Split 0 is 1970-01-01T00:04:00.010Z which is   240010ms.
   * Split 1 is 1970-01-01T00:03:00.010Z which is   180010ms.
   * Split 2 is 1970-01-01T00:02:00.010Z which is   120010ms.
   * Split 3 is 1970-01-01T00:01:00.010Z which is    60010ms.
   * Split 4 is 1970-01-01T00:00:00.010Z which is       10ms.
   *
   * </pre>
   *
   * Is printed.
   *
   *
   * @param dtDirection a DateTimeDirection value
   * @param interval an Interval value
   * @param duraMillis a long value specifying the duration between splits
   * @return a List&lt;DateTime&gt; value
   */
  public static List<DateTime> splits(DateTimeDirection dtDirection,
                                      Interval interval,
                                      long duraMillis) {
    checkNotNull(dtDirection, "Need a direction.");
    checkNotNull(interval, "Need an interval.");
    checkArgument(duraMillis != 0L, "DuraMillis can not be zero.");
    checkArgument(interval.toDurationMillis() >= duraMillis,
                  "Interval must be greater than or equal to the DuraMillis.");

    List<DateTime> dts = new ArrayList<DateTime>();

    for (DateTime dt = dtDirection.getStart(checkNotNull(interval));
         dtDirection.lteq(dt, dtDirection.getEnd(interval));
         dt = dtDirection.plus(dt, duraMillis)) {
      dts.add(dt);
    }

    /* START */
    //if (!interval.getEnd().isEqual(dts.get(dts.size() - 1))) {
    if (!dtDirection.getEnd(interval).isEqual(dts.get(dts.size() - 1))) {
      dts.add(interval.getEnd());
    }
    /* STOP */

    return dts;
  }


















  /**
   * Calculate split DateTimes using a start time, a step duration,
   * and number of steps.
   * <p>
   *
   * Uses period.toStandardDuration().getMillis() as the duration
   * and DateTimeDirection.FORWARD as the direction.
   *
   *
   * @param start a DateTime
   * @param size a long value
   * @param period a Period value specifying the duration between splits/steps
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, DateTime, long, long)
   *
   */
  public static List<DateTime> splits(DateTime start,
                                      long size,
                                      Period period) {
    return splits(DateTimeDirection.FORWARD, start, size, period);
  }

  /**
   * Calculate split DateTimes using a start time, a step duration,
   * and number of steps.
   * <p>
   *
   * Uses period.toStandardDuration().getMillis() as the duration.
   *
   *
   * @param dtDirection a DateTimeDirection value
   * @param start a DateTime
   * @param size a long value
   * @param period a Period value specifying the duration between splits/steps
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, DateTime, long, long)
   *
   */
  public static List<DateTime> splits(DateTimeDirection dtDirection,
                                      DateTime start,
                                      long size,
                                      Period period) {
    return splits(dtDirection, start, size, checkNotNull(period, "Need a period.").toStandardDuration());
  }




  /**
   * Calculate split DateTimes using a start time, a step duration,
   * and number of steps.
   * <p>
   *
   * Uses duration.getMillis() as the duration and
   * DateTimeDirection.FORWARD for the direction.
   *
   *
   * @param start a DateTime
   * @param size a long value
   * @param duration a Duration value specifying the duration between splits/steps
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, DateTime, long, long)
   *
   */
  public static List<DateTime> splits(DateTime start,
                                      long size,
                                      Duration duration) {
    return splits(DateTimeDirection.FORWARD, start, size, duration);
  }



  /**
   * Calculate split DateTimes using a start time, a step duration,
   * and number of steps.
   * <p>
   *
   * Uses duration.getMillis() as the duration.
   *
   *
   * @param dtDirection a DateTimeDirection value
   * @param start a DateTime
   * @param size a long value
   * @param duration a Duration value specifying the duration between splits/steps
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, DateTime, long, long)
   *
   */
  public static List<DateTime> splits(DateTimeDirection dtDirection,
                                      DateTime start,
                                      long size,
                                      Duration duration) {
    return splits(dtDirection, start, size, checkNotNull(duration, "Need a duration.").getMillis());
  }



  /**
   * Calculate split DateTimes using a start time, a step duration,
   * and number of steps.
   * <p>
   *
   * Uses DateTimeDirection.FORWARD as the direction.
   *
   *
   * @param start a DateTime
   * @param size a long value
   * @param duraMillis a long value specifying the duration between splits/steps
   * @return a List&lt;DateTime&gt; value
   * @see #splits(DateTimeDirection, DateTime, long, long)
   *
   */
  public static List<DateTime> splits(DateTime start,
                                      long size,
                                      long duraMillis) {
    return splits(DateTimeDirection.FORWARD, start, size, duraMillis);
  }






  /**
   * Calculate split DateTimes using a start time, a step duration,
   * and number of steps.
   * <p>
   *
   * This method will begin at DateTime start
   * and calculate long size number of splits, using
   * long duraMillis as a step size.
   * <p>
   *
   * A DateTimeDirection will work either forwards or backwards
   * along the datetime continuum.  DateTimes will be added to the
   * list until the returned list is size big.
   * <p>
   *
   * For example:
   * <p>
   *
   *
   *<pre>
   *
   *    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
   *    DateTime start = new DateTime(0L);
   *    long size = 5L;
   *    long duraLong = 60000L;
   *
   *    List<DateTime> dts = DateTimes.splits(dtDirection,
   *                                          start,
   *                                          size,
   *                                          duraLong);
   *    for (int i = 0; i < dts.size(); i++) {
   *      System.out.println(String.format("Split %d is %s which is %8dms.",
   *                                       i,
   *                                       dts.get(i).toString(),
   *                                       dts.get(i).getMillis()));
   *    }
   *
   *</pre>
   *
   * Will print:
   * <p>
   *
   * <pre>
   * Split 0 is 1970-01-01T00:00:00.000Z which is        0ms.
   * Split 1 is 1970-01-01T00:01:00.000Z which is    60000ms.
   * Split 2 is 1970-01-01T00:02:00.000Z which is   120000ms.
   * Split 3 is 1970-01-01T00:03:00.000Z which is   180000ms.
   * Split 4 is 1970-01-01T00:04:00.000Z which is   240000ms.
   * </pre>
   *
   * Whereas DateTimeDirection.REVERSE will print:
   * <p>
   *
   * <pre>
   * Split 0 is 1970-01-01T00:00:00.000Z which is        0ms.
   * Split 1 is 1969-12-31T23:59:00.000Z which is   -60000ms.
   * Split 2 is 1969-12-31T23:58:00.000Z which is  -120000ms.
   * Split 3 is 1969-12-31T23:57:00.000Z which is  -180000ms.
   * Split 4 is 1969-12-31T23:56:00.000Z which is  -240000ms.
   * </pre>
   *
   *
   *
   * @param dtDirection a DateTimeDirection value
   * @param start a DateTime
   * @param size a long value
   * @param duraMillis a long value specifying the duration between splits/steps
   * @return a List&lt;DateTime&gt; value
   */
  public static List<DateTime> splits(DateTimeDirection dtDirection,
                                      DateTime start,
                                      long size,
                                      long duraMillis) {
    checkNotNull(dtDirection, "Need a direction.");
    checkNotNull(start, "Need a start DateTime.");
    checkArgument(size > 1, "Need a size greater than one.");
    checkArgument(duraMillis != 0L, "DuraMillis can not be zero.");

    List<DateTime> dts = new ArrayList<DateTime>();

    for (long i = 0; i < size; i++) {
      dts.add(dtDirection.plus(start, i * duraMillis));
    }
    return dts;
  }







  /**
   * Returns the lesser of two DateTimes using isBefore().
   *
   * 
   * @param dtOne  a DateTime
   * @param dtTwo  a DateTime
   * @return the lesser of the two, or dtOne if equal
   */
  public static DateTime lesser(DateTime dtOne, DateTime dtTwo) {
    checkNotNull(dtOne, "Need a dtOne.");
    checkNotNull(dtTwo, "Need a dtTwo.");
    if (dtOne.isEqual(dtTwo)) { return dtOne; }

    return (dtOne.isBefore(dtTwo)) ? dtOne : dtTwo;

  }
  
  
  
  /**
   * Returns the greater of two DateTimes using isAfter().
   *
   * 
   * @param dtOne  a DateTime
   * @param dtTwo  a DateTime
   * @return the greater of the two, or dtOne if equal
   */

  public static DateTime greater(DateTime dtOne, DateTime dtTwo) {
    checkNotNull(dtOne, "Need a dtOne.");
    checkNotNull(dtTwo, "Need a dtTwo.");
    if (dtOne.isEqual(dtTwo)) { return dtOne; }

    return (dtOne.isAfter(dtTwo)) ? dtOne : dtTwo;

  }
  
  
  
  
  

  public static DateTime getDateTime(String s) {
    return getDateTime(getBasicFormatter(),
                       BASIC_DATETIME_NOMILLIS_PATTERN,
                       s);
  }



  /**
   * Describe <code>getDateTime</code> method here.
   *
   * @param dtf a <code>DateTimeFormatter</code> value
   * @param p a <code>Pattern</code> value
   * @param s a <code>String</code> value
   * @return a <code>DateTime</code> value
   */
  public static DateTime getDateTime(DateTimeFormatter dtf, Pattern p, String s) {
    List<DateTime> dateTimes = getDateTimes(dtf, p, s);
    if (dateTimes.isEmpty()) {
      return null;
    }
    return (dateTimes.get(dateTimes.size() - 1));
  }





  public static List<DateTime> getDateTimes(String s) {
    return getDateTimes(getBasicFormatter(),
                        BASIC_DATETIME_NOMILLIS_PATTERN,
                        s);
  }


  public static List<DateTime> getDateTimes(DateTimeFormatter dtf, Pattern p, String s) {
    List<DateTime> dateTimes = new ArrayList<DateTime>();
    
    Matcher m = p.matcher(s);
    while (m.find()) {
      dateTimes.add(dtf.parseDateTime(m.group()));
    }
    /*
    for (String dtString : Matchers.findAllMatches(s, p)) {
      dateTimes.add(dtf.parseDateTime(dtString));
    }
    */
    return dateTimes;
  }

  
  
}
