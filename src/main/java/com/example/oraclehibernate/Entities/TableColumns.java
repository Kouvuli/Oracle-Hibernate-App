package com.example.oraclehibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USER_TAB_COLUMNS")
public class TableColumns implements Serializable {
    @Id
    @Column(name = "TABLE_NAME")
    private String tableName;

    @Id
    @Column(name = "COLUMN_NAME")
    private String columnName;

    public TableColumns(){}
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }
}
