package com.tkmtwo.timex;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class DateTimeComparisonTest
{

  private DateTime dtNeg = new DateTime(-1000L);
  private DateTime dtZero = new DateTime(0L);
  private DateTime dtPos = new DateTime(1000L);


  @Test
  public void testEq()
  {
    assertTrue(!DateTimeComparison.EQ.compare((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeComparison.EQ.compare((DateTime) null, dtNeg));
    assertTrue(!DateTimeComparison.EQ.compare((DateTime) null, dtZero));
    assertTrue(!DateTimeComparison.EQ.compare((DateTime) null, dtPos));

    assertTrue(!DateTimeComparison.EQ.compare(dtNeg, (DateTime) null));
    assertTrue(DateTimeComparison.EQ.compare(dtNeg, dtNeg));
    assertTrue(!DateTimeComparison.EQ.compare(dtNeg, dtZero));
    assertTrue(!DateTimeComparison.EQ.compare(dtNeg, dtPos));

    assertTrue(!DateTimeComparison.EQ.compare(dtZero, (DateTime) null));
    assertTrue(!DateTimeComparison.EQ.compare(dtZero, dtNeg));
    assertTrue(DateTimeComparison.EQ.compare(dtZero, dtZero));
    assertTrue(!DateTimeComparison.EQ.compare(dtZero, dtPos));

    assertTrue(!DateTimeComparison.EQ.compare(dtPos, (DateTime) null));
    assertTrue(!DateTimeComparison.EQ.compare(dtPos, dtNeg));
    assertTrue(!DateTimeComparison.EQ.compare(dtPos, dtZero));
    assertTrue(DateTimeComparison.EQ.compare(dtPos, dtPos));
  }


  @Test
  public void testNeq()
  {
    assertTrue(!DateTimeComparison.NEQ.compare((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeComparison.NEQ.compare((DateTime) null, dtNeg));
    assertTrue(!DateTimeComparison.NEQ.compare((DateTime) null, dtZero));
    assertTrue(!DateTimeComparison.NEQ.compare((DateTime) null, dtPos));

    assertTrue(!DateTimeComparison.NEQ.compare(dtNeg, (DateTime) null));
    assertTrue(!DateTimeComparison.NEQ.compare(dtNeg, dtNeg));
    assertTrue(DateTimeComparison.NEQ.compare(dtNeg, dtZero));
    assertTrue(DateTimeComparison.NEQ.compare(dtNeg, dtPos));

    assertTrue(!DateTimeComparison.NEQ.compare(dtZero, (DateTime) null));
    assertTrue(DateTimeComparison.NEQ.compare(dtZero, dtNeg));
    assertTrue(!DateTimeComparison.NEQ.compare(dtZero, dtZero));
    assertTrue(DateTimeComparison.NEQ.compare(dtZero, dtPos));

    assertTrue(!DateTimeComparison.NEQ.compare(dtPos, (DateTime) null));
    assertTrue(DateTimeComparison.NEQ.compare(dtPos, dtNeg));
    assertTrue(DateTimeComparison.NEQ.compare(dtPos, dtZero));
    assertTrue(!DateTimeComparison.NEQ.compare(dtPos, dtPos));
  }



  @Test
  public void testLt()
  {
    assertTrue(!DateTimeComparison.LT.compare((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeComparison.LT.compare((DateTime) null, dtNeg));
    assertTrue(!DateTimeComparison.LT.compare((DateTime) null, dtZero));
    assertTrue(!DateTimeComparison.LT.compare((DateTime) null, dtPos));

    assertTrue(!DateTimeComparison.LT.compare(dtNeg, (DateTime) null));
    assertTrue(!DateTimeComparison.LT.compare(dtNeg, dtNeg));
    assertTrue(DateTimeComparison.LT.compare(dtNeg, dtZero));
    assertTrue(DateTimeComparison.LT.compare(dtNeg, dtPos));

    assertTrue(!DateTimeComparison.LT.compare(dtZero, (DateTime) null));
    assertTrue(!DateTimeComparison.LT.compare(dtZero, dtNeg));
    assertTrue(!DateTimeComparison.LT.compare(dtZero, dtZero));
    assertTrue(DateTimeComparison.LT.compare(dtZero, dtPos));

    assertTrue(!DateTimeComparison.LT.compare(dtPos, (DateTime) null));
    assertTrue(!DateTimeComparison.LT.compare(dtPos, dtNeg));
    assertTrue(!DateTimeComparison.LT.compare(dtPos, dtZero));
    assertTrue(!DateTimeComparison.LT.compare(dtPos, dtPos));
  }

  @Test
  public void testGt()
  {
    assertTrue(!DateTimeComparison.GT.compare((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeComparison.GT.compare((DateTime) null, dtNeg));
    assertTrue(!DateTimeComparison.GT.compare((DateTime) null, dtZero));
    assertTrue(!DateTimeComparison.GT.compare((DateTime) null, dtPos));

    assertTrue(!DateTimeComparison.GT.compare(dtNeg, (DateTime) null));
    assertTrue(!DateTimeComparison.GT.compare(dtNeg, dtNeg));
    assertTrue(!DateTimeComparison.GT.compare(dtNeg, dtZero));
    assertTrue(!DateTimeComparison.GT.compare(dtNeg, dtPos));

    assertTrue(!DateTimeComparison.GT.compare(dtZero, (DateTime) null));
    assertTrue(DateTimeComparison.GT.compare(dtZero, dtNeg));
    assertTrue(!DateTimeComparison.GT.compare(dtZero, dtZero));
    assertTrue(!DateTimeComparison.GT.compare(dtZero, dtPos));

    assertTrue(!DateTimeComparison.GT.compare(dtPos, (DateTime) null));
    assertTrue(DateTimeComparison.GT.compare(dtPos, dtNeg));
    assertTrue(DateTimeComparison.GT.compare(dtPos, dtZero));
    assertTrue(!DateTimeComparison.GT.compare(dtPos, dtPos));
  }



  @Test
  public void testLteq()
  {
    assertTrue(!DateTimeComparison.LTEQ.compare((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeComparison.LTEQ.compare((DateTime) null, dtNeg));
    assertTrue(!DateTimeComparison.LTEQ.compare((DateTime) null, dtZero));
    assertTrue(!DateTimeComparison.LTEQ.compare((DateTime) null, dtPos));

    assertTrue(!DateTimeComparison.LTEQ.compare(dtNeg, (DateTime) null));
    assertTrue(DateTimeComparison.LTEQ.compare(dtNeg, dtNeg));
    assertTrue(DateTimeComparison.LTEQ.compare(dtNeg, dtZero));
    assertTrue(DateTimeComparison.LTEQ.compare(dtNeg, dtPos));

    assertTrue(!DateTimeComparison.LTEQ.compare(dtZero, (DateTime) null));
    assertTrue(!DateTimeComparison.LTEQ.compare(dtZero, dtNeg));
    assertTrue(DateTimeComparison.LTEQ.compare(dtZero, dtZero));
    assertTrue(DateTimeComparison.LTEQ.compare(dtZero, dtPos));

    assertTrue(!DateTimeComparison.LTEQ.compare(dtPos, (DateTime) null));
    assertTrue(!DateTimeComparison.LTEQ.compare(dtPos, dtNeg));
    assertTrue(!DateTimeComparison.LTEQ.compare(dtPos, dtZero));
    assertTrue(DateTimeComparison.LTEQ.compare(dtPos, dtPos));
  }

  @Test
  public void testGteq()
  {
    assertTrue(!DateTimeComparison.GTEQ.compare((DateTime) null, (DateTime) null));
    assertTrue(!DateTimeComparison.GTEQ.compare((DateTime) null, dtNeg));
    assertTrue(!DateTimeComparison.GTEQ.compare((DateTime) null, dtZero));
    assertTrue(!DateTimeComparison.GTEQ.compare((DateTime) null, dtPos));

    assertTrue(!DateTimeComparison.GTEQ.compare(dtNeg, (DateTime) null));
    assertTrue(DateTimeComparison.GTEQ.compare(dtNeg, dtNeg));
    assertTrue(!DateTimeComparison.GTEQ.compare(dtNeg, dtZero));
    assertTrue(!DateTimeComparison.GTEQ.compare(dtNeg, dtPos));

    assertTrue(!DateTimeComparison.GTEQ.compare(dtZero, (DateTime) null));
    assertTrue(DateTimeComparison.GTEQ.compare(dtZero, dtNeg));
    assertTrue(DateTimeComparison.GTEQ.compare(dtZero, dtZero));
    assertTrue(!DateTimeComparison.GTEQ.compare(dtZero, dtPos));

    assertTrue(!DateTimeComparison.GTEQ.compare(dtPos, (DateTime) null));
    assertTrue(DateTimeComparison.GTEQ.compare(dtPos, dtNeg));
    assertTrue(DateTimeComparison.GTEQ.compare(dtPos, dtZero));
    assertTrue(DateTimeComparison.GTEQ.compare(dtPos, dtPos));
  }



}
