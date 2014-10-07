package com.tkmtwo.timex;


import java.util.Arrays;
import java.util.Collections;
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


public class WallClockTest
{
  private WallClock wc00000;
  private WallClock wc00010;
  private WallClock wc86300;
  private WallClock wc86400;


  @Before
  public void setUp()
  {
    wc00000 = WallClock.valueOf(0);
    assertEquals(0, wc00000.getHour());
    assertEquals(0, wc00000.getMinute());
    assertEquals(0, wc00000.getSecond());
    assertEquals(0, wc00000.getSeconds());

    wc00010 = WallClock.valueOf(10);
    assertEquals(0, wc00010.getHour());
    assertEquals(0, wc00010.getMinute());
    assertEquals(10, wc00010.getSecond());
    assertEquals(10, wc00010.getSeconds());

    wc86300 = WallClock.valueOf(86300);
    assertEquals(23, wc86300.getHour());
    assertEquals(58, wc86300.getMinute());
    assertEquals(20, wc86300.getSecond());
    assertEquals(86300, wc86300.getSeconds());

    wc86400 = WallClock.valueOf(86400);
    assertEquals(24, wc86400.getHour());
    assertEquals(0, wc86400.getMinute());
    assertEquals(0, wc86400.getSecond());
    assertEquals(86400, wc86400.getSeconds());
  }





  @Test
  public void testFields()
  {
    assertEquals(0, wc00010.getHour());
    assertEquals(0, wc00010.getMinute());
    assertEquals(10, wc00010.getSecond());
    assertEquals(10, wc00010.getSeconds());
  }

  @Test
  public void testParsePrint()
  {
    assertEquals(wc00010,
                 WallClock.parse("000010"));
    assertEquals(wc00010,
                 WallClock.valueOf("000010"));
    assertEquals(wc00010,
                 WallClock.parseBasic("000010"));
    assertEquals(wc00010,
                 WallClock.valueOf("000010"));
    assertEquals(wc00010,
                 WallClock.parseExtended("00:00:10"));
    assertEquals(wc00010,
                 WallClock.valueOf("00:00:10"));

    assertEquals("000000",
                 wc00000.print());
    assertEquals("000000",
                 wc00000.printBasic());
    assertEquals("00:00:00",
                 wc00000.printExtended());
  }


