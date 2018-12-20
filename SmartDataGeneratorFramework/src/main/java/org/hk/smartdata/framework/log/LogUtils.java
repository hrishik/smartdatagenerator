package org.hk.smartdata.framework.log;

import org.apache.log4j.Logger;

/**
 * This is a logger class used across all RCF Framework as well as implementation.
 *
 * @author Hrishikesh Karambelkar
 *
 * This class is based on Apache Logger class, and it adds more methods to ease
 * life of developer as well as to save up time during debugging issues.
 */
public class LogUtils
{
 
 private static final transient Logger logger =
  Logger.getLogger(LogUtils.class);

 /**
  * Log entering in method with results, it will work only in case of debugging
  * level is enabled
  * @param className Class
  * @param method Name of Method
  * @param result Results of method
  */

 public static void entering(Logger logger, Class className, String method,
                             Object parameters)
 {
  if (logger.isDebugEnabled())
  {
   logger.debug(className.getName() + " : " + method + " : Entry");
  }
 }

 /**
  * Log existing from method with results, it will work only in case of debugging
  * level is enabled
  * @param className Class
  * @param method Name of Method
  * @param result Results of method
  */
 public static void exiting(Logger logger, Class className, String method,
                            Object result)
 {
  if (logger.isDebugEnabled())
  {
   logger.debug(className.getName() + " : " + method + " : Exiting");
  }
 }

}
