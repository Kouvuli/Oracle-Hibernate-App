package com.example.oraclehibernate.Models;

import javafx.scene.control.CheckBox;

public class UserSysPrivsRow {
    private String sysPrivsName;
    private CheckBox grant;
    private CheckBox withAdmin;

    public UserSysPrivsRow() {
        grant=new CheckBox();
        withAdmin=new CheckBox();
    }

    public void setSysPrivsName(String sysPrivsName) {
        this.sysPrivsName = sysPrivsName;
    }

    public void setGrant(boolean isGrant) {
        grant.setSelected(isGrant);
    }

    public void setWithAdmin(boolean isWithAdmin) {
        withAdmin.setSelected(isWithAdmin);
    }

    public String getSysPrivsName() {
        return sysPrivsName;
    }

    public CheckBox getGrant() {
        return grant;
    }

    public CheckBox getWithAdmin() {
        return withAdmin;
    }
}
