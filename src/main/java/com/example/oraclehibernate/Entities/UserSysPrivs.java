package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DBA_SYS_PRIVS")
public class UserSysPrivs implements Serializable {
    @Id
    @Column(name = "GRANTEE")
    private String grantee;

    @Id
    @Column(name = "PRIVILEGE")
    private String privilege;

    @Column(name = "ADMIN_OPTION")
    private boolean adminOption;

    public UserSysPrivs(){};
    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setAdminOption(boolean adminOption) {
        this.adminOption = adminOption;
    }

    public String getGrantee() {
        return grantee;
    }

    public String getPrivilege() {
        return privilege;
    }

    public boolean getAdminOption() {
        return adminOption;
    }
}
