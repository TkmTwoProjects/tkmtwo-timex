package com.tkmtwo.timex.ibatis;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import org.joda.time.Period;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.timex.Periods;

public class PeriodIsoTypeHandlerTest
  extends BaseTypeHandlerTest {
  
  private static final TypeHandler<Period> TYPE_HANDLER = new PeriodIsoTypeHandler();
  private Period period;
  private String periodString;

  @Before
  public void setUp() {
    Period sanityPeriod = Periods.parse("P30D");
    period = new Period(0, 0, 0, 30,
                        0, 0, 0, 0);
    assertEquals(sanityPeriod, period);
    periodString = Periods.print(period);
  }
  
  @Test
  public void shouldSetParameter()
    throws Exception {
    
    TYPE_HANDLER.setParameter(ps, 1, period, JdbcType.VARCHAR);
    verify(ps).setString(1, periodString);
  }

  @Test
  public void shouldGetResultFromResultSet()
    throws Exception {

    when(rs.getString("column")).thenReturn(periodString);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(period, TYPE_HANDLER.getResult(rs, "column"));    

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
    when(cs.getString(1)).thenReturn(periodString);
    when(cs.wasNull()).thenReturn(false);
    assertEquals(period, TYPE_HANDLER.getResult(cs, 1));
  }

  @Test
  public void shouldGetNullResultFromCallableStatement() throws Exception {
    when(cs.getString(1)).thenReturn((String) null);
    when(cs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(cs, 1));
  }



}
