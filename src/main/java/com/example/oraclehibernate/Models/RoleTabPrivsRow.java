package com.example.oraclehibernate.Models;

import javafx.scene.control.CheckBox;

public class RoleTabPrivsRow {
    private String tabPrivsName;
    private CheckBox grant;


    public RoleTabPrivsRow() {
        grant=new CheckBox();


    }
    public void setTabPrivsName(String tabPrivsName) {
        this.tabPrivsName = tabPrivsName;
    }


    public void setGrant(boolean isGrant) {
        grant.setSelected(isGrant);
    }


    public String getTabPrivsName() {
        return tabPrivsName;
    }

    public CheckBox getGrant() {
        return grant;
    }

}
