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
@Table(name = "return_exchange")
@NamedQueries({@NamedQuery(name = "ReturnExchange.findAll", query = "SELECT r FROM ReturnExchange r"), @NamedQuery(name = "ReturnExchange.findByInvoiceId", query = "SELECT r FROM ReturnExchange r WHERE r.returnExchangePK.invoiceId = :invoiceId"), @NamedQuery(name = "ReturnExchange.findByItemCode", query = "SELECT r FROM ReturnExchange r WHERE r.returnExchangePK.itemCode = :itemCode"), @NamedQuery(name = "ReturnExchange.findByReturnItemCode", query = "SELECT r FROM ReturnExchange r WHERE r.returnItemCode = :returnItemCode"), @NamedQuery(name = "ReturnExchange.findByNumberOfItems", query = "SELECT r FROM ReturnExchange r WHERE r.numberOfItems = :numberOfItems"), @NamedQuery(name = "ReturnExchange.findByEnteredBy", query = "SELECT r FROM ReturnExchange r WHERE r.enteredBy = :enteredBy"), @NamedQuery(name = "ReturnExchange.findByEnteredDate", query = "SELECT r FROM ReturnExchange r WHERE r.enteredDate = :enteredDate")})
public class ReturnExchange implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReturnExchangePK returnExchangePK;
    @Basic(optional = false)
    @Column(name = "returnItemCode")
    private int returnItemCode;
    @Basic(optional = false)
    @Column(name = "numberOfItems")
    private int numberOfItems;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;

    public ReturnExchange() {
    }

    public ReturnExchange(ReturnExchangePK returnExchangePK) {
        this.returnExchangePK = returnExchangePK;
    }

    public ReturnExchange(ReturnExchangePK returnExchangePK, int returnItemCode, int numberOfItems, String enteredBy, Date enteredDate) {
        this.returnExchangePK = returnExchangePK;
        this.returnItemCode = returnItemCode;
        this.numberOfItems = numberOfItems;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
    }

    public ReturnExchange(int invoiceId, int itemCode) {
        this.returnExchangePK = new ReturnExchangePK(invoiceId, itemCode);
    }

    public ReturnExchangePK getReturnExchangePK() {
        return returnExchangePK;
    }

    public void setReturnExchangePK(ReturnExchangePK returnExchangePK) {
        this.returnExchangePK = returnExchangePK;
    }

    public int getReturnItemCode() {
        return returnItemCode;
    }

    public void setReturnItemCode(int returnItemCode) {
        this.returnItemCode = returnItemCode;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnExchangePK != null ? returnExchangePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnExchange)) {
            return false;
        }
        ReturnExchange other = (ReturnExchange) object;
        if ((this.returnExchangePK == null && other.returnExchangePK != null) || (this.returnExchangePK != null && !this.returnExchangePK.equals(other.returnExchangePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.ReturnExchange[returnExchangePK=" + returnExchangePK + "]";
    }

}
