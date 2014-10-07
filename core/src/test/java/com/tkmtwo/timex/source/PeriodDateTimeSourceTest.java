package com.tkmtwo.timex.source;


import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.Interval;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class PeriodDateTimeSourceTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();
  private static PeriodFormatter pf = ISOPeriodFormat.standard();


  @Test
  public void testPlus()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    PeriodDateTimeSource ddt = new PeriodDateTimeSource();
    ddt.setPeriod(new Period(pf.parsePeriod("P1D")));
    ddt.afterPropertiesSet();

    assertEquals(dtf.parseDateTime("1969-11-03T03:33:00Z"),
                 ddt.getDateTime(dt));
  }


  @Test
  public void testMinus()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    PeriodDateTimeSource ddt = new PeriodDateTimeSource();
    ddt.setPeriod(new Period(pf.parsePeriod("PT-33M")));
    ddt.afterPropertiesSet();

    assertEquals(dtf.parseDateTime("1969-11-02T03:00:00Z"),
                 ddt.getDateTime(dt));
  }



  @Test
  public void testMixed()
  {
    DateTime dt = dtf.parseDateTime("1969-11-02T03:33:00Z");

    PeriodDateTimeSource ddt = new PeriodDateTimeSource();
    ddt.setPeriod(new Period(pf.parsePeriod("P1DT-33M")));
    ddt.afterPropertiesSet();

    assertEquals(dtf.parseDateTime("1969-11-03T03:00:00Z"),
                 ddt.getDateTime(dt));
  }





}


