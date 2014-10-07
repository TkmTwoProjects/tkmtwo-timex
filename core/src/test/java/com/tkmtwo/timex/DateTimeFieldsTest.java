package com.tkmtwo.timex;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

//com.tkmtwo.util.interpolate.Interpolator;
//import com.tkmtwo.util.java.lang.Enums;


/*
  Moon Landing: 1969-07-24T16:50:35.730Z
 */

public class DateTimeFieldsTest
{
  DateTimeFormatter dtf;
  DateTime dt;


  @Before
  public void setUp()
  {
    dtf = ISODateTimeFormat.dateTime().withZoneUTC();
    dt = dtf.parseDateTime("1969-07-24T16:50:35.730Z");
  }



  @Test
  public void testRoundFloor()
  {
    /*
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.730Z")
               .isEqual(DateTimeFields.MILLIS.roundFloor(dt)));
    */




    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.730Z").isEqual(DateTimeFields.MILLIS.roundFloor(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.000Z").isEqual(DateTimeFields.SECOND.roundFloor(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:00.000Z").isEqual(DateTimeFields.MINUTE.roundFloor(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:00:00.000Z").isEqual(DateTimeFields.HOUR.roundFloor(dt)));

    assertTrue(dtf.parseDateTime("1969-07-24T00:00:00.000Z").isEqual(DateTimeFields.DAY.roundFloor(dt)));
    assertTrue(dtf.parseDateTime("1969-07-01T00:00:00.000Z").isEqual(DateTimeFields.MONTH.roundFloor(dt)));


    assertTrue(dtf.parseDateTime("1969-01-01T00:00:00.000Z").isEqual(DateTimeFields.YEAR.roundFloor(dt)));


