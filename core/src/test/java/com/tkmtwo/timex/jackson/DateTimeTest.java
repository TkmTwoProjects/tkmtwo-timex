package com.tkmtwo.timex.jackson;


import com.tkmtwo.timex.DateTimes;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
    
    assertEquals(quoted("1970-01-01T00:00:00Z"),
                 om.writeValueAsString(DateTimes.noMillis(0L)));
    assertEquals(quoted("1970-01-01T00:00:03Z"),
                 om.writeValueAsString(DateTimes.noMillis(3000L)));

    
  }


  @Test
  public void testDeser()
    throws Exception {
    
    ObjectMapper om = new JodaMapper();
    
    assertEquals(DateTimes.noMillis(0L),
                 om.readValue(quoted("1970-01-01T00:00:00Z"), DateTime.class));

    assertEquals(DateTimes.noMillis(3000L),
                 om.readValue(quoted("1970-01-01T00:00:03Z"), DateTime.class));

    assertNull(om.readValue(quoted(""), DateTime.class));
    assertNull(om.readValue(quoted(" "), DateTime.class));
    assertNull(om.readValue(quoted(" \\t\\n\\r "), DateTime.class));

    
  }
  
  
  
}
