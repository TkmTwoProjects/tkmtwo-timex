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

import com.tkmtwo.timex.Periods;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class PeriodIsoTypeHandler
  extends BaseTypeHandler<Period> {
  
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i,
                                  Period parameter, JdbcType jdbcType)
    throws SQLException {

    String periodString = Periods.print(parameter);
    logger.debug("Setting non-null Period parameter {} to {}.",
                 Periods.print(parameter),
                 periodString);
    ps.setString(i, periodString);
  }
  
  @Override
  public Period getNullableResult(ResultSet rs, String columnName)
    throws SQLException {
    
    String periodString = rs.getString(columnName);
    Period period = null;
    
    if (rs.wasNull()) {
      logger.debug("ResultSet column {} returned {}, but the value was NULL.",
                   columnName,
                   periodString);
    } else {
      period = Periods.parse(periodString);
      logger.debug("ResultSet column {} returned {}, returning {}.",
                   columnName,
                   periodString,
                   Periods.print(period));
    }
    
    return period;
  }


  @Override
  public Period getNullableResult(ResultSet rs, int columnIndex)
    throws SQLException {

    String periodString = rs.getString(columnIndex);
    Period period = null;
    
    if (rs.wasNull()) {
      logger.debug("ResultSet column {} returned {}, but the value was NULL.",
                   String.valueOf(columnIndex),
                   periodString);
    } else {
      period = Periods.parse(periodString);
      logger.debug("ResultSet column {} returned {}, returning {}.",
                   String.valueOf(columnIndex),
                   periodString,
                   Periods.print(period));
    }
    
    return period;
  }

  @Override
  public Period getNullableResult(CallableStatement cs, int columnIndex)
    throws SQLException {

    String periodString = cs.getString(columnIndex);
    Period period = null;
    
    if (cs.wasNull()) {
      logger.debug("CallableStatement column {} returned {}, but the value was NULL.",
                   String.valueOf(columnIndex),
                   periodString);
    } else {
      period = Periods.parse(periodString);
      logger.debug("CallableStatement column {} returned {}, returning {}.",
                   String.valueOf(columnIndex),
                   periodString,
                   Periods.print(period));
    }
    
    return period;
  }
  
}
