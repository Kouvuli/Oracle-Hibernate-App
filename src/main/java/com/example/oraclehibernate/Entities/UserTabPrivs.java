package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DBA_TAB_PRIVS")
public class UserTabPrivs implements Serializable {
    @Id
    @Column(name = "GRANTEE")
    private String grantee;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "GRANTOR")
    private String grantor;

    @Id
    @Column(name = "PRIVILEGE")
    private String privilege;

    @Column(name = "GRANTABLE")
    private String grantable;

    public UserTabPrivs(){

    }

    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setGrantable(String grantable) {
        this.grantable = grantable;
    }

    public String getGrantee() {
        return grantee;
    }

    public String getOwner() {
        return owner;
    }

    public String getTableName() {
        return tableName;
    }

    public String getGrantor() {
        return grantor;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getGrantable() {
        return grantable;
    }
}
