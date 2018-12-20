package org.hk.smartdata.framework.model.cache.impl;


import java.util.Set;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.ElementAttributes;
import org.apache.log4j.Logger;
import org.hk.smartdata.framework.cache.ICacheManager;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.log.LogUtils;
import org.hk.smartdata.framework.model.SmartDataGenConstants;


/**
 * This class provides JCS implementation of {@link ICacheManager}
 *
 * @author Hrishikesh Karambelkar
 *
 * This class provides implementation for JCS for generic cache interface of
 * Model
 */
public class JCSCacheManager
  implements ICacheManager
{

  private final transient Logger logger =
    Logger.getLogger(JCSCacheManager.class);
  private JCS cache;
  private final static String DEFAULT_GROUP = "group";
  private final static String DEFAULT_REGION = "default";

  /**
   * Constructor sets the path for cache.ccf
   */
  public JCSCacheManager()
    throws SmartGenException
  {
    final String METHOD_NAME = "JCSCacheManager()";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);

    JCS.setConfigFilename(SmartDataGenConstants.CACHE_CONFIG_FILE);
    try
    {
      cache = JCS.getInstance(DEFAULT_REGION);
    }
    catch (CacheException ex)
    {
      throw new SmartGenException("Unable to initialize JCS cache ", ex);
    }
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
  }

  /**
   * This method gets all keys in the default group of a region.
   *
   * @param region
   */
  @Override
  public Set<String> getAllKeys(String type)
    throws SmartGenException
  {
    final String METHOD_NAME = "getAllKeys";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, type);

    Set<String> keySet = null;
    try
    {
      keySet = JCS.getInstance(DEFAULT_REGION).getGroupKeys(DEFAULT_GROUP);
    }
    catch (CacheException ex)
    {
      throw new SmartGenException("Unable to get all keys in group ", ex);
    }
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, keySet);
    return keySet;
  }

  /**
   * This method inserts objects in JCS cache
   *
   * @throws ServiceException
   *
   */
  @Override
  public void put(String type, String key, Object obj)
    throws SmartGenException
  {
    final String METHOD_NAME = "put";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, new Object[] {type,key,obj});

    if (type == null || type.trim().length() < 1)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: put: Exception: region == null || region.trim().length() < 1");
      }
      throw new SmartGenException("Region cannot be empty");
    }
    if (key == null || key.trim().length() < 1)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: put: Exception: key == null || key.trim().length() < 1");
      }
      throw new SmartGenException("Key cannot be empty");
    }
    if (obj == null)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: put: Exception: obj == null");
      }
      throw new SmartGenException("Key Value for " + key +
                                  " cannot be empty");
    }
    try
    {
      cache.putInGroup(getCompositeKey(type, key), DEFAULT_GROUP, obj);
    }
    catch (CacheException ex)
    {
      throw new SmartGenException("Unable to put object in cache:Region not found.",
                                  ex);
    }
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
  }

  /**
   * This method inserts objects in JCS cache with expiry
   *
   * @throws ServiceException
   *
   */
  @Override
  public void put(String type, String key, Object obj,
                  double maxLifeHours)
    throws SmartGenException
  {
    final String METHOD_NAME = "put";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);

    if (type == null || type.trim().length() < 1)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: put: Exception: type == null " +
                     "|| type.trim().length() < 1");
      }
      throw new SmartGenException("Region cannot be empty");
    }
    if (key == null || key.trim().length() < 1)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: put: Exception: key == null " +
                     "|| key.trim().length() < 1");
      }
      throw new SmartGenException("Key cannot be empty");
    }
    if (obj == null)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: put: Exception: obj == null");
      }
      throw new SmartGenException("Key Value for " + key +
                                  " cannot be empty");
    }

    try
    {
      JCS regionCache = cache;

      ElementAttributes attributes = new ElementAttributes();
      long maxLifeSeconds = (long) (maxLifeHours * 3600D);
      attributes.setMaxLifeSeconds(maxLifeSeconds);
      attributes.setIsEternal(false);
      regionCache.putInGroup(getCompositeKey(type, key), DEFAULT_GROUP, obj, attributes);
    }
    catch (CacheException ex)
    {
      throw new SmartGenException("Unable to put object in cache:Region not found.",
                                  ex);
    }
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
  }


  /**
   * This method gets objects from JCS cache.
   *
   * @param region
   * @param key
   * @throws CacheException
   *
   */
  @Override
  public Object get(String type, String key)
    throws SmartGenException
  {
    final String METHOD_NAME = "get";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);

    if (type == null || type.trim().length() < 1)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: get: Exception: type == null || " +
         "type.trim().length() < 1");
      }
      throw new SmartGenException("Type cannot be empty");
    }
    if (key == null || key.trim().length() < 1)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("JCSCacheManager: get: Excpetion: key == null || key.trim().length() < 1");
      }
      throw new SmartGenException("Key cannot be empty");
    }
    Object object = null;
    try
    {
      JCS regionCache = JCS.getInstance(DEFAULT_REGION);
      object = regionCache.getFromGroup(getCompositeKey(type, key), DEFAULT_GROUP);
    }
    catch (CacheException ex)
    {
      throw new SmartGenException("Unable to get object from cache with type " +
                                  type + " and key " + key, ex);
    }
    if (logger.isDebugEnabled())
    {
      logger.debug("JCSCacheManager: get: Exit");
    }
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, object);
    return object;
  }

  /**
   * This method creates new region in JCS cache.
   *
   * @param region
   * @throws SmartGenException
   *
   */
  @Override
  public void createRegion(String region)
    throws SmartGenException
  {
    final String METHOD_NAME = "createRegion";
    LogUtils.entering(logger, this.getClass(), METHOD_NAME, null);

    if (region == null || region.trim().length() < 1)
      throw new SmartGenException("Region name is null, it cannot be NULL");
    //if its a default region, it is already created
    if (DEFAULT_REGION.equalsIgnoreCase(region))
    {
      logger.info("Region is already created");
      return;
    }

    try
    {
      JCS.defineRegion(region);
      JCS regionCache =
        JCS.getInstance(region, cache.getCacheAttributes());
      regionCache.setDefaultElementAttributes(cache.getElementAttributes());
    }
    catch (CacheException ex)
    {
      throw new SmartGenException("Unable to create new region in cache",
                                  ex);
    }
    if (logger.isDebugEnabled())
    {
      logger.debug("JCSCacheManager: createRegion: Exit: New region created in JCS");
    }
    LogUtils.exiting(logger, this.getClass(), METHOD_NAME, null);
  }
  
  private String getCompositeKey(String region, String key) 
  {
   return region + "-" + key;
  }
}
