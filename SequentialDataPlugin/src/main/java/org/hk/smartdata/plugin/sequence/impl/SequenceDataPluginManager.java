package org.hk.smartdata.plugin.sequence.impl;


import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;

public class SequenceDataPluginManager extends SmartDataPluginManager
{
  public SequenceDataPluginManager()
  {
    super();
  }
  
  @Override
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new SequenceDataPluginElement(clazz, uniqueKey, parameters);
  }
}
