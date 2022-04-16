package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.Entities.Role;
import com.example.oraclehibernate.Services.RoleService;
import com.example.oraclehibernate.Services.UserService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RoleDetailController {
    @FXML
    private Text roleId;

    @FXML
    private Text roleName;
    private ObservableList<String>roleList;
    public void setValue(Role role, ObservableList<String> roleList){
        this.roleList=roleList;
        roleId.setText(String.valueOf(role.getId()));
        roleName.setText(role.getRole());
    }
    public void editHandler(ActionEvent event) throws IOException {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/role-edit-dialog-view.fxml"));
        RoleEditDialogController controller=new RoleEditDialogController();
        controller.setRoleName(roleName.getText());
        loader.setController(controller);
        Parent root=loader.load();
//        RoleEditDialogController controller=loader.getController();
//        window.setUserData(username.getText());

        Scene editScene = new Scene(root, 500, 350);
        window.setTitle("Edit Role");
        window.setScene(editScene);
        window.show();
    }

    public void deleteHandler(ActionEvent event) {
        RoleService roleService=new RoleService();
        roleService.dropRole(roleName.getText());
        roleList.remove(roleName.getText());
        MainController mainController=new MainController();
        mainController.setNotifyText("Remove successfully");
    }
}
