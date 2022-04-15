package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.RoleSysPrivsDAO;
import com.example.oraclehibernate.Entities.RoleSysPrivs;
import com.example.oraclehibernate.Models.RoleSysPrivsRow;
import com.example.oraclehibernate.Models.UserRoleRow;
import com.example.oraclehibernate.Models.UserSysPrivsRow;
import com.example.oraclehibernate.Services.PrivsService;
import com.example.oraclehibernate.Services.RoleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoleEditDialogController implements Initializable {

    @FXML
    private TableView<RoleSysPrivsRow> sysPrivTable;

    @FXML
    private TableColumn<RoleSysPrivsRow,String> sysPrivsName;

    @FXML
    private TableColumn<RoleSysPrivsRow, String> grantSysPriv;

    @FXML
    private TableColumn<RoleSysPrivsRow, String> withAdminSysPriv;

    private String rolename;
    private List<RoleSysPrivsRow> sysPrivsList;
    private List<String> checkedSysPrivsList;
    public void setRolename(String rolename){
        this.rolename=rolename;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sysPrivsList=new ArrayList<>();
        checkedSysPrivsList=new ArrayList<>();
    }

    public void setRoleName(String rolename){
        this.rolename=rolename;
    }
    public void tabPrivsHandler(Event event) {

    }


    public void closeHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void applyHandler(ActionEvent event) {
//        RoleService roleService=new RoleService();
//        PrivsService privsService=new PrivsService();
//        if (checkedRoleList.size()!=0){
//            for (String checkedRole:checkedRoleList) {
//                roleService.revokeRole(checkedRole,username);
//            }
//            for (UserRoleRow row : roleTable.getItems()) {
//                if(row.getGrant().isSelected()) {
//                    roleService.grantRole(row.getRoleName(), username, row.getWithAdmin().isSelected());
//                }
//            }
//            checkedRoleList.clear();
//        }
//
//        if (checkedSysPrivsList.size()!=0){
//            for (String checkSysPrivs:checkedSysPrivsList) {
//                privsService.revokeSysPrivilege(checkSysPrivs,username);
//            }
//            for (UserSysPrivsRow row:sysPrivsTable.getItems()){
//                if(row.getGrant().isSelected()) {
//                    privsService.grantSysPrivilege(row.getSysPrivsName(), username, row.getWithAdmin().isSelected());
//                }
//            }
//            checkedSysPrivsList.clear();
//        }
//
//        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.close();
    }
    public void sysPrivsHandler(Event event) {
        RoleSysPrivsDAO roleSysPrivsDAO=new RoleSysPrivsDAO();
//        ObservableList<UserRole> allPrivs=user.getAll();
        ObservableList<RoleSysPrivs> checkPrivs=roleSysPrivsDAO.getRoleSysPriv(rolename);
        ObservableList<String> allPrivs=roleSysPrivsDAO.getAll();

        allPrivs.forEach(item -> {
            RoleSysPrivsRow roleSysPrivsRow = new RoleSysPrivsRow();
            roleSysPrivsRow.setSysPrivsName(item);
            checkPrivs.forEach(i->{
                if(i.getPrivilege().equals(item)){
                    checkedSysPrivsList.add(item);
                    roleSysPrivsRow.setGrant(true);
                    roleSysPrivsRow.setWithAdmin(i.getAdminOption());
                }

            });
            sysPrivsList.add(roleSysPrivsRow);
        });

        sysPrivsName.setCellValueFactory(new PropertyValueFactory<RoleSysPrivsRow,String>("sysPrivsName"));
        grantSysPriv.setCellValueFactory(new PropertyValueFactory<RoleSysPrivsRow,String>("grant"));
        withAdminSysPriv.setCellValueFactory(new PropertyValueFactory<RoleSysPrivsRow,String>("withAdmin"));

        sysPrivTable.setItems(FXCollections.observableArrayList(sysPrivsList));

    }

}
