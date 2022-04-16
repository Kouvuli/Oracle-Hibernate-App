package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.UserDAO;
import com.example.oraclehibernate.Services.UserService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;

public class NewUserController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    private ObservableList<String>usernameList;
    public void setValue(ObservableList<String>usernameList){
        this.usernameList=usernameList;
    }
    public void confirmHandler(ActionEvent event) {
        UserService service=new UserService();
        if(confirmPassword.getText().trim().equals(password.getText().trim())){
            service.createNewUser(username.getText().trim(),password.getText());
            usernameList.add(username.getText().trim().toUpperCase());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Password does not match!");
            alert.showAndWait();
        }

        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
