package com.tkmtwo.timex.ibatis;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import org.joda.time.DateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.timex.DateTimes;

public class DateTimeIntSecsTypeHandlerTest
  extends BaseTypeHandlerTest {
  
  private static final TypeHandler<DateTime> TYPE_HANDLER = new DateTimeIntSecsTypeHandler();
  private DateTime dateTime;
  private int dateTimeSecs;

  @Before
  public void setUp() {
    dateTime = DateTimes.noMillis();
    dateTimeSecs = DateTimes.getSecsAsInt(dateTime);
  }
  
  @Test
  public void shouldSetParameter()
    throws Exception {
    
    TYPE_HANDLER.setParameter(ps, 1, dateTime, JdbcType.INTEGER);
    verify(ps).setInt(1, dateTimeSecs);
  }

  @Test
  public void shouldGetResultFromResultSet()
    throws Exception {

    when(rs.getInt("column")).thenReturn(dateTimeSecs);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(dateTime, TYPE_HANDLER.getResult(rs, "column"));    

  }

  @Test
  public void shouldGetNullResultFromResultSet()
    throws Exception {

    when(rs.getInt("column")).thenReturn(0);
    when(rs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(rs, "column"));
  }


  @Test
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getInt(1)).thenReturn(dateTimeSecs);
    when(cs.wasNull()).thenReturn(false);
    assertEquals(dateTime, TYPE_HANDLER.getResult(cs, 1));
  }

  @Test
  public void shouldGetNullResultFromCallableStatement() throws Exception {
    when(cs.getInt(1)).thenReturn(0);
    when(cs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(cs, 1));
  }



}
