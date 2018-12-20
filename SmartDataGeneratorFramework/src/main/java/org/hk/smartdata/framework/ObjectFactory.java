package org.hk.smartdata.framework;


import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.cache.ICacheManager;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.internal.model.jaxb.JAXBUtils;
import org.hk.smartdata.framework.internal.model.jaxb.plugin.generated.SmartdataPluginConfigType;
import org.hk.smartdata.framework.load.SmartDataLoadManager;
import org.hk.smartdata.framework.log.LogUtils;
import org.hk.smartdata.framework.model.SmartDataGenConstants;
import org.hk.smartdata.framework.model.cache.impl.JCSCacheManager;
import org.hk.smartdata.framework.plugin.SmartDataPluginManager;


/**
 * This is the object factory for all RCF Managers.
 *
 * @author Hrishikesh Karambelkar
 *
 *  This object factory is responsible for returning various instances of
 *  Managers used in RCF. As RCF can be used with multiple implementations,
 *  respective implementations will be loaded dynamically by this object factory
 *  at the project startup, then based on the demands from calling APIs, it would
 *  return the appropriate implementation.
 *
 *  Following example demonstrates how one can call objectFactory
 *  <code>
 *  ObjectFactory objFac = ObjectFactory.getInstnace();
 *  objFact.getXXX...
 *  ...
 *  </code>
 *
 */
public class ObjectFactory
{

  //******************************************************* private Vars
  private static ObjectFactory objFac = new ObjectFactory();
  private final Logger logger = Logger.getLogger(ObjectFactory.class);

  private static ICacheManager cacheMgr;
  private static Map<String, SmartDataPluginManager> pluginMap; 
  
  private static Map<String,SmartDataLoadManager> loadMan;
    

  //******************************************************* private Methods

  private ObjectFactory()
  {
    final String METHOD_NAME = "ObjectFactory";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);
    pluginMap = new HashMap<String, SmartDataPluginManager>();
    loadMan = new HashMap<String, SmartDataLoadManager>();
    //cache loading
    loadCache();
    //first try loading all the plugs
    loadPlugins();
    //now load configuration with all managers    
  }

  private void loadCache() 
  {
    final String METHOD_NAME = "loadCache";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);
    
    try
    {
      cacheMgr = new JCSCacheManager();
      cacheMgr.createRegion(SmartDataGenConstants.CACHE_REGION);
    }
    catch (SmartGenException e)
    {
      logger.error("Failed to initialize Object Factory ", e);
      throw new ExceptionInInitializerError("Failed to initialize Object Factory" + e.getMessage());
    }
    finally
    {
      LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
    }
  }

  private void loadPlugins() 
  {
    final String METHOD_NAME = "loadPlugins";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);
    
    try
    {
      //ClassLoader loader = Thread.currentThread().getContextClassLoader();
      ClassLoader loader = this.getClass().getClassLoader();
      if (loader == null)
        loader = ClassLoader.getSystemClassLoader();

      Enumeration<URL> resURLs = loader.getResources(SmartDataGenConstants.DATA_PLUGIN_CONFIG);

      JAXBUtils<SmartdataPluginConfigType> util = new JAXBUtils<SmartdataPluginConfigType>();
      while (resURLs.hasMoreElements())
      {
        URL resURL = resURLs.nextElement();
        try
        {
          InputStream is = resURL.openStream();
          SmartdataPluginConfigType root = util.unmarshalDataPlugin(is);
          String clazzName = root.getPluginClass();
          try
          {
            Class clazz = loader.loadClass(clazzName);
            SmartDataPluginManager inst = (SmartDataPluginManager) clazz.newInstance();
            pluginMap.put(root.getName(), inst);
          }
          catch (InstantiationException e)
          {
            logger.error("Unable to instantiate class :" + clazzName, e);
          }
          catch (IllegalAccessException e)
          {
            logger.error("Unable to access class :" + clazzName, e);
          }
          catch (ClassNotFoundException e)
          {
            logger.error("Unable to find class :" + clazzName, e);
          }
        }
        catch (IOException e)
        {
          logger.error("Failed to initialize plugin for  " + resURL.getPath() +
                       ", moving forward for loading other files", e);

        }
        catch (SmartGenException e)
        {
          logger.error("Failed to initialize plugin for  " + resURL.getPath() +
                       ", moving forward for loading other files", e);
        }
      }

    }
    catch (IOException e)
    {
      logger.error("Failed to initialize load of data plugins completely, quitting ", e);
      throw new ExceptionInInitializerError("Failed to initialize Object Factory(Data Plugin)" + e.getMessage());
    }
    finally
    {
      LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
    }
  }
  
    //******************************************************* Public Methods
  public static ObjectFactory getInstance()
  {
    return objFac;
  }

  public SmartDataLoadManager getDataLoadManager(String fileName)
    throws SmartGenException
  {
    SmartDataLoadManager lMan = loadMan.get(fileName);
    if (lMan == null) 
    {
      lMan = new SmartDataLoadManager(fileName);
      loadMan.put(fileName, lMan);
    }
    return lMan;
  }
  
  public SmartDataPluginManager getPluginManager(String type) 
  {
    return pluginMap.get(type);
  }
  

  /**
   * Returns JCS Cache Manager
   * @return
   */
  public ICacheManager getCacheManager()
  {
    return cacheMgr;
  }
}
