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

package com.tkmtwo.timex.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


/**
 *
 */
public final class DateTimeSerializer
  extends StdScalarSerializer<DateTime> {
  
  private DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();

  public DateTimeSerializer() { super(DateTime.class); }
  public DateTimeSerializer(DateTimeFormatter dtf) {
    super(DateTime.class);
    dateTimeFormatter = dtf;
  }
  
  public DateTimeFormatter getDateTimeFormatter() {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = ISODateTimeFormat.dateTime();
    }
    
    return dateTimeFormatter;
  }
  public void setDateTimeFormatter(DateTimeFormatter dtf) {
    dateTimeFormatter = dtf;
  }


  public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException {
    jgen.writeString(getDateTimeFormatter().print(value));
  }
  
  
}
