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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "item_type")
@NamedQueries({@NamedQuery(name = "ItemType.findAll", query = "SELECT i FROM ItemType i"), @NamedQuery(name = "ItemType.findByItemtypename", query = "SELECT i FROM ItemType i WHERE i.itemtypename = :itemtypename"), @NamedQuery(name = "ItemType.findByTypedisabled", query = "SELECT i FROM ItemType i WHERE i.typedisabled = :typedisabled"), @NamedQuery(name = "ItemType.findByEnteredby", query = "SELECT i FROM ItemType i WHERE i.enteredby = :enteredby"), @NamedQuery(name = "ItemType.findByEntereddate", query = "SELECT i FROM ItemType i WHERE i.entereddate = :entereddate"), @NamedQuery(name = "ItemType.findByItemtypeid", query = "SELECT i FROM ItemType i WHERE i.itemtypeid = :itemtypeid")})
public class ItemType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "itemtypename")
    private String itemtypename;
    @Basic(optional = false)
    @Column(name = "typedisabled")
    private boolean typedisabled;
    @Basic(optional = false)
    @Column(name = "enteredby")
    private String enteredby;
    @Basic(optional = false)
    @Column(name = "entereddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entereddate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemtypeid")
    private Integer itemtypeid;

    public ItemType() {
    }

    public ItemType(Integer itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    public ItemType(Integer itemtypeid, String itemtypename, boolean typedisabled, String enteredby, Date entereddate) {
        this.itemtypeid = itemtypeid;
        this.itemtypename = itemtypename;
        this.typedisabled = typedisabled;
        this.enteredby = enteredby;
        this.entereddate = entereddate;
    }

    public String getItemtypename() {
        return itemtypename;
    }

    public void setItemtypename(String itemtypename) {
        this.itemtypename = itemtypename;
    }

    public boolean getTypedisabled() {
        return typedisabled;
    }

    public void setTypedisabled(boolean typedisabled) {
        this.typedisabled = typedisabled;
    }

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public Date getEntereddate() {
        return entereddate;
    }

    public void setEntereddate(Date entereddate) {
        this.entereddate = entereddate;
    }

    public Integer getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(Integer itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemtypeid != null ? itemtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemType)) {
            return false;
        }
        ItemType other = (ItemType) object;
        if ((this.itemtypeid == null && other.itemtypeid != null) || (this.itemtypeid != null && !this.itemtypeid.equals(other.itemtypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.ItemType[itemtypeid=" + itemtypeid + "]";
    }

}
