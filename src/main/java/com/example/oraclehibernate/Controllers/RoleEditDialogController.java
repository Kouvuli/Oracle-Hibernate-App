package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.DBTableDAO;
import com.example.oraclehibernate.DAO.RoleSysPrivsDAO;
import com.example.oraclehibernate.DAO.UserTabPrivsDAO;
import com.example.oraclehibernate.Entities.RoleSysPrivs;
import com.example.oraclehibernate.Entities.RoleTabPrivs;
import com.example.oraclehibernate.Entities.UserTabPrivs;
import com.example.oraclehibernate.Models.*;
import com.example.oraclehibernate.Services.PrivsService;
import com.example.oraclehibernate.Services.RoleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private TableView<RoleTabPrivsRow> tabPrivsTable;

    @FXML
    private TableColumn<RoleTabPrivsRow,String>tabPrivsName;

    @FXML
    private TableColumn<RoleTabPrivsRow,String>grantTabPrivs;


    @FXML
    private ComboBox<String>tableComboBox;

    @FXML
    private Button columnPermission;

    private String rolename;
    private List<RoleSysPrivsRow> sysPrivsList;
    private List<String> checkedSysPrivsList;

    private List<RoleTabPrivsRow> tabPrivsList;
    private List<String> checkTabPrivsList;
    private String selectedTabPrivs;
    private String currTable;
    private List<String>selectedColumns;
    public void setRolename(String rolename){
        this.rolename=rolename;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sysPrivsList=new ArrayList<>();
        selectedColumns=new ArrayList<>();
        checkedSysPrivsList=new ArrayList<>();
        columnPermission.setDisable(true);
        System.out.println(rolename);
        tabPrivsList=new ArrayList<>();
        checkTabPrivsList=new ArrayList<>();
        tabPrivsTable.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            if(t1.equals(0)){
                selectedTabPrivs="UPDATE";
                columnPermission.setDisable(false);
            }
            else if(t1.equals(3)){
                selectedTabPrivs="SELECT";
                columnPermission.setDisable(false);
            }
            else{
                columnPermission.setDisable(true);
            }
        });
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
    public void setRoleName(String rolename){
        this.rolename=rolename;
    }
    public void tabPrivsHandler(Event event) {
        DBTableDAO dbTableDAO=new DBTableDAO();
        tableComboBox.setItems(dbTableDAO.getAllTableByOwner("SYSTEM"));
    }


    public void closeHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void applyHandler(ActionEvent event) {
        RoleService roleService=new RoleService();
        PrivsService privsService=new PrivsService();
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

        for (String checkSysPrivs:checkedSysPrivsList) {
            privsService.revokeSysPrivilege(checkSysPrivs,rolename);
        }
        for (RoleSysPrivsRow row:sysPrivTable.getItems()){
            if(row.getGrant().isSelected()) {
                privsService.grantSysPrivilege(row.getSysPrivsName(), rolename, row.getWithAdmin().isSelected());
            }
        }
        checkedSysPrivsList.clear();

        if(currTable!=null){
            for (RoleTabPrivsRow row:tabPrivsTable.getItems()) {
                if(row.getGrant().isSelected()){
                    if(row.getTabPrivsName().equals("DELETE")){
                        privsService.grantDelete(selectedColumns,currTable,rolename,false);
                    }
                    else if(row.getTabPrivsName().equals("INSERT")) {
                        privsService.grantInsert(selectedColumns,currTable,rolename,false);
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")){
                        privsService.grantUpdate(selectedColumns,currTable,rolename,false);
                    }
                    else if(row.getTabPrivsName().equals("SELECT")){
                        privsService.grantSelect(selectedColumns,currTable,rolename,false);
                    }
                }
                else{
                    UserTabPrivsDAO dao=new UserTabPrivsDAO();

                    if(row.getTabPrivsName().equals("DELETE")&&dao.isTabPrivsExist(currTable,rolename,"DELETE")){
                        privsService.revokeDelete(currTable,rolename);
                    }
                    else if(row.getTabPrivsName().equals("INSERT")&&dao.isTabPrivsExist(currTable,rolename,"INSERT")) {
                        privsService.revokeInsert(currTable,rolename);
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")&&dao.isTabPrivsExist(currTable,rolename,"UPDATE")){
                        privsService.revokeUpdate(currTable,rolename);
                    }
                    else if(row.getTabPrivsName().equals("SELECT")&&dao.isTabPrivsExist(currTable,rolename,"SELECT")){
                        privsService.revokeSelect(currTable,rolename);
                    }
                }

            }
        }

        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void comboBoxChange(ActionEvent event) {
        PrivsService privsService=new PrivsService();
        if(currTable!=null){
            for (RoleTabPrivsRow row:tabPrivsTable.getItems()) {
                if(row.getGrant().isSelected()){
                    if(row.getTabPrivsName().equals("DELETE")){
                        privsService.grantDelete(selectedColumns,currTable,rolename,false);
                    }
                    else if(row.getTabPrivsName().equals("INSERT")) {
                        privsService.grantInsert(selectedColumns,currTable,rolename,false);
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")){
                        privsService.grantUpdate(selectedColumns,currTable,rolename,false);
                    }
                    else if(row.getTabPrivsName().equals("SELECT")){
                        privsService.grantSelect(selectedColumns,currTable,rolename,false);
                    }
                }
                else{
                    UserTabPrivsDAO dao=new UserTabPrivsDAO();

                    if(row.getTabPrivsName().equals("DELETE")&&dao.isTabPrivsExist(currTable,rolename,"DELETE")){
                        privsService.revokeDelete(currTable,rolename);
                    }
                    else if(row.getTabPrivsName().equals("INSERT")&&dao.isTabPrivsExist(currTable,rolename,"INSERT")) {
                        privsService.revokeInsert(currTable,rolename);
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")&&dao.isTabPrivsExist(currTable,rolename,"UPDATE")){
                        privsService.revokeUpdate(currTable,rolename);
                    }
                    else if(row.getTabPrivsName().equals("SELECT")&&dao.isTabPrivsExist(currTable,rolename,"SELECT")){
                        privsService.revokeSelect(currTable,rolename);
                    }
                }

            }
        }
        tabPrivsList.clear();
        currTable=tableComboBox.getValue();
        UserTabPrivsDAO userTabPrivsDAO =new UserTabPrivsDAO();
//        ObservableList<UserRole> allRoles=userRoleDAO.getAll();
        ObservableList<UserTabPrivs> checkedUserTabPrivs = userTabPrivsDAO.getUserTabPrivs(rolename);

        String[] listofTabPrivs={"UPDATE","INSERT","DELETE","SELECT"};
        for(int i=0;i<listofTabPrivs.length;i++){
            RoleTabPrivsRow r=new RoleTabPrivsRow();
            String privName=listofTabPrivs[i];
            r.setTabPrivsName(privName);
            int finalI = i;
            checkedUserTabPrivs.forEach(index->{
                if(index.getTableName().equals(currTable)&&index.getPrivilege().equals(privName)){
                    r.setGrant(true);
                }
            });
            tabPrivsList.add(r);
        }

        tabPrivsName.setCellValueFactory(new PropertyValueFactory<RoleTabPrivsRow,String>("tabPrivsName"));
        grantTabPrivs.setCellValueFactory(new PropertyValueFactory<RoleTabPrivsRow,String>("grant"));


        tabPrivsTable.setItems(FXCollections.observableArrayList(tabPrivsList));
    }

    public void columnPermissionHandler(ActionEvent event) throws IOException {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/column-permission-view.fxml"));
        ColumnPermissionController controller=new ColumnPermissionController();
        controller.setValue(selectedTabPrivs,rolename,currTable);
        loader.setController(controller);

        Parent root=loader.load();
//        ColumnPermissionController controller=loader.getController();
//        window.setUserData(username.getText());

        Scene scene = new Scene(root);
        window.setTitle("Column Permission");
        window.setScene(scene);
        window.showAndWait();
        selectedColumns=controller.getSelectedColumns();

    }
}
