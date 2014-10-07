package com.tkmtwo.timex.source;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class DurationDateTimeSourceTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();



  @Test
  public void testPlus()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    DurationDateTimeSource ddt = new DurationDateTimeSource();
    ddt.setDuration(new Duration(86400000L));
    ddt.afterPropertiesSet();

    assertEquals(dtf.parseDateTime("1969-11-03T03:33:00Z"),
                 ddt.getDateTime(dt));
  }


  @Test
  public void testMinus()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    DurationDateTimeSource ddt = new DurationDateTimeSource();
    ddt.setDuration(new Duration(-33L * 60L * 1000L));
    ddt.afterPropertiesSet();

    assertEquals(dtf.parseDateTime("1969-11-02T03:00:00Z"),
                 ddt.getDateTime(dt));
  }





}


