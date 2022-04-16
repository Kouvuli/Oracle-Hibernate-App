package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USER_COL_PRIVS_MADE")
public class ColumnPermission implements Serializable {
    @Id
    @Column(name = "GRANTEE")
    private String grantee;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Column(name = "PRIVILEGE")
    private String privilege;

    public ColumnPermission(){}

    public void setGrantee(String grantee) {
        this.grantee = grantee;
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

    public String getGrantee() {
        return grantee;
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
