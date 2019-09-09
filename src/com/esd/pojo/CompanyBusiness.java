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
@Table(name = "company_business")
@NamedQueries({@NamedQuery(name = "CompanyBusiness.findAll", query = "SELECT c FROM CompanyBusiness c"), @NamedQuery(name = "CompanyBusiness.findByBusinessDate", query = "SELECT c FROM CompanyBusiness c WHERE c.businessDate = :businessDate"), @NamedQuery(name = "CompanyBusiness.findByBusinessAmount", query = "SELECT c FROM CompanyBusiness c WHERE c.businessAmount = :businessAmount"), @NamedQuery(name = "CompanyBusiness.findByBusinessProfit", query = "SELECT c FROM CompanyBusiness c WHERE c.businessProfit = :businessProfit"), @NamedQuery(name = "CompanyBusiness.findByBusinessMonth", query = "SELECT c FROM CompanyBusiness c WHERE c.businessMonth = :businessMonth"), @NamedQuery(name = "CompanyBusiness.findByBusinessYear", query = "SELECT c FROM CompanyBusiness c WHERE c.businessYear = :businessYear"), @NamedQuery(name = "CompanyBusiness.findByDiscountAmount", query = "SELECT c FROM CompanyBusiness c WHERE c.discountAmount = :discountAmount"), @NamedQuery(name = "CompanyBusiness.findByEnteredBy", query = "SELECT c FROM CompanyBusiness c WHERE c.enteredBy = :enteredBy"), @NamedQuery(name = "CompanyBusiness.findByEnteredDate", query = "SELECT c FROM CompanyBusiness c WHERE c.enteredDate = :enteredDate"), @NamedQuery(name = "CompanyBusiness.findByCashierAmount", query = "SELECT c FROM CompanyBusiness c WHERE c.cashierAmount = :cashierAmount"), @NamedQuery(name = "CompanyBusiness.findByCompanyBusinessId", query = "SELECT c FROM CompanyBusiness c WHERE c.companyBusinessId = :companyBusinessId")})
public class CompanyBusiness implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "businessDate")
    private String businessDate;
    @Basic(optional = false)
    @Column(name = "businessAmount")
    private double businessAmount;
    @Basic(optional = false)
    @Column(name = "businessProfit")
    private double businessProfit;
    @Basic(optional = false)
    @Column(name = "businessMonth")
    private String businessMonth;
    @Basic(optional = false)
    @Column(name = "businessYear")
    private String businessYear;
    @Basic(optional = false)
    @Column(name = "discountAmount")
    private double discountAmount;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @Basic(optional = false)
    @Column(name = "cashierAmount")
    private double cashierAmount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyBusinessId")
    private Integer companyBusinessId;

    public CompanyBusiness() {
    }

    public CompanyBusiness(Integer companyBusinessId) {
        this.companyBusinessId = companyBusinessId;
    }

    public CompanyBusiness(Integer companyBusinessId, String businessDate, double businessAmount, double businessProfit, String businessMonth, String businessYear, double discountAmount, String enteredBy, Date enteredDate, double cashierAmount) {
        this.companyBusinessId = companyBusinessId;
        this.businessDate = businessDate;
        this.businessAmount = businessAmount;
        this.businessProfit = businessProfit;
        this.businessMonth = businessMonth;
        this.businessYear = businessYear;
        this.discountAmount = discountAmount;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
        this.cashierAmount = cashierAmount;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public double getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(double businessAmount) {
        this.businessAmount = businessAmount;
    }

    public double getBusinessProfit() {
        return businessProfit;
    }

    public void setBusinessProfit(double businessProfit) {
        this.businessProfit = businessProfit;
    }

    public String getBusinessMonth() {
        return businessMonth;
    }

    public void setBusinessMonth(String businessMonth) {
        this.businessMonth = businessMonth;
    }

    public String getBusinessYear() {
        return businessYear;
    }

    public void setBusinessYear(String businessYear) {
        this.businessYear = businessYear;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
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

    public double getCashierAmount() {
        return cashierAmount;
    }

    public void setCashierAmount(double cashierAmount) {
        this.cashierAmount = cashierAmount;
    }

    public Integer getCompanyBusinessId() {
        return companyBusinessId;
    }

    public void setCompanyBusinessId(Integer companyBusinessId) {
        this.companyBusinessId = companyBusinessId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyBusinessId != null ? companyBusinessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyBusiness)) {
            return false;
        }
        CompanyBusiness other = (CompanyBusiness) object;
        if ((this.companyBusinessId == null && other.companyBusinessId != null) || (this.companyBusinessId != null && !this.companyBusinessId.equals(other.companyBusinessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.CompanyBusiness[companyBusinessId=" + companyBusinessId + "]";
    }

}
