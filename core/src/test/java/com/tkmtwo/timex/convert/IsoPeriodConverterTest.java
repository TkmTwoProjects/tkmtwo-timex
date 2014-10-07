package com.tkmtwo.timex.convert;


import org.joda.time.Period;
import org.joda.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


public class IsoPeriodConverterTest
{
  private IsoPeriodConverter ipConverter = null;

  @Before
  public void setUp()
  {
    ipConverter = new IsoPeriodConverter();
  }


  @Test
  public void testInvalidStrings()
  {
    String[] ss = new String[] {
      null,
      "",
      "  ",
      "abc",
      "a34x",
      "new org.joda.time.Duration(abc)",
      "new org.joda.time.Duration(0L)",
      "new org.joda.time.Duration(\"abc\")"
    };

    for (String s : ss) {
      try {
        Period p = ipConverter.convert(s);
        fail("Should not have evaluated " + String.valueOf(p));
      } catch (IllegalArgumentException iae) {
        ; //do nothing
      }
    }
  }



  @Test
  public void testPass()
  {

    assertEquals(new Duration(3600000L),
                 ipConverter.convert("PT1H").toStandardDuration());

    assertEquals(new Duration(86400000L),
                 ipConverter.convert("P1D").toStandardDuration());

  }




}
