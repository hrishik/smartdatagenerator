package org.hk.smartdata.plugin.multichoice.impl;


import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;

public class MultipleChoiceDataPluginManager
  extends SmartDataPluginManager
{
  public MultipleChoiceDataPluginManager()
  {
    super();
  }
  
  @Override
  public SmartDataElement createElement(Class clazz, String uniqueKey, Map<String, String> parameters) 
  {
    return new MultipleChoiceDataElement(clazz, uniqueKey, parameters);
  }  

}
