package org.hk.smartdata.plugin.sequence.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.plugin.SmartDataElement;


public class SequenceDataPluginElement
  extends SmartDataElement
{
  
  private static final String FROM = "from";
  private static final String TO = "to";
  private static final String DATE_FORMAT = "date-format";
  private static final String INCREMENT_BY = "increment-by";
  private static final String REPEAT_BY = "repeat-by";
  private boolean firstTime = true;
  
  private int from = 0;
  private int to = 100000;
  private int currentInt = 0;
  private int incrementBy = 1;
  private int repeatBy = 0;
  private int currentRepeatCount = 0;
  
  private Date currentDate;  
  private Date fromDate;
  private Date toDate;
  private String format;
  
  private final Logger logger = Logger.getLogger(SequenceDataPluginElement.class);

  public SequenceDataPluginElement(Class clazz, String string, Map<String, String> map)
  {
    super(clazz, string, map);
    
    if (isInteger(clazz))
    {
      from = getIntValueFromStr(map.get(FROM));
      to = getIntValueFromStr(map.get(TO));
      
      String incrBy = map.get(INCREMENT_BY);
      if (incrBy != null && incrBy.trim().length() > 0)
        incrementBy = new Integer(incrBy);
      String rptBy = map.get(REPEAT_BY);
      if (rptBy != null && rptBy.trim().length() > 0)
        repeatBy = new Integer(rptBy);
      
      currentInt = from-1;
    }
    else if(isDate(clazz)) 
    {
      format = map.get(DATE_FORMAT);
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      try
      {
        fromDate = (Date) formatter.parseObject(map.get(FROM));
        toDate = (Date) formatter.parseObject(map.get(TO));            
        currentDate = fromDate;
      }
      catch (ParseException e)
      {
        logger.error("Failed to parse the formatted date", e); 
      }     
    }
  }
  
  protected String nextInt() 
  { 
    int nextInt = 0;
    
    if (firstTime) 
    {
      nextInt = from;          
      firstTime = false;      
    }           
    else 
    {
      if (currentRepeatCount < repeatBy) 
        nextInt = currentInt;
      else 
      {
        nextInt = currentInt + incrementBy;        
        if (nextInt > to) 
          nextInt = from;
        
        currentRepeatCount = 0;
      }            
    }        
    currentInt = nextInt;        
    currentRepeatCount++;
    return new Integer(nextInt).toString();
  }
  
  protected String nextDate() 
  {    
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    Calendar c = Calendar.getInstance();
    c.setTime(currentDate);
    c.add(Calendar.DAY_OF_YEAR,1);
    
    currentDate = c.getTime();    
    if(currentDate.compareTo(toDate) >= 0) 
    {
      currentDate = fromDate;
    }    
    return formatter.format(currentDate);
  }
}
