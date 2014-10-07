package com.tkmtwo.timex.ibatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTypeHandlerTest {

  @Mock
  protected ResultSet rs;
  @Mock
  protected PreparedStatement ps;
  @Mock
  protected CallableStatement cs;
  @Mock
  protected ResultSetMetaData rsmd;

  public abstract void shouldSetParameter() throws Exception;

  public abstract void shouldGetResultFromResultSet() throws Exception;

  public abstract void shouldGetResultFromCallableStatement() throws Exception;

}
