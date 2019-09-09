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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "items")
@NamedQueries({@NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"), @NamedQuery(name = "Items.findByItemCode", query = "SELECT i FROM Items i WHERE i.itemCode = :itemCode"), @NamedQuery(name = "Items.findByItemName", query = "SELECT i FROM Items i WHERE i.itemName = :itemName"), @NamedQuery(name = "Items.findBySalesPrice", query = "SELECT i FROM Items i WHERE i.salesPrice = :salesPrice"), @NamedQuery(name = "Items.findByItemType", query = "SELECT i FROM Items i WHERE i.itemType = :itemType"), @NamedQuery(name = "Items.findByItemCost", query = "SELECT i FROM Items i WHERE i.itemCost = :itemCost"), @NamedQuery(name = "Items.findByEnteredBy", query = "SELECT i FROM Items i WHERE i.enteredBy = :enteredBy"), @NamedQuery(name = "Items.findByEnteredDate", query = "SELECT i FROM Items i WHERE i.enteredDate = :enteredDate")})
public class Items implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "itemCode")
    private Integer itemCode;
    @Basic(optional = false)
    @Column(name = "itemName")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "salesPrice")
    private double salesPrice;
    @Basic(optional = false)
    @Column(name = "itemType")
    private String itemType;
    @Basic(optional = false)
    @Column(name = "itemCost")
    private double itemCost;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "items")
    private List<InvoiceItems> invoiceItemsList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "items")
    private Stores stores;

    public Items() {
    }

    public Items(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public Items(Integer itemCode, String itemName, double salesPrice, String itemType, double itemCost, String enteredBy, Date enteredDate) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.salesPrice = salesPrice;
        this.itemType = itemType;
        this.itemCost = itemCost;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
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

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCode != null ? itemCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.Items[itemCode=" + itemCode + "]";
    }

}
