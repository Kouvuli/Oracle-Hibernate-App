package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.RoleDAO;
import com.example.oraclehibernate.DAO.UserRoleDAO;
import com.example.oraclehibernate.DAO.UserSysPrivsDAO;
import com.example.oraclehibernate.Entities.Role;
import com.example.oraclehibernate.Entities.UserRole;
import com.example.oraclehibernate.Entities.UserSysPrivs;
import com.example.oraclehibernate.Models.UserRoleRow;
import com.example.oraclehibernate.Models.UserSysPrivsRow;
import com.example.oraclehibernate.Services.PrivsService;
import com.example.oraclehibernate.Services.RoleService;
import com.example.oraclehibernate.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserEditDialogController implements Initializable {
    @FXML
    private Text alertText;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TableView<UserRoleRow> roleTable;

    @FXML
    private TableView<UserSysPrivsRow> sysPrivsTable;

    @FXML
    private TableColumn<UserRoleRow,String> roleName;

    @FXML
    private TableColumn<UserRoleRow, String> grantRole;

    @FXML
    private TableColumn<UserRoleRow, String> withAdminRole;


    @FXML
    private TableColumn<UserSysPrivsRow,String> sysPrivsName;

    @FXML
    private TableColumn<UserSysPrivsRow, String> grantSysPrivs;

    @FXML
    private TableColumn<UserSysPrivsRow, String> withAdminSysPrivs;

    private List<UserRoleRow> roleList;
    private List<String> checkedRoleList;

    private List<UserSysPrivsRow> userSysPrivsList;
    private List<String> checkedSysPrivsList;


    private String username;
    public UserEditDialogController() {
    }
    public void setUsername(String username){
        this.username=username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkedRoleList=new ArrayList<>();
        roleList=new ArrayList<>();
        userSysPrivsList=new ArrayList<>();
        checkedSysPrivsList=new ArrayList<>();

    }


    public void closeHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void applyHandler(ActionEvent event) {
        RoleService roleService=new RoleService();
        PrivsService privsService=new PrivsService();
        UserService userService=new UserService();
        if (!password.getText().isEmpty()){
            if(password.getText().trim().equals(confirmPassword.getText().trim())){
                userService.changePassword(username,password.getText().trim());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Password does not match!");
                alert.showAndWait();
            }
        }


        for (String checkedRole:checkedRoleList) {
            roleService.revokeRole(checkedRole,username);
        }
        for (UserRoleRow row : roleTable.getItems()) {
            if(row.getGrant().isSelected()) {
                roleService.grantRole(row.getRoleName(), username, row.getWithAdmin().isSelected());
            }
        }
        checkedRoleList.clear();



        for (String checkSysPrivs:checkedSysPrivsList) {
            privsService.revokeSysPrivilege(checkSysPrivs,username);
        }
        for (UserSysPrivsRow row:sysPrivsTable.getItems()){
            if(row.getGrant().isSelected()) {
                privsService.grantSysPrivilege(row.getSysPrivsName(), username, row.getWithAdmin().isSelected());
            }
        }
        checkedSysPrivsList.clear();


        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }


    public void roleTabHandler(Event event) {
//        System.out.println("role tab");
//        roleTable.
//        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
//        Stage stage=(Stage) node.getScene().getWindow();
//
//        username = (String) stage.getUserData();

        UserRoleDAO userRoleDAO=new UserRoleDAO();
        RoleDAO roleDAO=new RoleDAO();
//        ObservableList<UserRole> allRoles=userRoleDAO.getAll();
        ObservableList<UserRole> checkedRoles=userRoleDAO.getUserRoles(username);
        ObservableList<Role> allRoles=roleDAO.getAll();

        allRoles.forEach(item -> {
            UserRoleRow roleRow = new UserRoleRow();
            roleRow.setRoleName(item.getRole());
            checkedRoles.forEach(i->{
                if(i.getGrantRole().equals(item.getRole())){
                    checkedRoleList.add(item.getRole());
                    roleRow.setGrant(true);
                    roleRow.setWithAdmin(i.getIsAdminOption());
                }

            });
            roleList.add(roleRow);
        });

        roleName.setCellValueFactory(new PropertyValueFactory<UserRoleRow,String>("roleName"));
        grantRole.setCellValueFactory(new PropertyValueFactory<UserRoleRow,String>("grant"));
        withAdminRole.setCellValueFactory(new PropertyValueFactory<UserRoleRow,String>("withAdmin"));

        roleTable.setItems(FXCollections.observableArrayList(roleList));
    }

    public void sysPrivsHandler(Event event) {

        UserSysPrivsDAO userSysPrivsDAO=new UserSysPrivsDAO();
//        ObservableList<UserRole> allPrivs=user.getAll();
        ObservableList<UserSysPrivs> checkPrivs=userSysPrivsDAO.getUserSysPriv(username);
        ObservableList<String> allPrivs=userSysPrivsDAO.getAll();

        allPrivs.forEach(item -> {
            UserSysPrivsRow userSysPrivsRow = new UserSysPrivsRow();
            userSysPrivsRow.setSysPrivsName(item);
            checkPrivs.forEach(i->{
                if(i.getPrivilege().equals(item)){
                    checkedSysPrivsList.add(item);
                    userSysPrivsRow.setGrant(true);
                    userSysPrivsRow.setWithAdmin(i.getAdminOption());
                }

            });
            userSysPrivsList.add(userSysPrivsRow);
        });

        sysPrivsName.setCellValueFactory(new PropertyValueFactory<UserSysPrivsRow,String>("sysPrivsName"));
        grantSysPrivs.setCellValueFactory(new PropertyValueFactory<UserSysPrivsRow,String>("grant"));
        withAdminSysPrivs.setCellValueFactory(new PropertyValueFactory<UserSysPrivsRow,String>("withAdmin"));

        sysPrivsTable.setItems(FXCollections.observableArrayList(userSysPrivsList));
    }

    public void tabPrivsHanlder(Event event) {
    }
}
