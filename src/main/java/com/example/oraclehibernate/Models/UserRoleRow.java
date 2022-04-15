package com.example.oraclehibernate.Models;

import com.example.oraclehibernate.Entities.UserRole;
import com.example.oraclehibernate.Services.RoleService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class UserRoleRow {
    private String roleName;
    private CheckBox grant;
    private CheckBox withAdmin;

    public UserRoleRow() {
        grant=new CheckBox();
        withAdmin=new CheckBox();


    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setGrant(boolean isSelected) {
        grant.setSelected(isSelected);
    }

    public void setWithAdmin(boolean isSelected) {
        withAdmin.setSelected(isSelected);
    }

    public String getRoleName() {
        return roleName;
    }

    public CheckBox getGrant() {
        return grant;
    }

    public CheckBox getWithAdmin() {
        return withAdmin;
    }
}
