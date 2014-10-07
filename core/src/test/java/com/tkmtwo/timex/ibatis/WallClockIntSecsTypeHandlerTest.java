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

import com.tkmtwo.timex.WallClock;

public class WallClockIntSecsTypeHandlerTest
  extends BaseTypeHandlerTest {
  
  private static final TypeHandler<WallClock> TYPE_HANDLER = new WallClockIntSecsTypeHandler();
  private WallClock wallClock;
  private int wallClockSecs;

  @Before
  public void setUp() {
    wallClock = WallClock.valueOf(73);
    wallClockSecs = wallClock.getSeconds();
  }
  
  @Test
  public void shouldSetParameter()
    throws Exception {
    
    TYPE_HANDLER.setParameter(ps, 1, wallClock, JdbcType.INTEGER);
    verify(ps).setInt(1, wallClockSecs);
  }

  @Test
  public void shouldGetResultFromResultSet()
    throws Exception {

    when(rs.getInt("column")).thenReturn(wallClockSecs);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(wallClock, TYPE_HANDLER.getResult(rs, "column"));    

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
    when(cs.getInt(1)).thenReturn(wallClockSecs);
    when(cs.wasNull()).thenReturn(false);
    assertEquals(wallClock, TYPE_HANDLER.getResult(cs, 1));
  }

  @Test
  public void shouldGetNullResultFromCallableStatement() throws Exception {
    when(cs.getInt(1)).thenReturn(0);
    when(cs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(cs, 1));
  }



}
