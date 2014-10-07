package com.tkmtwo.timex;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.Interval;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class DateTimeDirectionTest
{

  private DateTime dtNeg = new DateTime(-1000L);
  private DateTime dtZero = new DateTime(0L);
  private DateTime dtPos = new DateTime(1000L);



  @Test
  public void testForwardPlus()
  {
    DateTime dt = new DateTime(0L);

    assertTrue(dt.plus(60000L)
               .isEqual(DateTimeDirection.FORWARD.plus(dt,
                                                       new Duration(60000L))));
    assertTrue(dt.plus(60000L)
               .isEqual(DateTimeDirection.FORWARD.plus(dt,
                                                       new Period(60000L))));
    assertTrue(dt.plus(60000L)
               .isEqual(DateTimeDirection.FORWARD.plus(dt,
                                                       Periods.parse("PT1M"))));
  }


  @Test
  public void testReversePlus()
  {
    DateTime dt = new DateTime(0L);

    assertTrue(dt.minus(60000L)
               .isEqual(DateTimeDirection.REVERSE.plus(dt,
                                                       new Duration(60000L))));
    assertTrue(dt.minus(60000L)
               .isEqual(DateTimeDirection.REVERSE.plus(dt,
                                                       new Period(60000L))));
    assertTrue(dt.minus(60000L)
               .isEqual(DateTimeDirection.REVERSE.plus(dt,
                                                       Periods.parse("PT1M"))));
  }



  @Test
  public void testForwardMinus()
  {
    DateTime dt = new DateTime(0L);

    assertTrue(dt.minus(60000L)
               .isEqual(DateTimeDirection.FORWARD.minus(dt,
                                                        new Duration(60000L))));
    assertTrue(dt.minus(60000L)
               .isEqual(DateTimeDirection.FORWARD.minus(dt,
                                                        new Period(60000L))));
    assertTrue(dt.minus(60000L)
               .isEqual(DateTimeDirection.FORWARD.minus(dt,
                                                        Periods.parse("PT1M"))));
  }



  @Test
  public void testReverseMinus()
  {
    DateTime dt = new DateTime(0L);

    assertTrue(dt.plus(60000L)
               .isEqual(DateTimeDirection.REVERSE.minus(dt,
                                                        new Duration(60000L))));
    assertTrue(dt.plus(60000L)
               .isEqual(DateTimeDirection.REVERSE.minus(dt,
                                                        new Period(60000L))));
    assertTrue(dt.plus(60000L)
               .isEqual(DateTimeDirection.REVERSE.minus(dt,
                                                        Periods.parse("PT1M"))));
  }



  @Test
  public void testPluses()
  {
    DateTime dtStart = new DateTime(0L);
    long duraLong = 1000L;

    for (long l = 1; l < 5; l++) {
      Duration duration = new Duration(l * duraLong);
      DateTime forDateTime = DateTimeDirection.FORWARD.plus(dtStart, duration);
      DateTime revDateTime = DateTimeDirection.REVERSE.plus(dtStart, duration);

      assertTrue(dtStart.plus(duration).isEqual(forDateTime));
      assertTrue(dtStart.minus(duration).isEqual(revDateTime));

      assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtStart, forDateTime));
      assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtStart, forDateTime));
      assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtStart, forDateTime));
      assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtStart, forDateTime));
      assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtStart, forDateTime));
      assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtStart, forDateTime));


      assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtStart, revDateTime));
      assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtStart, revDateTime));
      assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtStart, revDateTime));
      assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtStart, revDateTime));
      assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtStart, revDateTime));
      assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtStart, revDateTime));
    }
  }





  @Test
  public void testForwardLt()
  {
    assertTrue(!DateTimeDirection.FORWARD.lt((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lt((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.lt((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.lt((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.lt(dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lt(dtNeg, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.lt(dtNeg, dtZero));
    assertTrue(DateTimeDirection.FORWARD.lt(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.lt(dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lt(dtZero, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.lt(dtZero, dtZero));
    assertTrue(DateTimeDirection.FORWARD.lt(dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.lt(dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lt(dtPos, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.lt(dtPos, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.lt(dtPos, dtPos));



    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtNeg, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtNeg, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtZero, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtZero, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtPos, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtPos, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LT, dtPos, dtPos));



  }

  @Test
  public void testReverseLt()
  {
    assertTrue(!DateTimeDirection.REVERSE.lt((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.lt((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.lt((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lt((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.lt(dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.lt(dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.lt(dtNeg, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lt(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.lt(dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.lt(dtZero, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.lt(dtZero, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lt(dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.lt(dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.lt(dtPos, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.lt(dtPos, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lt(dtPos, dtPos));



    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtNeg, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtZero, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtZero, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtPos, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtPos, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LT, dtPos, dtPos));
  }



  @Test
  public void testForwardLteq()
  {
    assertTrue(!DateTimeDirection.FORWARD.lteq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lteq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.lteq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.lteq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.lteq(dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.lteq(dtNeg, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.lteq(dtNeg, dtZero));
    assertTrue(DateTimeDirection.FORWARD.lteq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.lteq(dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lteq(dtZero, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.lteq(dtZero, dtZero));
    assertTrue(DateTimeDirection.FORWARD.lteq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.lteq(dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.lteq(dtPos, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.lteq(dtPos, dtZero));
    assertTrue(DateTimeDirection.FORWARD.lteq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtNeg, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtNeg, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtZero, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtZero, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtPos, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtPos, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.LTEQ, dtPos, dtPos));
  }



  @Test
  public void testReverseLteq()
  {
    assertTrue(!DateTimeDirection.REVERSE.lteq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.lteq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.lteq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lteq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.lteq(dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.lteq(dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.lteq(dtNeg, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lteq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.lteq(dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.lteq(dtZero, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.lteq(dtZero, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.lteq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.lteq(dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.lteq(dtPos, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.lteq(dtPos, dtZero));
    assertTrue(DateTimeDirection.REVERSE.lteq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtNeg, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtZero, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtZero, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtPos, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtPos, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.LTEQ, dtPos, dtPos));
  }


  @Test
  public void testForwardEq()
  {
    assertTrue(!DateTimeDirection.FORWARD.eq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.eq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.eq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.eq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.eq(dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.eq(dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.eq(dtNeg, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.eq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.eq(dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.eq(dtZero, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.eq(dtZero, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.eq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.eq(dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.eq(dtPos, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.eq(dtPos, dtZero));
    assertTrue(DateTimeDirection.FORWARD.eq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtNeg, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtZero, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtZero, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtPos, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtPos, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.EQ, dtPos, dtPos));
  }


  @Test
  public void testReverseEq()
  {
    assertTrue(!DateTimeDirection.REVERSE.eq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.eq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.eq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.eq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.eq(dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.eq(dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.eq(dtNeg, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.eq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.eq(dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.eq(dtZero, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.eq(dtZero, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.eq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.eq(dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.eq(dtPos, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.eq(dtPos, dtZero));
    assertTrue(DateTimeDirection.REVERSE.eq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtNeg, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtZero, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtZero, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtPos, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtPos, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.EQ, dtPos, dtPos));
  }


  @Test
  public void testForwardNeq()
  {
    assertTrue(!DateTimeDirection.FORWARD.neq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.neq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.neq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.neq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.neq(dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.neq(dtNeg, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.neq(dtNeg, dtZero));
    assertTrue(DateTimeDirection.FORWARD.neq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.neq(dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.neq(dtZero, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.neq(dtZero, dtZero));
    assertTrue(DateTimeDirection.FORWARD.neq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.neq(dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.neq(dtPos, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.neq(dtPos, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.neq(dtPos, dtPos));



    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtNeg, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtNeg, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtZero, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtZero, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtPos, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtPos, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.NEQ, dtPos, dtPos));

  }


  @Test
  public void testReverseNeq()
  {
    assertTrue(!DateTimeDirection.REVERSE.neq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.neq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.neq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.neq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.neq(dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.neq(dtNeg, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.neq(dtNeg, dtZero));
    assertTrue(DateTimeDirection.REVERSE.neq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.neq(dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.neq(dtZero, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.neq(dtZero, dtZero));
    assertTrue(DateTimeDirection.REVERSE.neq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.neq(dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.neq(dtPos, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.neq(dtPos, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.neq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtNeg, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtNeg, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtZero, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtZero, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtPos, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtPos, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.NEQ, dtPos, dtPos));
  }


  @Test
  public void testForwardGteq()
  {
    assertTrue(!DateTimeDirection.FORWARD.gteq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.gteq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.gteq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gteq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.gteq(dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.gteq(dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.gteq(dtNeg, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gteq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.gteq(dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.gteq(dtZero, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.gteq(dtZero, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gteq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.gteq(dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.gteq(dtPos, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.gteq(dtPos, dtZero));
    assertTrue(DateTimeDirection.FORWARD.gteq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtNeg, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtZero, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtZero, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtPos, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtPos, dtZero));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GTEQ, dtPos, dtPos));
  }

  @Test
  public void testReverseGteq()
  {
    assertTrue(!DateTimeDirection.REVERSE.gteq((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gteq((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.gteq((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.gteq((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.gteq(dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.gteq(dtNeg, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.gteq(dtNeg, dtZero));
    assertTrue(DateTimeDirection.REVERSE.gteq(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.gteq(dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gteq(dtZero, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.gteq(dtZero, dtZero));
    assertTrue(DateTimeDirection.REVERSE.gteq(dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.gteq(dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gteq(dtPos, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.gteq(dtPos, dtZero));
    assertTrue(DateTimeDirection.REVERSE.gteq(dtPos, dtPos));




    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtNeg, (DateTime) null));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtNeg, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtNeg, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtZero, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtZero, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtPos, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtPos, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GTEQ, dtPos, dtPos));
  }

  @Test
  public void testForwardGt()
  {
    assertTrue(!DateTimeDirection.FORWARD.gt((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.gt((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.gt((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gt((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.gt(dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.gt(dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.gt(dtNeg, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gt(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.gt(dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.gt(dtZero, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.gt(dtZero, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gt(dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.gt(dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.gt(dtPos, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.gt(dtPos, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.gt(dtPos, dtPos));




    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtNeg, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtNeg, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtZero, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtZero, dtNeg));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtZero, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtZero, dtPos));

    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtPos, (DateTime) null));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtPos, dtNeg));
    assertTrue(DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtPos, dtZero));
    assertTrue(!DateTimeDirection.FORWARD.compare(DateTimeComparison.GT, dtPos, dtPos));
  }


  @Test
  public void testReverseGt()
  {
    assertTrue(!DateTimeDirection.REVERSE.gt((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gt((DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.gt((DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.gt((DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.gt(dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gt(dtNeg, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.gt(dtNeg, dtZero));
    assertTrue(DateTimeDirection.REVERSE.gt(dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.gt(dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gt(dtZero, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.gt(dtZero, dtZero));
    assertTrue(DateTimeDirection.REVERSE.gt(dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.gt(dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.gt(dtPos, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.gt(dtPos, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.gt(dtPos, dtPos));




    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, (DateTime) null, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, (DateTime) null, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, (DateTime) null, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, (DateTime) null, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtNeg, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtNeg, dtNeg));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtNeg, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtNeg, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtZero, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtZero, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtZero, dtZero));
    assertTrue(DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtZero, dtPos));

    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtPos, (DateTime) null));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtPos, dtNeg));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtPos, dtZero));
    assertTrue(!DateTimeDirection.REVERSE.compare(DateTimeComparison.GT, dtPos, dtPos));
  }




}
