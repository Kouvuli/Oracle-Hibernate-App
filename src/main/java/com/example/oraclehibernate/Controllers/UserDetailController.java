package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.Entities.User;
import com.example.oraclehibernate.Services.UserService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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


    private ObservableList<String> userList;

    public void setValue(User user, ObservableList<String> userList) {
        this.userList=userList;
        userId.setText(String.valueOf(user.getId()));
        username.setText(user.getUsername());
        createAt.setText(user.getCreated().toString());
        if (user.getLockDate() != null) {
            lockDate.setText(user.getLockDate().toString());
        }
        if (user.getExpiryDate() != null) {
            expiryDate.setText(user.getExpiryDate().toString());
        }
    }


    public void editHandler(ActionEvent e) throws IOException {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/user-edit-dialog-view.fxml"));
        UserEditDialogController controller=new UserEditDialogController();
        controller.setUsername(username.getText());
        loader.setController(controller);

        Parent root=loader.load();
//        UserEditDialogController controller=loader.getController();

//        window.setUserData(username.getText());

        Scene editScene = new Scene(root, 500, 350);
        window.setTitle("Edit User");
        window.setScene(editScene);
        window.show();
    }

    public void deleteHandler(ActionEvent e) {
        UserService userService=new UserService();
        userService.dropUser(username.getText());
        userList.remove(username.getText());
        MainController mainController=new MainController();
        mainController.setNotifyText("Remove successfully");
    }
}
