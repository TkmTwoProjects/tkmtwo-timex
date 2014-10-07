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
package com.tkmtwo.timex.ibatis;

import com.tkmtwo.timex.DateTimes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 *
 */
public class DateTimeIntSecsTypeHandler
  extends BaseTypeHandler<DateTime> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  
  
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i,
                                  DateTime parameter, JdbcType jdbcType)
    throws SQLException {

    int dtSecs = DateTimes.getSecsAsInt(parameter);
    logger.debug("Setting non-null DateTime parameter {} to {}.",
                 DateTimes.printExtended(parameter),
                 String.valueOf(dtSecs));
    ps.setInt(i, dtSecs);
  }
  
  @Override
  public DateTime getNullableResult(ResultSet rs, String columnName)
    throws SQLException {

    int dtSecs = rs.getInt(columnName);
    DateTime dt = null;
    
    if (rs.wasNull()) {
      logger.debug("ResultSet column {} returned {}, but the value was NULL.",
                   columnName,
                   String.valueOf(dtSecs));
    } else {
      dt = DateTimes.fromSecs(dtSecs);
      logger.debug("ResultSet column {} returned {}, returning {}.",
                   columnName,
                   String.valueOf(dtSecs),
                   DateTimes.printExtended(dt));
    }
    return dt;
  }


  @Override
  public DateTime getNullableResult(ResultSet rs, int columnIndex)
    throws SQLException {
    
    int dtSecs = rs.getInt(columnIndex);
    DateTime dt = null;
    
    if (rs.wasNull()) {
      logger.debug("ResultSet column {} returned {}, but the value was NULL.",
                   String.valueOf(columnIndex),
                   String.valueOf(dtSecs));
    } else {
      dt = DateTimes.fromSecs(dtSecs);
      logger.debug("ResultSet column {} returned {}, returning {}.",
                   String.valueOf(columnIndex),
                   String.valueOf(dtSecs),
                   DateTimes.printExtended(dt));
    }
    return dt;
  }

  @Override
  public DateTime getNullableResult(CallableStatement cs, int columnIndex)
    throws SQLException {
    
    int dtSecs = cs.getInt(columnIndex);
    DateTime dt = null;
    
    if (cs.wasNull()) {
      logger.debug("CallableStatement column {} returned {}, but the value was NULL.",
                   String.valueOf(columnIndex),
                   String.valueOf(dtSecs));
    } else {
      dt = DateTimes.fromSecs(dtSecs);
      logger.debug("CallableStatement column {} returned {}, returning {}.",
                   String.valueOf(columnIndex),
                   String.valueOf(dtSecs),
                   DateTimes.printExtended(dt));
    }
    return dt;
  }
}
