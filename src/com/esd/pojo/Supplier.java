/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
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

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "supplier")
@NamedQueries({@NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"), @NamedQuery(name = "Supplier.findBySuppliercode", query = "SELECT s FROM Supplier s WHERE s.suppliercode = :suppliercode"), @NamedQuery(name = "Supplier.findBySuppliername", query = "SELECT s FROM Supplier s WHERE s.suppliername = :suppliername"), @NamedQuery(name = "Supplier.findBySuppliercontactnumber", query = "SELECT s FROM Supplier s WHERE s.suppliercontactnumber = :suppliercontactnumber")})
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "suppliercode")
    private String suppliercode;
    @Basic(optional = false)
    @Column(name = "suppliername")
    private String suppliername;
    @Column(name = "suppliercontactnumber")
    private String suppliercontactnumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<StoresMgt> storesMgtList;

    public Supplier() {
    }

    public Supplier(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public Supplier(String suppliercode, String suppliername) {
        this.suppliercode = suppliercode;
        this.suppliername = suppliername;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getSuppliercontactnumber() {
        return suppliercontactnumber;
    }

    public void setSuppliercontactnumber(String suppliercontactnumber) {
        this.suppliercontactnumber = suppliercontactnumber;
    }

    public List<StoresMgt> getStoresMgtList() {
        return storesMgtList;
    }

    public void setStoresMgtList(List<StoresMgt> storesMgtList) {
        this.storesMgtList = storesMgtList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suppliercode != null ? suppliercode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.suppliercode == null && other.suppliercode != null) || (this.suppliercode != null && !this.suppliercode.equals(other.suppliercode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.Supplier[suppliercode=" + suppliercode + "]";
    }

}