  @Test
  public void testValueOfs()
  {
    assertEquals(wc00000, WallClock.valueOf(0));
    assertEquals(wc00000.hashCode(), WallClock.valueOf(0).hashCode());
    assertEquals(wc00000, WallClock.valueOf(0, 0, 0));
    assertEquals(wc00000.hashCode(), WallClock.valueOf(0, 0, 0).hashCode());
    assertEquals(wc00000, WallClock.valueOf(0, 1, -60));
    assertEquals(wc00000.hashCode(), WallClock.valueOf(0, 1, -60).hashCode());
    assertEquals(wc00000, WallClock.valueOf("000000"));
    assertEquals(wc00000.hashCode(), WallClock.valueOf("000000").hashCode());
    assertEquals(wc00000, WallClock.valueOf("00:00:00"));
    assertEquals(wc00000.hashCode(), WallClock.valueOf("00:00:00").hashCode());
    assertEquals(wc00000, WallClock.valueOf("0", "0", "0"));
    assertEquals(wc00000.hashCode(), WallClock.valueOf("0", "0", "0").hashCode());
    assertEquals(wc00000, WallClock.valueOf("0", "1", "-60"));
    assertEquals(wc00000.hashCode(), WallClock.valueOf("0", "1", "-60").hashCode());
    assertEquals(wc00000, WallClock.valueOf(wc00000));
    assertEquals(wc00000.hashCode(), WallClock.valueOf(wc00000).hashCode());


    assertEquals(wc00010, WallClock.valueOf(10));
    assertEquals(wc00010.hashCode(), WallClock.valueOf(10).hashCode());
    assertEquals(wc00010, WallClock.valueOf(0, 0, 10));
    assertEquals(wc00010.hashCode(), WallClock.valueOf(0, 0, 10).hashCode());
    assertEquals(wc00010, WallClock.valueOf("000010"));
    assertEquals(wc00010.hashCode(), WallClock.valueOf("000010").hashCode());
    assertEquals(wc00010, WallClock.valueOf("00:00:10"));
    assertEquals(wc00010.hashCode(), WallClock.valueOf("00:00:10").hashCode());
    assertEquals(wc00010, WallClock.valueOf("0", "0", "10"));
    assertEquals(wc00010.hashCode(), WallClock.valueOf("0", "0", "10").hashCode());
    assertEquals(wc00010, WallClock.valueOf(wc00010));
    assertEquals(wc00010.hashCode(), WallClock.valueOf(wc00010).hashCode());


    assertEquals(wc86300, WallClock.valueOf(86300));
    assertEquals(wc86300.hashCode(), WallClock.valueOf(86300).hashCode());
    assertEquals(wc86300, WallClock.valueOf(23, 58, 20));
    assertEquals(wc86300.hashCode(), WallClock.valueOf(23, 58, 20).hashCode());
    assertEquals(wc86300, WallClock.valueOf(23, 59, -40));
    assertEquals(wc86300.hashCode(), WallClock.valueOf(23, 59, -40).hashCode());
    assertEquals(wc86300, WallClock.valueOf("235820"));
    assertEquals(wc86300.hashCode(), WallClock.valueOf("235820").hashCode());
    assertEquals(wc86300, WallClock.valueOf("23:58:20"));
    assertEquals(wc86300.hashCode(), WallClock.valueOf("23:58:20").hashCode());
    assertEquals(wc86300, WallClock.valueOf("23", "58", "20"));
    assertEquals(wc86300.hashCode(), WallClock.valueOf("23", "58", "20").hashCode());
    assertEquals(wc86300, WallClock.valueOf("23", "59", "-40"));
    assertEquals(wc86300.hashCode(), WallClock.valueOf("23", "59", "-40").hashCode());
    assertEquals(wc86300, WallClock.valueOf(wc86300));
    assertEquals(wc86300.hashCode(), WallClock.valueOf(wc86300).hashCode());


    assertEquals(wc86400, WallClock.valueOf(86400));
    assertEquals(wc86400.hashCode(), WallClock.valueOf(86400).hashCode());
    assertEquals(wc86400, WallClock.valueOf(24, 0, 0));
    assertEquals(wc86400.hashCode(), WallClock.valueOf(24, 0, 0).hashCode());
    assertEquals(wc86400, WallClock.valueOf(25, -60, 0));
    assertEquals(wc86400.hashCode(), WallClock.valueOf(25, -60, 0).hashCode());
    assertEquals(wc86400, WallClock.valueOf("240000"));
    assertEquals(wc86400.hashCode(), WallClock.valueOf("240000").hashCode());
    assertEquals(wc86400, WallClock.valueOf("24:00:00"));
    assertEquals(wc86400.hashCode(), WallClock.valueOf("24:00:00").hashCode());
    assertEquals(wc86400, WallClock.valueOf("24", "0", "0"));
    assertEquals(wc86400.hashCode(), WallClock.valueOf("24", "0", "0").hashCode());
    assertEquals(wc86400, WallClock.valueOf(wc86400));
    assertEquals(wc86400.hashCode(), WallClock.valueOf(wc86400).hashCode());
  }

