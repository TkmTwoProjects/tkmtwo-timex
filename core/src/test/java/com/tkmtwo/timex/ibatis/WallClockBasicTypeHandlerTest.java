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

public class WallClockBasicTypeHandlerTest
  extends BaseTypeHandlerTest {
  
  private static final TypeHandler<WallClock> TYPE_HANDLER = new WallClockBasicTypeHandler();

  private WallClock wallClock;
  private String wallClockBasic;

  @Before
  public void setUp() {
    wallClock = WallClock.valueOf(73);
    wallClockBasic = "000113";
  }
  
  @Test
  public void shouldSetParameter()
    throws Exception {
    
    TYPE_HANDLER.setParameter(ps, 1, wallClock, JdbcType.VARCHAR);
    verify(ps).setString(1, wallClockBasic);
  }

  @Test
  public void shouldGetResultFromResultSet()
    throws Exception {

    when(rs.getString("column")).thenReturn(wallClockBasic);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(wallClock, TYPE_HANDLER.getResult(rs, "column"));    

  }

  @Test
  public void shouldGetNullResultFromResultSet()
    throws Exception {

    when(rs.getString("column")).thenReturn((String) null);
    when(rs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(rs, "column"));
  }


  @Test
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getString(1)).thenReturn(wallClockBasic);
    when(cs.wasNull()).thenReturn(false);
    assertEquals(wallClock, TYPE_HANDLER.getResult(cs, 1));
  }

  @Test
  public void shouldGetNullResultFromCallableStatement() throws Exception {
    when(cs.getString(1)).thenReturn((String) null);
    when(cs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(cs, 1));
  }



}
