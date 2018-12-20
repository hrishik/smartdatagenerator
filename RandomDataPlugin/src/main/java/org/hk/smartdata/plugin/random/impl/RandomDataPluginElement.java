package org.hk.smartdata.plugin.random.impl;


import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.plugin.SmartDataElement;


public class RandomDataPluginElement
  extends SmartDataElement
{  
  private static final String STR_BEFORE = "str-before";
  private static final String STR_AFTER = "str-after";
  private static final String DATE_FORMAT = "date-format";
  
  private static final String FROM = "from";
  private static final String TO = "to";
  
  private static final String LENGTH = "length";
  
  private Date fromDate;
  private Date toDate;
  private long diff = 0;
  private String format;
  
  private int strLength = 10;
  private int from =0;
  private int to = 100000;
  private String strBefore = "";
  private String strAfter = "";
  
  private final Logger logger = Logger.getLogger(RandomDataPluginElement.class);
  
  Random r = new Random();
  
  public RandomDataPluginElement(Class clazz, String uniqueId, Map<String, String> map)
  {
    super(clazz, uniqueId, map);    
    
    strLength = getIntValueFromStr(map.get(LENGTH));
    if (isInteger(clazz))
    {
      from = getIntValueFromStr(map.get(FROM));
      to = getIntValueFromStr(map.get(TO));
    }
    else if(isDate(clazz)) 
    {
      format = map.get(DATE_FORMAT);
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      try
      {
        fromDate = (Date) formatter.parseObject(map.get(FROM));
        toDate = (Date) formatter.parseObject(map.get(TO));            
        diff = toDate.getTime() - fromDate.getTime() +1;
      }
      catch (ParseException e)
      {
        logger.error("Failed to parse the formatted date", e); 
      }     
    }
    else if (isString(clazz)) 
    {
      strBefore = map.get(STR_BEFORE);
      strAfter = map.get(STR_AFTER);
    }
  }
  
  protected String nextDate() 
  {    
    Timestamp rand = new Timestamp(fromDate.getTime() + (long)(Math.random()*diff));
    
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    return formatter.format(rand.getTime());
  }
  
  protected String nextString() 
  {
    StringBuilder sb = new StringBuilder();    
    String subset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i= 0;i<strLength;i++) 
    {
      int index = r.nextInt(subset.length());
      char c = subset.charAt(index);
      sb.append(c);
    }    
    String outStr = sb.toString();
    if (strBefore != null && strBefore.trim().length() > 0)
      outStr = strBefore + outStr;
    if (strAfter != null && strAfter.trim().length() > 0)
      outStr = outStr + strAfter; 
    return outStr;
  }
  
  protected String nextInt() 
  {
    return (new Integer(r.nextInt(to-from) + from)).toString();
  }
}
