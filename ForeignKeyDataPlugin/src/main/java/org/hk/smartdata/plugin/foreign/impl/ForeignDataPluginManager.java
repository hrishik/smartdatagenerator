package org.hk.smartdata.plugin.foreign.impl;


import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;

public class ForeignDataPluginManager
  extends SmartDataPluginManager
{
  public ForeignDataPluginManager()
  {
    super();
  }
  
  @Override
  public  SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new ForeignDataPluginElement(clazz, uniqueKey, parameters);
  }  
}
