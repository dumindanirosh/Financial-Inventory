/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Duminda
 */
@Embeddable
public class StoresMgtPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "itemCode")
    private String itemCode;
    @Basic(optional = false)
    @Column(name = "purchaseDate")
    private String purchaseDate;
    @Basic(optional = false)
    @Column(name = "suppliercode")
    private String suppliercode;

    public StoresMgtPK() {
    }

    public StoresMgtPK(String itemCode, String purchaseDate, String suppliercode) {
        this.itemCode = itemCode;
        this.purchaseDate = purchaseDate;
        this.suppliercode = suppliercode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCode != null ? itemCode.hashCode() : 0);
        hash += (purchaseDate != null ? purchaseDate.hashCode() : 0);
        hash += (suppliercode != null ? suppliercode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoresMgtPK)) {
            return false;
        }
        StoresMgtPK other = (StoresMgtPK) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        if ((this.purchaseDate == null && other.purchaseDate != null) || (this.purchaseDate != null && !this.purchaseDate.equals(other.purchaseDate))) {
            return false;
        }
        if ((this.suppliercode == null && other.suppliercode != null) || (this.suppliercode != null && !this.suppliercode.equals(other.suppliercode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.StoresMgtPK[itemCode=" + itemCode + ", purchaseDate=" + purchaseDate + ", suppliercode=" + suppliercode + "]";
    }

}
