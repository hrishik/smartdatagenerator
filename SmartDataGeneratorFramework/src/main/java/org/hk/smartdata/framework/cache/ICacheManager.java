package org.hk.smartdata.framework.cache;


import java.util.Set;

import org.hk.smartdata.framework.exception.SmartGenException;

/**
 * This interface is used for caching java objects.
 *
 * @author Hrishikesh Karambelkar
 *
 * This interface provides various APIs to store and access cached objects.
 *
 */
public interface ICacheManager
{  
  Set<String> getAllKeys(String region) throws SmartGenException;

  void put(String region, String key, Object obj) throws SmartGenException;

  void put(String region, String key, Object obj, double maxLifeHours)
                  throws SmartGenException;

  Object get(String region, String key) throws SmartGenException;

  void createRegion(final String region) throws SmartGenException;
}
