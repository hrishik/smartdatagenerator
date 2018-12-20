
package org.hk.smartdata.framework.internal.model.jaxb.plugin.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.hk.smartdata.framework.internal.model.jaxb.plugin.generated package. 
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
public class ObjectFactory {

    private final static QName _SmartdataPluginConfig_QNAME = new QName("http://www.hk.com/xsds/smartdata", "smartdata-plugin-config");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.hk.smartdata.framework.internal.model.jaxb.plugin.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SmartdataPluginConfigType }
     * 
     */
    public SmartdataPluginConfigType createSmartdataPluginConfigType() {
        return new SmartdataPluginConfigType();
    }

    /**
     * Create an instance of {@link AttributeType }
     * 
     */
    public AttributeType createAttributeType() {
        return new AttributeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmartdataPluginConfigType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hk.com/xsds/smartdata", name = "smartdata-plugin-config")
    public JAXBElement<SmartdataPluginConfigType> createSmartdataPluginConfig(SmartdataPluginConfigType value) {
        return new JAXBElement<SmartdataPluginConfigType>(_SmartdataPluginConfig_QNAME, SmartdataPluginConfigType.class, null, value);
    }

}
