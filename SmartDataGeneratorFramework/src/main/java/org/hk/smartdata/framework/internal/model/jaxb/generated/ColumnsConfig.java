
package org.hk.smartdata.framework.internal.model.jaxb.generated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for columns-config complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="columns-config">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="column" type="{http://www.hk.com/xsds/smartdata}column-config" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "columns-config", propOrder =
    { "column" })
public class ColumnsConfig
{

  @XmlElement(required = true)
  protected List<ColumnConfig> column;

  /**
   * Gets the value of the column property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the column property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getColumn().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link ColumnConfig }
   *
   *
   */
  public List<ColumnConfig> getColumn()
  {
    if (column == null)
    {
      column = new ArrayList<ColumnConfig>();
    }
    return this.column;
  }

}
