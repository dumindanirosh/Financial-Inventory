/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "salesref")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salesref.findAll", query = "SELECT s FROM Salesref s"),
    @NamedQuery(name = "Salesref.findBySalesrefid", query = "SELECT s FROM Salesref s WHERE s.salesrefid = :salesrefid"),
    @NamedQuery(name = "Salesref.findByRefstatus", query = "SELECT s FROM Salesref s WHERE s.refstatus = :refstatus")})
public class Salesref implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "salesrefid")
    private String salesrefid;
    @Basic(optional = false)
    @Column(name = "refstatus")
    private boolean refstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesrefid")
    private List<InvoiceSales> invoiceSalesList;

    public Salesref() {
    }

    public Salesref(String salesrefid) {
        this.salesrefid = salesrefid;
    }

    public Salesref(String salesrefid, boolean refstatus) {
        this.salesrefid = salesrefid;
        this.refstatus = refstatus;
    }

    public String getSalesrefid() {
        return salesrefid;
    }

    public void setSalesrefid(String salesrefid) {
        this.salesrefid = salesrefid;
    }

    public boolean getRefstatus() {
        return refstatus;
    }

    public void setRefstatus(boolean refstatus) {
        this.refstatus = refstatus;
    }

    @XmlTransient
    public List<InvoiceSales> getInvoiceSalesList() {
        return invoiceSalesList;
    }

    public void setInvoiceSalesList(List<InvoiceSales> invoiceSalesList) {
        this.invoiceSalesList = invoiceSalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salesrefid != null ? salesrefid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salesref)) {
            return false;
        }
        Salesref other = (Salesref) object;
        if ((this.salesrefid == null && other.salesrefid != null) || (this.salesrefid != null && !this.salesrefid.equals(other.salesrefid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.Salesref[ salesrefid=" + salesrefid + " ]";
    }
    
}