  @Test
  public void testEqualsBeforesAndAfters()
  {
    //
    //wc00000
    //
    assertTrue(wc00000.equals(wc00000));
    assertTrue(!wc00000.equals(wc00010));
    assertTrue(!wc00000.equals(wc86300));
    assertTrue(!wc00000.equals(wc86400));

    assertTrue(wc00000.compareTo(wc00000) == 0);
    assertTrue(wc00000.compareTo(wc00010)  < 0);
    assertTrue(wc00000.compareTo(wc86300)  < 0);
    assertTrue(wc00000.compareTo(wc86400)  < 0);

    assertTrue(!wc00000.isBefore(wc00000));
    assertTrue(wc00000.isBefore(wc00010));
    assertTrue(wc00000.isBefore(wc86300));
    assertTrue(wc00000.isBefore(wc86400));

    assertTrue(wc00000.isBeforeOrEquals(wc00000));
    assertTrue(wc00000.isBeforeOrEquals(wc00010));
    assertTrue(wc00000.isBeforeOrEquals(wc86300));
    assertTrue(wc00000.isBeforeOrEquals(wc86400));

    assertTrue(!wc00000.isAfter(wc00000));
    assertTrue(!wc00000.isAfter(wc00010));
    assertTrue(!wc00000.isAfter(wc86300));
    assertTrue(!wc00000.isAfter(wc86400));

    assertTrue(wc00000.isAfterOrEquals(wc00000));
    assertTrue(!wc00000.isAfterOrEquals(wc00010));
    assertTrue(!wc00000.isAfterOrEquals(wc86300));
    assertTrue(!wc00000.isAfterOrEquals(wc86400));



    //
    //wc00010
    //
    assertTrue(!wc00010.equals(wc00000));
    assertTrue(wc00010.equals(wc00010));
    assertTrue(!wc00010.equals(wc86300));
    assertTrue(!wc00010.equals(wc86400));

    assertTrue(wc00010.compareTo(wc00000)  > 0);
    assertTrue(wc00010.compareTo(wc00010) == 0);
    assertTrue(wc00010.compareTo(wc86300)  < 0);
    assertTrue(wc00010.compareTo(wc86400)  < 0);

    assertTrue(!wc00010.isBefore(wc00000));
    assertTrue(!wc00010.isBefore(wc00010));
    assertTrue(wc00010.isBefore(wc86300));
    assertTrue(wc00010.isBefore(wc86400));

    assertTrue(!wc00010.isBeforeOrEquals(wc00000));
    assertTrue(wc00010.isBeforeOrEquals(wc00010));
    assertTrue(wc00010.isBeforeOrEquals(wc86300));
    assertTrue(wc00010.isBeforeOrEquals(wc86400));

    assertTrue(wc00010.isAfter(wc00000));
    assertTrue(!wc00010.isAfter(wc00010));
    assertTrue(!wc00010.isAfter(wc86300));
    assertTrue(!wc00010.isAfter(wc86400));

    assertTrue(wc00010.isAfterOrEquals(wc00000));
    assertTrue(wc00010.isAfterOrEquals(wc00010));
    assertTrue(!wc00010.isAfterOrEquals(wc86300));
    assertTrue(!wc00010.isAfterOrEquals(wc86400));



    //
    //wx86300
    //
    assertTrue(!wc86300.equals(wc00000));
    assertTrue(!wc86300.equals(wc00010));
    assertTrue(wc86300.equals(wc86300));
    assertTrue(!wc86300.equals(wc86400));

    assertTrue(wc86300.compareTo(wc00000)  > 0);
    assertTrue(wc86300.compareTo(wc00010)  > 0);
    assertTrue(wc86300.compareTo(wc86300) == 0);
    assertTrue(wc86300.compareTo(wc86400)  < 0);

    assertTrue(!wc86300.isBefore(wc00000));
    assertTrue(!wc86300.isBefore(wc00010));
    assertTrue(!wc86300.isBefore(wc86300));
    assertTrue(wc86300.isBefore(wc86400));

    assertTrue(!wc86300.isBeforeOrEquals(wc00000));
    assertTrue(!wc86300.isBeforeOrEquals(wc00010));
    assertTrue(wc86300.isBeforeOrEquals(wc86300));
    assertTrue(wc86300.isBeforeOrEquals(wc86400));

    assertTrue(wc86300.isAfter(wc00000));
    assertTrue(wc86300.isAfter(wc00010));
    assertTrue(!wc86300.isAfter(wc86300));
    assertTrue(!wc86300.isAfter(wc86400));

    assertTrue(wc86300.isAfterOrEquals(wc00000));
    assertTrue(wc86300.isAfterOrEquals(wc00010));
    assertTrue(wc86300.isAfterOrEquals(wc86300));
    assertTrue(!wc86300.isAfterOrEquals(wc86400));



    //
    //wc86400
    //
    assertTrue(!wc86400.equals(wc00000));
    assertTrue(!wc86400.equals(wc00010));
    assertTrue(!wc86400.equals(wc86300));
    assertTrue(wc86400.equals(wc86400));

    assertTrue(wc86400.compareTo(wc00000)  > 0);
    assertTrue(wc86400.compareTo(wc00010)  > 0);
    assertTrue(wc86400.compareTo(wc86300)  > 0);
    assertTrue(wc86400.compareTo(wc86400) == 0);

    assertTrue(!wc86400.isBefore(wc00000));
    assertTrue(!wc86400.isBefore(wc00010));
    assertTrue(!wc86400.isBefore(wc86300));
    assertTrue(!wc86400.isBefore(wc86400));

    assertTrue(!wc86400.isBeforeOrEquals(wc00000));
    assertTrue(!wc86400.isBeforeOrEquals(wc00010));
    assertTrue(!wc86400.isBeforeOrEquals(wc86300));
    assertTrue(wc86400.isBeforeOrEquals(wc86400));

    assertTrue(wc86400.isAfter(wc00000));
    assertTrue(wc86400.isAfter(wc00010));
    assertTrue(wc86400.isAfter(wc86300));
    assertTrue(!wc86400.isAfter(wc86400));

    assertTrue(wc86400.isAfterOrEquals(wc00000));
    assertTrue(wc86400.isAfterOrEquals(wc00010));
    assertTrue(wc86400.isAfterOrEquals(wc86300));
    assertTrue(wc86400.isAfterOrEquals(wc86400));

  }


