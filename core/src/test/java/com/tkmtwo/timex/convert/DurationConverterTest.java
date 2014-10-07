package com.tkmtwo.timex.convert;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.Duration;
import org.joda.time.Hours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


public class DurationConverterTest
{
  private DurationConverter durationConverter = null;
  
  @Before
  public void setUp()
  {
    durationConverter = new DurationConverter();
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
      "new org.joda.time.Duration(\"abc\")"
    };

    for (String s : ss) {
      try {
        Duration d = durationConverter.convert(s);
        fail("Should not have evaluated " + String.valueOf(d));
      } catch (IllegalArgumentException ex) {
        System.out.println("OK: " + ex.getClass().getName());
        ; //do nothing
      }
    }
  }

  
  
  @Test
  public void testPass()
  {
    assertEquals(new Duration(5L),
                 durationConverter.convert("5L"));

    assertEquals(new Duration(-5L),
                 durationConverter.convert("-5L"));

    assertEquals(new Duration(60L),
                 durationConverter.convert("2L * 30L"));

    assertEquals(new Duration(3600000L),
                 durationConverter.convert("60 * 60 * 1000"));

    assertEquals(new Duration(86400000L),
                 durationConverter.convert("24*60*60*1000"));
    
  }
  

  
  
  
}
