/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "invoice_to")
@NamedQueries({@NamedQuery(name = "InvoiceTo.findAll", query = "SELECT i FROM InvoiceTo i"), @NamedQuery(name = "InvoiceTo.findByInvoicetoid", query = "SELECT i FROM InvoiceTo i WHERE i.invoicetoid = :invoicetoid"), @NamedQuery(name = "InvoiceTo.findByInvoiceto", query = "SELECT i FROM InvoiceTo i WHERE i.invoiceto = :invoiceto")})
public class InvoiceTo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoicetoid")
    private Integer invoicetoid;
    @Basic(optional = false)
    @Column(name = "invoiceto")
    private String invoiceto;

    public InvoiceTo() {
    }

    public InvoiceTo(Integer invoicetoid) {
        this.invoicetoid = invoicetoid;
    }

    public InvoiceTo(Integer invoicetoid, String invoiceto) {
        this.invoicetoid = invoicetoid;
        this.invoiceto = invoiceto;
    }

    public Integer getInvoicetoid() {
        return invoicetoid;
    }

    public void setInvoicetoid(Integer invoicetoid) {
        this.invoicetoid = invoicetoid;
    }

    public String getInvoiceto() {
        return invoiceto;
    }

    public void setInvoiceto(String invoiceto) {
        this.invoiceto = invoiceto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoicetoid != null ? invoicetoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceTo)) {
            return false;
        }
        InvoiceTo other = (InvoiceTo) object;
        if ((this.invoicetoid == null && other.invoicetoid != null) || (this.invoicetoid != null && !this.invoicetoid.equals(other.invoicetoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.InvoiceTo[invoicetoid=" + invoicetoid + "]";
    }

}
