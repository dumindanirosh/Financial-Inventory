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
@Table(name = "expense")
@NamedQueries({@NamedQuery(name = "Expense.findAll", query = "SELECT e FROM Expense e"), @NamedQuery(name = "Expense.findByExpenseId", query = "SELECT e FROM Expense e WHERE e.expenseId = :expenseId"), @NamedQuery(name = "Expense.findByExpenseDate", query = "SELECT e FROM Expense e WHERE e.expenseDate = :expenseDate"), @NamedQuery(name = "Expense.findByExpenseDescription", query = "SELECT e FROM Expense e WHERE e.expenseDescription = :expenseDescription"), @NamedQuery(name = "Expense.findByExpenseAmount", query = "SELECT e FROM Expense e WHERE e.expenseAmount = :expenseAmount"), @NamedQuery(name = "Expense.findByExpenseMonth", query = "SELECT e FROM Expense e WHERE e.expenseMonth = :expenseMonth"), @NamedQuery(name = "Expense.findByExpenseYear", query = "SELECT e FROM Expense e WHERE e.expenseYear = :expenseYear"), @NamedQuery(name = "Expense.findByEnteredBy", query = "SELECT e FROM Expense e WHERE e.enteredBy = :enteredBy"), @NamedQuery(name = "Expense.findByEnteredDate", query = "SELECT e FROM Expense e WHERE e.enteredDate = :enteredDate"), @NamedQuery(name = "Expense.findByExpenseType", query = "SELECT e FROM Expense e WHERE e.expenseType = :expenseType")})
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "expenseId")
    private Integer expenseId;
    @Basic(optional = false)
    @Column(name = "expenseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expenseDate;
    @Basic(optional = false)
    @Column(name = "expenseDescription")
    private String expenseDescription;
    @Basic(optional = false)
    @Column(name = "expenseAmount")
    private double expenseAmount;
    @Basic(optional = false)
    @Column(name = "expenseMonth")
    private String expenseMonth;
    @Basic(optional = false)
    @Column(name = "expenseYear")
    private String expenseYear;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @Basic(optional = false)
    @Column(name = "expenseType")
    private String expenseType;

    public Expense() {
    }

    public Expense(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Expense(Integer expenseId, Date expenseDate, String expenseDescription, double expenseAmount, String expenseMonth, String expenseYear, String enteredBy, Date enteredDate, String expenseType) {
        this.expenseId = expenseId;
        this.expenseDate = expenseDate;
        this.expenseDescription = expenseDescription;
        this.expenseAmount = expenseAmount;
        this.expenseMonth = expenseMonth;
        this.expenseYear = expenseYear;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
        this.expenseType = expenseType;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseMonth() {
        return expenseMonth;
    }

    public void setExpenseMonth(String expenseMonth) {
        this.expenseMonth = expenseMonth;
    }

    public String getExpenseYear() {
        return expenseYear;
    }

    public void setExpenseYear(String expenseYear) {
        this.expenseYear = expenseYear;
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

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expenseId != null ? expenseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expense)) {
            return false;
        }
        Expense other = (Expense) object;
        if ((this.expenseId == null && other.expenseId != null) || (this.expenseId != null && !this.expenseId.equals(other.expenseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.Expense[expenseId=" + expenseId + "]";
    }

}
