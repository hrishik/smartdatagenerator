package org.hk.smartdata.plugin.script.impl;


import java.util.Map;
import java.util.StringTokenizer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.plugin.SmartDataElement;


public class ScriptDataPluginElement
  extends SmartDataElement
{
  private final static String EXPRESSION = "expression";
  private final static String STARTS_WITH = "$";
  private final static String ENDS_WITH = "#";
  
  private String expression = "";
  ScriptEngineManager mgr;
  ScriptEngine engine;
  private final Logger logger = Logger.getLogger(ScriptDataPluginElement.class);
  
  public ScriptDataPluginElement(Class class1, String string, Map<String, String> parameters)
  {
    super(class1, string, parameters);
    this.order = 1;
    this.expression = parameters.get(EXPRESSION);
    mgr = new ScriptEngineManager();
    engine = mgr.getEngineByName("JavaScript");
  }

  @Override
  protected String nextInt()
  {
    return ((int)getDoubleValue()) + "";                        
  }
  @Override
  protected String nextFloat()
  {
    return ((float)getDoubleValue()) + "";                                            
  }
  
  @Override
  protected String nextDouble()
  {
    return getDoubleValue() + "";
  }

  protected String nextString()
  {
    
    return getDoubleValue() + "";
  }
  
  private double getDoubleValue() 
  {
    String runTimeExpression = "";
    double returnVar = 0.0;
    StringTokenizer startToken = new StringTokenizer(expression,STARTS_WITH);
    while (startToken.hasMoreTokens())
    {
      String appendStr = startToken.nextToken();
      if (appendStr.contains(ENDS_WITH)) 
      {
        int indexVar = appendStr.indexOf(ENDS_WITH);
        String varName = appendStr.substring(0, indexVar);
        appendStr = this.currentVals.get(varName) + appendStr.substring(indexVar+1, appendStr.length());
      }
      runTimeExpression += appendStr;
    }
    try
    {
      returnVar = (Double) engine.eval(runTimeExpression);
    }
    catch (ScriptException e)
    {
      logger.error("Failed to evaluate the script " + runTimeExpression,e);
    }
    return returnVar;
  }
}
