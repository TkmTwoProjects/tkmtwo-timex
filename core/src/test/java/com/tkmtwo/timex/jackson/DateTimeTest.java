package com.tkmtwo.timex.jackson;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Before;
import org.junit.Test;

public class DateTimeTest {


  @Test
  public void testThis() {
    assertTrue(System.currentTimeMillis() > 0L);
  }

  private String quoted(String s) {
    return '"' + s + '"';
  }

  @Test
  public void testSer()
    throws Exception {
    
    ObjectMapper om = new JodaMapper();

    /*    
    assertEquals(quoted("1970-01-01T00:00:00Z"),
                 om.writeValueAsString(DateTimes.noMillis(0L)));
    assertEquals(quoted("1970-01-01T00:00:03Z"),
                 om.writeValueAsString(DateTimes.noMillis(3000L)));
    */

    assertEquals(quoted("1970-01-01T00:00:00.000Z"),
                 om.writeValueAsString(new DateTime(0L)));
    assertEquals(quoted("1970-01-01T00:00:03.000Z"),
                 om.writeValueAsString(new DateTime(3000L)));

    
  }


  @Test
  public void testDeser()
    throws Exception {
    
    DateTime threeSeconds = new DateTime(3000L);
    ObjectMapper om = new JodaMapper();
    
    String[] ss = new String[] {
      "1970-01-01T00:00:03.000Z",
      "1970-01-01T00:00:03Z",
      "19700101T000003.000Z",
      "19700101T000003Z",
      "3000",
    };
    
    for (String s : ss) {
      assertEquals(threeSeconds, om.readValue(quoted(s), DateTime.class));
    }


    assertNull(om.readValue(quoted(""), DateTime.class));
    assertNull(om.readValue(quoted(" "), DateTime.class));
    assertNull(om.readValue(quoted(" \\t\\n\\r "), DateTime.class));

    
  }
  
  
  
}
