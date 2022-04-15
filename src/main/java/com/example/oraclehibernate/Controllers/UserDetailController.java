package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDetailController {
    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Text userId;

    @FXML
    private Text username;

    @FXML
    private Text createAt;

    @FXML
    private Text lockDate;

    @FXML
    private Text expiryDate;
    public void setUser(User user){
        userId.setText(String.valueOf(user.getId()));
        username.setText(user.getUsername());
        createAt.setText(user.getCreated().toString());
        if (user.getLockDate()!=null){
            lockDate.setText(user.getLockDate().toString());
        }
        if (user.getExpiryDate()!=null){
            expiryDate.setText(user.getExpiryDate().toString());
        }
    }


    public void editHandler(ActionEvent e) throws IOException {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/user-edit-dialog-view.fxml"));
        Parent root=loader.load();
        UserEditDialogController controller=loader.getController();
        controller.setUsername(username.getText());
//        window.setUserData(username.getText());

        Scene editScene = new Scene(root, 500, 350);
        window.setTitle("Edit User");
        window.setScene(editScene);
        window.show();
    }

    public void deleteHandler(ActionEvent e) {
    }
}
