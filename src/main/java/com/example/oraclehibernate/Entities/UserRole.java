package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DBA_ROLE_PRIVS")
public class UserRole implements Serializable {
    @Id
    @Column(name = "GRANTEE")
    private String grantee;

    @Id
    @Column(name = "GRANTED_ROLE")
    private String grantedRole;

    @Column(name = "ADMIN_OPTION")
    private boolean adminOption;

    private UserRole(){

    }

    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }

    public void setGrantRole(String grantRole) {
        this.grantedRole = grantRole;
    }

    public void setAdminOption(boolean adminOption) {
        this.adminOption = adminOption;
    }

    public String getGrantee() {
        return grantee;
    }

    public String getGrantRole() {
        return grantedRole;
    }

    public boolean getIsAdminOption() {
        return adminOption;
    }
}
