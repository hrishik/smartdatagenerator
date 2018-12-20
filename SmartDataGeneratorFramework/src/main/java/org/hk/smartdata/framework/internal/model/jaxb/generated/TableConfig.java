
package org.hk.smartdata.framework.internal.model.jaxb.generated;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for table-config complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="table-config">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="columns" type="{http://www.hk.com/xsds/smartdata}columns-config"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "table-config", propOrder =
    { "columns" })
public class TableConfig
{

  @XmlElement(required = true)
  protected ColumnsConfig columns;
  @XmlAttribute
  protected String name;
  @XmlAttribute
  protected BigInteger count;

  /**
   * Gets the value of the columns property.
   *
   * @return
   *     possible object is
   *     {@link ColumnsConfig }
   *
   */
  public ColumnsConfig getColumns()
  {
    return columns;
  }

  /**
   * Sets the value of the columns property.
   *
   * @param value
   *     allowed object is
   *     {@link ColumnsConfig }
   *
   */
  public void setColumns(ColumnsConfig value)
  {
    this.columns = value;
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
   * Gets the value of the count property.
   *
   * @return
   *     possible object is
   *     {@link BigInteger }
   *
   */
  public BigInteger getCount()
  {
    return count;
  }

  /**
   * Sets the value of the count property.
   *
   * @param value
   *     allowed object is
   *     {@link BigInteger }
   *
   */
  public void setCount(BigInteger value)
  {
    this.count = value;
  }

}
