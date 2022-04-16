package com.example.oraclehibernate.Models;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class UserTabPrivsRow {
    private String tabPrivsName;
    private CheckBox grant;
    private CheckBox withGrant;


    public UserTabPrivsRow() {
        grant=new CheckBox();
        withGrant=new CheckBox();

    }
    public void setTabPrivsName(String tabPrivsName) {
        this.tabPrivsName = tabPrivsName;
    }


    public void setGrant(boolean isGrant) {
        grant.setSelected(isGrant);
    }

    public void setWithGrant(boolean isWithGrant) {
        withGrant.setSelected(isWithGrant);
    }


    public String getTabPrivsName() {
        return tabPrivsName;
    }

    public CheckBox getGrant() {
        return grant;
    }

    public CheckBox getWithGrant() {
        return withGrant;
    }



}
