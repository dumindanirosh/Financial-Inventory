/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "invoice_sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceSales.findAll", query = "SELECT i FROM InvoiceSales i"),
    @NamedQuery(name = "InvoiceSales.findBySalesid", query = "SELECT i FROM InvoiceSales i WHERE i.salesid = :salesid"),
    @NamedQuery(name = "InvoiceSales.findByInvoiceId", query = "SELECT i FROM InvoiceSales i WHERE i.invoiceId = :invoiceId")})
public class InvoiceSales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "salesid")
    private Integer salesid;
    @Basic(optional = false)
    @Column(name = "invoiceId")
    private String invoiceId;
    @JoinColumn(name = "salesrefid", referencedColumnName = "salesrefid")
    @ManyToOne(optional = false)
    private Salesref salesrefid;

    public InvoiceSales() {
    }

    public InvoiceSales(Integer salesid) {
        this.salesid = salesid;
    }

    public InvoiceSales(Integer salesid, String invoiceId) {
        this.salesid = salesid;
        this.invoiceId = invoiceId;
    }

    public Integer getSalesid() {
        return salesid;
    }

    public void setSalesid(Integer salesid) {
        this.salesid = salesid;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Salesref getSalesrefid() {
        return salesrefid;
    }

    public void setSalesrefid(Salesref salesrefid) {
        this.salesrefid = salesrefid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salesid != null ? salesid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceSales)) {
            return false;
        }
        InvoiceSales other = (InvoiceSales) object;
        if ((this.salesid == null && other.salesid != null) || (this.salesid != null && !this.salesid.equals(other.salesid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.InvoiceSales[ salesid=" + salesid + " ]";
    }
    
}
