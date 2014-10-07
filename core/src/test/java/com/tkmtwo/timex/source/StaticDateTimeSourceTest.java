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


public class StaticDateTimeSourceTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();



  @Test
  public void testDefaultCtor()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    StaticDateTimeSource sdt = new StaticDateTimeSource();
    sdt.setDateTime(dt);
    sdt.afterPropertiesSet();

    assertEquals(dt, sdt.getDateTime());
    assertEquals(dt, sdt.getDateTime(new DateTime()));
  }


  @Test
  public void testArgedCtor()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    StaticDateTimeSource sdt = new StaticDateTimeSource(dt);
    sdt.afterPropertiesSet();

    assertEquals(dt, sdt.getDateTime());
    assertEquals(dt, sdt.getDateTime(new DateTime()));
  }

  @Test
  public void testFactory()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    StaticDateTimeSource sdt = StaticDateTimeSource.getInstance(dt);

    assertEquals(dt, sdt.getDateTime());
    assertEquals(dt, sdt.getDateTime(new DateTime()));
  }





}