  @Test
  public void testCompareToArray()
  {
    WallClock[] wcs = new WallClock[] {
      wc86300,
      wc00000,
      wc86400,
      wc00010
    };

    Arrays.sort(wcs);
    assertEquals(4, wcs.length);
    assertEquals(wc00000, wcs[0]);
    assertEquals(wc00010, wcs[1]);
    assertEquals(wc86300, wcs[2]);
    assertEquals(wc86400, wcs[3]);
  }


  @Test
  public void testCompareToList()
  {
    List<WallClock> wcs = new ArrayList<WallClock>();
    wcs.add(wc86300);
    wcs.add(wc00000);
    wcs.add(wc86400);
    wcs.add(wc00010);

    Collections.sort(wcs);
    assertEquals(4, wcs.size());
    assertEquals(wc00000, wcs.get(0));
    assertEquals(wc00010, wcs.get(1));
    assertEquals(wc86300, wcs.get(2));
    assertEquals(wc86400, wcs.get(3));
  }


  @Test
  public void testPlusMinus()
  {

    assertEquals(wc86400,
                 wc86300.plus(100));
    assertEquals(wc86400,
                 wc86300.plus(Periods.parse("PT100S")));
    assertEquals(wc86400,
                 wc86300.plus(Periods.parse("PT1M40S")));
    assertEquals(wc86400,
                 wc86300.plus(Periods.parse("PT2M-20S")));
    assertEquals(wc86400,
                 wc86300.plus(new Duration(100000L)));
    assertEquals(wc86400,
                 wc86300.plus(0, 0, 100));
    assertEquals(wc86400,
                 wc86300.plus(0, 1, 40));
    assertEquals(wc86400,
                 wc86300.plus("0", "0", "100"));
    assertEquals(wc86400,
                 wc86300.plus("0", "1", "40"));

    assertEquals(wc86300,
                 wc86400.minus(100));
    assertEquals(wc86300,
                 wc86400.minus(Periods.parse("PT100S")));
    assertEquals(wc86300,
                 wc86400.minus(Periods.parse("PT1M40S")));
    assertEquals(wc86300,
                 wc86400.minus(Periods.parse("PT2M-20S")));
    assertEquals(wc86300,
                 wc86400.minus(new Duration(100000L)));
    assertEquals(wc86300,
                 wc86400.minus(0, 0, 100));
    assertEquals(wc86300,
                 wc86400.minus(0, 1, 40));
    assertEquals(wc86300,
                 wc86400.minus("0", "0", "100"));
    assertEquals(wc86300,
                 wc86400.minus("0", "1", "40"));

  }









}
