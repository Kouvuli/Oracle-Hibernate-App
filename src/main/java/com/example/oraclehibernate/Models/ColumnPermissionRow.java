package com.example.oraclehibernate.Models;

import javafx.scene.control.CheckBox;

public class ColumnPermissionRow {
    private CheckBox checkBox;
    private String columnName;
    public ColumnPermissionRow() {
        checkBox=new CheckBox();
    }
    public void setCheckBox(boolean isChecked) {
        checkBox.setSelected(isChecked);
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public String getColumnName() {
        return columnName;
    }
}
