package org.hk.smartdata.plugin.multichoice.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hk.smartdata.framework.model.SmartDataGenConstants;
import org.hk.smartdata.framework.plugin.SmartDataElement;

public class MultipleChoiceDataElement
  extends SmartDataElement
{
  private static final String VALUES = "comma-separated-values";
  private List<String> choiceValues = new ArrayList<String>();
  private Random r;

  public MultipleChoiceDataElement(Class clazz, String string, Map<String, String> map)
  {
    super(clazz, string, map);
    String values = map.get(VALUES);
    for (String value: values.split(SmartDataGenConstants.COMMA))
    {
      choiceValues.add(value);
    }
    r = new Random(choiceValues.size());
  }
  
  private String getValue()
  {
    return choiceValues.get(r.nextInt(choiceValues.size()));
  }
  
  protected String nextInt() 
  {
    return getValue();
  }
  
  protected String nextString() 
  {
    return getValue();
  }
  
  protected String nextDate() 
  {
    return getValue();
  }  
}
