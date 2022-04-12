package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.Main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private Button loginBtn;
    @FXML
    private Button cancelBtn;

    public LoginController() {}

    @FXML
    private void initialize()
    {
        loginBtn.setDisable(true);
        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                loginBtn.setDisable(newValue.trim().isEmpty());
            }
        });


    }

    @FXML
    private void handleCancleButton(){
        // get a handle to the stage
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    public void handleLoginButton(ActionEvent event) throws IOException {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main-view.fxml"));
        Scene main = new Scene(loader.load(), 600, 400);
        window.setScene(main);
    }
//    @FXML
//    private void printOutput()
//    {
//        outputText.setText(inputText.getText());
//    }





}
