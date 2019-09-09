/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "invoice_items")
@NamedQueries({@NamedQuery(name = "InvoiceItems.findAll", query = "SELECT i FROM InvoiceItems i"), @NamedQuery(name = "InvoiceItems.findByInvoiceId", query = "SELECT i FROM InvoiceItems i WHERE i.invoiceItemsPK.invoiceId = :invoiceId"), @NamedQuery(name = "InvoiceItems.findByItemCode", query = "SELECT i FROM InvoiceItems i WHERE i.invoiceItemsPK.itemCode = :itemCode"), @NamedQuery(name = "InvoiceItems.findByQuantity", query = "SELECT i FROM InvoiceItems i WHERE i.quantity = :quantity"), @NamedQuery(name = "InvoiceItems.findBySalesPrice", query = "SELECT i FROM InvoiceItems i WHERE i.salesPrice = :salesPrice"), @NamedQuery(name = "InvoiceItems.findByRealPrice", query = "SELECT i FROM InvoiceItems i WHERE i.realPrice = :realPrice"), @NamedQuery(name = "InvoiceItems.findByItemName", query = "SELECT i FROM InvoiceItems i WHERE i.itemName = :itemName"), @NamedQuery(name = "InvoiceItems.findBySalesExpression", query = "SELECT i FROM InvoiceItems i WHERE i.salesExpression = :salesExpression"), @NamedQuery(name = "InvoiceItems.findByEnteredDate", query = "SELECT i FROM InvoiceItems i WHERE i.enteredDate = :enteredDate"), @NamedQuery(name = "InvoiceItems.findByItemdiscount", query = "SELECT i FROM InvoiceItems i WHERE i.itemdiscount = :itemdiscount")})
public class InvoiceItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvoiceItemsPK invoiceItemsPK;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "salesPrice")
    private double salesPrice;
    @Basic(optional = false)
    @Column(name = "realPrice")
    private double realPrice;
    @Basic(optional = false)
    @Column(name = "itemName")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "salesExpression")
    private String salesExpression;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    private String enteredDate;
    @Column(name = "itemdiscount")
    private Double itemdiscount;
    @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerInvoice customerInvoice;
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Items items;

    public InvoiceItems() {
    }

    public InvoiceItems(InvoiceItemsPK invoiceItemsPK) {
        this.invoiceItemsPK = invoiceItemsPK;
    }

    public InvoiceItems(InvoiceItemsPK invoiceItemsPK, int quantity, double salesPrice, double realPrice, String itemName, String salesExpression, String enteredDate) {
        this.invoiceItemsPK = invoiceItemsPK;
        this.quantity = quantity;
        this.salesPrice = salesPrice;
        this.realPrice = realPrice;
        this.itemName = itemName;
        this.salesExpression = salesExpression;
        this.enteredDate = enteredDate;
    }

    public InvoiceItems(int invoiceId, int itemCode) {
        this.invoiceItemsPK = new InvoiceItemsPK(invoiceId, itemCode);
    }

    public InvoiceItemsPK getInvoiceItemsPK() {
        return invoiceItemsPK;
    }

    public void setInvoiceItemsPK(InvoiceItemsPK invoiceItemsPK) {
        this.invoiceItemsPK = invoiceItemsPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSalesExpression() {
        return salesExpression;
    }

    public void setSalesExpression(String salesExpression) {
        this.salesExpression = salesExpression;
    }

    public String getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(String enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Double getItemdiscount() {
        return itemdiscount;
    }

    public void setItemdiscount(Double itemdiscount) {
        this.itemdiscount = itemdiscount;
    }

    public CustomerInvoice getCustomerInvoice() {
        return customerInvoice;
    }

    public void setCustomerInvoice(CustomerInvoice customerInvoice) {
        this.customerInvoice = customerInvoice;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceItemsPK != null ? invoiceItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceItems)) {
            return false;
        }
        InvoiceItems other = (InvoiceItems) object;
        if ((this.invoiceItemsPK == null && other.invoiceItemsPK != null) || (this.invoiceItemsPK != null && !this.invoiceItemsPK.equals(other.invoiceItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.InvoiceItems[invoiceItemsPK=" + invoiceItemsPK + "]";
    }

}