    assertTrue(dtf.parseDateTime("1900-01-01T00:00:00.000Z").isEqual(DateTimeFields.CENTURY.roundFloor(dt)));

  }


  @Test
  public void testRoundFloorAdd()
  {
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.730Z").isEqual(DateTimeFields.MILLIS.roundFloor(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:36.000Z").isEqual(DateTimeFields.SECOND.roundFloorAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:51:00.000Z").isEqual(DateTimeFields.MINUTE.roundFloorAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T17:00:00.000Z").isEqual(DateTimeFields.HOUR.roundFloorAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T15:00:00.000Z").isEqual(DateTimeFields.HOUR.roundFloorAdd(dt, -1)));
    assertTrue(dtf.parseDateTime("1969-07-25T00:00:00.000Z").isEqual(DateTimeFields.DAY.roundFloorAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-08-01T00:00:00.000Z").isEqual(DateTimeFields.MONTH.roundFloorAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:00:00.000Z").isEqual(DateTimeFields.YEAR.roundFloorAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("2000-01-01T00:00:00.000Z").isEqual(DateTimeFields.CENTURY.roundFloorAdd(dt, 1)));

  }





  @Test
  public void testRoundCeiling()
  {
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.730Z").isEqual(DateTimeFields.MILLIS.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:36.000Z").isEqual(DateTimeFields.SECOND.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:51:00.000Z").isEqual(DateTimeFields.MINUTE.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("1969-07-24T17:00:00.000Z").isEqual(DateTimeFields.HOUR.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("1969-07-25T00:00:00.000Z").isEqual(DateTimeFields.DAY.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("1969-08-01T00:00:00.000Z").isEqual(DateTimeFields.MONTH.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("1970-01-01T00:00:00.000Z").isEqual(DateTimeFields.YEAR.roundCeiling(dt)));
    assertTrue(dtf.parseDateTime("2000-01-01T00:00:00.000Z").isEqual(DateTimeFields.CENTURY.roundCeiling(dt)));
  }

  @Test
  public void testRoundCeilingAdd()
  {
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.731Z").isEqual(DateTimeFields.MILLIS.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:37.000Z").isEqual(DateTimeFields.SECOND.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:52:00.000Z").isEqual(DateTimeFields.MINUTE.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T18:00:00.000Z").isEqual(DateTimeFields.HOUR.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-26T00:00:00.000Z").isEqual(DateTimeFields.DAY.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-09-01T00:00:00.000Z").isEqual(DateTimeFields.MONTH.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("1971-01-01T00:00:00.000Z").isEqual(DateTimeFields.YEAR.roundCeilingAdd(dt, 1)));
    assertTrue(dtf.parseDateTime("2100-01-01T00:00:00.000Z").isEqual(DateTimeFields.CENTURY.roundCeilingAdd(dt, 1)));
  }

  @Test
  public void testAddSimple()
  {
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.731Z").isEqual(DateTimeFields.MILLIS.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.729Z").isEqual(DateTimeFields.MILLIS.add(dt, -1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:36.730Z").isEqual(DateTimeFields.SECOND.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:34.730Z").isEqual(DateTimeFields.SECOND.add(dt, -1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:51:35.730Z").isEqual(DateTimeFields.MINUTE.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:49:35.730Z").isEqual(DateTimeFields.MINUTE.add(dt, -1)));
    assertTrue(dtf.parseDateTime("1969-07-24T17:50:35.730Z").isEqual(DateTimeFields.HOUR.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-24T15:50:35.730Z").isEqual(DateTimeFields.HOUR.add(dt, -1)));
    assertTrue(dtf.parseDateTime("1969-07-25T16:50:35.730Z").isEqual(DateTimeFields.DAY.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-07-23T16:50:35.730Z").isEqual(DateTimeFields.DAY.add(dt, -1)));
    assertTrue(dtf.parseDateTime("1969-08-24T16:50:35.730Z").isEqual(DateTimeFields.MONTH.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1969-06-24T16:50:35.730Z").isEqual(DateTimeFields.MONTH.add(dt, -1)));
    assertTrue(dtf.parseDateTime("1970-07-24T16:50:35.730Z").isEqual(DateTimeFields.YEAR.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1968-07-24T16:50:35.730Z").isEqual(DateTimeFields.YEAR.add(dt, -1)));
    assertTrue(dtf.parseDateTime("2069-07-24T16:50:35.730Z").isEqual(DateTimeFields.CENTURY.add(dt, 1)));
    assertTrue(dtf.parseDateTime("1869-07-24T16:50:35.730Z").isEqual(DateTimeFields.CENTURY.add(dt, -1)));
  }



  @Test
  public void testAddComplex()
  {
    //Control
    assertTrue(dtf.parseDateTime("1969-07-24T16:50:35.730Z").isEqual(DateTimeFields.MILLIS.add(dt, 0)));



    //Roll a minute
    assertTrue(dtf.parseDateTime("1969-07-24T16:51:05.730Z").isEqual(DateTimeFields.SECOND.add(dt, 30)));
    assertTrue(dtf.parseDateTime("1969-07-24T16:49:30.730Z").isEqual(DateTimeFields.SECOND.add(dt, -65)));

    //Roll an hour
    assertTrue(dtf.parseDateTime("1969-07-24T17:02:35.730Z").isEqual(DateTimeFields.MINUTE.add(dt, 12)));
    assertTrue(dtf.parseDateTime("1969-07-24T15:49:35.730Z").isEqual(DateTimeFields.MINUTE.add(dt, -61)));

    //Roll a day
    assertTrue(dtf.parseDateTime("1969-07-25T00:50:35.730Z").isEqual(DateTimeFields.HOUR.add(dt, 8)));
    assertTrue(dtf.parseDateTime("1969-07-23T15:50:35.730Z").isEqual(DateTimeFields.HOUR.add(dt, -25)));

    //Roll a month
    assertTrue(dtf.parseDateTime("1969-08-01T16:50:35.730Z").isEqual(DateTimeFields.DAY.add(dt, 8)));
    assertTrue(dtf.parseDateTime("1969-06-29T16:50:35.730Z").isEqual(DateTimeFields.DAY.add(dt, -25)));

    //Roll a year
    assertTrue(dtf.parseDateTime("1970-01-24T16:50:35.730Z").isEqual(DateTimeFields.MONTH.add(dt, 6)));
    assertTrue(dtf.parseDateTime("1968-06-24T16:50:35.730Z").isEqual(DateTimeFields.MONTH.add(dt, -13)));

    //Roll a century
    assertTrue(dtf.parseDateTime("2000-07-24T16:50:35.730Z").isEqual(DateTimeFields.YEAR.add(dt, 31)));
    assertTrue(dtf.parseDateTime("1899-07-24T16:50:35.730Z").isEqual(DateTimeFields.YEAR.add(dt, -70)));
  }




  public void testResolution()
  {
    assertEquals(DateTimeFields.MILLIS, DateTimeFields.valueOf("MILLIS"));
    assertEquals(DateTimeFields.SECOND, DateTimeFields.valueOf("SECOND"));
    assertEquals(DateTimeFields.MINUTE, DateTimeFields.valueOf("MINUTE"));
    assertEquals(DateTimeFields.HOUR, DateTimeFields.valueOf("HOUR"));
    assertEquals(DateTimeFields.DAY, DateTimeFields.valueOf("DAY"));
    assertEquals(DateTimeFields.MONTH, DateTimeFields.valueOf("MONTH"));
    assertEquals(DateTimeFields.YEAR, DateTimeFields.valueOf("YEAR"));
    assertEquals(DateTimeFields.CENTURY, DateTimeFields.valueOf("CENTURY"));

    /*
    assertEquals(DateTimeFields.MILLIS,  Enums.valueOf(DateTimeFields.class, "MILLIS"));
    assertEquals(DateTimeFields.SECOND,  Enums.valueOf(DateTimeFields.class, "SECOND"));
    assertEquals(DateTimeFields.MINUTE,  Enums.valueOf(DateTimeFields.class, "MINUTE"));
    assertEquals(DateTimeFields.HOUR,    Enums.valueOf(DateTimeFields.class, "HOUR"));
    assertEquals(DateTimeFields.DAY,     Enums.valueOf(DateTimeFields.class, "DAY"));
    assertEquals(DateTimeFields.MONTH,   Enums.valueOf(DateTimeFields.class, "MONTH"));
    assertEquals(DateTimeFields.YEAR,    Enums.valueOf(DateTimeFields.class, "YEAR"));
    assertEquals(DateTimeFields.CENTURY, Enums.valueOf(DateTimeFields.class, "CENTURY"));
    */
  }





}

