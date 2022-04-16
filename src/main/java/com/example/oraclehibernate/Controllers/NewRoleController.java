package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.Services.RoleService;
import com.example.oraclehibernate.Services.UserService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRoleController {
    @FXML
    private TextField roleName;

    public ObservableList<String>roleList;
    public NewRoleController(){}
    public void setValue(ObservableList<String>roleList){
        this.roleList=roleList;
    }
    public void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void confirmHandler(ActionEvent event) {
        RoleService service=new RoleService();
        service.createNewRole(roleName.getText().trim());
        roleList.add(roleName.getText().trim().toUpperCase());
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
