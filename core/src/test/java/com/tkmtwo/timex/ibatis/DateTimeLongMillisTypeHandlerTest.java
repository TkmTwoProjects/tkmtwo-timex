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

public class DateTimeLongMillisTypeHandlerTest
  extends BaseTypeHandlerTest {
  
  private static final TypeHandler<DateTime> TYPE_HANDLER = new DateTimeLongMillisTypeHandler();
  private DateTime dateTime;
  private long dateTimeMillis;

  @Before
  public void setUp() {
    dateTime = new DateTime();
    dateTimeMillis = dateTime.getMillis();
  }
  
  @Test
  public void shouldSetParameter()
    throws Exception {
    
    TYPE_HANDLER.setParameter(ps, 1, dateTime, JdbcType.INTEGER);
    verify(ps).setLong(1, dateTimeMillis);
  }

  @Test
  public void shouldGetResultFromResultSet()
    throws Exception {

    when(rs.getLong("column")).thenReturn(dateTimeMillis);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(dateTime, TYPE_HANDLER.getResult(rs, "column"));    

  }

  @Test
  public void shouldGetNullResultFromResultSet()
    throws Exception {

    when(rs.getLong("column")).thenReturn(0L);
    when(rs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(rs, "column"));
  }


  @Test
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getLong(1)).thenReturn(dateTimeMillis);
    when(cs.wasNull()).thenReturn(false);
    assertEquals(dateTime, TYPE_HANDLER.getResult(cs, 1));
  }

  @Test
  public void shouldGetNullResultFromCallableStatement() throws Exception {
    when(cs.getLong(1)).thenReturn(0L);
    when(cs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(cs, 1));
  }



}
