package com.tkmtwo.timex;

import java.util.List;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.Interval;
import org.joda.time.Hours;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


public class IntervalsTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();

  @Test
  public void testNoMillis()
  {
    Interval i = new Interval(new DateTime(4L),
                              new DateTime(1004L));
    Interval iNoMillis = Intervals.noMillis(i);
    assertEquals(new DateTime(0L),
                 iNoMillis.getStart());
    assertEquals(new DateTime(1000L),
                 iNoMillis.getEnd());
  }




  @Test
  public void testBasicFormat()
  {
    
    assertEquals("19700101T000003Z/19700101T000004Z",
                 Intervals.printBasic(new Interval(new DateTime(3000L), new DateTime(4000L))));



  }



  @Test
  public void testExtendedFormat()
  {
    assertEquals("1970-01-01T00:00:03Z/1970-01-01T00:00:04Z",
                 Intervals.printExtended(new Interval(new DateTime(3000L), new DateTime(4000L))));
  }



  @Test
  public void testFromSecs()
  {
    Long startSecs = Long.valueOf(2000L);
    Long endSecs = Long.valueOf(4000L);
    
    Interval i = new Interval(startSecs.longValue(),
                              endSecs.longValue());
    
    assertEquals(i, Intervals.fromSecs(Long.valueOf(2L), Long.valueOf(4L)));
    assertEquals(i, Intervals.fromSecs(2L, 4L));
    assertEquals(i, Intervals.fromSecs(Integer.valueOf(2), Integer.valueOf(4)));
    assertEquals(i, Intervals.fromSecs(2, 4));
  }
  
  
  
  
  
  
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////


  private void verifyIntervalsForward(List<Interval> intervals)
  {
    assertEquals(4, intervals.size());
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:00:00Z"),
                            dtf.parseDateTime("1970-01-01T00:01:00Z")).isEqual(intervals.get(0)));
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:01:00Z"),
                            dtf.parseDateTime("1970-01-01T00:02:00Z")).isEqual(intervals.get(1)));
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:02:00Z"),
                            dtf.parseDateTime("1970-01-01T00:03:00Z")).isEqual(intervals.get(2)));
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:03:00Z"),
                            dtf.parseDateTime("1970-01-01T00:04:00Z")).isEqual(intervals.get(3)));
  }
  private void verifyIntervalsReverse(List<Interval> intervals)
  {
    assertEquals(4, intervals.size());
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:00:00Z"),
                            dtf.parseDateTime("1970-01-01T00:01:00Z")).isEqual(intervals.get(3)));
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:01:00Z"),
                            dtf.parseDateTime("1970-01-01T00:02:00Z")).isEqual(intervals.get(2)));
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:02:00Z"),
                            dtf.parseDateTime("1970-01-01T00:03:00Z")).isEqual(intervals.get(1)));
    assertTrue(new Interval(dtf.parseDateTime("1970-01-01T00:03:00Z"),
                            dtf.parseDateTime("1970-01-01T00:04:00Z")).isEqual(intervals.get(0)));
  }



  @Test
  public void testIntervalsForwardByInterval()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    Interval interval = new Interval(new DateTime(0L),
                                     new DateTime(240000L));
    long duraLong = 60000L;


    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               interval,
                                               duraLong));
    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               interval,
                                               new Duration(duraLong)));
    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               interval,
                                               new Period(duraLong)));
    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               interval,
                                               Periods.parse("PT1M")));


    verifyIntervalsForward(Intervals.intervals(interval,
                                               duraLong));
    verifyIntervalsForward(Intervals.intervals(interval,
                                               new Duration(duraLong)));
    verifyIntervalsForward(Intervals.intervals(interval,
                                               new Period(duraLong)));
    verifyIntervalsForward(Intervals.intervals(interval,
                                               Periods.parse("PT1M")));
  }




  @Test
  public void testIntervalsReverseByInterval()
  {
    DateTimeDirection dtDirection = DateTimeDirection.REVERSE;
    Interval interval = new Interval(new DateTime(0L),
                                     new DateTime(240000L));
    long duraLong = 60000L;


    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               interval,
                                               duraLong));
    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               interval,
                                               new Duration(duraLong)));
    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               interval,
                                               new Period(duraLong)));
    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               interval,
                                               Periods.parse("PT1M")));
  }



  @Test
  public void testIntervalsForwardBySize()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    DateTime start = new DateTime(0L);
    long duraLong = 60000L;

    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               duraLong));
    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               new Duration(duraLong)));
    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               new Period(duraLong)));
    verifyIntervalsForward(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               Periods.parse("PT1M")));

    verifyIntervalsForward(Intervals.intervals(start,
                                               4L,
                                               duraLong));
    verifyIntervalsForward(Intervals.intervals(start,
                                               4L,
                                               new Duration(duraLong)));
    verifyIntervalsForward(Intervals.intervals(start,
                                               4L,
                                               new Period(duraLong)));
    verifyIntervalsForward(Intervals.intervals(start,
                                               4L,
                                               Periods.parse("PT1M")));
  }


  @Test
  public void testIntervalsReverseBySize()
  {
    DateTimeDirection dtDirection = DateTimeDirection.REVERSE;
    DateTime start = new DateTime(240000L);
    long duraLong = 60000L;

    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               duraLong));
    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               new Duration(duraLong)));
    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               new Period(duraLong)));
    verifyIntervalsReverse(Intervals.intervals(dtDirection,
                                               start,
                                               4L,
                                               Periods.parse("PT1M")));

  }





  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  //////////////////////DOCUMENTATION TESTS //////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////

  

  //@Test
  public void testIntrvalsIntervalForDocumentation()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    Interval interval = new Interval(new DateTime(0L),
                                     new DateTime(240010L));
    long duraLong = 60000L;


    List<Interval> intervals = Intervals.intervals(dtDirection,
                                                   interval,
                                                   duraLong);


    for (int i = 0; i < intervals.size(); i++) {
      System.out.println(String.format("Interval %d is %s -> %s which is %8dms to %8dms.",
                                       i,
                                       intervals.get(i).getStart().toString(),
                                       intervals.get(i).getEnd().toString(),
                                       intervals.get(i).getStart().getMillis(),
                                       intervals.get(i).getEnd().getMillis()));
    }
  }







}
