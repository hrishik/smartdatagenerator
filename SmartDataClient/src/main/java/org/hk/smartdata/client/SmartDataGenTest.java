package org.hk.smartdata.client;

import org.hk.smartdata.framework.ObjectFactory;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.load.SmartDataLoadManager;


public class SmartDataGenTest
{
  public SmartDataGenTest()
  {
    super();
  }

  public void insertData()
  {

  }

  public static void main(String[] args)
    throws SmartGenException
  {
    ObjectFactory objFact = ObjectFactory.getInstance();
    SmartDataLoadManager manager =
      objFact.getDataLoadManager("xml\\smartdata-metadata-config.xml");
    
    for (int i=0;i<10;i++)
      manager.next();
    manager.close();
  }
}
