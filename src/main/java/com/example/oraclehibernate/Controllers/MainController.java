package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.RoleDAO;
import com.example.oraclehibernate.DAO.UserDAO;
import com.example.oraclehibernate.DAO.UserRoleDAO;
import com.example.oraclehibernate.Models.Role;
import com.example.oraclehibernate.Models.User;
import com.example.oraclehibernate.Models.UserRole;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ListView userList;
    @FXML
    private ListView roleList;

    @FXML
    private ScrollPane content;


    private ObservableList<String> usernameList;
    private ObservableList<String>userRoleList;
    private ObservableList<String> roleNameList;
    public MainController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDAO userDAO=new UserDAO();
        RoleDAO roleDAO=new RoleDAO();


        usernameList=userDAO.getAllUsername();
        userList.setItems(usernameList);


        userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String username=(String) userList.getSelectionModel().getSelectedItem();
                UserRoleDAO userRoleDAO=new UserRoleDAO();
//                userRoleDAO.getAll();
                User currUser=userDAO.getUser(username);

                FXMLLoader loader=new FXMLLoader(getClass().getResource("/user-detail-view.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                UserDetailController controller=loader.getController();
                controller.setUser(currUser);

                content.setContent(root);


                userRoleList=userRoleDAO.getRoles(username);
                userRoleList.forEach(item->{
                    System.out.println(item);
                });
            }
        });
//        rList.forEach(role -> {
//            roleList.getItems().add(role.getRole());
//        });
        roleNameList=roleDAO.getAllRole();
        roleList.setItems(roleNameList);
        roleList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String username=(String) userList.getSelectionModel().getSelectedItem();
                UserRoleDAO userRoleDAO=new UserRoleDAO();
//                userRoleDAO.getAll();
                User currUser=userDAO.getUser(username);

                FXMLLoader loader=new FXMLLoader(getClass().getResource("/user-detail-view.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                UserDetailController controller=loader.getController();
                controller.setUser(currUser);

                content.setContent(root);


                userRoleList=userRoleDAO.getRoles(username);
                userRoleList.forEach(item->{
                    System.out.println(item);
                });
            }
        });

    }

    public void addHandler(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/new-user-dialog.fxml"));
        Scene editScene = new Scene(loader.load(), 300, 200);
        window.setTitle("Add User");
        window.setScene(editScene);
        window.show();
    }
}
