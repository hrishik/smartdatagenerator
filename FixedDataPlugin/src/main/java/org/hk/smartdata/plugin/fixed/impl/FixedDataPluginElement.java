package org.hk.smartdata.plugin.fixed.impl;


import java.util.Map;

import org.hk.smartdata.framework.plugin.SmartDataElement;

public class FixedDataPluginElement<T>
  extends SmartDataElement
{
  private final static String FIXED_VALUE = "fixed-value";
  
  private String value = "";
  public FixedDataPluginElement(Class clazz, String uniqueId, Map<String,String> parameters)
  {
    super(clazz, uniqueId, parameters);
    this.value = parameters.get(FIXED_VALUE);
  }
  
  @Override
  protected String nextString() 
  {
    return this.value;
  }
  
  @Override
  protected String nextDate() 
  {
    return this.value;
  }
  
  @Override
  protected String nextInt() 
  {
    return this.value;
  }
  
  @Override
  protected String nextFloat() 
  {
    return this.value;
  }  
}
