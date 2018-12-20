package org.hk.smartdata.framework.load;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.ObjectFactory;
import org.hk.smartdata.framework.db.SmartGenDBManager;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.internal.model.jaxb.JAXBUtils;
import org.hk.smartdata.framework.internal.model.jaxb.generated.AttributeConfig;
import org.hk.smartdata.framework.internal.model.jaxb.generated.ColumnConfig;
import org.hk.smartdata.framework.internal.model.jaxb.generated.SchemaConfig;
import org.hk.smartdata.framework.internal.model.jaxb.generated.SmartdataMetadataType;
import org.hk.smartdata.framework.internal.model.jaxb.generated.TableConfig;
import org.hk.smartdata.framework.log.LogUtils;
import org.hk.smartdata.framework.model.SmartDataGenConstants;
import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;
import org.hk.smartdata.framework.utils.SmartDataGenUtils;


public class SmartDataLoadManager
{
  
  private final Logger logger = Logger.getLogger(SmartDataLoadManager.class);
  private SmartdataMetadataType config = null;
  private SmartGenDBManager dbM;
  private Map<String, Map<String, Map<String,SmartDataElement>>> elements;  
  
  public SmartDataLoadManager(String fileName)
    throws SmartGenException
  {
    super();
    final String METHOD_NAME = "SmartDataLoadManager";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, fileName);
    //initialize database
    dbM = new SmartGenDBManager();    
    InputStream inStr;
    //read the data connection and create a pool of connection
    try
    {
      inStr = new FileInputStream(fileName);
      config = JAXBUtils.unmarshal(inStr);
      //initialize DB for all schemas
      dbM.initializeDBs(config);        
    }
    catch (FileNotFoundException e)
    {
      throw new SmartGenException("Failed to initialize input stream on file :" + fileName,e);
    }    
    
    //now parse the configuration file for information
    ObjectFactory objFac = ObjectFactory.getInstance();    
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    elements = new HashMap<String, Map<String, Map<String,SmartDataElement>>>();
    
    for (SchemaConfig sConfig : config.getSchema())
    {
      Map<String,Map<String,SmartDataElement>> sElements = new HashMap<String,Map<String,SmartDataElement>>();
      for (TableConfig tConfig : sConfig.getTable())
      {
        Map<String, SmartDataElement> tElements = new HashMap<String,SmartDataElement>();
        
        for (ColumnConfig cConfig : tConfig.getColumns().getColumn())
        {
          SmartDataPluginManager mgr = objFac.getPluginManager(cConfig.getPluginName());   
          try 
          {
            Class clazz = loader.loadClass(cConfig.getDatabaseType());
            Map<String,String> param = new HashMap<String,String>();
            for (AttributeConfig aConfig : cConfig.getAttribute()) 
            {
              param.put(aConfig.getName(),aConfig.getValue());
            }
            //now read all parameters in map
            String uniqueId = SmartDataGenUtils.getUniqueId(tConfig.getName(), cConfig.getName());
            tElements.put(cConfig.getName(), mgr.createElement(clazz, uniqueId, param));
          }
          catch (ClassNotFoundException e)
          {
            logger.error("Unable to find class :" + cConfig.getDatabaseType(), e);
          }
        }
        sElements.put(tConfig.getName(), tElements);
      }      
      elements.put(sConfig.getName(), sElements);
    }
  }
  
  public void next()
  {
    //for each table, create an entry to be run as insert command
    for (String schemaName: elements.keySet()) 
    {
        
      Map<String,Map<String,SmartDataElement>> sElements = elements.get(schemaName);
      
      for (String tableName:  sElements.keySet()) 
      {
        String insertQuery = "insert into " + tableName + "(";
        String valuesStr = ") values (";
        Map<String, SmartDataElement> tableData = sElements.get(tableName);
        Map<String, String> currentVals = new HashMap<String,String>();
        
        List<String> orderedList = new ArrayList<String>();
        List<String> lastElements = new ArrayList<String>();
        
        for (String columnName: tableData.keySet())
        {
          if (tableData.get(columnName).getOrder() == 0)
            orderedList.add(columnName);
          else
            lastElements.add(columnName);
        }
        orderedList.addAll(lastElements);
        
        for (String columnName : orderedList)
        {
          SmartDataElement elem = tableData.get(columnName);        
          insertQuery += columnName + SmartDataGenConstants.COMMA;
          String nextValue = elem.getNextValue(currentVals);
          currentVals.put(columnName, nextValue);
          valuesStr += nextValue + SmartDataGenConstants.COMMA;        
        }
        String query = insertQuery.substring(0,insertQuery.length()-1) + valuesStr.substring(0,valuesStr.length()-1) + ")";
        try
        {
          getDBManager().runSQL(schemaName, query);
        }
        catch (SmartGenException e)
        {
          logger.error("Failed running for query :" + schemaName + ", on SQL " + query, e);  
        }
        System.out.println(query);
      }
    }
    //for that run each implementor passing the map values, and again set it back once you get a value
    //preserve the state of each table:column in the map
    //run the insert statement
  }

  public void reset()
  {
    //for each table, create an entry to be run as insert command
    for (String schemaName: elements.keySet()) 
    {        
      Map<String,Map<String,SmartDataElement>> sElements = elements.get(schemaName);      
      for (String tableName:  sElements.keySet()) 
      {
        Map<String, SmartDataElement> tableData = sElements.get(tableName);
        
        for (String columnName: tableData.keySet())
        {
          SmartDataElement elem = tableData.get(columnName);        
          elem.reset();
        }
      }
    }
  }

  public void close()
  {
    //close all db connection
  }
  
  private SmartGenDBManager getDBManager() 
  {
    return dbM;
  }
}
