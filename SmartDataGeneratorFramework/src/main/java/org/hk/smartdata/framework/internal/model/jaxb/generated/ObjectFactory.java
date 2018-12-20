
package org.hk.smartdata.framework.internal.model.jaxb.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.hk.smartdata.framework.internal.model.jaxb.generated package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory
{

  private final static QName _SmartdataMetadata_QNAME =
    new QName("http://www.hk.com/xsds/smartdata", "smartdata-metadata");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.hk.smartdata.framework.internal.model.jaxb.generated
   *
   */
  public ObjectFactory()
  {
  }

  /**
   * Create an instance of {@link SmartdataMetadataType }
   *
   */
  public SmartdataMetadataType createSmartdataMetadataType()
  {
    return new SmartdataMetadataType();
  }

  /**
   * Create an instance of {@link ColumnConfig }
   *
   */
  public ColumnConfig createColumnConfig()
  {
    return new ColumnConfig();
  }

  /**
   * Create an instance of {@link ColumnsConfig }
   *
   */
  public ColumnsConfig createColumnsConfig()
  {
    return new ColumnsConfig();
  }

  /**
   * Create an instance of {@link TableConfig }
   *
   */
  public TableConfig createTableConfig()
  {
    return new TableConfig();
  }

  /**
   * Create an instance of {@link SchemaConfig }
   *
   */
  public SchemaConfig createSchemaConfig()
  {
    return new SchemaConfig();
  }

  /**
   * Create an instance of {@link AttributeConfig }
   *
   */
  public AttributeConfig createAttributeConfig()
  {
    return new AttributeConfig();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link SmartdataMetadataType }{@code >}}
   *
   */
  @XmlElementDecl(namespace = "http://www.hk.com/xsds/smartdata", name = "smartdata-metadata")
  public JAXBElement<SmartdataMetadataType> createSmartdataMetadata(SmartdataMetadataType value)
  {
    return new JAXBElement<SmartdataMetadataType>(_SmartdataMetadata_QNAME, SmartdataMetadataType.class, null, value);
  }

}
