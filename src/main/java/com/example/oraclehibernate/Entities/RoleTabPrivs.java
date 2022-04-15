package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ROLE_TAB_PRIVS")
public class RoleTabPrivs implements Serializable {
    @Id
    @Column(name = "ROLE")
    private String role;

    @Column(name = "OWNER")
    private String owner;

    @Id
    @Column(name = "TABLE_NAME")
    private String tableName;


    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Id
    @Column(name = "PRIVILEGE")
    private String privilege;

    public RoleTabPrivs(){};
    public void setRole(String role) {
        this.role = role;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getRole() {
        return role;
    }

    public String getOwner() {
        return owner;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getPrivilege() {
        return privilege;
    }
}
