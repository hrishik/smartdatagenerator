package org.hk.smartdata.plugin.pattern.impl;

import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;

public class PatternDataPluginManager
  extends SmartDataPluginManager
{
  public PatternDataPluginManager()
  {
    super();
  }
  
  
  @Override
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new PatternDataPluginElement(clazz, uniqueKey, parameters);
  }
}
