package com.tkmtwo.timex.convert;

import org.joda.time.DateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class IsoDateTimeConverterTest {

  private IsoDateTimeConverter dtConverter = new IsoDateTimeConverter();

  
  @Test
  public void testBlanksAndNulls() {
    String[] ss = new String[] {
      null,
      "",
      "  ",
      "\t\r\n",
      " \t \r \n "
    };
    
    for (String s : ss) {
      assertNull(dtConverter.convert(s));
    }
    
  }
  
  
  @Test
  public void testPass() {
    DateTime dt = new DateTime(1000L);

    String[] ss = new String[] {

      //Extended with millis
      "1970-01-01T00:00:01.000Z",
      " 1970-01-01T00:00:01.000Z",
      "1970-01-01T00:00:01.000Z ",
      " 1970-01-01T00:00:01.000Z ",

      //Extended without millis
      "1970-01-01T00:00:01Z",
      " 1970-01-01T00:00:01Z",
      "1970-01-01T00:00:01Z ",
      " 1970-01-01T00:00:01Z ",

      //Basic with millis
      "19700101T000001.000Z",
      " 19700101T000001.000Z",
      "19700101T000001.000Z ",
      " 19700101T000001.000Z ",

      //Basic without millis
      "19700101T000001Z",
      " 19700101T000001Z",
      "19700101T000001Z ",
      " 19700101T000001Z ",

      //Longs
      "1000",
      " 1000",
      "1000 ",
      " 1000 "
      
    };
    
    for (String s : ss) {
      assertEquals(dt, dtConverter.convert(s));
    }
    
  }
  
  
  
  
  @Test
  public void testInvalidStrings() {
    String[] ss = new String[] {
      "abc",
      "a34x"
    };

    for (String s : ss) {
      try {
        DateTime dt = dtConverter.convert(s);
        fail("Should not have evaluated " + String.valueOf(dt));
      } catch (IllegalArgumentException iae) {
        ; //do nothing
      }
    }
  }
  
  
}
