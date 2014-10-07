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

import static com.google.common.base.TkmTwoStrings.isBlank;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.tkmtwo.timex.DateTimes;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;


/**
 *
 */
public final class DateTimeDeserializer
  extends StdScalarDeserializer<DateTime> {
  
  private static final long serialVersionUID = 1L;
  private transient DateTimeFormatter dateTimeFormatter = DateTimes.getExtendedFormatter();

  public DateTimeDeserializer() { super(DateTime.class); }
  public DateTimeDeserializer(DateTimeFormatter dtf) {
    super(DateTime.class);
    dateTimeFormatter = dtf;
  }
  
  public DateTimeFormatter getDateTimeFormatter() {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = DateTimes.getExtendedFormatter();
    }
    
    return dateTimeFormatter;
  }
  public void setDateTimeFormatter(DateTimeFormatter dtf) {
    dateTimeFormatter = dtf;
  }


  public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    throws IOException, JsonProcessingException {
    if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
      String dtText = jsonParser.getText();
      if (isBlank(dtText)) { return null; }

      //return getDateTimeFormatter().parseDateTime(jsonParser.getText());
      return getDateTimeFormatter().parseDateTime(dtText);
    }
    
    throw deserializationContext.mappingException("Expected JSON Text");
  }
  
}
