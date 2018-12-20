package org.hk.smartdata.framework.exception;

/**
 * This is the main Exception class for all RCF related Exceptions.
 * 
 * @author Hrishikesh Karambelkar
 * 
 * 
 */
public class SmartGenException extends Exception
{    

    public SmartGenException()
  {
    super();
  }
  
  public SmartGenException(String message)
  {
    super(message);
  }
  public SmartGenException(Exception e)
  {
    super(e);
  }
  public SmartGenException(String message, Exception e)
  {
    super(message,e);
  }
}