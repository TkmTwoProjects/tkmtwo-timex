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

import com.tkmtwo.timex.WallClock;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class WallClockIntSecsTypeHandler
  extends BaseTypeHandler<WallClock> {
  
  protected final Logger logger = LoggerFactory.getLogger(getClass()); 
  
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i,
                                  WallClock parameter, JdbcType jdbcType)
    throws SQLException {
    
    int wcSecs = parameter.getSeconds();
    logger.debug("Setting non-null WallClock parameter {} to {}.",
                 parameter.printExtended(),
                 String.valueOf(wcSecs));
    ps.setInt(i, wcSecs);

  }
  
  @Override
  public WallClock getNullableResult(ResultSet rs, String columnName)
    throws SQLException {
    
    int wcSecs = rs.getInt(columnName);
    WallClock wc = null;
    
    if (rs.wasNull()) {
      logger.debug("ResultSet column {} returned {}, but the value was NULL.",
                   columnName,
                   String.valueOf(wcSecs));
    } else {
      wc = WallClock.valueOf(wcSecs);
      logger.debug("ResultSet column {} returned {}, returning {}.",
                   columnName,
                   String.valueOf(wcSecs),
                   wc.printExtended());
    }
    
    return wc;
  }


  @Override
  public WallClock getNullableResult(ResultSet rs, int columnIndex)
    throws SQLException {

    int wcSecs = rs.getInt(columnIndex);
    WallClock wc = null;
    
    if (rs.wasNull()) {
      logger.debug("ResultSet column {} returned {}, but the value was NULL.",
                   String.valueOf(columnIndex),
                   String.valueOf(wcSecs));
    } else {
      wc = WallClock.valueOf(wcSecs);
      logger.debug("ResultSet column {} returned {}, returning {}.",
                   String.valueOf(columnIndex),
                   String.valueOf(wcSecs),
                   wc.printExtended());
    }
    
    return wc;
  }

  @Override
  public WallClock getNullableResult(CallableStatement cs, int columnIndex)
    throws SQLException {

    int wcSecs = cs.getInt(columnIndex);
    WallClock wc = null;
    
    if (cs.wasNull()) {
      logger.debug("CallableStatement column {} returned {}, but the value was NULL.",
                   String.valueOf(columnIndex),
                   String.valueOf(wcSecs));
    } else {
      wc = WallClock.valueOf(wcSecs);
      logger.debug("CallableStatement column {} returned {}, returning {}.",
                   String.valueOf(columnIndex),
                   String.valueOf(wcSecs),
                   wc.printExtended());
    }
    
    return wc;
  }
  
}
