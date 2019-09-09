/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "customer_invoice")
@NamedQueries({@NamedQuery(name = "CustomerInvoice.findAll", query = "SELECT c FROM CustomerInvoice c"), @NamedQuery(name = "CustomerInvoice.findByInvoiceId", query = "SELECT c FROM CustomerInvoice c WHERE c.invoiceId = :invoiceId"), @NamedQuery(name = "CustomerInvoice.findByTotalAmount", query = "SELECT c FROM CustomerInvoice c WHERE c.totalAmount = :totalAmount"), @NamedQuery(name = "CustomerInvoice.findByDiscountAmount", query = "SELECT c FROM CustomerInvoice c WHERE c.discountAmount = :discountAmount"), @NamedQuery(name = "CustomerInvoice.findByDiscountPercent", query = "SELECT c FROM CustomerInvoice c WHERE c.discountPercent = :discountPercent"), @NamedQuery(name = "CustomerInvoice.findByInvoicedDate", query = "SELECT c FROM CustomerInvoice c WHERE c.invoicedDate = :invoicedDate"), @NamedQuery(name = "CustomerInvoice.findByEnteredBy", query = "SELECT c FROM CustomerInvoice c WHERE c.enteredBy = :enteredBy"), @NamedQuery(name = "CustomerInvoice.findByEnteredDate", query = "SELECT c FROM CustomerInvoice c WHERE c.enteredDate = :enteredDate")})
public class CustomerInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "invoiceId")
    private Integer invoiceId;
    @Basic(optional = false)
    @Column(name = "totalAmount")
    private double totalAmount;
    @Basic(optional = false)
    @Column(name = "discountAmount")
    private double discountAmount;
    @Basic(optional = false)
    @Column(name = "discountPercent")
    private double discountPercent;
    @Basic(optional = false)
    @Column(name = "invoicedDate")
    private String invoicedDate;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerInvoice")
    private List<InvoiceItems> invoiceItemsList;

    public CustomerInvoice() {
    }

    public CustomerInvoice(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public CustomerInvoice(Integer invoiceId, double totalAmount, double discountAmount, double discountPercent, String invoicedDate, String enteredBy, Date enteredDate) {
        this.invoiceId = invoiceId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.invoicedDate = invoicedDate;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getInvoicedDate() {
        return invoicedDate;
    }

    public void setInvoicedDate(String invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public List<InvoiceItems> getInvoiceItemsList() {
        return invoiceItemsList;
    }

    public void setInvoiceItemsList(List<InvoiceItems> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceId != null ? invoiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerInvoice)) {
            return false;
        }
        CustomerInvoice other = (CustomerInvoice) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.CustomerInvoice[invoiceId=" + invoiceId + "]";
    }

}
