package org.hk.smartdata.plugin.foreign.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.log.LogUtils;
import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.utils.SmartDataGenUtils;


public class ForeignDataPluginElement
  extends SmartDataElement
{
    private static final String JNDI_NAME = "jndi-name";
    private static final String JDBC_DRIVER = "jdbc-driver";
    private static final String JDBC_USER = "jdbc-user";
    private static final String JDBC_PASS = "jdbc-password";
    private static final String JDBC_URL = "jdbc-url";
    private static final String JDBC_SQL = "sql";
    private static final String SQL_RET_VARNAME = "sql-return-var";
    
    private static final int FETCH_SIZE = 30;
  
    private static final String REF_TABLE = "refTableName";
    private static final String REF_COLUMN = "refColumnName";
    private static final String REF_SCHEMA = "refSchemaName";
  
    private final Logger logger = Logger.getLogger(ForeignDataPluginElement.class);  
  
    
    private String jndiName,jdbcDriver,jdbcUser,jdbcPass,jdbcURL,sql,refTableName,refColumnName,refSchemaName;
    private Connection conn;
    private ResultSet rs = null;
    private String returnVar = "name";
    private Statement stmt = null;
  
  public ForeignDataPluginElement(Class clazz, String string, Map<String, String> map)
  {
    super(clazz, string, map);
    final String METHOD_NAME = "ForeignDataPluginElement";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);
    
    jndiName = map.get(JNDI_NAME);
    jdbcDriver = map.get(JDBC_DRIVER);
    jdbcUser = map.get(JDBC_USER);
    jdbcPass = map.get(JDBC_PASS);
    jdbcURL = map.get(JDBC_URL);
    sql = map.get(JDBC_SQL);
    String temp = map.get(SQL_RET_VARNAME);
    if (temp != null && temp.trim().length() > 0)
      returnVar = map.get(SQL_RET_VARNAME);
    
    refTableName = map.get(REF_TABLE);
    refColumnName = map.get(REF_COLUMN);
    refSchemaName = map.get(REF_SCHEMA);
    
    if (jndiName != null && jndiName.trim().length() > 0) 
    {
      try
      {
        conn = SmartDataGenUtils.getDBConnectionFromJNDI(jndiName);
      }
      catch (NamingException e)
      {
        logger.error("JNDI Name " + jndiName + " not found in the context", e);
      }
      catch (SQLException e)
      {
        logger.error("Exception while initializing database " + jndiName, e);
      }
    }
    else if (jdbcDriver != null && jdbcDriver.trim().length() > 0)
    {
      try
      {
        conn = SmartDataGenUtils.getDBConnectionFromDB(jdbcUser,jdbcPass,jdbcURL,jdbcDriver);
      }
      catch (ClassNotFoundException e)
      {
        logger.error("Unable to load class  " + jdbcDriver + ".", e);
      }
      catch (SQLException e)
      {
        logger.error("Exception while initializing database " + jndiName, e);
      }
    }
    //else is the case of local
  }

  private String getNextStrValue()
    throws SmartGenException
  {
    boolean reConnect = false;
    String nextVal = "";
        
    try
    {
      if (stmt == null)
        reConnect = true;
      if (rs == null)
        reConnect = true;
      if (rs != null && !rs.next())
        reConnect = true;
      if (reConnect)
      {
        try
        {
          if (stmt!= null)
            stmt.close();
          
          stmt = conn.createStatement();
          stmt.setFetchSize(FETCH_SIZE);
          rs = stmt.executeQuery(sql);
          rs.next();
        }
        catch (SQLException e)
        {
          throw new SmartGenException("Failed to run a sql " + sql, e);
        }
      }
      
      nextVal = rs.getString(returnVar);
    }
    catch (SQLException e)
    {
      throw new SmartGenException("Failed to fetch value " + sql, e);
    }
    return nextVal;
  }
  
  @Override
  protected String nextString()
  {
    String nextVal = "";
    try
    {
      nextVal = getNextStrValue();
    }
    catch (SmartGenException e)
    {
      logger.error("Failed to generate value, returning null",e);
    }
    return nextVal;
  }
  
  @Override
  protected String nextDate()
  {
    String nextVal = "";
    try
    {
      nextVal = getNextStrValue();
    }
    catch (SmartGenException e)
    {
      logger.error("Failed to generate value, returning null",e);
    }
    return nextVal;
  }
  
  @Override
  protected String nextInt()
  {
    Integer nextVal = null;
    try
    {
      String newNum = getNextStrValue();
      /*if (newNum == null || newNum.trim().length()<1)
        newNum = "0";*/
      nextVal = new Integer(newNum);
    }
    catch (SmartGenException e)
    {
      logger.error("Failed to generate value, returning null",e);
    }
    return nextVal.toString();
  }
  
  @Override
  protected String nextDouble()
  {
    Double nextVal = null;
    try
    {
      nextVal = new Double(getNextStrValue());
    }
    catch (SmartGenException e)
    {
      logger.error("Failed to generate value, returning null",e);
    }
    return nextVal.toString();
  }  
}
