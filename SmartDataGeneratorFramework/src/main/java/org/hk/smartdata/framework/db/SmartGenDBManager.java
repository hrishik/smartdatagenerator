package org.hk.smartdata.framework.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.internal.model.jaxb.generated.SchemaConfig;
import org.hk.smartdata.framework.internal.model.jaxb.generated.SmartdataMetadataType;
import org.hk.smartdata.framework.log.LogUtils;
import org.hk.smartdata.framework.utils.SmartDataGenUtils;


public class SmartGenDBManager
{
  private final Logger logger = Logger.getLogger(SmartGenDBManager.class);  
  private Map<String, Connection> connMap = new HashMap<String,Connection>();

  public SmartGenDBManager()
  {
    super();
  }


  public void initializeDBs(SmartdataMetadataType config)
    throws SmartGenException
  {
    final String METHOD_NAME = "getDBConnectionFromJNDI";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);
    
    if (config == null)
      throw new SmartGenException("Configuration is not yet initialized");
    
    for (SchemaConfig sc : config.getSchema()) 
    {
      if (sc.getJndiName()!= null && sc.getJndiName().trim().length() > 0) 
      {
        try
        {
          connMap.put(sc.getName(), SmartDataGenUtils.getDBConnectionFromJNDI(sc.getJndiName()));
        }
        catch (NamingException e)
        {
          throw new SmartGenException("Unable to find JNDI properties for " + sc.getJndiName(),e);  
        }
        catch (SQLException e)
        {
          throw new SmartGenException("Unable to initialize database for " + sc.getJndiName(),e);
        }      
      }
      else
      {
        try
        {
          connMap.put(sc.getName(), SmartDataGenUtils.getDBConnectionFromDB(sc.getJdbcUser(),sc.getJdbcPassword(),sc.getJdbcUrl(),sc.getJdbcDriver()));
        }
        catch (ClassNotFoundException e)
        {
          throw new SmartGenException("Unable to find Class " + sc.getJdbcDriver(),e);  
        }
        catch (SQLException e)
        {
          throw new SmartGenException("Unable to initialize database for " + sc.getJdbcUrl(),e);
        }      
      }
    }  
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
  }
  
  public boolean runSQL(String schema, String query)
    throws SmartGenException
  {
    final String METHOD_NAME = "runSQL";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, new Object[] {schema, query});
    Connection conn = getConnection(schema);
    boolean returnVal = false;
    try {
        Statement stmt = conn.createStatement();
        returnVal = stmt.execute(query);
        stmt.close();
    
    } catch (SQLException e) {
        throw new SmartGenException("Failed to execute Query" + query, e);
    } finally {
        LogUtils.exiting(logger, this.getClass(), METHOD_NAME, returnVal);
    }
    return returnVal;
  }  
  
  private Connection getConnection(String name) 
  {
    return connMap.get(name);
  }
 


}
