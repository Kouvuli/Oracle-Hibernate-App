package com.example.oraclehibernate.Models;

import java.util.List;

public class TabPrivsRow {
    private String privilege;
    private String object;
    private List<String> columns;
    private boolean withGrant;
    private String grantee;
    public TabPrivsRow(){}

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setTable(String table) {
        this.object = table;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public void setWithGrant(boolean withGrant) {
        this.withGrant = withGrant;
    }

    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getTable() {
        return object;
    }

    public List<String> getColumns() {
        return columns;
    }

    public boolean isWithGrant() {
        return withGrant;
    }

    public String getGrantee() {
        return grantee;
    }
}
