package org.hk.smartdata.plugin.fixed.impl;


import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;


public class FixedDataPluginManager
  extends SmartDataPluginManager
{
  public FixedDataPluginManager()
  {
    super();
  }
  
  @Override
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new FixedDataPluginElement(clazz, uniqueKey, parameters);
  }  
}
