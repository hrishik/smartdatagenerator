package org.hk.smartdata.plugin.script.impl;

import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;

public class ScriptDataPluginManager
extends SmartDataPluginManager
{
  public ScriptDataPluginManager()
  {
    super();
  }
  
  @Override
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new ScriptDataPluginElement(clazz, uniqueKey, parameters);
  }  
}
