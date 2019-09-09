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
public class InvoiceItemsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "invoiceId")
    private int invoiceId;
    @Basic(optional = false)
    @Column(name = "itemCode")
    private int itemCode;

    public InvoiceItemsPK() {
    }



    public InvoiceItemsPK(int invoiceId, int itemCode) {
        this.invoiceId = invoiceId;
        this.itemCode = itemCode;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) invoiceId;
        hash += (int) itemCode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceItemsPK)) {
            return false;
        }
        InvoiceItemsPK other = (InvoiceItemsPK) object;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (this.itemCode != other.itemCode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.InvoiceItemsPK[invoiceId=" + invoiceId + ", itemCode=" + itemCode + "]";
    }

}
