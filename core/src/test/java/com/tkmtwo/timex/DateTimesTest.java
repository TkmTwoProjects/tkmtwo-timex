package com.tkmtwo.timex;

import java.util.List;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.Interval;
//import org.joda.time.Hours;
//import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


public class DateTimesTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();


  @Test
  public void testNoMillis()
  {
    assertEquals(new DateTime(0L),
                 DateTimes.noMillis(0L));
    assertEquals(new DateTime(0L),
                 DateTimes.noMillis(1L));
    assertEquals(new DateTime(0L),
                 DateTimes.noMillis(999L));
  }


  @Test
  public void testBasicFormat()
  {
    //Straight up...
    assertEquals("19700101T000003Z",
                 DateTimes.printBasic(new DateTime(3000L)));
    assertEquals(new DateTime(3000L).getMillis(),
                 DateTimes.parseBasic("19700101T000003Z").getMillis());

    //With DateTimes.noMillis()
    assertEquals("19700101T000003Z",
                 DateTimes.printBasic(DateTimes.noMillis(3999L)));
    assertEquals(DateTimes.noMillis(3999L).getMillis(),
                 DateTimes.parseBasic("19700101T000003Z").getMillis());
    
  }



  @Test
  public void testExtendedFormat()
  {
    //Straight up...
    assertEquals("1970-01-01T00:00:03Z",
                 DateTimes.printExtended(new DateTime(3000L)));
    assertEquals(new DateTime(3000L).getMillis(),
                 DateTimes.parseExtended("1970-01-01T00:00:03Z").getMillis());

    //With DateTimes.noMillis()
    assertEquals("1970-01-01T00:00:03Z",
                 DateTimes.printExtended(DateTimes.noMillis(3999L)));
    assertEquals(DateTimes.noMillis(3999L).getMillis(),
                 DateTimes.parseExtended("1970-01-01T00:00:03Z").getMillis());
  }



  @Test
  public void testFromSecs()
  {
    DateTime dt = new DateTime(3000L);

    assertEquals(dt.getMillis(),
                 DateTimes.fromSecs(Long.valueOf(3L)).getMillis());
    assertEquals(dt.getMillis(),
                 DateTimes.fromSecs(3l).getMillis());
    assertEquals(dt.getMillis(),
                 DateTimes.fromSecs(Integer.valueOf(3)).getMillis());
    assertEquals(dt.getMillis(),
                 DateTimes.fromSecs(3).getMillis());
  }


  @Test
  public void testSeconds()
  {
    DateTime dt = new DateTime(3000L);

    assertEquals(3L,
                 DateTimes.getSecsAsLong(dt));
    assertEquals(3,
                 DateTimes.getSecsAsInt(dt));
    assertEquals("3",
                 DateTimes.getSecsAsString(dt));
  }





  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////




  private void verifySplitsForward(List<DateTime> dts)
  {
    assertEquals(5, dts.size());
    assertTrue(dtf.parseDateTime("1970-01-01T00:00:00Z").isEqual(dts.get(0)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:01:00Z").isEqual(dts.get(1)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:02:00Z").isEqual(dts.get(2)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:03:00Z").isEqual(dts.get(3)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:04:00Z").isEqual(dts.get(4)));
  }

  private void verifySplitsReverse(List<DateTime> dts)
  {
    assertEquals(5, dts.size());
    assertTrue(dtf.parseDateTime("1970-01-01T00:04:00Z").isEqual(dts.get(0)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:03:00Z").isEqual(dts.get(1)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:02:00Z").isEqual(dts.get(2)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:01:00Z").isEqual(dts.get(3)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:00:00Z").isEqual(dts.get(4)));
  }




  @Test
  public void testSplitForwardByInterval()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    Interval interval = new Interval(new DateTime(0L),
                                     new DateTime(240000L));
    long duraLong = 60000L;

    verifySplitsForward(DateTimes.splits(dtDirection,
                                         interval,
                                         duraLong));
    verifySplitsForward(DateTimes.splits(dtDirection,
                                         interval,
                                         new Duration(duraLong)));
    verifySplitsForward(DateTimes.splits(dtDirection,
                                         interval,
                                         new Period(duraLong)));
    verifySplitsForward(DateTimes.splits(dtDirection,
                                         interval,
                                         Periods.parse("PT1M")));

    verifySplitsForward(DateTimes.splits(interval,
                                         duraLong));
    verifySplitsForward(DateTimes.splits(interval,
                                         new Duration(duraLong)));
    verifySplitsForward(DateTimes.splits(interval,
                                         new Period(duraLong)));
    verifySplitsForward(DateTimes.splits(interval,
                                         Periods.parse("PT1M")));
  }


  @Test
  public void testSplitReverseByInterval()
  {
    DateTimeDirection dtDirection = DateTimeDirection.REVERSE;
    Interval interval = new Interval(new DateTime(0L),
                                     new DateTime(240000L));
    long duraLong = 60000L;

    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         interval,
                                         duraLong));
    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         interval,
                                         new Duration(duraLong)));
    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         interval,
                                         new Period(duraLong)));
    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         interval,
                                         Periods.parse("PT1M")));
  }






  @Test
  public void testSplitForwardBySize()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    DateTime start = new DateTime(0L);
    long duraLong = 60000L;

    verifySplitsForward(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         duraLong));
    verifySplitsForward(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         new Duration(duraLong)));
    verifySplitsForward(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         new Period(duraLong)));
    verifySplitsForward(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         Periods.parse("PT1M")));

    verifySplitsForward(DateTimes.splits(start,
                                         5L,
                                         duraLong));
    verifySplitsForward(DateTimes.splits(start,
                                         5L,
                                         new Duration(duraLong)));
    verifySplitsForward(DateTimes.splits(start,
                                         5L,
                                         new Period(duraLong)));
    verifySplitsForward(DateTimes.splits(start,
                                         5L,
                                         Periods.parse("PT1M")));
  }





  @Test
  public void testSplitReverseBySize()
  {
    DateTimeDirection dtDirection = DateTimeDirection.REVERSE;
    DateTime start = new DateTime(240000L);
    long duraLong = 60000L;

    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         duraLong));
    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         new Duration(duraLong)));
    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         new Period(duraLong)));
    verifySplitsReverse(DateTimes.splits(dtDirection,
                                         start,
                                         5L,
                                         Periods.parse("PT1M")));
  }






  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  //////////////////////DOCUMENTATION TESTS //////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////

  @Test
  public void testSplitIntervalForDocumentation()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    Interval interval = new Interval(new DateTime(0L),
                                     new DateTime(240010L));
    long duraLong = 60000L;

    List<DateTime> dts = DateTimes.splits(dtDirection,
                                          interval,
                                          duraLong);
    for (int i = 0; i < dts.size(); i++) {
      System.out.println(String.format("Split %d is %s which is %8dms.",
                                       i,
                                       dts.get(i).toString(),
                                       dts.get(i).getMillis()));
    }
  }

  @Test
  public void testSplitSizeForDocumentation()
  {
    DateTimeDirection dtDirection = DateTimeDirection.FORWARD;
    DateTime start = new DateTime(0L);
    long size = 5L;
    long duraLong = 60000L;

    List<DateTime> dts = DateTimes.splits(dtDirection,
                                          start,
                                          size,
                                          duraLong);
    for (int i = 0; i < dts.size(); i++) {
      System.out.println(String.format("Split %d is %s which is %8dms.",
                                       i,
                                       dts.get(i).toString(),
                                       dts.get(i).getMillis()));
    }
  }
  
  
  
  
  
  @Test
  public void testGetDateTimes()
  {
    String s = "My dob is 19691102T033333Z and today is 20130427T000345Z which makes me over 40!";

    List<DateTime> dts = DateTimes.getDateTimes(s);
    assertEquals(2, dts.size());
    assertEquals(dtf.parseDateTime("1969-11-02T03:33:33Z"),
                 dts.get(0));
    assertEquals(dtf.parseDateTime("2013-04-27T00:03:45Z"),
                 dts.get(1));
    
    
    assertEquals(dtf.parseDateTime("2013-04-27T00:03:45Z"),
                 DateTimes.getDateTime(s));
    
    
    
  }
  
  


}
