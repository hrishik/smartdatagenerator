package org.hk.smartdata.framework.plugin;


import java.util.Map;


public abstract class SmartDataPluginManager
{  

  public SmartDataPluginManager()
  {
    super();
  }
  
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return null;
  }

  public static boolean isSupported(Class clazz) 
  {
    return true;
  }
  
}
