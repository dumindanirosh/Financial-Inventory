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
@Table(name = "user_activity")
@NamedQueries({@NamedQuery(name = "UserActivity.findAll", query = "SELECT u FROM UserActivity u"), @NamedQuery(name = "UserActivity.findByActivityId", query = "SELECT u FROM UserActivity u WHERE u.activityId = :activityId"), @NamedQuery(name = "UserActivity.findByActivityDetails", query = "SELECT u FROM UserActivity u WHERE u.activityDetails = :activityDetails"), @NamedQuery(name = "UserActivity.findByEnteredBy", query = "SELECT u FROM UserActivity u WHERE u.enteredBy = :enteredBy"), @NamedQuery(name = "UserActivity.findByEnteredDate", query = "SELECT u FROM UserActivity u WHERE u.enteredDate = :enteredDate"), @NamedQuery(name = "UserActivity.findByEnteredMonth", query = "SELECT u FROM UserActivity u WHERE u.enteredMonth = :enteredMonth"), @NamedQuery(name = "UserActivity.findByEnteredYear", query = "SELECT u FROM UserActivity u WHERE u.enteredYear = :enteredYear")})
public class UserActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "activityId")
    private Integer activityId;
    @Basic(optional = false)
    @Column(name = "activityDetails")
    private String activityDetails;
    @Basic(optional = false)
    @Column(name = "enteredBy")
    private String enteredBy;
    @Basic(optional = false)
    @Column(name = "enteredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteredDate;
    @Basic(optional = false)
    @Column(name = "enteredMonth")
    private String enteredMonth;
    @Basic(optional = false)
    @Column(name = "enteredYear")
    private String enteredYear;

    public UserActivity() {
    }

    public UserActivity(Integer activityId) {
        this.activityId = activityId;
    }

    public UserActivity(Integer activityId, String activityDetails, String enteredBy, Date enteredDate, String enteredMonth, String enteredYear) {
        this.activityId = activityId;
        this.activityDetails = activityDetails;
        this.enteredBy = enteredBy;
        this.enteredDate = enteredDate;
        this.enteredMonth = enteredMonth;
        this.enteredYear = enteredYear;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
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

    public String getEnteredMonth() {
        return enteredMonth;
    }

    public void setEnteredMonth(String enteredMonth) {
        this.enteredMonth = enteredMonth;
    }

    public String getEnteredYear() {
        return enteredYear;
    }

    public void setEnteredYear(String enteredYear) {
        this.enteredYear = enteredYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityId != null ? activityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserActivity)) {
            return false;
        }
        UserActivity other = (UserActivity) object;
        if ((this.activityId == null && other.activityId != null) || (this.activityId != null && !this.activityId.equals(other.activityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.UserActivity[activityId=" + activityId + "]";
    }

}
