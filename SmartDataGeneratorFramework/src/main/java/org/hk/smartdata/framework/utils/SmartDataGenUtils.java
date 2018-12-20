package org.hk.smartdata.framework.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.db.SmartGenDBManager;
import org.hk.smartdata.framework.log.LogUtils;
import org.hk.smartdata.framework.model.SmartDataGenConstants;


public class SmartDataGenUtils
{
  private static final Logger logger = Logger.getLogger(SmartGenDBManager.class);  
  
  public static String getUniqueId(String tableName, String columnName)
  {
    return tableName + SmartDataGenConstants.UID_SEPARATOR + columnName;
  }

  public static String getTableName(String uniqueId)
  {
    return uniqueId.substring(0, uniqueId.indexOf(SmartDataGenConstants.UID_SEPARATOR));
  }

  public static String getColumnName(String uniqueId)
  {
    return uniqueId.substring(uniqueId.indexOf(SmartDataGenConstants.UID_SEPARATOR) + 1, uniqueId.length());
  }
  
  public static Connection getDBConnectionFromJNDI(String jndiSrcName)
    throws NamingException, SQLException
  {
    final String METHOD_NAME = "getDBConnectionFromJNDI";
    LogUtils.entering(logger, SmartDataGenUtils.class, METHOD_NAME, jndiSrcName);
    Connection conn = null;
    try
    {
      logger.info("Creating RCF Database connection...for:" + jndiSrcName);
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(jndiSrcName);
      conn = ds.getConnection();
      logger.info("Connection created...");
    }
    finally
    {
      LogUtils.exiting(logger, SmartDataGenUtils.class, METHOD_NAME, null);
    }
    return conn;
  }

  public static Connection getDBConnectionFromDB(String jdbcUser, String jdbcPass, String jdbcURL, String jdbcDriver)
    throws SQLException, ClassNotFoundException
  {
    final String METHOD_NAME = "getDBConnectionFromDB";
    LogUtils.entering(logger, SmartDataGenUtils.class, METHOD_NAME, new Object[]
        { jdbcUser, jdbcPass, jdbcURL, jdbcDriver });
    Connection conn = null;
    try
    {
      logger.debug("Loading class:" + jdbcDriver);
      Class.forName(jdbcDriver);
      conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);

    }
    finally
    {
      LogUtils.exiting(logger, SmartDataGenUtils.class, METHOD_NAME, null);
    }
    return conn;
  }

}
