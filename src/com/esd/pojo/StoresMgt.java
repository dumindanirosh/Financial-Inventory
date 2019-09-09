/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "stores_mgt")
@NamedQueries({@NamedQuery(name = "StoresMgt.findAll", query = "SELECT s FROM StoresMgt s"), @NamedQuery(name = "StoresMgt.findByItemCode", query = "SELECT s FROM StoresMgt s WHERE s.storesMgtPK.itemCode = :itemCode"), @NamedQuery(name = "StoresMgt.findByItemName", query = "SELECT s FROM StoresMgt s WHERE s.itemName = :itemName"), @NamedQuery(name = "StoresMgt.findByPurchaseDate", query = "SELECT s FROM StoresMgt s WHERE s.storesMgtPK.purchaseDate = :purchaseDate"), @NamedQuery(name = "StoresMgt.findByPurchaseQuantity", query = "SELECT s FROM StoresMgt s WHERE s.purchaseQuantity = :purchaseQuantity"), @NamedQuery(name = "StoresMgt.findByRejectedQuantity", query = "SELECT s FROM StoresMgt s WHERE s.rejectedQuantity = :rejectedQuantity"), @NamedQuery(name = "StoresMgt.findByAccptedQuantity", query = "SELECT s FROM StoresMgt s WHERE s.accptedQuantity = :accptedQuantity"), @NamedQuery(name = "StoresMgt.findBySellQuantity", query = "SELECT s FROM StoresMgt s WHERE s.sellQuantity = :sellQuantity"), @NamedQuery(name = "StoresMgt.findByEnteredBy", query = "SELECT s FROM StoresMgt s WHERE s.enteredBy = :enteredBy"), @NamedQuery(name = "StoresMgt.findByEnteredDate", query = "SELECT s FROM StoresMgt s WHERE s.enteredDate = :enteredDate"), @NamedQuery(name = "StoresMgt.findByPurchaseMonth", query = "SELECT s FROM StoresMgt s WHERE s.purchaseMonth = :purchaseMonth"), @NamedQuery(name = "StoresMgt.findByPurchaseYear", query = "SELECT s FROM StoresMgt s WHERE s.purchaseYear = :purchaseYear"), @NamedQuery(name = "StoresMgt.findBySuppliercode", query = "SELECT s FROM StoresMgt s WHERE s.storesMgtPK.suppliercode = :suppliercode")})
public class StoresMgt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoresMgtPK storesMgtPK;
    @Basic(optional = false)
    @Column(name = "itemName")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "purchaseQuantity")
    private int purchaseQuantity;
    @Basic(optional = false)
    @Column(name = "rejectedQuantity")
    private int rejectedQuantity;
    @Basic(optional = false)
    @Column(name = "accptedQuantity")
    private int accptedQuantity;
    @Basic(optional = false)
    @Column(name = "sellQuantity")
    private int sellQuantity;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @Basic(optional = false)
    @Column(name = "purchaseMonth")
    private String purchaseMonth;
    @Basic(optional = false)
    @Column(name = "purchaseYear")
    private String purchaseYear;
    @JoinColumn(name = "suppliercode", referencedColumnName = "suppliercode", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Supplier supplier;
    private transient String avilableQuantity;
    public StoresMgt() {
    }

    public StoresMgt(StoresMgtPK storesMgtPK) {
        this.storesMgtPK = storesMgtPK;
    }

    public StoresMgt(StoresMgtPK storesMgtPK, String itemName, int purchaseQuantity, int rejectedQuantity, int accptedQuantity, int sellQuantity, String enteredBy, Date enteredDate, String purchaseMonth, String purchaseYear) {
        this.storesMgtPK = storesMgtPK;
        this.itemName = itemName;
        this.purchaseQuantity = purchaseQuantity;
        this.rejectedQuantity = rejectedQuantity;
        this.accptedQuantity = accptedQuantity;
        this.sellQuantity = sellQuantity;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
        this.purchaseMonth = purchaseMonth;
        this.purchaseYear = purchaseYear;
    }

    public StoresMgt(String itemCode, String purchaseDate, String suppliercode) {
        this.storesMgtPK = new StoresMgtPK(itemCode, purchaseDate, suppliercode);
    }

    public StoresMgtPK getStoresMgtPK() {
        return storesMgtPK;
    }

    public void setStoresMgtPK(StoresMgtPK storesMgtPK) {
        this.storesMgtPK = storesMgtPK;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public int getRejectedQuantity() {
        return rejectedQuantity;
    }

    public void setRejectedQuantity(int rejectedQuantity) {
        this.rejectedQuantity = rejectedQuantity;
    }

    public int getAccptedQuantity() {
        return accptedQuantity;
    }

    public void setAccptedQuantity(int accptedQuantity) {
        this.accptedQuantity = accptedQuantity;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
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

    public String getPurchaseMonth() {
        return purchaseMonth;
    }

    public void setPurchaseMonth(String purchaseMonth) {
        this.purchaseMonth = purchaseMonth;
    }

    public String getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(String purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storesMgtPK != null ? storesMgtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoresMgt)) {
            return false;
        }
        StoresMgt other = (StoresMgt) object;
        if ((this.storesMgtPK == null && other.storesMgtPK != null) || (this.storesMgtPK != null && !this.storesMgtPK.equals(other.storesMgtPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.StoresMgt[storesMgtPK=" + storesMgtPK + "]";
    }

    /**
     * @return the avilableQuantity
     */
    public String getAvilableQuantity() {
        return avilableQuantity;
    }

    /**
     * @param avilableQuantity the avilableQuantity to set
     */
    public void setAvilableQuantity(String avilableQuantity) {
        this.avilableQuantity = avilableQuantity;
    }

}
