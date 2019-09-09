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
@Table(name = "new_orders")
@NamedQueries({@NamedQuery(name = "NewOrders.findAll", query = "SELECT n FROM NewOrders n"), @NamedQuery(name = "NewOrders.findByOrderId", query = "SELECT n FROM NewOrders n WHERE n.orderId = :orderId"), @NamedQuery(name = "NewOrders.findByCustomerName", query = "SELECT n FROM NewOrders n WHERE n.customerName = :customerName"), @NamedQuery(name = "NewOrders.findByContactNumber", query = "SELECT n FROM NewOrders n WHERE n.contactNumber = :contactNumber"), @NamedQuery(name = "NewOrders.findByOrderDetails", query = "SELECT n FROM NewOrders n WHERE n.orderDetails = :orderDetails"), @NamedQuery(name = "NewOrders.findByOrderMonth", query = "SELECT n FROM NewOrders n WHERE n.orderMonth = :orderMonth"), @NamedQuery(name = "NewOrders.findByOrderYear", query = "SELECT n FROM NewOrders n WHERE n.orderYear = :orderYear"), @NamedQuery(name = "NewOrders.findByEnteredBy", query = "SELECT n FROM NewOrders n WHERE n.enteredBy = :enteredBy"), @NamedQuery(name = "NewOrders.findByEnteredDate", query = "SELECT n FROM NewOrders n WHERE n.enteredDate = :enteredDate")})
public class NewOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderId")
    private Integer orderId;
    @Basic(optional = false)
    @Column(name = "customerName")
    private String customerName;
    @Basic(optional = false)
    @Column(name = "contactNumber")
    private String contactNumber;
    @Basic(optional = false)
    @Column(name = "orderDetails")
    private String orderDetails;
    @Basic(optional = false)
    @Column(name = "orderMonth")
    private String orderMonth;
    @Basic(optional = false)
    @Column(name = "orderYear")
    private String orderYear;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;

    public NewOrders() {
    }

    public NewOrders(Integer orderId) {
        this.orderId = orderId;
    }

    public NewOrders(Integer orderId, String customerName, String contactNumber, String orderDetails, String orderMonth, String orderYear, String enteredBy, Date enteredDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.orderDetails = orderDetails;
        this.orderMonth = orderMonth;
        this.orderYear = orderYear;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    public String getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(String orderYear) {
        this.orderYear = orderYear;
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
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewOrders)) {
            return false;
        }
        NewOrders other = (NewOrders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.NewOrders[orderId=" + orderId + "]";
    }

}
