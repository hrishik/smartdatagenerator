package org.hk.smartdata.framework.plugin;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.ObjectFactory;
import org.hk.smartdata.framework.model.SmartDataGenConstants;


public abstract class SmartDataElement
{
  private final String uniqueId;
  private final Class clazz;
  private Map<String, String> parameters = new HashMap<String, String>();
  private String currentValue;  
  private final Logger logger = Logger.getLogger(ObjectFactory.class);
  protected Map<String,String> currentVals = null;
  protected int order = 0;
  
  protected SmartDataElement(Class clazz, String uniqueId, Map<String, String> parameters) 
  {
    this.clazz = clazz;
    this.uniqueId = uniqueId;
    this.parameters = parameters;
  }
 
  protected String nextDate() 
  {
    return null;
  }
  
  protected String nextString() 
  {
    return null;
  }
  
  protected String nextInt() 
  {
    return null;
  }
  
  protected String nextFloat() 
  {
    return null;
  }
  
  protected String nextDouble() 
  {
    return null;
  }
    
  protected String nextDefault() 
  {
    return null;
  }
  
  public String getCurrentValue()
  {    
    return currentValue;
  }
  
  protected int getIntValueFromStr(String value) 
  {
    int strLength = 0;
    if (value != null && value.trim().length() > 0) 
    {
      try 
      {
        strLength = new Integer(value).intValue();
        
      }
      catch (Exception ex) 
      {
        logger.info("Failed to find integer value for " + value, ex);
      }      
    }
    return strLength;
  }
  
  protected double getDoubleValueFromStr(String value) 
  {
    double strLength = 0;
    if (value != null && value.trim().length() > 0) 
    {
      try 
      {
        strLength = new Double(value).doubleValue();
        
      }
      catch (Exception ex) 
      {
        logger.info("Failed to find integer value for " + value, ex);
      }      
    }
    return strLength;
  }
  
  protected boolean isInteger(Class clazz) 
  {
    return (clazz == Integer.class);
  }
  protected boolean isString(Class clazz) 
  {
    return (clazz == String.class);
  }
  protected boolean isDate(Class clazz) 
  {
    return (clazz == Date.class);
  }
  protected boolean isFloat(Class clazz) 
  {
    return (clazz == Float.class);
  }
  
  protected boolean isDouble(Class clazz) 
  {
    return (clazz == Double.class);
  }
  
  public String getNextValue(Map<String,String> currentVals) 
  {
    String nextValue = null;
    this.currentVals = currentVals;
    
    if (isDate(clazz))
      nextValue = SmartDataGenConstants.QUOTE +  nextDate() + SmartDataGenConstants.QUOTE;
    else if(isString(clazz))
      nextValue = SmartDataGenConstants.QUOTE +  nextString() + SmartDataGenConstants.QUOTE;
    else if(isFloat(clazz))
      nextValue = nextFloat();
    else if(isDouble(clazz)) 
    {
      Double val = new Double(nextDouble());
      nextValue = val.toString();
    }
    else if(isInteger(clazz))
      nextValue = nextInt();
    else 
      nextValue = nextDefault();    
    currentValue = nextValue;
    return nextValue;
  }
  
  public int getOrder() 
  {
    return order;
  }
    
  public String getUniqueId() 
  {
    return this.uniqueId;
  }
  
  public void reset() 
  {
    return;
  }
}
