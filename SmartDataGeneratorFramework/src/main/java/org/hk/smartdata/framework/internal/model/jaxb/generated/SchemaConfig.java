
package org.hk.smartdata.framework.internal.model.jaxb.generated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for schema-config complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="schema-config">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="table" type="{http://www.hk.com/xsds/smartdata}table-config" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jndi-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jdbc-driver" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jdbc-user" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jdbc-password" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jdbc-url" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="max-connection" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "schema-config", propOrder =
    { "table" })
public class SchemaConfig
{

  @XmlElement(required = true)
  protected List<TableConfig> table;
  @XmlAttribute
  protected String name;
  @XmlAttribute(name = "jndi-name")
  protected String jndiName;
  @XmlAttribute(name = "jdbc-driver")
  protected String jdbcDriver;
  @XmlAttribute(name = "jdbc-user")
  protected String jdbcUser;
  @XmlAttribute(name = "jdbc-password")
  protected String jdbcPassword;
  @XmlAttribute(name = "jdbc-url")
  protected String jdbcUrl;
  @XmlAttribute(name = "max-connection")
  protected String maxConnection;

  /**
   * Gets the value of the table property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the table property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getTable().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link TableConfig }
   *
   *
   */
  public List<TableConfig> getTable()
  {
    if (table == null)
    {
      table = new ArrayList<TableConfig>();
    }
    return this.table;
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
   * Gets the value of the jndiName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getJndiName()
  {
    return jndiName;
  }

  /**
   * Sets the value of the jndiName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setJndiName(String value)
  {
    this.jndiName = value;
  }

  /**
   * Gets the value of the jdbcDriver property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getJdbcDriver()
  {
    return jdbcDriver;
  }

  /**
   * Sets the value of the jdbcDriver property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setJdbcDriver(String value)
  {
    this.jdbcDriver = value;
  }

  /**
   * Gets the value of the jdbcUser property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getJdbcUser()
  {
    return jdbcUser;
  }

  /**
   * Sets the value of the jdbcUser property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setJdbcUser(String value)
  {
    this.jdbcUser = value;
  }

  /**
   * Gets the value of the jdbcPassword property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getJdbcPassword()
  {
    return jdbcPassword;
  }

  /**
   * Sets the value of the jdbcPassword property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setJdbcPassword(String value)
  {
    this.jdbcPassword = value;
  }

  /**
   * Gets the value of the jdbcUrl property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getJdbcUrl()
  {
    return jdbcUrl;
  }

  /**
   * Sets the value of the jdbcUrl property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setJdbcUrl(String value)
  {
    this.jdbcUrl = value;
  }

  /**
   * Gets the value of the maxConnection property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMaxConnection()
  {
    return maxConnection;
  }

  /**
   * Sets the value of the maxConnection property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMaxConnection(String value)
  {
    this.maxConnection = value;
  }

}
