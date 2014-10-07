package com.tkmtwo.timex;




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
import org.junit.Before;
import org.junit.Test;


public class WallClockIntervalTest
{
  private WallClockInterval wci0000;
  private WallClockInterval wci2424;
  private WallClockInterval wci0024;
  private WallClockInterval wci1014;
  private WallClockInterval wci1212;
  private WallClockInterval wci0810;
  private WallClockInterval wci1416;
  private WallClockInterval wci0011;
  private WallClockInterval wci1324;

  @Before
  public void setUp()
  {
    /*
     * ===============================================================================================
     *   0000                                        1200                                         2400
     * ===============================================================================================
     *
     *
     *    *0                                                                                       *0
     *   0000                                                                                     2400
     *
     *    *-----------------------------------------------------------------------------------------0
     * 0000                                                                                       2400
     *
     *
     *                               *------------------------------------0
     *                             1000                                 1400
     *
     *                                                *0
     *                                               1200
     *
     *                    *----------0                                    *----------0
     *                  0800       1000                                 1400       1600
     *
     *
     *    *----------------------------------0                   *----------------------------------0
     *  0000                               1100                1300                               2400
     *
     *
     *
     */

    wci0000 = new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("000000"));
    wci2424 = new WallClockInterval(WallClock.valueOf("240000"), WallClock.valueOf("240000"));
    wci0024 = new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("240000"));
    wci1014 = new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("140000"));
    wci1212 = new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("120000"));
    wci0810 = new WallClockInterval(WallClock.valueOf("080000"), WallClock.valueOf("100000"));
    wci1416 = new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("160000"));
    wci0011 = new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("110000"));
    wci1324 = new WallClockInterval(WallClock.valueOf("130000"), WallClock.valueOf("240000"));



  }

  @Test
  public void testEquals()
  {
    assertEquals(wci0000,
                 new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("000000")));
    assertEquals(wci0000.hashCode(),
                 new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("000000")).hashCode());
  }


  /*
  public void testTemplate()
  {
    WallClockInterval wci = null;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000) == 0);
    assertTrue(wci.compareTo(wci2424) == 0);
    assertTrue(wci.compareTo(wci0024) == 0);
    assertTrue(wci.compareTo(wci1014) == 0);
    assertTrue(wci.compareTo(wci1212) == 0);
    assertTrue(wci.compareTo(wci0810) == 0);
    assertTrue(wci.compareTo(wci1416) == 0);
    assertTrue(wci.compareTo(wci0011) == 0);
    assertTrue(wci.compareTo(wci1324) == 0);

    assertTrue(wci.contains(wci0000));
    assertTrue(wci.contains(wci2424));
    assertTrue(wci.contains(wci0024));
    assertTrue(wci.contains(wci1014));
    assertTrue(wci.contains(wci1212));
    assertTrue(wci.contains(wci0810));
    assertTrue(wci.contains(wci1416));
    assertTrue(wci.contains(wci0011));
    assertTrue(wci.contains(wci1324));

    assertTrue(wci.abuts(wci0000));
    assertTrue(wci.abuts(wci2424));
    assertTrue(wci.abuts(wci0024));
    assertTrue(wci.abuts(wci1014));
    assertTrue(wci.abuts(wci1212));
    assertTrue(wci.abuts(wci0810));
    assertTrue(wci.abuts(wci1416));
    assertTrue(wci.abuts(wci0011));
    assertTrue(wci.abuts(wci1324));



    assertTrue(wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1014));
    assertNull(wci.overlap(wci1014));
    assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0011));
    assertNull(wci.overlap(wci0011));
    assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1324));
    assertNull(wci.overlap(wci1324));
    assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));








    assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0024));
    assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0810));
    assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1416));
    assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0011));
    assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1324));
    assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

  }
  */






















  @Test
  public void testWci0000()
  {
    WallClockInterval wci = wci0000;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000) == 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  < 0);
    assertTrue(wci.compareTo(wci1014)  < 0);
    assertTrue(wci.compareTo(wci1212)  < 0);
    assertTrue(wci.compareTo(wci0810)  < 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011)  < 0);
    assertTrue(wci.compareTo(wci1324)  < 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(!wci.contains(wci0810));
    assertTrue(!wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(wci.abuts(wci0000));
    assertTrue(!wci.abuts(wci2424));
    assertTrue(wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0024));
    assertNull(wci.overlap(wci0024));
    //assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1014));
    assertNull(wci.overlap(wci1014));
    //assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    //assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    //assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0011));
    assertNull(wci.overlap(wci0011));
    //assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1324));
    assertNull(wci.overlap(wci1324));
    //assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));








    assertNull(wci.gap(wci0000));
    //assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1014));
    assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("100000")));

    //assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("120000")));

    //assertNull(wci.gap(wci0810));
    assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("080000")));

    //assertNull(wci.gap(wci1416));
    assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("140000")));

    assertNull(wci.gap(wci0011));
    //assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1324));
    assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("130000")));
    //
    // END wciXXX
    //





  }





  @Test
  public void testWci2424()
  {
    WallClockInterval wci = wci2424;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424) == 0);
    assertTrue(wci.compareTo(wci0024)  > 0);
    assertTrue(wci.compareTo(wci1014)  > 0);
    assertTrue(wci.compareTo(wci1212)  > 0);
    assertTrue(wci.compareTo(wci0810)  > 0);
    assertTrue(wci.compareTo(wci1416)  > 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324)  > 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(!wci.contains(wci0810));
    assertTrue(!wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(!wci.abuts(wci0000));
    assertTrue(wci.abuts(wci2424));
    assertTrue(wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf("240000"), WallClock.valueOf("240000")));

    assertTrue(!wci.overlaps(wci0024));
    assertNull(wci.overlap(wci0024));
    //assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1014));
    assertNull(wci.overlap(wci1014));
    //assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    //assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    //assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0011));
    assertNull(wci.overlap(wci0011));
    //assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1324));
    assertNull(wci.overlap(wci1324));
    //assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));








    //assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci2424));
    //assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1014));
    assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("240000")));

    //assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("240000")));

    //assertNull(wci.gap(wci0810));
    assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("240000")));

    //assertNull(wci.gap(wci1416));
    assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf("160000"), WallClock.valueOf("240000")));

    //assertNull(wci.gap(wci0011));
    assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci1324));
    //assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

  }











  @Test
  public void testWci0024()
  {
    WallClockInterval wci = wci0024;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024) == 0);
    assertTrue(wci.compareTo(wci1014)  < 0);
    assertTrue(wci.compareTo(wci1212)  < 0);
    assertTrue(wci.compareTo(wci0810)  < 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324)  < 0);

    assertTrue(wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(wci.contains(wci0024));
    assertTrue(wci.contains(wci1014));
    assertTrue(wci.contains(wci1212));
    assertTrue(wci.contains(wci0810));
    assertTrue(wci.contains(wci1416));
    assertTrue(wci.contains(wci0011));
    assertTrue(wci.contains(wci1324));

    assertTrue(wci.abuts(wci0000));
    assertTrue(wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("240000")));

    assertTrue(wci.overlaps(wci1014));
    //assertNull(wci.overlap(wci1014));
    assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("140000")));

    assertTrue(wci.overlaps(wci1212));
    //assertNull(wci.overlap(wci1212));
    assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("120000")));

    assertTrue(wci.overlaps(wci0810));
    //assertNull(wci.overlap(wci0810));
    assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf("080000"), WallClock.valueOf("100000")));

    assertTrue(wci.overlaps(wci1416));
    //assertNull(wci.overlap(wci1416));
    assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("160000")));

    assertTrue(wci.overlaps(wci0011));
    //assertNull(wci.overlap(wci0011));
    assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("110000")));

    assertTrue(wci.overlaps(wci1324));
    //assertNull(wci.overlap(wci1324));
    assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf("130000"), WallClock.valueOf("240000")));








    assertNull(wci.gap(wci0000));
    //assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci2424));
    //assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1212));
    //assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0810));
    //assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1416));
    //assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0011));
    //assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1324));
    //assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

  }









  @Test
  public void testWci1014()
  {
    WallClockInterval wci = wci1014;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  > 0);
    assertTrue(wci.compareTo(wci1014) == 0);
    assertTrue(wci.compareTo(wci1212)  < 0);
    assertTrue(wci.compareTo(wci0810)  > 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324)  < 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(wci.contains(wci1014));
    assertTrue(wci.contains(wci1212));
    assertTrue(!wci.contains(wci0810));
    assertTrue(!wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(!wci.abuts(wci0000));
    assertTrue(!wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(wci.abuts(wci0810));
    assertTrue(wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("140000")));

    assertTrue(wci.overlaps(wci1014));
    //assertNull(wci.overlap(wci1014));
    assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("140000")));

    assertTrue(wci.overlaps(wci1212));
    //assertNull(wci.overlap(wci1212));
    assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("120000")));

    assertTrue(!wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    //assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    //assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0011));
    //assertNull(wci.overlap(wci0011));
    assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("110000")));

    assertTrue(wci.overlaps(wci1324));
    //assertNull(wci.overlap(wci1324));
    assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf("130000"), WallClock.valueOf("140000")));








    //assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("100000")));

    //assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1212));
    //assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0810));
    //assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1416));
    //assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0011));
    //assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1324));
    //assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

  }




  @Test
  public void testWci1212()
  {
    WallClockInterval wci = wci1212;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  > 0);
    assertTrue(wci.compareTo(wci1014)  > 0);
    assertTrue(wci.compareTo(wci1212) == 0);
    assertTrue(wci.compareTo(wci0810)  > 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324)  < 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(!wci.contains(wci0810));
    assertTrue(!wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(!wci.abuts(wci0000));
    assertTrue(!wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("120000")));

    assertTrue(wci.overlaps(wci1014));
    //assertNull(wci.overlap(wci1014));
    assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("120000")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    //assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    //assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0011));
    assertNull(wci.overlap(wci0011));
    //assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1324));
    assertNull(wci.overlap(wci1324));
    //assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));








    //assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("120000")));

    //assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1212));
    //assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci0810));
    assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("120000")));

    //assertNull(wci.gap(wci1416));
    assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("140000")));

    //assertNull(wci.gap(wci0011));
    assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("120000")));

    //assertNull(wci.gap(wci1324));
    assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("130000")));

  }






  @Test
  public void testWci0810()
  {
    WallClockInterval wci = wci0810;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  > 0);
    assertTrue(wci.compareTo(wci1014)  < 0);
    assertTrue(wci.compareTo(wci1212)  < 0);
    assertTrue(wci.compareTo(wci0810) == 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324)  < 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(wci.contains(wci0810));
    assertTrue(!wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(!wci.abuts(wci0000));
    assertTrue(!wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("080000"), WallClock.valueOf("100000")));

    assertTrue(!wci.overlaps(wci1014));
    assertNull(wci.overlap(wci1014));
    //assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0810));
    //assertNull(wci.overlap(wci0810));
    assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf("080000"), WallClock.valueOf("100000")));

    assertTrue(!wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    //assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0011));
    //assertNull(wci.overlap(wci0011));
    assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf("080000"), WallClock.valueOf("100000")));

    assertTrue(!wci.overlaps(wci1324));
    assertNull(wci.overlap(wci1324));
    //assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));








    //assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("080000")));

    //assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("120000")));

    assertNull(wci.gap(wci0810));
    //assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1416));
    assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("140000")));

    assertNull(wci.gap(wci0011));
    //assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1324));
    assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("130000")));

  }




  @Test
  public void testWci1416()
  {
    WallClockInterval wci = wci1416;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  > 0);
    assertTrue(wci.compareTo(wci1014)  > 0);
    assertTrue(wci.compareTo(wci1212)  > 0);
    assertTrue(wci.compareTo(wci0810)  > 0);
    assertTrue(wci.compareTo(wci1416) == 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324)  > 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(!wci.contains(wci0810));
    assertTrue(wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(!wci.abuts(wci0000));
    assertTrue(!wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("160000")));

    assertTrue(!wci.overlaps(wci1014));
    assertNull(wci.overlap(wci1014));
    //assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    //assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1416));
    //assertNull(wci.overlap(wci1416));
    assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("160000")));

    assertTrue(!wci.overlaps(wci0011));
    assertNull(wci.overlap(wci0011));
    //assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1324));
    //assertNull(wci.overlap(wci1324));
    assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("160000")));








    //assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("140000")));

    //assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf("160000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("140000")));

    //assertNull(wci.gap(wci0810));
    assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("140000")));

    assertNull(wci.gap(wci1416));
    //assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci0011));
    assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("140000")));

    assertNull(wci.gap(wci1324));
    //assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

  }






  @Test
  public void testWci0011()
  {
    WallClockInterval wci = wci0011;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  < 0);
    assertTrue(wci.compareTo(wci1014)  < 0);
    assertTrue(wci.compareTo(wci1212)  < 0);
    assertTrue(wci.compareTo(wci0810)  < 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011) == 0);
    assertTrue(wci.compareTo(wci1324)  < 0);

    assertTrue(wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(wci.contains(wci0810));
    assertTrue(!wci.contains(wci1416));
    assertTrue(wci.contains(wci0011));
    assertTrue(!wci.contains(wci1324));

    assertTrue(wci.abuts(wci0000));
    assertTrue(!wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("110000")));

    assertTrue(wci.overlaps(wci1014));
    //assertNull(wci.overlap(wci1014));
    assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("110000")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0810));
    //assertNull(wci.overlap(wci0810));
    assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf("080000"), WallClock.valueOf("100000")));

    assertTrue(!wci.overlaps(wci1416));
    assertNull(wci.overlap(wci1416));
    //assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0011));
    //assertNull(wci.overlap(wci0011));
    assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("110000")));

    assertTrue(!wci.overlaps(wci1324));
    assertNull(wci.overlap(wci1324));
    //assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));








    assertNull(wci.gap(wci0000));
    //assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci2424));
    assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("240000")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("120000")));

    assertNull(wci.gap(wci0810));
    //assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1416));
    assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("140000")));

    assertNull(wci.gap(wci0011));
    //assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1324));
    assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("130000")));

  }









  @Test
  public void testWci1324()
  {
    WallClockInterval wci = wci1324;
    WallClockInterval wciNull = null;

    assertTrue(wci.compareTo(wci0000)  > 0);
    assertTrue(wci.compareTo(wci2424)  < 0);
    assertTrue(wci.compareTo(wci0024)  > 0);
    assertTrue(wci.compareTo(wci1014)  > 0);
    assertTrue(wci.compareTo(wci1212)  > 0);
    assertTrue(wci.compareTo(wci0810)  > 0);
    assertTrue(wci.compareTo(wci1416)  < 0);
    assertTrue(wci.compareTo(wci0011)  > 0);
    assertTrue(wci.compareTo(wci1324) == 0);

    assertTrue(!wci.contains(wci0000));
    assertTrue(!wci.contains(wci2424));
    assertTrue(!wci.contains(wci0024));
    assertTrue(!wci.contains(wci1014));
    assertTrue(!wci.contains(wci1212));
    assertTrue(!wci.contains(wci0810));
    assertTrue(wci.contains(wci1416));
    assertTrue(!wci.contains(wci0011));
    assertTrue(wci.contains(wci1324));

    assertTrue(!wci.abuts(wci0000));
    assertTrue(wci.abuts(wci2424));
    assertTrue(!wci.abuts(wci0024));
    assertTrue(!wci.abuts(wci1014));
    assertTrue(!wci.abuts(wci1212));
    assertTrue(!wci.abuts(wci0810));
    assertTrue(!wci.abuts(wci1416));
    assertTrue(!wci.abuts(wci0011));
    assertTrue(!wci.abuts(wci1324));



    assertTrue(!wci.overlaps(wci0000));
    assertNull(wci.overlap(wci0000));
    //assertEquals(wci.overlap(wci0000), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci2424));
    assertNull(wci.overlap(wci2424));
    //assertEquals(wci.overlap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci0024));
    //assertNull(wci.overlap(wci0024));
    assertEquals(wci.overlap(wci0024), new WallClockInterval(WallClock.valueOf("130000"), WallClock.valueOf("240000")));

    assertTrue(wci.overlaps(wci1014));
    //assertNull(wci.overlap(wci1014));
    assertEquals(wci.overlap(wci1014), new WallClockInterval(WallClock.valueOf("130000"), WallClock.valueOf("140000")));

    assertTrue(!wci.overlaps(wci1212));
    assertNull(wci.overlap(wci1212));
    //assertEquals(wci.overlap(wci1212), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(!wci.overlaps(wci0810));
    assertNull(wci.overlap(wci0810));
    //assertEquals(wci.overlap(wci0810), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1416));
    //assertNull(wci.overlap(wci1416));
    assertEquals(wci.overlap(wci1416), new WallClockInterval(WallClock.valueOf("140000"), WallClock.valueOf("160000")));

    assertTrue(!wci.overlaps(wci0011));
    assertNull(wci.overlap(wci0011));
    //assertEquals(wci.overlap(wci0011), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertTrue(wci.overlaps(wci1324));
    //assertNull(wci.overlap(wci1324));
    assertEquals(wci.overlap(wci1324), new WallClockInterval(WallClock.valueOf("130000"), WallClock.valueOf("240000")));








    //assertNull(wci.gap(wci0000));
    assertEquals(wci.gap(wci0000), new WallClockInterval(WallClock.valueOf("000000"), WallClock.valueOf("130000")));

    assertNull(wci.gap(wci2424));
    //assertEquals(wci.gap(wci2424), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci0024));
    //assertEquals(wci.gap(wci0024), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    assertNull(wci.gap(wci1014));
    //assertEquals(wci.gap(wci1014), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci1212));
    assertEquals(wci.gap(wci1212), new WallClockInterval(WallClock.valueOf("120000"), WallClock.valueOf("130000")));

    //assertNull(wci.gap(wci0810));
    assertEquals(wci.gap(wci0810), new WallClockInterval(WallClock.valueOf("100000"), WallClock.valueOf("130000")));

    assertNull(wci.gap(wci1416));
    //assertEquals(wci.gap(wci1416), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

    //assertNull(wci.gap(wci0011));
    assertEquals(wci.gap(wci0011), new WallClockInterval(WallClock.valueOf("110000"), WallClock.valueOf("130000")));

    assertNull(wci.gap(wci1324));
    //assertEquals(wci.gap(wci1324), new WallClockInterval(WallClock.valueOf(""), WallClock.valueOf("")));

  }





}

