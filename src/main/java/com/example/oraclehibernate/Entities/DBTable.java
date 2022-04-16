package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DBA_TABLES")
public class DBTable implements Serializable {
    @Column(name = "OWNER")
    private String owner;

    @Id
    @Column(name="TABLE_NAME")
    private String tableName;

    public DBTable(){}
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOwner() {
        return owner;
    }

    public String getTableName() {
        return tableName;
    }
}
