package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.UserDAO;
import com.example.oraclehibernate.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;


    public void confirmHandler(ActionEvent event) {
        UserService service=new UserService();

        service.createNewUser(username.getText(),password.getText());
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
