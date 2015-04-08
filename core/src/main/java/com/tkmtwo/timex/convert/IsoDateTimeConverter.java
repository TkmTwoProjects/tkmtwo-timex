/*
 *
 * Copyright 2014 Tom Mahaffey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tkmtwo.timex.convert;




import static com.google.common.base.TkmTwoStrings.blankToNull;

import com.google.common.base.CharMatcher;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.core.convert.converter.Converter;


/**
 * Coverts a <code>String</code> to a JodaTime <code>DateTime</code>
 * consistent with Spring's <code>Converter</code> facility.
 *
 *
 * Formats supported:
 *
 * <p>
 * <ul>
 *  <li>ISO8601 Extended (<code>yyyy-MM-dd'T'HH:mm:ss.SSSZ</code>)</li>
 *  <li>ISO8601 Basic (<code>yyyyMMdd'T'HHmmss.SSSZZ</code>)</li>
 *  <li>Milliseconds since epoch</li>
 * </ul>
 *
 * <p>
 * Date/time values may be provided with or without millisecond values.
 * Additionally, either the character 'T' or a single space may be used to
 * separate date from time.
 *
 * <p>
 * This converter uses JodaTime's <code>ISODateTimeFormat</code>.
 *
 * @author Tom Mahaffey
 * @see org.springframework.core.convert.converter.Converter
 * @see org.joda.time.format.ISODateTimeFormat#dateTime()
 * @see org.joda.time.format.ISODateTimeFormat#basicDateTime()
 */
public final class IsoDateTimeConverter
  implements Converter<String, DateTime> {
  
  private static final CharMatcher ASCII_DIGITS =
    CharMatcher.inRange('0', '9');
  private static final CharMatcher DATE_TIME_SEPARATOR =
    CharMatcher.anyOf("T ");
  
  
  @Override
  public DateTime convert(String s) {
    String ts = blankToNull(s);
    if (ts == null) { return null; }
    
    int tsLength = ts.length();
    
    if (tsLength >= 23 && DATE_TIME_SEPARATOR.matches(ts.charAt(10))) {
      
      //ISO Extended with millis yyyy-MM-ddTHH:mm:ss.SSSZ
      return ISODateTimeFormat.dateTime().parseDateTime(ensureTeeAndZee(ts, 10));
      
    } else if (tsLength >= 19 && DATE_TIME_SEPARATOR.matches(ts.charAt(10))) {
      
      //ISO Extended without millis yyyy-MM-ddTHH:mm:ssZ
      return ISODateTimeFormat.dateTimeNoMillis().parseDateTime(ensureTeeAndZee(ts, 10));
      
    } else if (tsLength >= 19 && DATE_TIME_SEPARATOR.matches(ts.charAt(8))) {
      
      //ISO Basic with millis yyyyMMddTHHmmss.SSSZ
      return ISODateTimeFormat.basicDateTime().parseDateTime(ensureTeeAndZee(ts, 8));
      
    } else if (tsLength >= 15 && DATE_TIME_SEPARATOR.matches(ts.charAt(8))) {
      
      //ISO Basic without millis yyyyMMddTHHmmssZ
      return ISODateTimeFormat.basicDateTimeNoMillis().parseDateTime(ensureTeeAndZee(ts, 8));
      
    } else if (ASCII_DIGITS.matchesAllOf(ts)) {
      
      return new DateTime(Long.parseLong(ts));
      
    }
    
    throw new IllegalArgumentException(String.format("Input string '%s' can not be converted to a DateTime.", s));
  }
  

  //caveat emptor...no error checking here
  //ensure we have a T separator where we expect it, and assume timezone "Z" 
  private String ensureTeeAndZee(String s, int tpos) {
    String rs = s;
    if (rs.charAt(tpos) != 'T') {
      rs = rs.substring(0, tpos) + 'T' + rs.substring(tpos + 1);
    }
    
    if (ASCII_DIGITS.matches(rs.charAt(rs.length() - 1))) {
      return rs + "Z";
    }
    return rs;
  }
  
  
}
