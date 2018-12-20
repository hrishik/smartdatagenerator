package org.hk.smartdata.plugin.random.impl;


import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;

public class RandomDataPluginManager extends SmartDataPluginManager
{
  public RandomDataPluginManager()
  {
    super();
  }
 
  
  @Override
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new RandomDataPluginElement(clazz, uniqueKey, parameters);
  }  
}
