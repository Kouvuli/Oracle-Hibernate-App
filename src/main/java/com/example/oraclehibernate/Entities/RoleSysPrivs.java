package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ROLE_SYS_PRIVS")
public class RoleSysPrivs implements Serializable {
    @Id
    @Column(name = "ROLE")
    private String role;

    @Id
    @Column(name = "PRIVILEGE")
    private String privilege;

    @Column(name = "ADMIN_OPTION")
    private boolean adminOption;

    public RoleSysPrivs(){}

    public void setRole(String role) {
        this.role = role;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setAdminOption(boolean adminOption) {
        this.adminOption = adminOption;
    }

    public String getRole() {
        return role;
    }

    public String getPrivilege() {
        return privilege;
    }

    public boolean getAdminOption() {
        return adminOption;
    }
}
