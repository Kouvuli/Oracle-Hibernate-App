package com.example.oraclehibernate.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "DBA_USERS")
public class User implements Serializable {
    @Id
    @Column(name = "USER_ID")
    private int id;


    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD",nullable = true)
    private String password;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "LOCK_DATE")
    private Date lockDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;
    @ManyToMany
    @JoinTable(
            name = "DBA_ROLE_PRIVS",
            joinColumns = {
                    @JoinColumn(name = "GRANTEE")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "GRANTED_ROLE")
            }

    )
    private Set<Role> roles;

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }



    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

}
