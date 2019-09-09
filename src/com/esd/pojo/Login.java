/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duminda
 */
@Entity
@Table(name = "login")
@NamedQueries({@NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"), @NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username"), @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password"), @NamedQuery(name = "Login.findByUserType", query = "SELECT l FROM Login l WHERE l.userType = :userType"), @NamedQuery(name = "Login.findByIsDisabled", query = "SELECT l FROM Login l WHERE l.isDisabled = :isDisabled")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "userType")
    private String userType;
    @Basic(optional = false)
    @Column(name = "isDisabled")
    private boolean isDisabled;

    public Login() {
    }

    public Login(String username) {
        this.username = username;
    }

    public Login(String username, String password, String userType, boolean isDisabled) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.isDisabled = isDisabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esd.pojo.Login[username=" + username + "]";
    }

}
