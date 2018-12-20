package org.hk.smartdata.framework.internal.model.jaxb;


import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.hk.smartdata.framework.exception.SmartGenException;
import org.hk.smartdata.framework.internal.model.jaxb.generated.SmartdataMetadataType;
import org.hk.smartdata.framework.internal.model.jaxb.plugin.generated.SmartdataPluginConfigType;
import org.hk.smartdata.framework.log.LogUtils;


/**
 * This class deals with marshalling and unmarshalling of JAXB objects.
 *
 * @author Hrishikesh Karambelkar
 *
 * This class is used for generic marshalling and unmarshalling of JAXB objects
 * @param <T> Returns the required object
 */
public class JAXBUtils<T>
{
  private final static Logger logger =
    Logger.getLogger(JAXBUtils.class);

  /**
   * This API takes a className and input stream and creates an JAXB based object
   * for a given stream.
   *
   * @param className Name of Class (JaxB class)
   * @param is - FIle stream
   * @return Returns given jaxb object, returns null in case if it cannot
   * create it
   * @throws SmartGenException Throws exception if it fails
   */
  public static SmartdataMetadataType unmarshal(InputStream is)
    throws SmartGenException
  {
    final String METHOD_NAME = "unmarshal";
    LogUtils.entering(logger, JAXBUtils.class, METHOD_NAME, new Object[]
        {  is });
    SmartdataMetadataType configRoot = null;
    try
    {
      JAXBContext jaxbContext =
        JAXBContext.newInstance(SmartdataMetadataType.class.getPackage().getName(),SmartdataMetadataType.class.getClassLoader());
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      JAXBElement configRootElements =
        (JAXBElement) unmarshaller.unmarshal(is);
      configRoot = (SmartdataMetadataType) configRootElements.getValue();
      LogUtils.exiting(logger, JAXBUtils.class, METHOD_NAME, configRoot);
    }
    catch (JAXBException e)
    {
      throw new SmartGenException("Failed to load jaxb Object", e);
    }
    finally
    {
      LogUtils.exiting(logger, JAXBUtils.class, METHOD_NAME, configRoot);
    }
    return configRoot;
  }

  /**
   * This API takes a className and input stream and creates an JAXB based object
   * for a given stream.
   *
   * @param className Name of Class (JaxB class)
   * @param is - FIle stream
   * @return Returns given jaxb object, returns null in case if it cannot
   * create it
   * @throws SmartGenException Throws exception if it fails
   */
  public static SmartdataPluginConfigType unmarshalDataPlugin(InputStream is)
    throws SmartGenException
  {
    final String METHOD_NAME = "unmarshal";
    LogUtils.entering(logger, JAXBUtils.class, METHOD_NAME, new Object[]
        {  is });
    SmartdataPluginConfigType configRoot = null;
    try
    {
      JAXBContext jaxbContext =
        JAXBContext.newInstance(SmartdataPluginConfigType.class.getPackage().getName(),SmartdataPluginConfigType.class.getClassLoader());
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      JAXBElement configRootElements =
        (JAXBElement) unmarshaller.unmarshal(is);
      configRoot = (SmartdataPluginConfigType) configRootElements.getValue();
      LogUtils.exiting(logger, JAXBUtils.class, METHOD_NAME, configRoot);
    }
    catch (JAXBException e)
    {
      throw new SmartGenException("Failed to load jaxb Object", e);
    }
    finally
    {
      LogUtils.exiting(logger, JAXBUtils.class, METHOD_NAME, configRoot);
    }
    return configRoot;
  }
  
  /**
   * This API does marshalling of JAXB object and provides output stream in return.
   *
   * @param jaxbObj JAXB Object to be marshalled, it has to be {@link RCFConfigRoot}
   * @return Returns outputstream of given jaxbObject
   * @throws SmartGenException Throws exception in case of failure
   */
  public static OutputStream marshal(SmartdataMetadataType jaxbObj)
    throws SmartGenException
  {
    OutputStream str = null;
    //TODO:Shweta's code to come here
    final String METHOD_NAME = "marshal";
    LogUtils.entering(logger, JAXBUtils.class, METHOD_NAME, jaxbObj);

    org.hk.smartdata.framework.internal.model.jaxb.generated.ObjectFactory obj1 =
      new org.hk.smartdata.framework.internal.model.jaxb.generated.ObjectFactory();

    JAXBElement<SmartdataMetadataType> configRoot = obj1.createSmartdataMetadata(jaxbObj);
    JAXBContext jaxbContext;
    try
    {
      jaxbContext = JAXBContext.newInstance(SmartdataMetadataType.class.getCanonicalName(), SmartdataMetadataType.class.getClassLoader());
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.marshal(configRoot, str);
    }
    catch (JAXBException e)
    {
      throw new SmartGenException("Failed to marshal jaxb Object:" + jaxbObj,
                             e);
    }
    finally
    {
      LogUtils.exiting(logger, JAXBUtils.class, METHOD_NAME, str);
    }
    return str;
  }

}
