package com.example.oraclehibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditDialogController {

    public EditDialogController() {
    }
    @FXML
    private void initialize(){

    }

    public void closeHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void applyHandler(ActionEvent actionEvent) {
    }
}
