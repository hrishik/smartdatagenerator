
package org.hk.smartdata.framework.internal.model.jaxb.generated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for column-config complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="column-config">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attribute" type="{http://www.hk.com/xsds/smartdata}attribute-config" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="database-type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="plugin-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "column-config", propOrder =
    { "attribute" })
public class ColumnConfig
{

  @XmlElement(required = true)
  protected List<AttributeConfig> attribute;
  @XmlAttribute
  protected String name;
  @XmlAttribute(name = "database-type")
  protected String databaseType;
  @XmlAttribute(name = "plugin-name")
  protected String pluginName;

  /**
   * Gets the value of the attribute property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the attribute property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getAttribute().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link AttributeConfig }
   *
   *
   */
  public List<AttributeConfig> getAttribute()
  {
    if (attribute == null)
    {
      attribute = new ArrayList<AttributeConfig>();
    }
    return this.attribute;
  }

  /**
   * Gets the value of the name property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setName(String value)
  {
    this.name = value;
  }

  /**
   * Gets the value of the databaseType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getDatabaseType()
  {
    return databaseType;
  }

  /**
   * Sets the value of the databaseType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDatabaseType(String value)
  {
    this.databaseType = value;
  }

  /**
   * Gets the value of the pluginName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPluginName()
  {
    return pluginName;
  }

  /**
   * Sets the value of the pluginName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPluginName(String value)
  {
    this.pluginName = value;
  }

}
