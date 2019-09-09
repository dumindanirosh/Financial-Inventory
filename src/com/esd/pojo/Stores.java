/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "stores")
@NamedQueries({@NamedQuery(name = "Stores.findAll", query = "SELECT s FROM Stores s"), @NamedQuery(name = "Stores.findByItemCode", query = "SELECT s FROM Stores s WHERE s.itemCode = :itemCode"), @NamedQuery(name = "Stores.findByQuantity", query = "SELECT s FROM Stores s WHERE s.quantity = :quantity"), @NamedQuery(name = "Stores.findByReOrder", query = "SELECT s FROM Stores s WHERE s.reOrder = :reOrder"), @NamedQuery(name = "Stores.findByEnteredBy", query = "SELECT s FROM Stores s WHERE s.enteredBy = :enteredBy"), @NamedQuery(name = "Stores.findByEnteredDate", query = "SELECT s FROM Stores s WHERE s.enteredDate = :enteredDate")})
public class Stores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "itemCode")
    private Integer itemCode;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "reOrder")
    private Integer reOrder;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Items items;

    public Stores() {
    }

    public Stores(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public Stores(Integer itemCode, String enteredBy, Date enteredDate) {
        this.itemCode = itemCode;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReOrder() {
        return reOrder;
    }

    public void setReOrder(Integer reOrder) {
        this.reOrder = reOrder;
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

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
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
        if (!(object instanceof Stores)) {
            return false;
        }
        Stores other = (Stores) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.Stores[itemCode=" + itemCode + "]";
    }

}
